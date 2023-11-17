package com.academy.spring_lv4.dto.comment;

import com.academy.spring_lv4.dto.user.UserResponseDto;
import com.academy.spring_lv4.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private String contents;
    private UserResponseDto user;

    public CommentResponseDto(Comment comment) {
        this.contents = comment.getContents();
        this.user = new UserResponseDto(comment.getUserId());
    }
}
