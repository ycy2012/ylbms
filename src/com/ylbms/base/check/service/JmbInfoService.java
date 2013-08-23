package com.ylbms.base.check.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.check.dao.JmbInfoDao;
import com.ylbms.base.check.model.JmylbModel;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.system.utils.UserUtils;

/**
 * 精密表管理业务层
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-7-26
 */
@Repository
@Transactional
public class JmbInfoService {

	@Autowired
	JmbInfoDao jmbInfoDao;

	/**
	 * 添加或修改
	 * 
	 * @param jmbInfo
	 */
	@Transactional(readOnly = false)
	public void save(JmylbModel jmbInfo) {
		jmbInfo.setCreater(UserUtils.getUser());
		jmbInfoDao.save(jmbInfo);
	}

	/**
	 * 删除
	 * 
	 * @param jmbInfo
	 */
	@Transactional(readOnly = false)
	public void deleteJmbInfo(Long jmbID) {
		jmbInfoDao.delete(jmbID);
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<JmylbModel> list(Page<JmylbModel> page,
			List<PropertyFilter> filters) {
		return jmbInfoDao.findPage(page, filters);
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	@Transactional(readOnly = false)
	public void delByIds(String ids) {
		String delHQL = "delete SingleInfo where mid in(" + ids + ")";
		jmbInfoDao.getSession().createQuery(delHQL).executeUpdate();
	}

	/**
	 * 获取id
	 * 
	 * @param id
	 * @return
	 */
	public JmylbModel getId(Long id) {
		JmylbModel jmylbModel = jmbInfoDao.get(id);
		return jmylbModel;
	}

	/**
	 * 修改精密表
	 * 
	 * @param jmbInfo
	 */
	@Transactional(readOnly = false)
	public void update(JmylbModel jmbInfo) {
		jmbInfo.setCreater(UserUtils.getUser());
		jmbInfoDao.save(jmbInfo);
	}

	/**
	 * get jmb all infos
	 * 
	 * @return
	 */
	public List<JmylbModel> getJmbAll() {
		return jmbInfoDao.getAll();
	}
}
