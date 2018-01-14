package com.wildb.account.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`expenditure`")
@Data
public class Expenditure {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "`gmt_create`")
    private Date gmtCreate;

    @Column(name = "`gmt_modified`")
    private Date gmtModified;

    /**
     * 金额
     */
    @Column(name = "`amount`")
    private BigDecimal amount;

    /**
     * 付款类型ID
     */
    @Column(name = "`payment_category_id`")
    private String paymentCategoryId;

    /**
     * 消费地点
     */
    @Column(name = "`location_consumption`")
    private String locationConsumption;

    /**
     * 文字备注
     */
    @Column(name = "`remarks`")
    private String remarks;

    /**
     * 图片备注
     */
    @Column(name = "`remarks_img`")
    private String remarksImg;

    /**
     * 账本ID
     */
    @Column(name = "`book_id`")
    private String bookId;

    /**
     * 是否删除 1：是   0：否
     */
    @Column(name = "`is_delete`")
    private Boolean isDelete;

    /**
     * 账单类型ID
     */
    @Column(name = "`billing_type_id`")
    private String billingTypeId;


}