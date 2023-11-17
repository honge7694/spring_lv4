package com.academy.spring_lv4.controller;

import com.academy.spring_lv4.security.UserDetailsImpl;
import com.academy.spring_lv4.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("lecture")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/{lectureId}/like")
    public ResponseEntity<String> addLike(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                  @PathVariable Long lectureId){
        return likeService.addLike(lectureId, userDetails.getUser());
    }
}
