package com.hack.service;

import com.hack.entity.Exchanger;
import com.hack.entity.ExchangerCustom;

import java.util.List;

public interface ExchangerService {
    //根据id个更新交换生信息
    void updataById(Integer id, ExchangerCustom exchangerCustom) throws Exception;

    //根据id删除交换生信息
    void removeById(Integer id) throws Exception;

    //获取分页查询交换生信息
    List<ExchangerCustom> findByPaging(Integer toPageNo) throws Exception;

    //保存交换生信息
    Boolean save(ExchangerCustom exchangerCustom) throws Exception;

    //获取交换生总数
    int getCountExchanger() throws Exception;

    //根据id获取交换生信息
    ExchangerCustom findById(Integer id) throws Exception;

    //根据名字模糊查询
    List<ExchangerCustom> findByName(String name) throws Exception;

    // 一对多查询，查询该交换生的选课信息
    ExchangerCustom findExchangerAndSelectCourseListByName(String name) throws Exception;

}
