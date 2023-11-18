package com.academy.spring_lv4.service;

import com.academy.spring_lv4.dto.lecture.LectureCommentResponseDto;
import com.academy.spring_lv4.dto.lecture.LectureRequestDto;
import com.academy.spring_lv4.dto.lecture.LectureResponseDto;
import com.academy.spring_lv4.dto.teacher.TeacherResponseDto;
import com.academy.spring_lv4.entity.*;
import com.academy.spring_lv4.repository.CommentRepository;
import com.academy.spring_lv4.repository.LectureRepository;
import com.academy.spring_lv4.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "lecture service")
@Service
@RequiredArgsConstructor
public class LectureService {
    private static final int DEFAULT_PAGE_SIZE = 5;
    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;
    private final CommentRepository commentRepository;

    public LectureResponseDto registerBook(LectureRequestDto requestDto) {
        Lecture lecture = new Lecture(requestDto);
        Teacher teacher = teacherRepository.findById(requestDto.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("강사가 존재하지 않습니다."));

        // Lecture 엔터티에 강사 정보 설정
        lecture.setTeacher(teacher);
        return new LectureResponseDto(lectureRepository.save(lecture));
    }

    public LectureCommentResponseDto findLecture(Long lectureId) {
        Lecture findLecture = lectureRepository.findLectureByLectureIdOrderByCreatedAt(lectureId)
                .orElseThrow(() -> new EntityNotFoundException("해당 강의를 찾을 수 없습니다."));

        return new LectureCommentResponseDto(findLecture, commentRepository.findByComments(lectureId));
    }

    @Transactional
    public LectureResponseDto updateLecture(Long lectureId, LectureRequestDto requestDto) {
        Lecture lecture = lectureRepository.findLectureByLectureIdOrderByCreatedAt(lectureId)
                .orElseThrow(() -> new EntityNotFoundException("해당 강의를 찾을 수 없습니다."));
        lecture.update(requestDto);
        return new LectureResponseDto(lecture);
    }

    @Transactional(readOnly = true)
    public Page<LectureResponseDto> searchByCategory(LectureCategoryEnum category, int pageNo, String criteria, String sort) {
        // 페이징 처리
        Pageable pageable = (sort.equals("ASC")) ?
                PageRequest.of(pageNo, DEFAULT_PAGE_SIZE, Sort.by(Sort.Direction.ASC, criteria))
                : PageRequest.of(pageNo, DEFAULT_PAGE_SIZE, Sort.by(Sort.Direction.DESC, criteria));

        Page<Lecture> lectures = lectureRepository.findLecturesByCategory(category, pageable);

        return lectures.map(LectureResponseDto::new);
    }
    public ResponseEntity deleteLecture(Long lectureId) {
        lectureRepository.deleteById(lectureId);
        return ResponseEntity.status(HttpStatus.OK).body(lectureId + "번 강의가 삭제되었습니다.");
    }

}
