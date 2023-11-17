package com.academy.spring_lv4.repository;

import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.entity.Like;
import com.academy.spring_lv4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndLecture(User user, Lecture lecture);

    @Modifying
    @Query(value = "DELETE FROM likes as l where l.user_id=? and l.lecture_lecture_id=?", nativeQuery = true)
    void deleteByUserLecture(Long userId, Long lectureId);
}
