package com.ylbms.base.location.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * 动态切换数据源
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-16
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DBContextHolder.getDB();
	}

}
