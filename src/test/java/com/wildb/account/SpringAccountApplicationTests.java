package com.wildb.account;

import com.wildb.account.common.utils.MyMapper;
import com.wildb.account.entity.BillType;
import com.wildb.account.entity.Book;
import com.wildb.account.entity.Currency;
import com.wildb.account.entity.Permission;
import com.wildb.account.mapper.BillTypeMapper;
import com.wildb.account.mapper.BookMapper;
import com.wildb.account.mapper.CurrencyMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAccountApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Resource
	private CurrencyMapper currencyMapper;

	@Resource
	private BillTypeMapper billTypeMapper;

	@Resource
	private MyMapper mapper;

	@Test
	public void  test() {
		BillType billType = new BillType();
		billType.setBillTypeName("3");
		billType.setIsIncome(false);
//		this.billTypeMapper.insert(billType);
		this.mapper.insert(billType);
	}

}