package com.ylbms.base.single.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
@Transactional
public class SingleInfoService {

	@Autowired
	private SingleInfoDao singleDao;

	/**
	 * 添加单件明细
	 * 
	 * @param singleInfo
	 */
	@Transactional(readOnly = false)
	public void saveSingleInfo(SingleInfo singleInfo) {
		singleDao.save(singleInfo);
	}

	/**
	 * 获取ID
	 * 
	 * @param id
	 * @return
	 */
	public SingleInfo getSingleById(String id) {
		return singleDao.get(id);
	}

	/**
	 * 修改单件明细
	 * 
	 * @param singleInfo
	 */
	@Transactional(readOnly = false,noRollbackFor=RuntimeException.class)
	public void updateSingleInfo(SingleInfo singleInfo) {
		singleDao.save(singleInfo);
	}

	/**
	 * 删除单件明细
	 * 
	 * @param singleInfo
	 */
	@Transactional(readOnly = false)
	public void deleteSingleInfo(SingleInfo singleInfo) {
		singleDao.delete(singleInfo);

	}

	/**
	 * 根据主键删除单件明细
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteSingleInfo(String id) {
		singleDao.delete(id);
	}

	/**
	 * 添加明细查询
	 * 
	 * @param page
	 * @param filters
	 * @param mids
	 * @return
	 */
	public Page<SingleInfo> findSingleNotInMids(Page<SingleInfo> page,
			List<PropertyFilter> filters, String mids, String state,
			String wzName) {
		return singleDao.findPageNotInMids(page, filters, mids, state, wzName);
	}
	/**
	 * 给安装记录添加单件明细
	 * @param page
	 * @param filters
	 * @param mids
	 * @param state
	 * @param wzName
	 * @return
	 */
	public Page<SingleInfo> findSingleByInstall(Page<SingleInfo> page,List<PropertyFilter> filters, String mids, String state,
			String wzName){
		return singleDao.findPageByInstall(page, filters, mids, state, wzName);
	}

	/**
	 * 根据分页查询单件明细
	 * 
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<SingleInfo> findSingleInfo(Page<SingleInfo> page,
			List<PropertyFilter> filters) {
		return singleDao.findPage(page, filters);
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	@Transactional(readOnly=false)
	public void delByIds(String ids) {
		String delHQL = "delete SingleInfo where mid in(" + ids + ")";
		singleDao.getSession().createQuery(delHQL).executeUpdate();
	}

	/**
	 * 测试的玩意儿！！不要当真
	 */
	public void test(){
		Session session=singleDao.getSession();
		 List cats = session.createCriteria(SingleInfo.class)
			     .createAlias("location", "l",JoinType.LEFT_OUTER_JOIN)
			     .add( Restrictions.like("l.id", 1400L) )
			     .list();
		 
		 System.out.print("------"+cats.size());
	}
}
