package com.academy.spring_lv4.controller;

import com.academy.spring_lv4.dto.lecture.LectureRequestDto;
import com.academy.spring_lv4.dto.lecture.LectureResponseDto;
import com.academy.spring_lv4.entity.UserRoleEnum;
import com.academy.spring_lv4.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/admin")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public LectureResponseDto createLecture(@RequestBody LectureRequestDto requestDto) {
        return lectureService.createLecture(requestDto);
    }

}
