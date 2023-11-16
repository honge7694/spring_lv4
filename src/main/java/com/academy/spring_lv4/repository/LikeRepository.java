package com.academy.spring_lv4.repository;

import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.entity.Like;
import com.academy.spring_lv4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByLectureIdAndUserId(Lecture lectureId, User userId);

    int countByLectureId(Lecture lecture);
}
