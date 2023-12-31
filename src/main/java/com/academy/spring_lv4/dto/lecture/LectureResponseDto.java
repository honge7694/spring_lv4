package com.academy.spring_lv4.dto.lecture;

import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.entity.LectureCategoryEnum;
import com.academy.spring_lv4.entity.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LectureResponseDto {
    private Long lectureId;
    private String name;
    private int price;
    private String introduce;
    private LectureCategoryEnum category;
    private LocalDateTime createdAt;

    public LectureResponseDto(Lecture lecture){
        this.lectureId = lecture.getLectureId();
        this.name = lecture.getName();
        this.price = lecture.getPrice();
        this.introduce = lecture.getIntroduce();
        this.category = lecture.getCategory();
        this.createdAt = lecture.getCreatedAt();
    }
}
