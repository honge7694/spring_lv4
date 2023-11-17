package com.academy.spring_lv4.entity;

import com.academy.spring_lv4.dto.lecture.LectureRequestDto;
import com.academy.spring_lv4.time.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Table(name="lecture")
@Getter
@NoArgsConstructor
public class Lecture extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "category", nullable = false)
    private String category;

    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "lecture")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    Set<Like> likes = new HashSet<>();

    public Lecture(LectureRequestDto requestDto){
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.introduce = requestDto.getIntroduce();
        this.category = requestDto.getCategory();
        this.teacher = requestDto.getTeacher();
    }

    public Lecture update(LectureRequestDto requestDto){
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.introduce = requestDto.getIntroduce();
        this.category = requestDto.getCategory();
        //this.teacherId = requestDto.getTeacherId();
        return this;
    }

    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }

}
