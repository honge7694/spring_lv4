package com.academy.spring_lv4.relation;

import com.academy.spring_lv4.dto.lecture.LectureResponseDto;
import com.academy.spring_lv4.entity.LectureCategoryEnum;
import com.academy.spring_lv4.entity.Teacher;
import com.academy.spring_lv4.repository.LectureRepository;
import com.academy.spring_lv4.repository.TeacherRepository;
import com.academy.spring_lv4.service.LectureService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class LectureTest {
    @Autowired
    LectureService lectureService;
    @Autowired
    LectureRepository lectureRepository;

    @Test
    @Rollback(value = false)
    @DisplayName("페이징 테스트")
    void test(){
        Page<LectureResponseDto> responseDtoList =
                lectureService.searchByCategory(LectureCategoryEnum.SPRING, 0, "name", "ASC");

        System.out.println(responseDtoList.get().toList());
    }
}