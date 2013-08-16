package com.ylbms.test.tdmis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tedmis.location.service.TdmisLocationService;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class LocationTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private TdmisLocationService tdmisService;

	@Test
	public void testGet() {
		Object obj=tdmisService.getTdmisLocation("00000000000311");
		System.out.println(obj);
	}

}
