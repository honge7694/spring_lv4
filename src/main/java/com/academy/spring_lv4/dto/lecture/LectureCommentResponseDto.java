package com.academy.spring_lv4.dto.lecture;

import com.academy.spring_lv4.dto.comment.CommentResponseDto;
import com.academy.spring_lv4.dto.teacher.TeacherResponseDto;
import com.academy.spring_lv4.entity.Comment;
import com.academy.spring_lv4.entity.Lecture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comments;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class LectureCommentResponseDto {
    private Long lectureId;
    private String lectureName;
    private int lecturePrice;
    private String lectureCategory;
    private String lectureIntroduce;

    private TeacherResponseDto teacherResponseDto;
    private List<CommentResponseDto> commentList = new ArrayList<>();
    //TODO like 추가
    public LectureCommentResponseDto(Lecture lecture, List<Comment> comments) {
        this.lectureId = lecture.getLectureId();
        this.lectureName = lecture.getName();
        this.lecturePrice = lecture.getPrice();
        this.lectureCategory = lecture.getCategory();
        this.lectureIntroduce = lecture.getIntroduce();
        this.teacherResponseDto = new TeacherResponseDto(lecture.getTeacher());
        this.commentList = comments.stream().map(CommentResponseDto::new).toList();
    }
}
