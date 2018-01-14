package com.wildb.account.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`budget_type`")
@Data
public class BudgetType {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "`gmt_create`")
    private Date gmtCreate;

    @Column(name = "`gmt_modified`")
    private Date gmtModified;

    /**
     * 账单分类ID
     */
    @Column(name = "`bill_type_id`")
    private String billTypeId;

    /**
     * 预算金额
     */
    @Column(name = "`budget_amount`")
    private BigDecimal budgetAmount;

    /**
     * 预算名称
     */
    @Column(name = "`budget_name`")
    private String budgetName;

    /**
     * 总预算ID
     */
    @Column(name = "`budget_id`")
    private String budgetId;


}