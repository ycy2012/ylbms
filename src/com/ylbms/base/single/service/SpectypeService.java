package com.ylbms.base.single.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.single.dao.SpectypeInfoDao;
import com.ylbms.base.single.model.SpectypeInfo;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;

/**
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-9
 */
@Component
@Transactional
public class SpectypeService {

	@Autowired
	SpectypeInfoDao spectypeDao;

	/**
	 * 增加规格型号
	 * 
	 * @param spectype
	 */
	@Transactional(readOnly = false)
	public void addSpectype(SpectypeInfo spectype) {
		spectypeDao.save(spectype);
	}

	public SpectypeInfo getSpetypeById(int id) {
		return spectypeDao.get(id);
	}

	/**
	 * delete spectype by Id
	 * 
	 * @param id
	 */

	@Transactional(readOnly = false, noRollbackFor = RuntimeException.class)
	public void deleteSpectype(String ids) {
		spectypeDao
				.delSpectypeInfo("delect from YLBMS_BAS_SPECTYPEINFO where id in("
						+ ids + ")");
	}

	/**
	 * 删除一条记录
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteSpectype(int id) {
		spectypeDao.delete(id);
		// spectypeDao.findPage(page, filters)
	}

	/**
	 * 修改规格型号
	 * 
	 * @param spectype
	 */
	@Transactional(readOnly = false)
	public void updateSpectypeInfo(SpectypeInfo spectype) {
		spectypeDao.save(spectype);
	}

	/**
	 * 根据名称查询
	 * 
	 * @param spename
	 */
	public void findSpectypeInfo(String spename, int sort) {
		String hql = "select spename from YLBMS_BAS_SPECTYPEINFO where spename like "
				+ spename + " and sort like" + sort + "";
		spectypeDao.find(hql, spename, sort);
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<SpectypeInfo> getSpectypeInfo(final Page<SpectypeInfo> page,
			final List<PropertyFilter> filters) {
		return spectypeDao.findPage(page, filters);

	}

	/**
	 * 可变参数分页查询
	 * 
	 * @param page
	 * @param spename
	 * @param sort
	 * @return
	 */
	public Page<SpectypeInfo> getSpectypeInfo(Page<SpectypeInfo> page,
			String spename, int sort) {
		// spectypeDao.findPage(page<SpectypeInfo>,String spename,int state);
		String hql = "select spename,sort from YLBMS_BAS_SPECTYPEINFO where spename="
				+ spename + " and sort=" + sort + "";
		return spectypeDao.findPage(page, hql, spename, sort);
	}
}
