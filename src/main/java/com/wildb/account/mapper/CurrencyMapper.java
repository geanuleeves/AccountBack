package com.wildb.account.mapper;

import com.wildb.account.common.utils.MyMapper;
import com.wildb.account.entity.Currency;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CurrencyMapper extends MyMapper<Currency> {

    @Select("select * from currency where currency_name like CONCAT('%',#{keyword},'%') order by gmt_modified desc")
    List<Currency> selectLikeName(String keyword);
}