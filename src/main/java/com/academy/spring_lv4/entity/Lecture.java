package com.academy.spring_lv4.entity;

import com.academy.spring_lv4.entity.common.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "teacher")
public class Lecture extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String introduce;
    private LectureCategoryEnum category;
    private int price;

    @ManyToOne
    @JoinColumn(name = "teacher_id") // 외래키 컬럼 이름 지정
    private Teacher teacher;

}
