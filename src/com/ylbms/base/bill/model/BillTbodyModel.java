package com.ylbms.base.bill.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ylbms.base.single.model.SingleInfo;
import com.ylbms.common.model.BaseModel;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-14
 */
@Entity
@IdClass(BillTbodyPK.class)
// 复合主键
@Table(name = "ylbms_dj_detail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BillTbodyModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	private SingleInfo mid;

	private BillHeadModel billId;

	private String oldState;

	private String newState;

	private String oldWz;

	private String newWz;

	private String remark;

	private String tempS1;

	private String tempS2;

	private String tempS3;

	private Long tempN1;

	private Long tempN2;

	private Long tempN3;

	private Date tempD1;

	private Date tempD2;
	
	

	// 构造函数
	public BillTbodyModel() {
	}

	// setter getter
	
	
	@Id
	public SingleInfo getMid() {
		return mid;
	}

	public void setMid(SingleInfo mid) {
		this.mid = mid;
	}
	@Id
	public BillHeadModel getBillId() {
		return billId;
	}

	public void setBillId(BillHeadModel billId) {
		this.billId = billId;
	}

	@Column(name = "OLD_STATE")
	public String getOldState() {
		return oldState;
	}

	public void setOldState(String oldState) {
		this.oldState = oldState;
	}

	@Column(name = "new_state")
	public String getNewState() {
		return newState;
	}

	public void setNewState(String newState) {
		this.newState = newState;
	}

	@Column(name = "old_LOCATION")
	public String getOldWz() {
		return oldWz;
	}

	public void setOldWz(String oldWz) {
		this.oldWz = oldWz;
	}

	@Column(name = "NEW_LOCATION")
	public String getNewWz() {
		return newWz;
	}

	public void setNewWz(String newWz) {
		this.newWz = newWz;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Column(name = "temp_s3")
	public String getTempS3() {
		return tempS3;
	}

	public void setTempS3(String tempS3) {
		this.tempS3 = tempS3;
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

}
