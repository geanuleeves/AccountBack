package com.wildb.account.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "`currency`")
@Data
public class Currency {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "`gmt_create`")
    private Date gmtCreate;

    @Column(name = "`gmt_modified`")
    private Date gmtModified;

    /**
     * 币种名称
     */
    @Column(name = "`currency_name`")
    private String currencyName;

}