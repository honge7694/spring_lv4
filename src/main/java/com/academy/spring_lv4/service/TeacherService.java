package com.academy.spring_lv4.service;

import com.academy.spring_lv4.dto.lecture.LectureResponseDto;
import com.academy.spring_lv4.dto.teacher.TeacherRequestDto;
import com.academy.spring_lv4.dto.teacher.TeacherResponseDto;
import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.entity.Teacher;
import com.academy.spring_lv4.exception.TeacherNotFoundException;
import com.academy.spring_lv4.repository.LectureRepository;
import com.academy.spring_lv4.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final LectureRepository lectureRepository;

    public TeacherResponseDto createTeacher(TeacherRequestDto requestDto) {
        // RequestDto -> Entity
        Teacher teacher = new Teacher(requestDto);
        // DB 저장
        teacherRepository.save(teacher);
        return new TeacherResponseDto(teacher);
    }

    public TeacherResponseDto getTeacher(Long id) {
        return new TeacherResponseDto(findTeacher(id));
    }

    @Transactional
    public TeacherResponseDto editTeacher(Long id, TeacherRequestDto requestDto) {
        // 강사 존재 확인
        Teacher teacher = findTeacher(id);

        teacher.update(requestDto);
        return new TeacherResponseDto(teacher);
    }

    public List<LectureResponseDto> getTecherLectures(Long id) {
        List<Lecture> lectures = lectureRepository.findLecturesByTeacherId(id);
        return lectures.stream().map(LectureResponseDto::new).toList();
    }

    private Teacher findTeacher(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("존재하지 않는 강사입니다."));
    }
}
