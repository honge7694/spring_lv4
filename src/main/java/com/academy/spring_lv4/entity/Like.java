package com.academy.spring_lv4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Lecture lecture;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Like(User user, Lecture lecture) {
        this.lecture = lecture;
        this.user = user;
    }
}
