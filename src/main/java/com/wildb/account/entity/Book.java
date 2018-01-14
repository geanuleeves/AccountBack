package com.wildb.account.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`book`")
@Data
public class Book {
    /**
     * 主键
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 更新时间
     */
    @Column(name = "`gmt_create`")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @Column(name = "`gmt_modified`")
    private Date gmtModified;

    /**
     * 账本名称
     */
    @Column(name = "`book_name`")
    private String bookName;

    /**
     * 是否删除 1：是   0：否
     */
    @Column(name = "`is_deleted`")
    private Boolean isDeleted;

    /**
     * 账本图片
     */
    @Column(name = "`book_img`")
    private String bookImg;

    /**
     * 币种ID
     */
    @Column(name = "`currency_id`")
    private String currencyId;

    /**
     * 账本颜色
     */
    @Column(name = "`book_color`")
    private String bookColor;

}