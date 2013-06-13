package com.ylbms.base.single.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ylbms.common.model.BaseModel;

/**
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-5
 */
@Entity
@Table(name = "YLBMS_BAS_SPECTYPEINFO")
public class SpectypeInfo extends BaseModel {

	private static final long serialVersionUID = 1L;
	private int speId;// 规格型号id
	private String speName;// 规格型号名称
	private String status;// 规格型号状态

	private int sort;// 规格型号类别
	
	private String remark;// 备注信息
	
	
	public SpectypeInfo() {
	}

	public SpectypeInfo(int speId, String speName, String status, int sort,
			String remark) {
		this.speId = speId;
		this.speName = speName;
		this.status = status;
		this.sort = sort;
		this.remark = remark;
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_base_spectypeinfo")
	@SequenceGenerator(name = "seq_base_spectypeinfo", sequenceName = "seq_base_spectypeinfo")
	public int getSpeId() {
		return speId;
	}
	
	public void setSpeId(int speId) {
		this.speId = speId;
	}

	@Column(length=50)
	public String getSpeName() {
		return speName;
	}
	
	public void setSpeName(String speName) {
		this.speName = speName;
	}

	@Column(length=4)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Column(length=50)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
