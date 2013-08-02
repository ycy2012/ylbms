package com.ylbms.base.report.model;

import java.io.Serializable;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-31
 */
public class SingleBarDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String stateName;
	private Integer counts;
	private String remark;

	public SingleBarDTO() {
	}

	// getter setter
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
