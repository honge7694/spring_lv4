package com.academy.spring_lv4.entity;

public enum LectureCategoryEnum {
    SPRING(Category.SPRING),  // Spring
    REACT(Category.REACT),  // React
    NODE(Category.NODE);


    private final String category;

    LectureCategoryEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public static class Category {
        public static final String SPRING = "SPRING";
        public static final String REACT = "React";
        public static final String NODE = "Node";
    }
}
