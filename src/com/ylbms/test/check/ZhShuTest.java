package com.ylbms.test.check;

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

import com.google.common.collect.Lists;
import com.ylbms.base.check.model.JmylbModel;
import com.ylbms.base.check.model.ZhShInfosModel;
import com.ylbms.base.check.model.ZhShuMasterModel;
import com.ylbms.base.check.service.ZhShModelService;
import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.system.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class ZhShuTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	ZhShModelService zhShService;

	@Test
	public void createZhsh() {
		try {
			List<ZhShInfosModel> list = Lists.newArrayList();
			ZhShInfosModel info = new ZhShInfosModel();
			info.setSingle(new SingleInfo("YLB1000000000000001"));
			info.setAzLocation("安装位置测试 ");
			list.add(info);

			ZhShuMasterModel zs = new ZhShuMasterModel();
			zs.setCreateUser(new User(1L));
			zs.setSjUnit("dddddd");
			zs.setBasis("ddd");
			zs.setzCode("dddd");
			zs.setCreateDate(new Date());
			zs.setResult("1.65");
			zs.setJmbInfo(new JmylbModel(11L));
			zs.setPzren("dfasdfdsf");

			zs.setInfos(list);
			zhShService.saveOrUpdate(zs);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
