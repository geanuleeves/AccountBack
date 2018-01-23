package com.wildb.account.common.beans;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

/**
 * 分页响应对象
 * 
 * @author 郑邦振
 */
@Data
public class PageResp<T> {
	private List<T> rows;

	private int pageNum;

	private int pagesize;

	private long total;

	private int pages;


	public PageResp(Page<T> page) {
		this.rows = page.getContent();
		this.pageNum = page.getNumber() + 1;
		this.pagesize = page.getSize();
		this.total = page.getTotalElements();
	}

	public PageResp(List<T> rows,int pageNum,int pagesize,long total,int pages){
		this.rows = rows;
		this.pageNum = pageNum;
		this.pagesize = pagesize;
		this.total = total;
		this.pages = pages;
	}

}
