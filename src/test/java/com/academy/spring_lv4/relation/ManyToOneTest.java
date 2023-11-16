package com.academy.spring_lv4.relation;

import com.academy.spring_lv4.dto.lecture.LectureResponseDto;
import com.academy.spring_lv4.dto.teacher.TeacherResponseDto;
import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.entity.Teacher;
import com.academy.spring_lv4.repository.LectureRepository;
import com.academy.spring_lv4.repository.TeacherRepository;
import com.academy.spring_lv4.service.LectureService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class ManyToOneTest {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    LectureRepository lectureRepository;
    @Autowired
    LectureService lectureService;

    @Test
    @Rollback(value = false)
    @DisplayName("강의 등록 테스트(강사:1)")
    void test1(){
        Teacher teacher = new Teacher();
        teacher.setName("선생님1");
        teacher.setCareer(2);
        teacher.setPhoneNumber("010-1111-1111");
        teacher.setCompany("스파르타");
        teacher.setIntroduce("스프링 강의 테스트");

        Lecture lecture = new Lecture();
        lecture.setName("자바 스프링2");
        lecture.setPrice(200000);
        lecture.setCategory("IT교육");
        lecture.setIntroduce("스프링 테스트");

        lecture.setTeacher(teacher);

        teacherRepository.save(teacher);
        lectureRepository.save(lecture);
    }


    @Test
    @Rollback(value = false)
    @DisplayName("선택한 강의 조회 기능")
    void test2(){
        Lecture lecture = lectureRepository.findLectureByLectureIdOrderByCreatedAt(2L);
        //System.out.println(lecture.getName() + "의 강사 이름 = " + lecture.getTeacher().getName());
        TeacherResponseDto teacher = lectureService.findLecture(2L);
        System.out.println(lecture.getName() + "의 강사 이름 = " + teacher.getName());
    }
}




