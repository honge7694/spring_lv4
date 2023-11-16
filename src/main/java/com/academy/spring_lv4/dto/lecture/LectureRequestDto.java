package com.academy.spring_lv4.dto.lecture;

import com.academy.spring_lv4.entity.LectureCategoryEnum;
import com.academy.spring_lv4.entity.Teacher;
import lombok.Getter;

@Getter
public class LectureRequestDto {
    private String name;
    private String introduce;
    private LectureCategoryEnum category;
    private int price;
    private Long teacherId;
}
