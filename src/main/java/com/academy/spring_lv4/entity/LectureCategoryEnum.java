package com.academy.spring_lv4.entity;

public enum LectureCategoryEnum {
    SPRING(Lecture.SPRING),  // Spring
    REACT(Lecture.REACT),  // React
    NODE(Lecture.NODE);


    private final String lecture;

    LectureCategoryEnum(String lecture) {
        this.lecture = lecture;
    }

    public String getLecture() {
        return this.lecture;
    }

    public static class Lecture {
        public static final String SPRING = "SPRING";
        public static final String REACT = "REACT";
        public static final String NODE = "NODE";
    }
}
