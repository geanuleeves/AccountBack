package com.wildb.account.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`bill_type`")
@Data
public class BillType {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "`gmt_create`")
    private Date gmtCreate;

    @Column(name = "`gmt_modified`")
    private Date gmtModified;

    /**
     * 账单分类名称
     */
    @Column(name = "`bill_type_name`")
    private String billTypeName;

    /**
     * 收支账本 1：收入账本  0：支出账本
     */
    @Column(name = "`is_income`")
    private Boolean isIncome;


}