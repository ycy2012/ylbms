package com.ylbms.base.check.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.common.model.BaseModel;
import com.ylbms.common.utils.excel.annotation.ExcelField;

/**
 * 证书明细信息及检定记录信息
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-29
 */
@Entity
@IdClass(ZhShDetailIdPK.class)
@Table(name = "ylbms_jc_jdNotes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ZhShInfosModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	private SingleInfo single;
	private SingleInfo spectype; // 不进行持久化
	private SingleInfo clfw; // 不进行持久化
	private SingleInfo factoryCode; // 不进行持久化
	private ZhShuMasterModel master;
	private Date jdDate;// 检定日期
	private Date yxDate;// 有效日期
	private String azLocation;// 安装位置
	private String jmbCode;// 精密表编码
	private String zShuCode;// 证书 编码
	private Date zsyxDate; // 证书有效日期
	private String shbCode; // 设备编码
	private String grade; // 等级
	private Long order; // 排序
	private String remark; // 备注
	private String status;

	public ZhShInfosModel() {
	}

	// getter setter
	@Id
	@ExcelField(title = "物资名称", value = "single.wzname")
	public SingleInfo getSingle() {
		return single;
	}

	public void setSingle(SingleInfo single) {
		this.single = single;
	}

	/**
	 * 不进行持久化 为导出EXCEL
	 * 
	 * @return
	 */
	@Transient
	@ExcelField(title = "规格型号", value = "single.spectype", sort = 1)
	public SingleInfo getSpectype() {
		return spectype;
	}

	public void setSpectype(SingleInfo spectype) {
		this.spectype = spectype;
	}
	/**
	 * 不进行持久化 为导出EXCEL
	 * 
	 * @return
	 */
	@Transient
	@ExcelField(title = "测量范围", value = "single.clfw", sort = 2)
	public SingleInfo getClfw() {
		return clfw;
	}

	public void setClfw(SingleInfo clfw) {
		this.clfw = clfw;
	}
	/**
	 * 不进行持久化 为导出EXCEL
	 * 
	 * @return
	 */
	@Transient
	@ExcelField(title = "出厂编码", value = "single.factoryCode", sort = 3)
	public SingleInfo getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(SingleInfo factoryCode) {
		this.factoryCode = factoryCode;
	}

	@Id
	public ZhShuMasterModel getMaster() {
		return master;
	}

	public void setMaster(ZhShuMasterModel master) {
		this.master = master;
	}

	@Column(name = "jd_date")
	@ExcelField(title = "检定日期", sort = 4)
	public Date getJdDate() {
		return jdDate;
	}

	public void setJdDate(Date jdDate) {
		this.jdDate = jdDate;
	}

	@Column(name = "yx_Date")
	@ExcelField(title = "有效日期", sort = 5)
	public Date getYxDate() {
		return yxDate;
	}

	public void setYxDate(Date yxDate) {
		this.yxDate = yxDate;
	}

	@Column(name = "az_location")
	@ExcelField(title = "安装位置", sort = 6)
	public String getAzLocation() {
		return azLocation;
	}

	public void setAzLocation(String azLocation) {
		this.azLocation = azLocation;
	}

	@Column(name = "jmb_code")
	@ExcelField(title = "精密表编码", sort = 7)
	public String getJmbCode() {
		return jmbCode;
	}

	public void setJmbCode(String jmbCode) {
		this.jmbCode = jmbCode;
	}

	@Column(name = "zshu_code")
	@ExcelField(title = "证书编码", sort = 8)
	public String getzShuCode() {
		return zShuCode;
	}

	public void setzShuCode(String zShuCode) {
		this.zShuCode = zShuCode;
	}

	@Column(name = "zsyx_date")
	@ExcelField(title = "证书有效期", sort = 9)
	public Date getZsyxDate() {
		return zsyxDate;
	}

	public void setZsyxDate(Date zsyxDate) {
		this.zsyxDate = zsyxDate;
	}

	@Column(name = "shb_code")
	@ExcelField(title = "设备编码", sort = 10)
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

	@ExcelField(title = "备注信息", sort = 20)
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
