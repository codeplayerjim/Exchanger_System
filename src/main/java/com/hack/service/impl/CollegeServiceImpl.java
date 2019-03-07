package com.hack.service.impl;

import com.hack.entity.College;
import com.hack.entity.CollegeExample;
import com.hack.mapper.CollegeMapper;
import com.hack.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    CollegeMapper collegeMapper;

    @Override
    public List<College> findAll() throws Exception {
        CollegeExample collegeExample=new CollegeExample();
        CollegeExample.Criteria criteria=collegeExample.createCriteria();
        criteria.andCollegeidIsNotNull();
        return collegeMapper.selectByExample(collegeExample);
    }
}
