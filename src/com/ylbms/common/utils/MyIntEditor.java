package com.ylbms.common.utils;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

/**
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-14
 */
public class MyIntEditor extends PropertyEditorSupport {

	@Override
	public String getAsText() {
		return getValue().toString();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.equals("")) {
			text = "0";
		}
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			setValue(Integer.parseInt(text));// 这句话是最重要的，他的目的是通过传入参数的类型来匹配相应的databind
		}
	}

}
