package com.wildb.account.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "`budget`")
@Data
public class Budget {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "`gmt_create`")
    private Date gmtCreate;

    @Column(name = "`gmt_modified`")
    private Date gmtModified;

    /**
     * 总预算
     */
    @Column(name = "`total_budget`")
    private BigDecimal totalBudget;

    /**
     * 账本ID
     */
    @Column(name = "`book_id`")
    private String bookId;


}