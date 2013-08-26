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
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ylbms.common.model.BaseModel;
import com.ylbms.common.utils.excel.annotation.ExcelField;

/**
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-5
 */
@Entity
@Table(name = "YLBMS_BAS_SPECTYPEINFO")
@DynamicUpdate(true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpectypeInfo extends BaseModel {

	private static final long serialVersionUID = 1L;
	private Long speId;// 规格型号id
	private String speName;// 规格型号名称
	private String status;// 规格型号状态
	private Integer sort;// 规格顺序
	private String remark;// 备注信息

	private Set<SingleInfo> singles = new HashSet<SingleInfo>(); // one to many

	public SpectypeInfo() {
		this.status = DEL_FLAG_NORMAL;
	}

	public SpectypeInfo(Long speId) {
		this.speId = speId;
	}

	public SpectypeInfo(Long speId, String speName, String status, int sort,
			String remark) {
		this.speId = speId;
		this.speName = speName;
		this.status = status;
		this.sort = sort;
		this.remark = remark;
	}

	// setter getter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_base_spectypeinfo")
	@SequenceGenerator(name = "seq_base_spectypeinfo", sequenceName = "seq_base_spectypeinfo", allocationSize = 1)
	public Long getSpeId() {
		return speId;
	}

	public void setSpeId(Long speId) {
		this.speId = speId;
	}

	@Column(length = 50)
	@ExcelField(title = "规格型号名称", align = 2, sort = 2, type = 0)
	public String getSpeName() {
		return speName;
	}

	public void setSpeName(String speName) {
		this.speName = speName;
	}

	@Column(length = 4)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ExcelField(title = "排序", align = 3, sort = 3, type = 0)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(length = 100)
	@ExcelField(title = "备注信息", sort = 4, type = 0)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.REFRESH }, mappedBy = "spectype")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<SingleInfo> getSingles() {
		return singles;
	}

	public void setSingles(Set<SingleInfo> singles) {
		this.singles = singles;
	}
}
