package com.ylbms.base.check.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.alibaba.fastjson.annotation.JSONField;
import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.common.model.BaseModel;

/**
 * 检定记录明细信息实体
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-16
 */
@Entity
@IdClass(CheckNotesInfoPK.class)
@Table(name = "ylbms_jc_jdInfos")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CheckNotesInfo extends BaseModel {

	private static final long serialVersionUID = 1L;

	private SingleInfo single;// 单件虚拟编号
	private CheckNotes checkNotes; // 检定记录编码
	private Date jdDate;// 检定日期
	private Date yxDate;// 有效日期
	private String azLocation;// 安装位置
	private String jmbCode;// 精密表编码
	private String zShuCode;// 证书 编码
	private Date zsyxDate; // 证书有效日期
	private String shbCode; // 设备编码
	private String grade; // 检定等级
	private Long order;// 排序
	private String remark;// 备注信息

	private CheckZhShuModel zCode;  //检定证书编号

	public CheckNotesInfo() {
	}

	// setter getter
	@Id
	public SingleInfo getSingle() {
		return single;
	}

	public void setSingle(SingleInfo single) {
		this.single = single;
	}

	@Id
	public CheckNotes getCheckNotes() {
		return checkNotes;
	}

	public void setCheckNotes(CheckNotes checkNotes) {
		this.checkNotes = checkNotes;
	}

	@Column(name = "jd_date")
	@JSONField(format = "yyyy-mm-dd")
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

	@Column(name = "az_location")
	public String getAzLocation() {
		return azLocation;
	}

	public void setAzLocation(String azLocation) {
		this.azLocation = azLocation;
	}

	@Column(name = "jmb_code")
	public String getJmbCode() {
		return jmbCode;
	}

	public void setJmbCode(String jmbCode) {
		this.jmbCode = jmbCode;
	}

	@Column(name = "zshu_code")
	public String getzShuCode() {
		return zShuCode;
	}

	public void setzShuCode(String zShuCode) {
		this.zShuCode = zShuCode;
	}

	@Column(name = "zsyx_date")
	@JSONField(format = "yyyy-mm-dd")
	public Date getZsyxDate() {
		return zsyxDate;
	}

	public void setZsyxDate(Date zsyxDate) {
		this.zsyxDate = zsyxDate;
	}

	@Column(name = "shb_code")
	public String getShbCode() {
		return shbCode;
	}

	public void setShbCode(String shbCode) {
		this.shbCode = shbCode;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "sort")
	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
