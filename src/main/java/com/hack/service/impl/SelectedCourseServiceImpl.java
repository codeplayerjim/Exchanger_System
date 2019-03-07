package com.hack.service.impl;

import com.hack.entity.*;
import com.hack.mapper.ExchangerMapper;
import com.hack.mapper.SelectedCourseMapper;
import com.hack.service.SelectedCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SelectedCourseServiceImpl implements SelectedCourseService {

    @Autowired
    private SelectedCourseMapper selectedcourseMapper;

    @Autowired
    private ExchangerMapper exchangerMapper;

    @Override
    public List<SelectedCourseCustom> findByCourseID(Integer id) throws Exception {
        SelectedCourseExample example = new SelectedCourseExample();
        SelectedCourseExample.Criteria criteria = example.createCriteria();
        criteria.andCourseidEqualTo(id);

        List<SelectedCourse> list = selectedcourseMapper.selectByExample(example);
        List<SelectedCourseCustom> secList = new ArrayList<SelectedCourseCustom>();
        for (SelectedCourse s: list) {
            SelectedCourseCustom sec = new SelectedCourseCustom();
            BeanUtils.copyProperties(s, sec);
            //判断是否完成类该课程
            if (sec.getMark() != null) {
                sec.setFinished(true);
            }
            Exchanger exchanger = exchangerMapper.selectByPrimaryKey(sec.getExchangerid());
            ExchangerCustom exchangerCustom = new ExchangerCustom();
            BeanUtils.copyProperties(exchanger, exchangerCustom);

            sec.setExchangerCustom(exchangerCustom);

            secList.add(sec);
        }

        return secList;
    }

    @Override
    public List<SelectedCourseCustom> findByCourseIDPaging(Integer page, Integer id) throws Exception {
        return null;
    }

    @Override
    public Integer countByCourseID(Integer id) throws Exception {
        SelectedCourseExample example = new SelectedCourseExample();
        SelectedCourseExample.Criteria criteria = example.createCriteria();
        criteria.andCourseidEqualTo(id);

        return selectedcourseMapper.countByExample(example);
    }

    @Override
    public SelectedCourseCustom findOne(SelectedCourseCustom selectedCourseCustom) throws Exception {
        SelectedCourseExample example = new SelectedCourseExample();
        SelectedCourseExample.Criteria criteria = example.createCriteria();

        criteria.andCourseidEqualTo(selectedCourseCustom.getCourseid());
        criteria.andExchangeridEqualTo(selectedCourseCustom.getExchangerid());

        List<SelectedCourse> list = selectedcourseMapper.selectByExample(example);


        if (list.size() > 0) {
            SelectedCourseCustom sc = new SelectedCourseCustom();
            BeanUtils.copyProperties(list.get(0), sc);

            Exchanger exchanger = exchangerMapper.selectByPrimaryKey(selectedCourseCustom.getExchangerid());
            ExchangerCustom exchangerCustom = new ExchangerCustom();
            BeanUtils.copyProperties(exchanger, exchangerCustom);

            sc.setExchangerCustom(exchangerCustom);

            return sc;
        }

        return null;
    }

    @Override
    public void updataOne(SelectedCourseCustom selectedCourseCustom) throws Exception {
        SelectedCourseExample example = new SelectedCourseExample();
        SelectedCourseExample.Criteria criteria = example.createCriteria();

        criteria.andCourseidEqualTo(selectedCourseCustom.getCourseid());
        criteria.andExchangeridEqualTo(selectedCourseCustom.getExchangerid());

        selectedcourseMapper.updateByExample(selectedCourseCustom, example);


    }

    @Override
    public void save(SelectedCourseCustom selectedCourseCustom) throws Exception {
        selectedcourseMapper.insert(selectedCourseCustom);
    }

    @Override
    public List<SelectedCourseCustom> findByExchangerID(Integer id) throws Exception {
        return null;
    }

    @Override
    public void remove(SelectedCourseCustom selectedCourseCustom) throws Exception {
        SelectedCourseExample example = new SelectedCourseExample();
        SelectedCourseExample.Criteria criteria = example.createCriteria();

        criteria.andCourseidEqualTo(selectedCourseCustom.getCourseid());
        criteria.andExchangeridEqualTo(selectedCourseCustom.getExchangerid());

        selectedcourseMapper.deleteByExample(example);
    }
}
