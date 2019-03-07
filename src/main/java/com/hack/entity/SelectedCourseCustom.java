package com.hack.entity;

public class SelectedCourseCustom extends SelectedCourse {
    public CourseCustom getCourseCustom() {
        return courseCustom;
    }

    public void setCourseCustom(CourseCustom courseCustom) {
        this.courseCustom = courseCustom;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public ExchangerCustom getExchangerCustom() {
        return exchangerCustom;
    }

    public void setExchangerCustom(ExchangerCustom exchangerCustom) {
        this.exchangerCustom = exchangerCustom;
    }

    //新增exchanger对象字段
    private ExchangerCustom exchangerCustom;
    //扩展课程信息对象
    private CourseCustom courseCustom;
    //判断学生是否已经完成该课程
    private Boolean isFinished=false;


}
