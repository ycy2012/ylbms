package com.ylbms.base.bill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.bill.dao.BillHeadDao;
import com.ylbms.base.bill.model.BillHeadModel;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-14
 */
@Service
@Transactional
public class BillHeadService {

	@Autowired
	BillHeadDao billHDao;

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<BillHeadModel> searchBill(Page<BillHeadModel> page,
			List<PropertyFilter> filters) {
		return billHDao.findPage(page, filters);
	}

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteBill(String id) {
		billHDao.delete(id);
	}

	@Transactional(readOnly = false)
	public void saveBillHead(BillHeadModel bhm) {
		billHDao.save(bhm);
	}
	/***
	 * 根据djID来查询
	 * @param id
	 * @return
	 */
	public BillHeadModel getBillHeadByID(String djId){
		return billHDao.get(djId);
	}

}
