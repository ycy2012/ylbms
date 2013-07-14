package com.ylbms.base.single.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ylbms.common.model.BaseModel;

/**
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-5
 */
@Entity
@Table(name = "YLBMS_BAS_SPECTYPEINFO")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpectypeInfo extends BaseModel {

	private static final long serialVersionUID = 1L;
	private int speId;// 规格型号id
	private String speName;// 规格型号名称
	private String status;// 规格型号状态
	private int sort;// 规格顺序
	private String remark;// 备注信息
	
	private Set<SingleInfo> singles=new HashSet<SingleInfo>(); //one to many
	
	public SpectypeInfo() {
		this.status=DEL_FLAG_NORMAL;
	}

	public SpectypeInfo(int speId) {
		this.speId = speId;
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

	@Column(length=100)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="spectype")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<SingleInfo> getSingles() {
		return singles;
	}

	public void setSingles(Set<SingleInfo> singles) {
		this.singles = singles;
	}
}
