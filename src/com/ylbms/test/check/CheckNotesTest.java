package com.ylbms.test.check;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-24
 */

import com.ylbms.base.check.model.CheckNotes;
import com.ylbms.base.check.model.CheckNotesInfo;
import com.ylbms.base.check.service.CheckNotesService;
import com.ylbms.base.single.model.SingleInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class CheckNotesTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	CheckNotesService checkService;
	
	/**
	 * save
	 */
	@Test
	public void createCheckNotes() {
		List<CheckNotesInfo> list = new ArrayList<CheckNotesInfo>();
		CheckNotes master = new CheckNotes();
		master.setJdLocation("dafsadfasdfsadfasf");
		CheckNotesInfo info = new CheckNotesInfo();
		info.setSingle(new SingleInfo("YLB1000000000000019"));
		info.setAzLocation("安装位置");
		info.setCheckNotes(master);
		list.add(info);
		master.setNotesInfo(list);
		
//		checkService.save(master);
	}
}
