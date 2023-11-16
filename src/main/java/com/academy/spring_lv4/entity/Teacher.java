package com.academy.spring_lv4.entity;

import com.academy.spring_lv4.dto.teacher.TeacherRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int career;
    private String company;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String introduce;

    public Teacher(TeacherRequestDto requestDto) {
        this.name = requestDto.getName();
        this.career = requestDto.getCareer();
        this.company = requestDto.getCompany();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.introduce = requestDto.getIntroduce();
    }
}
