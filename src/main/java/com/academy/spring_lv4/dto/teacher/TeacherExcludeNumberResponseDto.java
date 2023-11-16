package com.academy.spring_lv4.dto.teacher;

import com.academy.spring_lv4.entity.Teacher;
import lombok.Getter;

@Getter
public class TeacherExcludeNumberResponseDto {
    private Long id;
    private String name;
    private int career;
    private String company;

    private String introduce;

    public TeacherExcludeNumberResponseDto(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.career = teacher.getCareer();
        this.company = teacher.getCompany();
        this.introduce = teacher.getIntroduce();
    }
}
