package com.academy.spring_lv4.service;

import com.academy.spring_lv4.dto.lecture.LectureRequestDto;
import com.academy.spring_lv4.dto.lecture.LectureResponseDto;
import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.repository.LectureRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j(topic="lecture service")
@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    public LectureResponseDto registerBook(LectureRequestDto requestDto) {
        Lecture lecture = new Lecture(requestDto);
        return new LectureResponseDto(lectureRepository.save(lecture));
    }


    public LectureResponseDto findLecture(Long lectureId) {
        return new LectureResponseDto(lectureRepository.findLectureByLectureIdOrderByCreatedAt(lectureId));
    }

    @Transactional
    public LectureResponseDto updateLecture(Long lectureId, LectureRequestDto requestDto) {
        Lecture lecture = lectureRepository.findLectureByLectureIdOrderByCreatedAt(lectureId);
        if(lecture == null){
            throw new EntityNotFoundException("해당 강의를 찾지 못했습니다.");
        }
        lecture.update(requestDto);
        return new LectureResponseDto(lecture);
    }

    public List<LectureResponseDto> searchByCategory(String category){
        List<Lecture> lectures = lectureRepository.findLecturesByCategoryOrderByCreatedAt(category);
        if(lectures == null){
            throw new EntityNotFoundException("해당 카테고리로 조회가 되지 않습니다.");
        }
        return lectures.stream().map(LectureResponseDto::new).toList();
    }

    public ResponseEntity deleteLecture(Long lectureId) {
        lectureRepository.deleteById(lectureId);
        return ResponseEntity.status(HttpStatus.OK).body(lectureId + "번 강의가 삭제되었습니다.");
    }
}
