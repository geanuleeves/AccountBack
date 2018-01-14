package com.wildb.account.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`income`")
@Data
public class Income {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "`gmt_create`")
    private Date gmtCreate;

    @Column(name = "`gmt_modified`")
    private Date gmtModified;

    /**
     * 收入金额
     */
    @Column(name = "`amount`")
    private BigDecimal amount;

    /**
     * 账单类型ID
     */
    @Column(name = "`billing_type_id`")
    private String billingTypeId;

    /**
     * 账本ID
     */
    @Column(name = "`book_id`")
    private String bookId;


}