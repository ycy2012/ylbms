package com.ylbms.common.web;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.ylbms.common.utils.spring.SpringContextHolder;
import com.ylbms.system.model.User;
import com.ylbms.system.service.SystemService;


public class ConvertTag extends BodyTagSupport
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8901255728370099837L;

	public int doEndTag() throws JspException
	{
		JspWriter jspOut = pageContext.getOut();
		try 
		{
			BodyContent bodyContent = getBodyContent();
			if(bodyContent == null || bodyContent.getString() == null || bodyContent.getString().trim().length() == 0)
			{
				return super.doEndTag();
			}
			
			Long userId = Long.parseLong(bodyContent.getString());
			SystemService systemService = (SystemService)SpringContextHolder.getBean("systemService");
			User user = systemService.getUser(userId);
			jspOut.print(user == null ? userId : user.getFullname());
		} 
		catch (IOException e) 
		{
			throw new JspException(e);
		}
		return super.doEndTag();
	}
}
