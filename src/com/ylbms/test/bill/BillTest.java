package com.ylbms.test.bill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.bill.model.BillHeadModel;
import com.ylbms.base.bill.model.BillTbodyModel;
import com.ylbms.base.bill.service.BillHeadService;
import com.ylbms.base.single.model.SingleInfo;

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
		BillHeadModel bhm = new BillHeadModel();
		bhm.setDjTitle("测试单据");
		bhm.setCreateDate(new Date());

		BillTbodyModel btm = new BillTbodyModel();
		btm.setNewState("dddddd");
		btm.setBillId(bhm);
		btm.setMid(new SingleInfo("YLB1000000000000009"));
		
		List<BillTbodyModel> billTbody = new ArrayList<BillTbodyModel>();
		billTbody.add(btm);
		
		bhm.setBillTbody(billTbody);
		billHDao.saveBillHead(bhm);
	}

}
