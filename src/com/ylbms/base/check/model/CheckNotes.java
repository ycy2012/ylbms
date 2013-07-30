package com.ylbms.base.check.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.google.common.collect.Lists;
import com.ylbms.common.model.BaseModel;
import com.ylbms.system.model.User;

/**
 * 检定记录表头信息实体
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-16
 */
@Entity
@Table(name = "YLBMS_JC_JDMASTER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CheckNotes extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String jdID; // id
	private String title; // 标题
	private String sjLocation; // 送检位置信息
	private String jdLocation; // 检定位置
	private Date sxDate; // 生效日期
	private User createUser; // 制作人
	private Date createDate; // 制作日期
	private String remark; // 备注信息
	private String status; // 状态

	private List<CheckNotesInfo> notesInfo=Lists.newArrayList();

	// 构造函数
	public CheckNotes() {
		this.sxDate = new Date();
		this.status=DEL_FLAG_NORMAL;
	}

	public CheckNotes(String jdID) {
		this.jdID = jdID;
	}

	// getter setter
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "com.ylbms.base.check.model.CheckNotesPK")
	@Column(name = "jd_id", nullable = false)
	public String getJdID() {
		return jdID;
	}

	public void setJdID(String jdID) {
		this.jdID = jdID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "sj_location")
	public String getSjLocation() {
		return sjLocation;
	}

	public void setSjLocation(String sjLocation) {
		this.sjLocation = sjLocation;
	}

	@Column(name = "jd_Location")
	public String getJdLocation() {
		return jdLocation;
	}

	public void setJdLocation(String jdLocation) {
		this.jdLocation = jdLocation;
	}

	@Column(name = "sx_Date")
	public Date getSxDate() {
		return sxDate;
	}

	public void setSxDate(Date sxDate) {
		this.sxDate = sxDate;
	}

	@ManyToOne
	@JoinColumn(name = "create_user")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	@OneToMany(mappedBy = "checkNotes", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy("order")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<CheckNotesInfo> getNotesInfo() {
		return notesInfo;
	}

	public void setNotesInfo(List<CheckNotesInfo> notesInfo) {
		this.notesInfo = notesInfo;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getJdID()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CheckNotes == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		CheckNotes other = new CheckNotes();
		return new EqualsBuilder().append(getJdID(), other.getJdID())
				.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("jdID", this.getJdID())
				.append("tilte", this.getTitle()).toString();
	}

}
