package com.academy.spring_lv4.entity;

import com.academy.spring_lv4.dto.comment.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lectureId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    private String contents;

    public Comment(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }
}
