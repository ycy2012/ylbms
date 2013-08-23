package com.ylbms.base.location.model;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * tree bean
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-19
 */
public class TreeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String pid;
	private String wzCode;

	public TreeBean() {
	}

	// getter setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getWzCode() {
		return wzCode;
	}

	public void setWzCode(String wzCode) {
		this.wzCode = wzCode;
	}

}
