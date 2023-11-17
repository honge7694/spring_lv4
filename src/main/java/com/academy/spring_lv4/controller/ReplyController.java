package com.academy.spring_lv4.controller;

import com.academy.spring_lv4.dto.reply.ReplyRequestDto;
import com.academy.spring_lv4.security.UserDetailsImpl;
import com.academy.spring_lv4.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/{commentId}")
    public ResponseEntity createReply(@PathVariable Long commentId, @RequestBody ReplyRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return replyService.createReply(commentId, requestDto, userId);
    }

    @PutMapping("/{replyId}")
    public ResponseEntity editReply(@PathVariable Long replyId, @RequestBody ReplyRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return replyService.editReply(replyId, requestDto, userId);
    }

}
