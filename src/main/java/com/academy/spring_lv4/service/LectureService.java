package com.academy.spring_lv4.service;

import com.academy.spring_lv4.dto.lecture.LectureExcludeNumberResponse;
import com.academy.spring_lv4.dto.lecture.LecturePureResponseDto;
import com.academy.spring_lv4.dto.lecture.LectureRequestDto;
import com.academy.spring_lv4.dto.lecture.LectureResponseDto;
import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.entity.LectureCategoryEnum;
import com.academy.spring_lv4.entity.Teacher;
import com.academy.spring_lv4.repository.LectureRepository;
import com.academy.spring_lv4.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;

    public LectureResponseDto createLecture(LectureRequestDto requestDto) {
        // dto -> entity
        Lecture lecture = new Lecture(requestDto);

        // 강사 정보
        Teacher teacher = teacherRepository.findById(requestDto.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("강사가 존재하지 않습니다."));

        // Lecture 엔터티에 강사 정보 설정
        lecture.setTeacher(teacher);

        // DB 저장 후 반환
        return new LectureResponseDto(lectureRepository.save(lecture));
    }

    public LectureExcludeNumberResponse getLecture(Long id) {
        return new LectureExcludeNumberResponse(findLecture(id));
    }

    @Transactional(readOnly = true)
    public List<LecturePureResponseDto> searchByCategory(LectureCategoryEnum category, String sortBy, boolean isAsc) {
        // 정렬
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy); // 방향, 속성(강의명, 가격, 등록일)

        List<Lecture> lectureList = lectureRepository.findByCategory(category, sort); // 기술 매니저님 질문하기.
        return lectureList.stream().map(LecturePureResponseDto::new).toList();
    }

    private Lecture findLecture(Long id) {
        return lectureRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("강의가 존재하지 않습니다."));
    }


}
