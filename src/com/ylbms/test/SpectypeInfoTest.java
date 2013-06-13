package com.ylbms.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.single.model.SpectypeInfo;
import com.ylbms.base.single.service.SpectypeService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc.xml",
		"classpath:applicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class SpectypeInfoTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	// SpectypeInfoDao spectype;
	SpectypeService spectype;

	@Test
	public void addSpectypeInfo() {
		SpectypeInfo s = new SpectypeInfo();
		try {
			s.setSpeName("dddddd");
			spectype.addSpectype(s);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("-----------" + e);
		}

	}

	public void updateSpectyInfo() {
		SpectypeInfo s1 = new SpectypeInfo();
	}

}
