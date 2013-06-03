package com.ylbms.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.ylbms.common.orm.hibernate.HibernateDao;
import com.ylbms.system.model.Authority;
import com.ylbms.system.model.Menu;
import com.ylbms.system.model.Role;
import com.ylbms.system.model.User;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-5-31
 */
@Component
public class MenuDao extends HibernateDao<Menu, Long> {
	/**
	 * 批量删除
	 * 
	 * @param HQL
	 * @param values
	 * @return
	 */
	public int createQuery(String HQL) {
		return getSession().createQuery(HQL).executeUpdate();
	}

	/**
	 * get menuInfo by userID
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Menu> findByUserId(Long id) {
		String queHQL = "select distinct m from Menu m, Role r, User u where m in elements (r.menuList) and r in elements (u.roleList)"
				+ " and m.delFlag='"
				+ Menu.DEL_FLAG_NORMAL
				+ "' and r.delFlag='"
				+ Role.DEL_FLAG_NORMAL
				+ "' and u.delFlag='"
				+ User.DEL_FLAG_NORMAL
				+ "' and u.id="+id+" or (m.user.id="+id+"  and m.delFlag='"
				+ Menu.DEL_FLAG_NORMAL + "') order by m.sort";
		Query query=getSession().createQuery(queHQL);
		return query.list();
	}

	public List<Menu> findByParentIdsLike(String string) {
		String queHQL = "from Menu where parentIds like :ids";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", string);
		return find(queHQL, map);
	}

	public void saveMenu(List<Menu> list) {
		for (Menu m : list) {
			save(m);
		}
	}
}
