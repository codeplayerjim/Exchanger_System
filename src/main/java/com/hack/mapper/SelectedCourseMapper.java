package com.hack.mapper;

import com.hack.entity.SelectedCourse;
import com.hack.entity.SelectedCourseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectedCourseMapper {
    int countByExample(SelectedCourseExample example);

    int deleteByExample(SelectedCourseExample example);

    int insert(SelectedCourse record);

    int insertSelective(SelectedCourse record);

    List<SelectedCourse> selectByExample(SelectedCourseExample example);

    int updateByExampleSelective(@Param("record") SelectedCourse record, @Param("example") SelectedCourseExample example);

    int updateByExample(@Param("record") SelectedCourse record, @Param("example") SelectedCourseExample example);
}
