package com.ylbms.common.utils.excel.fieldtype;

import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.base.utils.BaseUtils;

/**
 * 单件信息类型转换
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-6
 */
public class SingleInfoType {
	/**
	 * 
	 * @param val
	 * @return
	 */
	public static Object getValue(String val) {
		for (SingleInfo s : BaseUtils.getSingleAll()) {
			if (s.getWzname().equals(val)) {
				return s;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param val
	 * @return
	 */
	public static String setValue(Object val) {
		if (val != null && ((SingleInfo) val).getMid() != null) {
			return ((SingleInfo) val).getWzname();
		}
		return null;
	}
}
