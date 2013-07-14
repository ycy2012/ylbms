package com.ylbms.base.single.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ylbms.common.model.BaseModel;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-5
 */
@Entity
@Table(name = "ylbms_bas_state")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StateInfo extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String id;
	private String stateName;
	private String remark;
	private String status;

	public StateInfo() {
		this.status = DEL_FLAG_NORMAL;
	}

	public StateInfo(String id) {
		this.id = id;
	}

	// setter getter
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

//	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE}, mappedBy = "state")
//	@NotFound(action = NotFoundAction.IGNORE)
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//	public List<SingleInfo> getSingles() {
//		return singles;
//	}
//
//	public void setSingles(List<SingleInfo> singles) {
//		this.singles = singles;
//	}

}
