package com.academy.spring_lv4.controller;

import com.academy.spring_lv4.dto.lecture.LectureResponseDto;
import com.academy.spring_lv4.dto.teacher.TeacherRequestDto;
import com.academy.spring_lv4.dto.teacher.TeacherResponseDto;
import com.academy.spring_lv4.entity.UserRoleEnum;
import com.academy.spring_lv4.service.TeacherService;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/")
    public TeacherResponseDto createTeacher(@RequestBody TeacherRequestDto requestDto) {
        return teacherService.createTeacher(requestDto);
    }

    @GetMapping("/{id}")
    public TeacherResponseDto getTeacher(@PathVariable Long id) {
        return teacherService.getTeacher(id);
    }

    @PutMapping("/{id}")
    public TeacherResponseDto editTeacher(@PathVariable Long id, @RequestBody TeacherRequestDto requestDto) {
        return teacherService.editTeacher(id, requestDto);
    }

    @GetMapping("/{id}/lectures")
    public List<LectureResponseDto> getTeacherLectures(@PathVariable Long id) {
        return teacherService.getTecherLectures(id);
    }

}

