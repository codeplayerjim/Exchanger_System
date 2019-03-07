package com.hack.mapper;

import com.hack.entity.CourseCustom;
import com.hack.entity.PagingVO;

import java.util.List;

public interface CourseMapperCustom {

    List<CourseCustom>findByPaging(PagingVO pagingVO)throws Exception;
}
