package com.ylbms.base.bill.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.ylbms.common.model.BaseModel;
import com.ylbms.system.utils.UserUtils;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-14
 */
@Entity
@Table(name = "ylbms_dj_thead")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BillHeadModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String djId;

	private String djTitle;

	private String sendLocation;

	private String acceptLocation;

	private Date sxDate; // 生效日期

	private Date createDate; // 创建日期
	
	private String createUser;

	private String llUnit;

	private String llren; // 领料人

	private String billType;

	private String remark;

	private String status;

	private String tempS1;

	private String tempS2;

	private Long tempN1;

	private Long tempN2;

	private Long tempN3;

	private Date tempD1;

	private Date tempD2;

	private List<BillTbodyModel> billTbody;

	public BillHeadModel() {
		this.status=DEL_FLAG_NORMAL;
	}

	public BillHeadModel(String djId) {
		this.djId = djId;
	}

	// getter setter
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "com.ylbms.base.bill.model.IncrementGenerator")
	@Column(name = "dj_id", nullable = false)
	public String getDjId() {
		return djId;
	}

	public void setDjId(String djId) {
		this.djId = djId;
	}

	@Column(name = "dj_title")
	public String getDjTitle() {
		return djTitle;
	}

	public void setDjTitle(String djTitle) {
		this.djTitle = djTitle;
	}

	public String getSendLocation() {
		return sendLocation;
	}

	public void setSendLocation(String sendLocation) {
		this.sendLocation = sendLocation;
	}

	public String getAcceptLocation() {
		return acceptLocation;
	}

	public void setAcceptLocation(String acceptLocation) {
		this.acceptLocation = acceptLocation;
	}

	@Column(name = "sx_Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getSxDate() {
		return sxDate;
	}

	public void setSxDate(Date sxDate) {
		this.sxDate = sxDate;
	}

	@Column(name = "create_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name="create_user")
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser=UserUtils.getUser().getFullname();
	}

	public String getLlUnit() {
		return llUnit;
	}

	public void setLlUnit(String llUnit) {
		this.llUnit = llUnit;
	}

	public String getLlren() {
		return llren;
	}

	public void setLlren(String llren) {
		this.llren = llren;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
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
		this.status = DEL_FLAG_NORMAL;
	}

	@Column(name = "temp_s1")
	public String getTempS1() {
		return tempS1;
	}

	public void setTempS1(String tempS1) {
		this.tempS1 = tempS1;
	}

	@Column(name = "temp_s2")
	public String getTempS2() {
		return tempS2;
	}

	public void setTempS2(String tempS2) {
		this.tempS2 = tempS2;
	}

	@Column(name = "temp_n1")
	public Long getTempN1() {
		return tempN1;
	}

	public void setTempN1(Long tempN1) {
		this.tempN1 = tempN1;
	}

	@Column(name = "temp_n2")
	public Long getTempN2() {
		return tempN2;
	}

	public void setTempN2(Long tempN2) {
		this.tempN2 = tempN2;
	}

	@Column(name = "temp_n3")
	public Long getTempN3() {
		return tempN3;
	}

	public void setTempN3(Long tempN3) {
		this.tempN3 = tempN3;
	}

	@Column(name = "temp_D1")
	public Date getTempD1() {
		return tempD1;
	}

	public void setTempD1(Date tempD1) {
		this.tempD1 = tempD1;
	}

	@Column(name = "temp_D2")
	public Date getTempD2() {
		return tempD2;
	}

	public void setTempD2(Date tempD2) {
		this.tempD2 = tempD2;
	}

	@OneToMany(mappedBy = "billId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<BillTbodyModel> getBillTbody() {
		return billTbody;
	}

	public void setBillTbody(List<BillTbodyModel> billTbody) {
		this.billTbody = billTbody;
	}

}
