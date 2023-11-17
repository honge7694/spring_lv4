package com.academy.spring_lv4.dto.lecture;

import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.entity.LectureCategoryEnum;
import lombok.Getter;

@Getter
public class LecturePureResponseDto { // 카테고리 검색할 때 사용
    private Long id;
    private String name;
    private String introduce;
    private LectureCategoryEnum category;
    private int price;

    public LecturePureResponseDto(Lecture lecture) {
        this.id = lecture.getId();
        this.name = lecture.getName();
        this.introduce = lecture.getIntroduce();
        this.price = lecture.getPrice();
        this.category = lecture.getCategory();
    }

}
