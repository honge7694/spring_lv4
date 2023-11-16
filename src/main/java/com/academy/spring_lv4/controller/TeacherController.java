package com.academy.spring_lv4.controller;

import com.academy.spring_lv4.dto.teacher.TeacherRequestDto;
import com.academy.spring_lv4.dto.teacher.TeacherResponseDto;
import com.academy.spring_lv4.entity.UserRoleEnum;
import com.academy.spring_lv4.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@Secured(UserRoleEnum.Authority.ADMIN)
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/")
    public TeacherResponseDto createTeacher(@RequestBody TeacherRequestDto requestDto) {
        return teacherService.createTeacher(requestDto);
    }
}
