package com.hack.mapper;

import com.hack.entity.ExchangerCustom;
import com.hack.entity.PagingVO;

import java.util.List;

public interface ExchangerMapperCustom {
    //分页查询学生信息
    List<ExchangerCustom>findByPaging(PagingVO pagingVO)throws Exception;
    //查询学生信息，和其选课信息
    ExchangerCustom findExchangerAndSelectCourseListById(Integer id)throws Exception;
}
