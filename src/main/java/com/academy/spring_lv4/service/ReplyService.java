package com.academy.spring_lv4.service;

import com.academy.spring_lv4.dto.reply.ReplyRequestDto;
import com.academy.spring_lv4.entity.Comment;
import com.academy.spring_lv4.entity.Reply;
import com.academy.spring_lv4.entity.User;
import com.academy.spring_lv4.repository.CommentRepository;
import com.academy.spring_lv4.repository.ReplyRepository;
import com.academy.spring_lv4.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

        // 유저 찾기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저를 찾을 수 없습니다."));

        Reply reply = new Reply(user, comment, requestDto);
        replyRepository.save(reply);

        return ResponseEntity.status(HttpStatus.CREATED).body("대댓글을 생성하였습니다.");
    }
}
