package com.ylbms.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.location.service.LocationService;

/**
 * 位置信息测试
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class LocationTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	LocationService locationService;

	@Test
	public void createLocation() {


	}
	
	public void getAllLocation(){
		locationService.getAllLocation();
	}
	
}
