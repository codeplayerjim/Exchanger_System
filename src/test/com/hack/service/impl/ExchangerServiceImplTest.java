package com.hack.service.impl;

import com.hack.entity.Exchanger;
import com.hack.entity.ExchangerCustom;
import com.hack.entity.ExchangerExample;
import com.hack.mapper.ExchangerMapper;
import com.hack.service.ExchangerService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ExchangerServiceImplTest {

    private ApplicationContext applicationContext;

    @Before
    public void setUp()throws Exception {
        applicationContext = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext-dao.xml",
                "spring/applicationContext-service.xml"});
    }
    @Test
    public void updataById() throws Exception {
        ExchangerService exchangerService = (ExchangerService) applicationContext.getBean("exchangerServiceImpl");

        ExchangerCustom exchangerCustom = new  ExchangerCustom();
        exchangerCustom.setE_id(101);
        exchangerCustom.setUsername("小拉");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-m-d");
        Date date=simpleDateFormat.parse("2012-09-12");

        exchangerCustom.setCreateTime(date);


        exchangerCustom.setCollegeid(1);
        exchangerCustom.setAge(20);
        exchangerService.updataById(101, exchangerCustom);
    }

    @Test
    public void save() throws Exception {
        ExchangerCustom exchangerCustom = new ExchangerCustom();
        exchangerCustom.setE_id(103);
        exchangerCustom.setUsername("李想");
//        studentCustom.setBirthyear(new Date(1996, 9, 2));

        //指定时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        // 指定一个日期
        Date date = dateFormat.parse("1996-09-02");
        exchangerCustom.setCreateTime(date);

        exchangerCustom.setCollegeid(1);
        exchangerCustom.setAge(19);

        ExchangerService exchangerService = (ExchangerService) applicationContext.getBean("exchangerServiceImpl");
        exchangerService.save(exchangerCustom);
    }

    @Test
    public void selectById() {
        ExchangerMapper exchangerMapper= (ExchangerMapper) applicationContext.getBean("exchangerMapper");
        ExchangerExample exchangerExample=new ExchangerExample();
        ExchangerExample.Criteria criteria=exchangerExample.createCriteria();

        criteria.andUsernameLike("张%");
        List<Exchanger>list=exchangerMapper.selectByExample(exchangerExample);
        System.out.println(list.get(0));
    }


}