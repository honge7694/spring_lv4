package com.academy.spring_lv4.service;


import com.academy.spring_lv4.dto.teacher.TeacherRequestDto;
import com.academy.spring_lv4.dto.teacher.TeacherResponseDto;
import com.academy.spring_lv4.entity.Teacher;
import com.academy.spring_lv4.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;



    public TeacherResponseDto createTeacher(TeacherRequestDto requestDto) {
        // dtd -> entity
        Teacher teacher = new Teacher(requestDto);
        System.out.println("??선생님실행");

        // DB 저장
        teacherRepository.save(teacher);
        return new TeacherResponseDto(teacher);
    }
}
