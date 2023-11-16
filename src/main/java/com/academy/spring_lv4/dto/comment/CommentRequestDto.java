package com.academy.spring_lv4.dto.comment;

import com.academy.spring_lv4.entity.Lecture;
import com.academy.spring_lv4.entity.User;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String contents;
}
