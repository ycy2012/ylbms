package com.ylbms.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.single.dao.SingleInfoDao;
import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.base.single.model.SpectypeInfo;
import com.ylbms.base.single.model.StateInfo;
import com.ylbms.base.single.service.SingleInfoService;

/**
 * 
 * @author zhangJl
 * @version 1.0
 * @date 2013-6-13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc.xml",
		"classpath:applicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class SingleInfoServiceTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private SingleInfoService singleInfoService;
	
	private SingleInfoDao singleDao;

	@Test
	public void test() {
		for (int i = 0; i < 20; i++) {
			StateInfo s=new StateInfo("010");
			SpectypeInfo sp=new SpectypeInfo(5050);
			SingleInfo singleinfo = new SingleInfo();
			singleinfo.setOwercode("78996385"+i);
			singleinfo.setLocation("基地");
			singleinfo.setSpectype(sp);
			singleinfo.setWzname("压力表SZ型号");
			singleinfo.setState(s);
			singleInfoService.saveSingleInfo(singleinfo);
		}
	}
	
	public void updateSingle() {
		String hql="update SingleInfo set owercode=? where state='20' ";
	    singleDao.batchExecute(hql, "00000000");
	}
	
}
