package com.ylbms.base.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ylbms.base.bill.dao.BillTbodyDao;
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
}
