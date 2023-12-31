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

    @Column(name="commentInfo")
    private String commentInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lecture_id", nullable = false)
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public Comment(CommentRequestDto requestDto, User user) {
        this.commentInfo = requestDto.getComment();
        this.user = user;
    }

    public void update(CommentRequestDto requestDto){
        this.commentInfo = requestDto.getComment();
    }
}
