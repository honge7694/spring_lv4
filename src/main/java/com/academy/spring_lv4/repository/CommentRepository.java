package com.academy.spring_lv4.repository;

import com.academy.spring_lv4.entity.Comment;
import com.academy.spring_lv4.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByLectureId(Lecture lecture);
}
