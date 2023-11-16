package com.academy.spring_lv4.repository;

import com.academy.spring_lv4.dto.lecture.LecturePureResponseDto;
import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.entity.LectureCategoryEnum;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findByCategory(LectureCategoryEnum category, Sort sort);
}
