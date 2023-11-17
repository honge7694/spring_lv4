package com.academy.spring_lv4.dto.lecture;

import com.academy.spring_lv4.entity.LectureCategoryEnum;
import com.academy.spring_lv4.entity.Teacher;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LectureRequestDto {

    @NotBlank(message = "강의 이름을 입력하세요.")
    private String name;

    @NotBlank(message = "강의 가격을 입력하세요.")
    private int price;

    private String introduce;

    @NotBlank(message = "카테고리를 입력하세요.")
    LectureCategoryEnum category;

    //private Long teacherId;
    @NotBlank(message = "강사정보를 입력하세요.")
    private Long teacherId;

}

