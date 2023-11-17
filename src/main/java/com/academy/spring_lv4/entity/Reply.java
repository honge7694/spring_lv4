package com.academy.spring_lv4.entity;


import com.academy.spring_lv4.dto.reply.ReplyRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "replies")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment commentId;

    private String contents;

    public Reply(User user, Comment comment, ReplyRequestDto requestDto) {
        this.userId = user;
        this.commentId = comment;
        this.contents = requestDto.getContents();
    }

    public void update(String contents) {
        this.contents = contents;
    }
}
