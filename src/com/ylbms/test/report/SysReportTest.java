package com.ylbms.test.report;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.ylbms.base.report.model.SingleBarDTO;
import com.ylbms.base.report.service.SysReportService;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class SysReportTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private SysReportService reportService;

	@Test
	public void queryTest() {
		List<SingleBarDTO> barDTO = reportService.singleCountsBystate();
		List<Integer> values = Lists.newArrayList();
		for (SingleBarDTO bar : barDTO) {
			values.add(bar.getCounts());
		}
		System.out.println(values);
	}

}
