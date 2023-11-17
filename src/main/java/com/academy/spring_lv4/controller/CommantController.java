package com.academy.spring_lv4.controller;

import com.academy.spring_lv4.dto.comment.CommentRequestDto;
import com.academy.spring_lv4.dto.lecture.LectureRequestDto;
import com.academy.spring_lv4.dto.lecture.LectureResponseDto;
import com.academy.spring_lv4.security.UserDetailsImpl;
import com.academy.spring_lv4.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class CommantController {

    private final CommentService commentService;

    @PostMapping("/{lectureId}/comment")
    public ResponseEntity registerComment(@PathVariable Long lectureId,
                                          @RequestBody CommentRequestDto requestDto,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return commentService.registerComment(lectureId, requestDto, userDetails.getUser());
    }

    @PutMapping("/{lectureId}/comment/{commentId}")
    public ResponseEntity editComment(@PathVariable Long lectureId,
                            @PathVariable Long commentId,
                            @RequestBody CommentRequestDto requestDto,
                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.editComment(lectureId, commentId, requestDto, userDetails.getUser());
    }

    @DeleteMapping("/{lectureId}/comment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long lectureId,
                                        @PathVariable Long commentId,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(lectureId, commentId, userDetails.getUser());
    }

}
