package com.academy.spring_lv4.repository;


import com.academy.spring_lv4.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM comment where lecture_id = ? ", nativeQuery = true)
    List<Comment> findByComments(Long LectureId);

    @Query(value = "SELECT * FROM comment as c where c.lecture_id=? and c.comment_id=? and c.user_id=? ", nativeQuery = true)
    Optional<Comment> findUserComment(Long LectureId, Long CommentId, Long userId);

    @Modifying
    @Query(value = "DELETE FROM comment as c where c.lecture_id=? and c.comment_id=? and c.user_id=? ", nativeQuery = true)
    void deleteUserComment(Long lectureId, Long commentId, Long id);

}
