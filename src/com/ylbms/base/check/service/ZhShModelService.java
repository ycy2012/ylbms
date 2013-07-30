package com.ylbms.base.check.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.check.dao.ZhShMasterDao;
import com.ylbms.base.check.model.JmylbModel;
import com.ylbms.base.check.model.ZhShInfosModel;
import com.ylbms.base.check.model.ZhShuMasterModel;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.system.utils.UserUtils;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-26
 */
@Service
@Transactional
public class ZhShModelService {

	@Autowired
	private ZhShMasterDao zhShuDao;

	@Autowired
	private JmbInfoService jmbService;

	/**
	 * save method
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdate(ZhShuMasterModel entity) {
		zhShuDao.save(entity);
	}

	/**
	 * 
	 * @param master
	 * @param detail
	 */
	@Transactional(readOnly = false)
	public void saveZhShu(ZhShuMasterModel master, List<ZhShInfosModel> detail) {
		master.setCreateUser(UserUtils.getUser());
		JmylbModel jmb = jmbService.getId(master.getJmbInfo().getJmbID());
		for (ZhShInfosModel zs : detail) {
			zs.setJdDate(master.getJdDate());
			zs.setYxDate(master.getYxDate());
			zs.setJmbCode(jmb.getJmbCode());
			zs.setzShuCode(jmb.getZhShCode());
			zs.setZsyxDate(jmb.getYxDate());
			zs.setMaster(master);
		}
		master.setInfos(detail);
		zhShuDao.save(master);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		this.zhShuDao.delete(id);
	}

	/**
	 * list
	 * 
	 * @param page
	 * @param filters
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<ZhShuMasterModel> findZhShByPage(Page<ZhShuMasterModel> page,
			List<PropertyFilter> filters) {
		return this.zhShuDao.findPage(page, filters);
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public void delByIds(String ids) {
		String delHQL = "delete ZhShuMasterModel where  zId in (" + ids + ")";
		this.zhShuDao.getSession().createQuery(delHQL).executeUpdate();
	}

}
