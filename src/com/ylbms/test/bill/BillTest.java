package com.ylbms.test.bill;


import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.bill.model.BillHeadModel;
import com.ylbms.base.bill.service.BillHeadService;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc.xml",
		"classpath:applicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class BillTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	BillHeadService billHDao;


	@Test
	@Transactional
	public void createBill() {
//		for(int i=0;i<100;i++){
			BillHeadModel bhm=new BillHeadModel();
			bhm.setSendLocation("测试位置");
			bhm.setAcceptLocation("测试位置");
			bhm.setDjTitle("测试单据");
			bhm.setCreateDate(new Date());
			
			billHDao.saveBillHead(bhm);
		}
}
