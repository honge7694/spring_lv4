package com.academy.spring_lv4.service;

import com.academy.spring_lv4.dto.reply.ReplyRequestDto;
import com.academy.spring_lv4.entity.Comment;
import com.academy.spring_lv4.entity.Reply;
import com.academy.spring_lv4.entity.User;
import com.academy.spring_lv4.repository.CommentRepository;
import com.academy.spring_lv4.repository.ReplyRepository;
import com.academy.spring_lv4.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public ResponseEntity createReply(Long commentId, ReplyRequestDto requestDto, Long userId) {
        // 댓글 찾기
        Comment comment = findByComment(commentId);

        // 유저 찾기
        User user = findByUser(userId);

        Reply reply = new Reply(user, comment, requestDto);
        replyRepository.save(reply);

        return ResponseEntity.status(HttpStatus.CREATED).body("대댓글을 생성하였습니다.");
    }

    @Transactional
    public ResponseEntity editReply(Long replyId, ReplyRequestDto requestDto, Long userId) {
        // 유저 찾기
        User user = findByUser(userId);

        // 대댓글 찾기
        Reply reply = findByReply(replyId);

        if (userId != reply.getUserId().getId()) {
            throw new IllegalArgumentException("댓글을 작성한 유저가 아닙니다.");
        }

        // 대댓글 수정
        reply.update(requestDto.getContents());
        return ResponseEntity.status(HttpStatus.OK).body("대댓글을 수정하였습니다.");
    }

    public Comment findByComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));
    }

    public User findByUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("유저를 찾을 수 없습니다."));
    }

    public Reply findByReply(Long id) {
        return replyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("대댓글을 찾을 수 없습니다."));
    }
}
