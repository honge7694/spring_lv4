package com.academy.spring_lv4.repository;

import com.academy.spring_lv4.entity.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Optional<Lecture> findLectureByLectureId(Long lectureId);
    Lecture findLectureByLectureIdOrderByCreatedAt(Long lectureId);

    List<Lecture> findLecturesByTeacherId(Long teacherId);

    //List<Lecture> findLecturesByCategory(String category);
    Page<Lecture> findLecturesByCategory(String category, Pageable pageable);


}
