package com.academy.spring_lv4.service;

import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.entity.Like;
import com.academy.spring_lv4.entity.User;
import com.academy.spring_lv4.repository.LectureRepository;
import com.academy.spring_lv4.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final LectureRepository lectureRepository;

    @Transactional
    public ResponseEntity<String> addLike(Long lectureId, User user){
        Lecture lecture = lectureRepository.findLectureByLectureId(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의가 없습니다."));

        if(isNotAlreadyLike(user, lecture)){
            likeRepository.save(new Like(user, lecture));
            return new ResponseEntity<>("좋아요가 등록되었습니다.", HttpStatus.OK);
        }
        likeRepository.deleteByUserLecture(user.getId(), lecture.getLectureId());
        return new ResponseEntity<>("좋아요가 취소되었습니다.", HttpStatus.OK);

    }

    private boolean isNotAlreadyLike(User user, Lecture lecture){
        if(likeRepository.findByUserAndLecture(user, lecture).isEmpty()){
            return true;
        }
        return false;
    }
}
