package com.wildb.account.service;

import com.wildb.account.common.beans.PageReq;
import com.wildb.account.common.service.IService;
import com.wildb.account.entity.Currency;

import java.util.List;

public interface CurrencyService extends IService<Currency> {

    /**
     * 根据关键词查询币种列表
     * @param pageReq
     * @return
     */
    List<Currency> findLikeName(PageReq pageReq);
}
