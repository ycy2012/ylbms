package com.ylbms.common.utils.excel.fieldtype;

import com.ylbms.base.single.model.SpectypeInfo;
import com.ylbms.base.utils.BaseUtils;

/**
 * 字段类型转换
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-5
 */
public class SpectypeInfoType {
	/**
	 * 获取对象(导出)
	 * 
	 * @param val
	 * @return
	 */
	public static Object getValue(String val) {
		for (SpectypeInfo s : BaseUtils.getSpectypeAll()) {
			if (s.getSpeName().equals(val)) {
				return s;
			}
		}
		return null;
	}

	/**
	 * 设置对象值导入
	 * 
	 * @param val
	 */
	public static String setValue(Object val) {
		if (val != null && ((SpectypeInfo) val).getSpeId() != null) {
			return ((SpectypeInfo) val).getSpeName();
		}
		return "";
	}
}
