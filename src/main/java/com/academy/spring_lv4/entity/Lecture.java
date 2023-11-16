package com.academy.spring_lv4.entity;

import com.academy.spring_lv4.dto.lecture.LectureRequestDto;
import com.academy.spring_lv4.entity.common.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "lectures")
public class Lecture extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String introduce;
    @Enumerated(value = EnumType.STRING)
    private LectureCategoryEnum category;
    private int price;

    @ManyToOne
    @JoinColumn(name = "teacher_id") // 외래키 컬럼 이름 지정
    private Teacher teacher;

    public Lecture(LectureRequestDto requestDto) {
        this.name = requestDto.getName();
        this.introduce = requestDto.getIntroduce();
        this.category = requestDto.getCategory();
        this.price = requestDto.getPrice();
    }
}
