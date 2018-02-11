package com.wildb.account.mapper;

import com.wildb.account.common.utils.MyMapper;
import com.wildb.account.entity.BillType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BillTypeMapper extends MyMapper<BillType> {

    @Select("select * from bill_type where bill_type_name like CONCAT('%',#{keyword},'%') order by gmt_modified desc")
    List<BillType> selectLikeName(String keyword);
}