package com.ylbms.base.single.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.single.dao.SingleInfoDao;
import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;

/**
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-8
 */
@Component
@Transactional
public class SingleInfoService {
	
	@Autowired
	private SingleInfoDao singleDao;
	
	/**
	 * 添加单件明细
	 * @param singleInfo
	 */
	@Transactional(readOnly=false)
	public void saveSingleInfo(SingleInfo singleInfo){
		singleDao.save(singleInfo);
	}
	
	/**
	 * 修改单件明细
	 * @param singleInfo
	 */
	@Transactional(readOnly=false)
	public void updateSingleInfo( SingleInfo singleInfo){
		singleDao.save(singleInfo);
	}
	
	/**
	 * 删除单件明细
	 * @param singleInfo
	 */
	@Transactional(readOnly=false)
	public void deleteSingleInfo(SingleInfo singleInfo){
		singleDao.delete(singleInfo);
		
	}
	
	/**
	 * 根据主键删除单件明细
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void deleteSingleInfo(String id){
		singleDao.delete(id);
	}
	
	/**
	 * 根据分页查询单件明细
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<SingleInfo> findSingleInfo(Page<SingleInfo> page,List<PropertyFilter> filters){
		return singleDao.findPage(page,filters);
	}

}
