package com.academy.spring_lv4.relation;

import com.academy.spring_lv4.entity.Teacher;
import com.academy.spring_lv4.repository.TeacherRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class TeacherTest {
    @Autowired
    TeacherRepository teacherRepository;

    @Test
    @Rollback(value = false)
    @DisplayName("강의 등록 테스트(강사:1)")
    void test(){
        Teacher teacher = new Teacher();
        teacher.setName("남궁성");
        teacher.setCareer(10);
        teacher.setPhoneNumber("010-2222-2222");
        teacher.setCompany("데이원컴퍼니");
        teacher.setIntroduce("스프링 강의 테스트");
        teacherRepository.save(teacher);

    }
}
