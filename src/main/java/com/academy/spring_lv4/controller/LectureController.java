package com.academy.spring_lv4.controller;

import com.academy.spring_lv4.auth.Auth;
import com.academy.spring_lv4.dto.lecture.LectureCommentResponseDto;
import com.academy.spring_lv4.dto.lecture.LectureRequestDto;
import com.academy.spring_lv4.dto.lecture.LectureResponseDto;
import com.academy.spring_lv4.dto.teacher.TeacherResponseDto;
import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.entity.LectureCategoryEnum;
import com.academy.spring_lv4.security.UserDetailsImpl;
import com.academy.spring_lv4.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.academy.spring_lv4.entity.UserRoleEnum.ADMIN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lectures")
public class LectureController {

    private final LectureService lectureService;

    @Auth(role = ADMIN)
    @PostMapping("/register")
    public ResponseEntity<LectureResponseDto> register(@RequestBody LectureRequestDto requestDto){
        return new ResponseEntity<>(lectureService.registerBook(requestDto), HttpStatus.OK);

    }

    @GetMapping("/{lecture_id}")
    public ResponseEntity<LectureCommentResponseDto> lectureListOfTeacher(@PathVariable Long lecture_id){
        return new ResponseEntity<>(lectureService.findLecture(lecture_id), HttpStatus.OK);
    }
    @Auth(role = ADMIN)
    @PutMapping("/{lecture_id}")
    public ResponseEntity<LectureResponseDto> lectureUpdate(@PathVariable Long lecture_id, @RequestBody LectureRequestDto requestDto){
        return new ResponseEntity<>(lectureService.updateLecture(lecture_id, requestDto), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public Page<LectureResponseDto> searchByCategory(
            @PathVariable LectureCategoryEnum category,
            @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
            @RequestParam(required = false, defaultValue = "name", value = "orderby") String criteria,
            @RequestParam(required = false, defaultValue = "DESC", value = "sort") String sort){
        return lectureService.searchByCategory(category, pageNo, criteria, sort);
    }

    @Auth(role = ADMIN)
    @DeleteMapping("/drop")
    public ResponseEntity dropLecture(@RequestParam("lecture_id") Long lectureId){
        return lectureService.deleteLecture(lectureId);
    }


}

