package com.ylbms.base.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.bill.dao.BillTbodyDao;
import com.ylbms.base.bill.model.BillTbodyModel;
/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-14
 */
@Service
public class BillTbodyService {
   
	@Autowired
	BillTbodyDao billTbodyDao;

	@Transactional(readOnly=false)
	public void save(BillTbodyModel btm) {
		billTbodyDao.save(btm);
	}
}
