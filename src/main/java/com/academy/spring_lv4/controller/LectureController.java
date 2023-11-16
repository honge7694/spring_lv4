package com.academy.spring_lv4.controller;

import com.academy.spring_lv4.dto.lecture.LectureExcludeNumberResponse;
import com.academy.spring_lv4.dto.lecture.LecturePureResponseDto;
import com.academy.spring_lv4.dto.lecture.LectureRequestDto;
import com.academy.spring_lv4.dto.lecture.LectureResponseDto;
import com.academy.spring_lv4.entity.LectureCategoryEnum;
import com.academy.spring_lv4.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/category/{category}")
    public List<LecturePureResponseDto> searchByCategory(
            @PathVariable LectureCategoryEnum category,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc
    ) {
        return lectureService.searchByCategory(category, sortBy, isAsc);
    }

}
