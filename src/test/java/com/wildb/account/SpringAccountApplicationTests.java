package com.wildb.account;

import com.wildb.account.entity.Book;
import com.wildb.account.entity.Currency;
import com.wildb.account.entity.Permission;
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

	@Test
	public void  test() {
		Currency currency = new Currency();
		currency = currencyMapper.selectByPrimaryKey("f4588cc0f91411e792c651ca02a8486f");
		System.out.println(currency);
		currency.setCurrencyName("哇哈哈");
		currency.setGmtModified(new Date());
		currencyMapper.updateByPrimaryKey(currency);
		System.out.println(currency);
	}

}