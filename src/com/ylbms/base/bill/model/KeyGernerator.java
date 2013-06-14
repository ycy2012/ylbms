package com.ylbms.base.bill.model;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.type.Type;

/**
 * 序列生成类
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-14
 */

public class KeyGernerator extends SequenceGenerator implements
		PersistentIdentifierGenerator, Configurable {

	private String entityName;

	@Override
	public void configure(Type type, Properties params, Dialect dialect)
			throws MappingException {
		entityName = params.getProperty(ENTITY_NAME);
		super.configure(type, params, dialect);
	}

	@Override
	public Serializable generate(SessionImplementor session, Object obj) {
		Serializable result = super.generate(session, obj);
		// 想要的自定义字符在这里加
		return result + "";
	}

}
