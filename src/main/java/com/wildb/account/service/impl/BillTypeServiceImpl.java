package com.wildb.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.wildb.account.common.beans.PageReq;
import com.wildb.account.common.service.BaseService;
import com.wildb.account.entity.BillType;
import com.wildb.account.mapper.BillTypeMapper;
import com.wildb.account.service.BillTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BillTypeServiceImpl extends BaseService<BillType> implements BillTypeService{

    @Resource
    private BillTypeMapper billTypeMapper;

    @Override
    public List<BillType> findLikeName(PageReq pageReq) {
        PageHelper.startPage(pageReq.getPage(),pageReq.getPagesize());
        List<BillType> billTypes = this.billTypeMapper.selectLikeName(pageReq.getKeyword());
        return billTypes;
    }
}
