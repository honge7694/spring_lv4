package com.academy.spring_lv4.repository;


import com.academy.spring_lv4.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
