package com.ylbms.base.single.dao;

import org.springframework.stereotype.Repository;

import com.ylbms.base.single.model.SpectypeInfo;
import com.ylbms.common.orm.hibernate.HibernateDao;

/**
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-5
 */
@Repository("spectypeDao")
public class SpectypeInfoDao extends HibernateDao<SpectypeInfo, Long> {
	/**
	 * 批量删除
	 * @param ids
	 */
	public int delSpectypeInfo(String HQL) {
		return getSession().createQuery(HQL).executeUpdate();
	}
	
	/**
	 * 删除一条记录
	 * @param HQL
	 */
	public void delSpectypeInfo(SpectypeInfo spetype){
		delete(spetype);
	}

	/**
	 * 根据规格型号名称查询（模糊查询）
	 * @param string
	 * @return
	 */

	/*public List<Menu> findSpectypeInfo(String string) {
		String HQL = "from YLBMS_BAS_SPECTYPEINFO where speName like name";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", string);
		return find(HQL, map);
	}*/
	
}

