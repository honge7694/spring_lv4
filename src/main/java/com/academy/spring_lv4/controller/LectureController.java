package com.academy.spring_lv4.controller;

import com.academy.spring_lv4.dto.lecture.LectureRequestDto;
import com.academy.spring_lv4.dto.lecture.LectureResponseDto;
import com.academy.spring_lv4.dto.teacher.TeacherResponseDto;
import com.academy.spring_lv4.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lecture")
public class LectureController {
    LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping("/register")
    public ResponseEntity<LectureResponseDto> register(@RequestBody LectureRequestDto requestDto){
        return new ResponseEntity<>(lectureService.registerBook(requestDto), HttpStatus.OK);

    }

    @GetMapping("/{lecture_id}")
    public ResponseEntity<TeacherResponseDto> lectureListOfTeacher(@PathVariable Long lecture_id){
        return new ResponseEntity<>(lectureService.findLecture(lecture_id), HttpStatus.OK);
    }

    @PutMapping("/{lecture_id}")
    public ResponseEntity<LectureResponseDto> lectureUpdate(@PathVariable Long lecture_id, @RequestBody LectureRequestDto requestDto){
        return new ResponseEntity<>(lectureService.updateLecture(lecture_id, requestDto), HttpStatus.OK);
    }

//    @GetMapping("/category/{category}")
//    public ResponseEntity<List<LectureResponseDto>> searchByCategory(@PathVariable String category){
//        return new ResponseEntity<>(lectureService.searchByCategory(category), HttpStatus.OK);
//    }

     //카테고리별 조회하기 강의명, 가격 등록일 기준 정렬 선택가능
     //내림차순,오름차순 선택가능
    @GetMapping("/category/{category}")
    public Page<LectureResponseDto> searchByCategory(
            @PathVariable String category,
            @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
            @RequestParam(required = false, defaultValue = "name", value = "orderby") String criteria,
            @RequestParam(required = false, defaultValue = "DESC", value = "sort") String sort){
        return lectureService.searchByCategory(category, pageNo, criteria, sort);
    }


    @DeleteMapping("/drop")
    public ResponseEntity dropLecture(@RequestParam("lecture_id") Long lectureId){
        return lectureService.deleteLecture(lectureId);
    }


}

