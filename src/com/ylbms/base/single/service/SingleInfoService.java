package com.ylbms.base.single.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.swing.StringUIClientPropertyKey;

import com.ylbms.base.single.dao.SingleInfoDao;
import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.utils.StringUtils;

/**
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-8
 * @editor jackLiang
 * @date 2013年7月14日 10:29:33
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
	@Transactional(readOnly = false)
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
	 * 
	 * @param page
	 * @param filters
	 * @param mids
	 * @param state
	 * @param wzName
	 * @return
	 */
	public Page<SingleInfo> findSingleByInstall(Page<SingleInfo> page,
			List<PropertyFilter> filters, String mids, String state,
			String wzName) {
		return singleDao.findPageByInstall(page, filters, mids, state, wzName);
	}

	/**
	 * 根据分页查询单件明细
	 * 
	 * @param page 页面
	 * @param single 对象
	 * @param mids 单件信息集合 not in
	 * @return
	 */
	public Page<SingleInfo> findSingleInfo(Page<SingleInfo> page,
			SingleInfo single, String mids) {

		DetachedCriteria dc = singleDao.createDetachedCriteria();
		if (single.getLocation() != null
				&& single.getLocation().getId() != null) {
			dc.add(Restrictions.eq("location.id", single.getLocation().getId()));
		}
		if (single.getSpectype() != null
				&& single.getSpectype().getSpeId() != null) {
			dc.add(Restrictions.eq("spectype.speId", single.getSpectype()
					.getSpeId()));
		}
		if (null != single.getState() && single.getState().getId() != null) {
			dc.add(Restrictions.eq("state.id", single.getState().getId()));
		}
		// 根据主键
		if (StringUtils.isNotEmpty(single.getMid())) {
			dc.add(Restrictions.eq("mid", single.getMid()));
		}
		/**
		 * 重新添加的！
		 * 
		 * @author JackinLiang
		 */
		if (StringUtils.isNotBlank(mids)) {
			String[] mid = mids.split(",");
			dc.add(Restrictions.not(Restrictions.in("mid", mid)));
		}

		if (StringUtils.isNotEmpty(single.getOwercode())) {
			dc.add(Restrictions.eq("owercode", single.getOwercode()));
		}

		if (StringUtils.isNotEmpty(single.getWzname())) {
			dc.add(Restrictions.like("wzname", single.getWzname(),
					MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotEmpty(single.getStatus())) {
			dc.add(Restrictions.eq("status", single.getStatus()));
		}

		if (single.getJdtime() != null) {
			dc.add(Restrictions.like("jdtime", single.getJdtime()));
		}

		if (StringUtils.isNotEmpty(single.getGdzcCode())) {
			dc.add(Restrictions.like("gdzcCode", single.getGdzcCode()));
		}
		// if (single!=null&&single.getFactory() > 0) {
		// dc.add(Restrictions.like("factory", single.getFactory()));
		// }

		if (!StringUtils.isNotEmpty(page.getOrderBy())) {
			dc.addOrder(Order.asc("spectype.speId"));
		}
		return singleDao.find(page, dc);
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	@Transactional(readOnly = false)
	public void delByIds(String ids) {
		String delHQL = "delete SingleInfo where mid in(" + ids + ")";
		singleDao.getSession().createQuery(delHQL).executeUpdate();
	}

	/**
	 * 测试的玩意儿！！不要当真
	 */
	public void test() {
		Session session = singleDao.getSession();
		Criteria c = session.createCriteria(SingleInfo.class);
		List cats = session.createCriteria(SingleInfo.class)
		// .createAlias("location", "l",JoinType.LEFT_OUTER_JOIN)
				.add(Restrictions.like("location", 1400L)).list();

		System.out.print("------" + cats.size());
	}

}
