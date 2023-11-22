package com.academy.spring_lv4.controller;

import com.academy.spring_lv4.dto.lecture.LectureExcludeNumberResponse;
import com.academy.spring_lv4.dto.lecture.LecturePureResponseDto;
import com.academy.spring_lv4.dto.lecture.LectureRequestDto;
import com.academy.spring_lv4.dto.lecture.LectureResponseDto;
import com.academy.spring_lv4.entity.LectureCategoryEnum;
import com.academy.spring_lv4.interceptor.AuthRole;
import com.academy.spring_lv4.security.UserDetailsImpl;
import com.academy.spring_lv4.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

import static com.academy.spring_lv4.entity.UserRoleEnum.ADMIN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/admin")
    public LectureResponseDto createLecture(@RequestBody LectureRequestDto requestDto) {
        return lectureService.createLecture(requestDto);
    }

    @GetMapping("/{id}")
    public LectureExcludeNumberResponse findLecture(@PathVariable Long id) {
        return lectureService.getLecture(id);
    }

    @GetMapping("/categories/{category}")
    public List<LecturePureResponseDto> searchByCategory(
            @PathVariable LectureCategoryEnum category,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc
    ) {
        return lectureService.searchByCategory(category, sortBy, isAsc);
    }

    @PostMapping("/likes/{lectureId}")
    public ResponseEntity likeLecture(@PathVariable Long lectureId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return lectureService.likeLecture(lectureId, userId);
    }

}
