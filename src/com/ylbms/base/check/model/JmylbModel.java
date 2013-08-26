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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.alibaba.fastjson.annotation.JSONField;
import com.ylbms.common.model.BaseModel;
import com.ylbms.common.utils.excel.annotation.ExcelField;
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
@DynamicUpdate(true)
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
	private String UserUnit; // 使用部门
	private User creater;
	private Date createDate;
	private String remark;
	private String status; // 是否有效

	public JmylbModel() {
		this.status = DEL_FLAG_NORMAL;
		this.createDate = new Date();
	}

	public JmylbModel(Long jmbID) {
		this.jmbID = jmbID;
	}

	// setter getter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_jd_jmbinfo")
	@SequenceGenerator(name = "seq_jd_jmbinfo", sequenceName = "seq_jd_jmbinfo", allocationSize = 1)
	@Column(name = "jmb_id")
	public Long getJmbID() {
		return jmbID;
	}

	public void setJmbID(Long jmbID) {
		this.jmbID = jmbID;
	}

	@Column(name = "jmb_name")
	@ExcelField(title = "器具名称", sort = 1, type = 0)
	public String getJmbName() {
		return jmbName;
	}

	public void setJmbName(String jmbName) {
		this.jmbName = jmbName;
	}

	@Column(name = "jmb_type")
	@ExcelField(title = " 规格", sort = 2, type = 0)
	public String getJmbType() {
		return jmbType;
	}

	public void setJmbType(String jmbType) {
		this.jmbType = jmbType;
	}

	@Column(name = "factory_code")
	@ExcelField(title = "出厂编号", sort = 4, type = 0)
	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	@Column(name = "Jmb_Code")
	@ExcelField(title = "精密表编码", type = 1, sort = 5)
	public String getJmbCode() {
		return jmbCode;
	}

	public void setJmbCode(String jmbCode) {
		this.jmbCode = jmbCode;
	}

	@Column
	@ExcelField(title = "测量范围", sort = 3)
	public String getClfw() {
		return clfw;
	}

	public void setClfw(String clfw) {
		this.clfw = clfw;
	}

	@ExcelField(title = "等级", sort = 5, type = 1)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "zhsh_code")
	@ExcelField(title = "证书编码", sort = 9)
	public String getZhShCode() {
		return zhShCode;
	}

	public void setZhShCode(String zhShCode) {
		this.zhShCode = zhShCode;
	}

	@Column
	@ExcelField(title = "生产厂家", sort = 6)
	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	@Column(name = "jd_unit")
	@ExcelField(title = "检定单位", sort = 10)
	public String getJdUnit() {
		return jdUnit;
	}

	public void setJdUnit(String jdUnit) {
		this.jdUnit = jdUnit;
	}

	@Column(name = "jd_Date")
	@ExcelField(title = "检定日期", sort = 8)
	@Temporal(TemporalType.DATE)
	@JSONField(format = "yyyy-mm-dd")
	public Date getJdDate() {
		return jdDate;
	}

	public void setJdDate(Date jdDate) {
		this.jdDate = jdDate;
	}

	@Column(name = "yx_date")
	@JSONField(format = "yyyy-mm-dd")
	@Temporal(TemporalType.DATE)
	@ExcelField(title = "有效日期", sort = 7)
	public Date getYxDate() {
		return yxDate;
	}

	public void setYxDate(Date yxDate) {
		this.yxDate = yxDate;
	}

	@ExcelField(title = "使用部门", sort = 11)
	public String getUserUnit() {
		return UserUnit;
	}

	public void setUserUnit(String userUnit) {
		UserUnit = userUnit;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.REFRESH })
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

	@ExcelField(title = "备注", type = 1, sort = 20)
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
