package com.hack.mapper;

import com.hack.entity.Exchanger;
import com.hack.entity.ExchangerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExchangerMapper {
    int countByExample(ExchangerExample example);

    int deleteByExample(ExchangerExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(Exchanger record);

    int insertSelective(Exchanger record);

    List<Exchanger> selectByExample(ExchangerExample example);

    Exchanger selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") Exchanger record, @Param("example") ExchangerExample example);

    int updateByExample(@Param("record") Exchanger record, @Param("example") ExchangerExample example);

    int updateByPrimaryKeySelective(Exchanger record);

    int updateByPrimaryKey(Exchanger record);
}
