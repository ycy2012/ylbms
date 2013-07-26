package com.ylbms.base.check.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.alibaba.fastjson.annotation.JSONField;
import com.ylbms.common.model.BaseModel;
import com.ylbms.system.model.User;

/**
 * 精密压力表实体类
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-26
 */
@Entity
@Table(name = "ylbms_base_jmbInfo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JmylbModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	private Long jmbID; // id
	private String jmbName; // 名称
	private String jmbType; // 精密表型号
	private String factoryCode; // 出厂编码
	private String jmbCode; // 精密表编码
	private String clfw; // 测量范围
	private String grade; // 准确度等级
	private String zhShCode; // 证书编码
	private String madeIn; // 生产厂家
	private String jdUnit; // 检定单位
	private Date jdDate;// 检定日期
	private Date yxDate; // 证书有效期
	private User creater;
	private Date createDate;
	private String remark;
	private String status; // 是否有效

	public JmylbModel() {
		this.status = DEL_FLAG_NORMAL;
	}

	public JmylbModel(Long jmbID) {
		this.jmbID = jmbID;
	}

	// setter getter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_jd_jmbinfo")
	@SequenceGenerator(name = "seq_jd_jmbinfo", sequenceName = "seq_jd_jmbinfo")
	public Long getJmbID() {
		return jmbID;
	}

	public void setJmbID(Long jmbID) {
		this.jmbID = jmbID;
	}

	public String getJmbName() {
		return jmbName;
	}

	public void setJmbName(String jmbName) {
		this.jmbName = jmbName;
	}

	public String getJmbType() {
		return jmbType;
	}

	public void setJmbType(String jmbType) {
		this.jmbType = jmbType;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	@Column(name = "Jmb_Code")
	public String getJmbCode() {
		return jmbCode;
	}

	public void setJmbCode(String jmbCode) {
		this.jmbCode = jmbCode;
	}

	public String getClfw() {
		return clfw;
	}

	public void setClfw(String clfw) {
		this.clfw = clfw;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getZhShCode() {
		return zhShCode;
	}

	public void setZhShCode(String zhShCode) {
		this.zhShCode = zhShCode;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public String getJdUnit() {
		return jdUnit;
	}

	public void setJdUnit(String jdUnit) {
		this.jdUnit = jdUnit;
	}

	@Column(name = "jd_Date")
	public Date getJdDate() {
		return jdDate;
	}

	public void setJdDate(Date jdDate) {
		this.jdDate = jdDate;
	}

	@Column(name = "yx_date")
	@JSONField(format = "yyyy-mm-dd")
	public Date getYxDate() {
		return yxDate;
	}

	public void setYxDate(Date yxDate) {
		this.yxDate = yxDate;
	}

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "creater")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public User getCreater() {
		return creater;
	}

	public void setCreater(User creater) {
		this.creater = creater;
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

}
