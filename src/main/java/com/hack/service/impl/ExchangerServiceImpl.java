package com.hack.service.impl;

import com.hack.entity.*;
import com.hack.mapper.CollegeMapper;
import com.hack.mapper.ExchangerMapper;
import com.hack.mapper.ExchangerMapperCustom;
import com.hack.service.ExchangerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangerServiceImpl implements ExchangerService {

    @Autowired
    private ExchangerMapperCustom exchangerMapperCustom;

    @Autowired
    private ExchangerMapper exchangerMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public void updataById(Integer id, ExchangerCustom exchangerCustom) throws Exception {
        exchangerMapper.updateByPrimaryKey(exchangerCustom);
    }

    @Override
    public void removeById(Integer id) throws Exception {
        exchangerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ExchangerCustom> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        List<ExchangerCustom> list = exchangerMapperCustom.findByPaging(pagingVO);

        return list;
}

    @Override
    public Boolean save(ExchangerCustom exchangerCustom) throws Exception {
        Exchanger exchanger = exchangerMapper.selectByPrimaryKey(exchangerCustom.getE_id());
        if (exchanger == null) {
            exchangerMapper.insert(exchangerCustom);
            return true;
        }

        return false;
    }

    @Override
    public int getCountExchanger() throws Exception {
        //自定义查询对象
        ExchangerExample exchangerExample = new ExchangerExample();
        //通过criteria构造查询条件
        ExchangerExample.Criteria criteria = exchangerExample.createCriteria();
        criteria.andUseridIsNotNull();

        return exchangerMapper.countByExample(exchangerExample);
    }

    @Override
    public ExchangerCustom findById(Integer id) throws Exception {
        Exchanger exchanger  = exchangerMapper.selectByPrimaryKey(id);
        ExchangerCustom exchangerCustom = null;
        if (exchanger != null) {
            exchangerCustom = new ExchangerCustom();
            //类拷贝
            BeanUtils.copyProperties(exchanger, exchangerCustom);
        }

        return exchangerCustom;
    }

    @Override
    public List<ExchangerCustom> findByName(String name) throws Exception {
        ExchangerExample exchangerExample = new ExchangerExample();
        //自定义查询条件
        ExchangerExample.Criteria criteria = exchangerExample.createCriteria();

        criteria.andUsernameLike("%" + name + "%");

        List<Exchanger> list = exchangerMapper.selectByExample(exchangerExample);

        List<ExchangerCustom> exchangerCustomList = null;

        if (list != null) {
            exchangerCustomList = new ArrayList<ExchangerCustom>();
            for (Exchanger s : list) {
                ExchangerCustom exchangerCustom = new ExchangerCustom();
                //类拷贝
                BeanUtils.copyProperties(s, exchangerCustom);
                //获取课程名
                College college = collegeMapper.selectByPrimaryKey(s.getCollegeid());
                exchangerCustom.setCollegeName(college.getCollegename());

                exchangerCustomList.add(exchangerCustom);
            }
        }

        return exchangerCustomList;
    }

    @Override
    public ExchangerCustom findExchangerAndSelectCourseListByName(String name) throws Exception {

        ExchangerCustom exchangerCustom = exchangerMapperCustom.findExchangerAndSelectCourseListById(Integer.parseInt(name));

        List<SelectedCourseCustom> list = exchangerCustom.getSelectedCourseList();

        // 判断该课程是否修完
        for (SelectedCourseCustom s : list) {
            if (s.getMark() != null) {
                s.setFinished(true);
            }
        }
        return exchangerCustom;
    }
}
