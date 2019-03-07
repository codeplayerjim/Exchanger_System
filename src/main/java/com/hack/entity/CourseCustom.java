package com.hack.entity;

public class CourseCustom extends Course {
    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    //所属院系名
    private String collegeName;
}
