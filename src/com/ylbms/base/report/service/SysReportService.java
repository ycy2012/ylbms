package com.ylbms.base.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.ylbms.base.report.model.SingleBarDTO;
import com.ylbms.base.single.dao.SingleInfoDao;

@Service
@Transactional
public class SysReportService {

	@Autowired
	private SingleInfoDao singleDao;

	/**
	 * 根据单件当前状态来进行分类查询
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<SingleBarDTO> singleCountsBystate() {
		List<SingleBarDTO> values = Lists.newArrayList();
		// 查询sql
		String querySQL = "select s.id ,count(t.mid) from ylbms_bas_single_info t right "
				+ "join  ylbms_bas_state s on s.id=t.state where s.status='0'  group by s.id  order by s.id";

		List<Object[]> obj = singleDao.getSession().createSQLQuery(querySQL)
				.list();
		for (int i = 0, len = obj.size(); i < len; i++) {
			SingleBarDTO bar = new SingleBarDTO();
			bar.setStateName(obj.get(i)[0].toString());
			bar.setCounts(Integer.parseInt(obj.get(i)[1] == null ? "0" : obj
					.get(i)[1].toString()));
			values.add(bar);
		}
		return values;
	}

	/**
	 * 获得即将到期的单件信息 目前的阀值是2
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Long getCheckCount() {
		String querySQL = "select count(1) from ylbms_bas_single_info t  where (t.yx_time-t.jd_time) < 2";
		Object obj=singleDao.getSession().createSQLQuery(querySQL)
				.uniqueResult();
		return Long.parseLong(obj.toString());
	}
}
