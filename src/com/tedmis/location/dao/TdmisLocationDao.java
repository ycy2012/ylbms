package com.tedmis.location.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.tedmis.location.model.TdmisLocation;
import com.tedmis.util.DBContextHolder;
import com.ylbms.common.utils.spring.SpringContextHolder;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-16
 */
@Repository
public class TdmisLocationDao {

	private SessionFactory wzSessionFactory;

	public SessionFactory getWzSessionFactory() {
		return wzSessionFactory;
	}

	@Autowired
	public void setWzSessionFactory(SessionFactory wzSessionFactory) {
		DBContextHolder.setDB(DBContextHolder.DB_TDMIS);
		this.wzSessionFactory = SpringContextHolder.getBean("sessionFactory");
	}

	/**
	 * 取得当前Session.
	 */
	public Session getSession() {
		return wzSessionFactory.getCurrentSession();
	}

	/**
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> createSQLQuery(final String sql) {
		Assert.notNull(sql, "SQL不可以为空!");
		return getSession().createSQLQuery(sql).list();
	}

	/**
	 * 
	 * @param entity
	 */
	public void save(TdmisLocation entity) {
		getSession().save(entity);
	}

	/**
	 * delete method
	 * 
	 * @param entity
	 */
	public void delete(TdmisLocation entity) {
		getSession().delete(entity);
	}

	/**
	 * delete entity by id
	 * 
	 * @param id
	 */
	public void delete(final String id) {
		Assert.notNull(id, "id不能为空！");
		getSession().delete(get(id));
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public TdmisLocation get(final String id) {
		Assert.notNull(id, "id不能为空");
		return (TdmisLocation) getSession().get(TdmisLocation.class, id);
	}

	/**
	 * get all List
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TdmisLocation> getAll() {
		return getSession().createCriteria(TdmisLocation.class).list();
	}
}
