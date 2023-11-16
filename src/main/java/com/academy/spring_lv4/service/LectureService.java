package com.academy.spring_lv4.service;

import com.academy.spring_lv4.dto.lecture.LectureExcludeNumberResponse;
import com.academy.spring_lv4.dto.lecture.LecturePureResponseDto;
import com.academy.spring_lv4.dto.lecture.LectureRequestDto;
import com.academy.spring_lv4.dto.lecture.LectureResponseDto;
import com.academy.spring_lv4.entity.*;
import com.academy.spring_lv4.repository.LectureRepository;
import com.academy.spring_lv4.repository.LikeRepository;
import com.academy.spring_lv4.repository.TeacherRepository;
import com.academy.spring_lv4.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

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
        // 강의 찾기
        Lecture lecture = findLecture(id);

        // 좋아요 수 조회
        int likes = likeRepository.countByLectureId(lecture);
        return new LectureExcludeNumberResponse(lecture, likes);
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


    public ResponseEntity likeLecture(Long lectureId, Long userId) {

        // 강의 확인
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new EntityNotFoundException("강의를 찾을 수 없습니다."));

        // 유저 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("유저를 찾을 수 없습니다."));

        // 좋아요 확인
        Optional<Like> like = likeRepository.findByLectureIdAndUserId(lecture, user);

        if (like.isPresent()) {
            // 좋아요 취소
            likeRepository.delete(like.get());
            return ResponseEntity.status(HttpStatus.OK).body("좋아요를 취소했습니다.");
        }

        Like newLike = new Like(lecture, user);
        likeRepository.save(newLike);
        return ResponseEntity.status(HttpStatus.OK).body("좋아요를 눌렀습니다.");
    }
}
