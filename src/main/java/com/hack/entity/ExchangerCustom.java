package com.hack.entity;

import java.util.List;

/**
 * Exchanger扩展类
 */

public class ExchangerCustom extends Exchanger {
    //所属院系名
    private String collegeName;

    //选课列表
    private List<SelectedCourseCustom>selectedCourseList;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public List<SelectedCourseCustom> getSelectedCourseList() {
        return selectedCourseList;
    }

    public void setSelectedCourseList(List<SelectedCourseCustom> selectedCourseList) {
        this.selectedCourseList = selectedCourseList;
    }
}
