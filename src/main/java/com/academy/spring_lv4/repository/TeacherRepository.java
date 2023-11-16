package com.academy.spring_lv4.repository;

import com.academy.spring_lv4.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
