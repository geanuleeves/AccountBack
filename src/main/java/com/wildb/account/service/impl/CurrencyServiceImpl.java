package com.wildb.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.wildb.account.common.beans.PageReq;
import com.wildb.account.common.service.BaseService;
import com.wildb.account.entity.Currency;
import com.wildb.account.mapper.CurrencyMapper;
import com.wildb.account.service.CurrencyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CurrencyServiceImpl extends BaseService<Currency> implements CurrencyService {


    @Resource
    private CurrencyMapper currencyMapper;

    @Override
    public List<Currency> findLikeName(PageReq pageReq) {
        PageHelper.startPage(pageReq.getPage(),pageReq.getPagesize());
        List<Currency> currencies = this.currencyMapper.selectLikeName(pageReq.getKeyword());
        return currencies;
    }
}
