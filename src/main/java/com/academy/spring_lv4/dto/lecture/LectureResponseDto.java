package com.academy.spring_lv4.dto.lecture;

import com.academy.spring_lv4.dto.teacher.TeacherExcludeNumberResponseDto;
import com.academy.spring_lv4.dto.teacher.TeacherResponseDto;
import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.entity.LectureCategoryEnum;
import com.academy.spring_lv4.entity.Teacher;
import lombok.Getter;

@Getter
public class LectureResponseDto {
    private Long id;
    private String name;
    private String introduce;
    private LectureCategoryEnum category;
    private int price;
    private Teacher teacher;

    public LectureResponseDto(Lecture lecture) {
        this.id = lecture.getId();
        this.name = lecture.getName();
        this.introduce = lecture.getIntroduce();
        this.price = lecture.getPrice();
        this.category = lecture.getCategory();
        this.teacher = lecture.getTeacher();
    }
}
