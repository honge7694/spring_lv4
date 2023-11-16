package com.academy.spring_lv4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id") // 외래키 컬럼 이름 지정
    private User userId;

    @ManyToOne
    @JoinColumn(name = "lecture_id") // 외래키 컬럼 이름 지정
    private Lecture lectureId;

    public Like(Lecture lectureId, User userId) {
        this.lectureId = lectureId;
        this.userId = userId;
    }
}
