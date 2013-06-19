package com.ylbms.base.single.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.type.Type;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-19
 */
public class SingleInfoPK implements IdentifierGenerator, Configurable {
	
	private static final Log log = LogFactory.getLog(SingleInfoPK.class);
	private static final String TILTE = "YLB";

	private String sql;
	private String next;

	@Override
	public void configure(Type arg0, Properties params, Dialect arg2)
			throws MappingException {
		String table = params.getProperty("table");
		if (table == null)
			table = params.getProperty(PersistentIdentifierGenerator.TABLE);
		String column = params.getProperty("column");
		if (column == null)
			column = params.getProperty(PersistentIdentifierGenerator.PK);
		String schema = params
				.getProperty(PersistentIdentifierGenerator.SCHEMA);
		sql = "select max(" + column + ") from "
				+ (schema == null ? table : schema + '.' + table);
		log.info(sql);
	}

	@Override
	public Serializable generate(SessionImplementor session, Object obj)
			throws HibernateException {
		if (sql != null) {
			getNext(session.connection());
		}
		return next;
	}

	private void getNext(Connection conn) throws HibernateException {
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String old = rs.getString(1);
				if (StringUtils.isNotBlank(old)) {
					Long id = Long.parseLong(old.substring(2, old.length())) + 1L;
					next = TILTE + id.toString();
				} else {
					next = "YLB1000000000000000";
				}
			} else {
				next = "YLB1000000000000000";
			}
		} catch (SQLException e) {
			throw new HibernateException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new HibernateException(e);
			}
		}
	}
}
