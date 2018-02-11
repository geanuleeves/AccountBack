package com.wildb.account.service;

import com.wildb.account.common.beans.PageReq;
import com.wildb.account.common.service.IService;
import com.wildb.account.entity.BillType;

import java.util.List;

public interface BillTypeService extends IService<BillType>{

    /**
     * 根据关键词查询账单类型列表
     * @param pageReq
     * @return
     */
    List<BillType> findLikeName(PageReq pageReq);
}
