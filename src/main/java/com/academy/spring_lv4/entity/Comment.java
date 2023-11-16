package com.academy.spring_lv4.entity;

import com.academy.spring_lv4.dto.comment.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="comment")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name="comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name="lecture_id")
    private Lecture lecture;

    public Comment(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}
