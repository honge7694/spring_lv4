package com.academy.spring_lv4.service;

import com.academy.spring_lv4.dto.lecture.LectureRequestDto;
import com.academy.spring_lv4.dto.lecture.LectureResponseDto;
import com.academy.spring_lv4.dto.teacher.TeacherResponseDto;
import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.repository.LectureRepository;
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

import java.util.List;

@Slf4j(topic="lecture service")
@Service
@RequiredArgsConstructor
public class LectureService {
    private static final int DEFAULT_PAGE_SIZE = 5;
    private final LectureRepository lectureRepository;
    public LectureResponseDto registerBook(LectureRequestDto requestDto) {
        Lecture lecture = new Lecture(requestDto);
        return new LectureResponseDto(lectureRepository.save(lecture));
    }

    public TeacherResponseDto findLecture(Long lectureId) {
        Lecture findLecture = lectureRepository.findLectureByLectureIdOrderByCreatedAt(lectureId);
        if(findLecture == null){ throw new EntityNotFoundException("해당 강의를 찾을 수 없습니다."); }
        return new TeacherResponseDto(findLecture.getTeacher());
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

//    public List<LectureResponseDto> searchByCategory(String category){
//        List<Lecture> lectures = lectureRepository.findLecturesByCategory(category);
//        if(lectures == null){
//            throw new EntityNotFoundException("해당 카테고리로 조회가 되지 않습니다.");
//        }
//        return lectures.stream().map(LectureResponseDto::new).toList();
//    }

    //@Transactional(readOnly = true)
    public Page<LectureResponseDto> searchByCategory(String category, int pageNo, String criteria, String sort){
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
