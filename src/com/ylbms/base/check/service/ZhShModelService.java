package com.ylbms.base.check.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ylbms.base.check.dao.JmbInfoDao;
import com.ylbms.base.check.dao.ZhShMasterDao;
import com.ylbms.base.check.model.JmylbModel;
import com.ylbms.base.check.model.ZhShInfosModel;
import com.ylbms.base.check.model.ZhShuMasterModel;
import com.ylbms.base.single.dao.SingleInfoDao;
import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.base.single.model.StateInfo;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.system.utils.UserUtils;

/**
 * 检定证书模块Service
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-26
 * @modified 更加单件信息在完成检定证书以后 2013年7月31日 09:54:47
 * 
 */
@Service
@Transactional
public class ZhShModelService {

	@Autowired
	private ZhShMasterDao zhShuDao;

	@Autowired
	private JmbInfoDao jmbDao;

	@Autowired
	private SingleInfoDao singleDao;

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
		// 获取精密压力表
		JmylbModel jmb = jmbDao.get(master.getJmbInfo().getJmbID());
		for (ZhShInfosModel zs : detail) {
			zs.setJdDate(master.getJdDate() == null ? null : master.getJdDate());
			zs.setYxDate(master.getYxDate() == null ? null : master.getYxDate());
			zs.setJmbCode(jmb.getJmbCode() == null ? "" : jmb.getJmbCode());
			zs.setzShuCode(jmb.getZhShCode() == null ? "" : jmb.getZhShCode());
			zs.setZsyxDate(jmb.getYxDate() == null ? null : jmb.getYxDate());
			zs.setMaster(master);
			// 更新单件信息
			updateSingleInfo(zs);
		}
		master.setInfos(detail);
		zhShuDao.save(master);
	}

	/**
	 * 更新单件信息内容
	 * 
	 * @param detail
	 */
	public void updateSingleInfo(ZhShInfosModel detail) {
		if (null != detail && !detail.equals("")) {
			SingleInfo single = singleDao.get(detail.getSingle().getMid());
			single.setYxTime(detail.getYxDate());
			single.setJdtime(detail.getJdDate());
			single.setAzLocation(detail.getAzLocation()==null?"":detail.getAzLocation());
			single.setState(new StateInfo("020"));
			singleDao.save(single); //持久化
		}

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

	/**
	 * 根据zID来查询检定证书信息
	 * 
	 * @param zId
	 * @return
	 */
	public ZhShuMasterModel getZhShMasterByZid(Long zId) {
		return zhShuDao.get(zId);
	}

}
