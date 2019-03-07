package com.hack.service.mapper;

import com.hack.entity.Exchanger;
import com.hack.entity.ExchangerExample;
import com.hack.mapper.ExchangerMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ExchangerMapperTest {

    private ApplicationContext applicationContext;

    @Before
    public void setUp()throws Exception{
        applicationContext=new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
    }

    @Test
    public void selectByExample() throws Exception {
        ExchangerMapper exchangerMapper = (ExchangerMapper) applicationContext.getBean("exchangerMapper");

        //自定义条件查询对象
        ExchangerExample exchangerExample = new ExchangerExample();
        ExchangerExample.Criteria criteria =exchangerExample.createCriteria();

        //自定义查询条件
        criteria.andUsernameLike("李%");
        List<Exchanger> list = exchangerMapper.selectByExample(exchangerExample);

        System.out.println(list.get(0));

    }

}
