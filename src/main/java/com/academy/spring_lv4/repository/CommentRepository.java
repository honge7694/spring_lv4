package com.academy.spring_lv4.repository;

import com.academy.spring_lv4.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
