package com.academy.spring_lv4.repository;

import com.academy.spring_lv4.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

}
