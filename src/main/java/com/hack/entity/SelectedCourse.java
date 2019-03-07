package com.hack.entity;

/**
 * 所选课程
 */
public class SelectedCourse {
    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public Integer getExchangerid() {
        return exchangerid;
    }

    public void setExchangerid(Integer exchangerid) {
        this.exchangerid = exchangerid;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    private Integer courseid;
    private Integer exchangerid;
    private Integer mark;

}
