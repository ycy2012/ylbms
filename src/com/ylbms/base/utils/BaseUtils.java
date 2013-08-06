package com.ylbms.base.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ylbms.base.single.dao.SingleInfoDao;
import com.ylbms.base.single.dao.SpectypeInfoDao;
import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.base.single.model.SpectypeInfo;
import com.ylbms.common.utils.spring.SpringContextHolder;

/**
 * 基础信息工具类
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-5
 */
public class BaseUtils {

	private static Logger log = LoggerFactory.getLogger(BaseUtils.class);

	private static SpectypeInfoDao spectypeDao = SpringContextHolder
			.getBean("spectypeDao");
	private static SingleInfoDao singelDao = SpringContextHolder
			.getBean("SingleDao");

	/**
	 * 返回所有的规格型号信息
	 * 
	 * @return
	 */
	public static List<SpectypeInfo> getSpectypeAll() {
		return spectypeDao.getAll() == null ? null : spectypeDao.getAll();
	}

	/**
	 * search all singleinfos
	 * 
	 * @return
	 */
	public static List<SingleInfo> getSingleAll() {
		return singelDao.getAll() == null ? null : singelDao.getAll();
	}
}
