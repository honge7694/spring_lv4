package com.academy.spring_lv4.service;

import com.academy.spring_lv4.dto.lecture.LectureRequestDto;
import com.academy.spring_lv4.dto.lecture.LectureTeacherResponseDto;
import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.entity.Teacher;
import com.academy.spring_lv4.repository.LectureRepository;
import com.academy.spring_lv4.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;

    public LectureTeacherResponseDto createLecture(LectureRequestDto requestDto) {
        // dto -> entity
        Lecture lecture = new Lecture(requestDto);

        // 강사 정보
        Teacher teacher = teacherRepository.findById(requestDto.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("강사가 존재하지 않습니다."));

        // Lecture 엔터티에 강사 정보 설정
        lecture.setTeacher(teacher);

        // DB 저장 후 반환
        return new LectureTeacherResponseDto(lectureRepository.save(lecture));
    }

    public LectureTeacherResponseDto getLecture(Long id) {
        return new LectureTeacherResponseDto(findLecture(id));
    }

    private Lecture findLecture(Long id) {
        return (lectureRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("강의가 존재하지 않습니다.")));
    }
}
