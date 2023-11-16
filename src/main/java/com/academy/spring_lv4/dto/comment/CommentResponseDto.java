package com.academy.spring_lv4.dto.comment;

import com.academy.spring_lv4.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    String comments;
    Long userId;

    public CommentResponseDto(Comment comment) {
        this.comments = comment.getComment();
        this.userId = comment.getUser().getId();
    }
}
