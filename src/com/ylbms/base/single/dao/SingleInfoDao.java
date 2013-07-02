package com.ylbms.base.single.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.common.orm.Page;
import com.ylbms.common.orm.PropertyFilter;
import com.ylbms.common.orm.hibernate.HibernateDao;

/**
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-8
 */
@Repository("SingleDao")
public class SingleInfoDao extends HibernateDao<SingleInfo, Long> {
	/**
	 * 在制作单据时添加明细的时候，防止信息重复添加
	 * 
	 * @author JackLiang
	 * @param page
	 * @param filters
	 *            其他的参数
	 * @param mids
	 *            已添加的mid集合
	 * @return
	 */
	public Page<SingleInfo> findPageNotInMids(Page<SingleInfo> page,
			List<PropertyFilter> filters, String mids, String state) {

		Criterion[] criterions = buildCriterionByPropertyFilter(filters);
		Criteria c = createCriteria(criterions);

		Criterion stateEQ = Restrictions.eq("state", state); // 状态
		c.add(stateEQ);
		
		if (StringUtils.isNotBlank(mids)) {
			String[] values = mids.split(",");
			Criterion cIn = Restrictions.not(Restrictions.in("mid", values)); //已选择的
			c.add(cIn);
		}
		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}
		setPageParameterToCriteria(c, page);
		@SuppressWarnings("unchecked")
		List<SingleInfo> result = c.list();
		page.setResult(result);
		return page;
	}
}
