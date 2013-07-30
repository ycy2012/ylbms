package com.ylbms.base.single.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.single.dao.SpectypeInfoDao;
import com.ylbms.base.single.model.SpectypeInfo;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.system.utils.UserUtils;

/**
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-9
 */
@Service
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
		UserUtils.removeCache("spectypes");
	}

	public SpectypeInfo getSpetypeById(long  id) {
		return spectypeDao.get(id);
	}

	/**
	 * delete spectype by Id
	 * 
	 * @param id
	 */

	@Transactional(readOnly = false)
	public void deleteSpectype(String ids) {
		spectypeDao
				.delSpectypeInfo("delete from SpectypeInfo where speid in("
						+ ids + ")");
		UserUtils.removeCache("spectypes");
	}

	/**
	 * 删除一条记录
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteSpectype(Long id) {
		spectypeDao.delete(id);
		UserUtils.removeCache("spectypes");
	}

	/**
	 * 修改规格型号
	 * 
	 * @param spectype
	 */
	@Transactional(readOnly = false)
	public void updateSpectypeInfo(SpectypeInfo spectype) {
		spectypeDao.save(spectype);
		UserUtils.removeCache("spectypes");
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
	 * get all spectype infos
	 * 
	 * @author JackLiang
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SpectypeInfo> getAllSpectype() {
		Object obj = UserUtils.getCache("spectypes");
		if (obj == null) {
			obj = spectypeDao.getAll();
			UserUtils.putCache("spectypes", obj);
		}
		return ((List<SpectypeInfo>) obj);
	}
}
