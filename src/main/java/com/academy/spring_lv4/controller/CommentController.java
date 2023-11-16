package com.academy.spring_lv4.controller;

import com.academy.spring_lv4.dto.comment.CommentRequestDto;
import com.academy.spring_lv4.security.UserDetailsImpl;
import com.academy.spring_lv4.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/{lectureId}")
    public ResponseEntity createComment(@PathVariable Long lectureId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return commentService.createComment(lectureId, requestDto, userId);
    }

}
