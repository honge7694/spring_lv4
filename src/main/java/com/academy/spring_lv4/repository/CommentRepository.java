package com.academy.spring_lv4.repository;


import com.academy.spring_lv4.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM comment where lecture_id = ? ", nativeQuery = true)
    List<Comment> findByComments(Long LectureId);
}
