package com.ylbms.base.location.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ylbms.common.model.BaseModel;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-21
 */
@Entity
@Table(name = "TDMIS_LOCATION_FULLNAME")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdmisLocationFullName extends BaseModel {

	private static final long serialVersionUID = 3705113966123007315L;

	private String wzId;
	private String fullName;
	private String isZdj; // 是否重点井

	public TdmisLocationFullName() {
	}

	public TdmisLocationFullName(String wzId) {
		this.wzId = wzId;
	}

	// getter setter
	@Id
	public String getWzId() {
		return wzId;
	}

	public void setWzId(String wzId) {
		this.wzId = wzId;
	}

	@Column
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column
	public String getIsZdj() {
		return isZdj;
	}

	public void setIsZdj(String isZdj) {
		this.isZdj = isZdj;
	}

}
