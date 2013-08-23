package com.ylbms.base.location.model;

import java.util.Date;

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
 * @date 2013-8-16
 */
 @Entity
 @Table(name = "TDMIS_LOCATION")
 @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdmisLocation extends BaseModel {

	private static final long serialVersionUID = 1L;
	private String wzId; // 位置编号
	private String wzName; // 位置名称
	private String isWj; // 是否完井
	private String leftPointer; // 左指针
	private String rightPointer; // 右指针
	private String wellName; // 井号
	private String kName; // 勘号
	private String teamName; // 队号
	private Long sjJs; // 设计井深
	private Date kzDate; // 开钻日期
	private String njtx; // 泥浆体系
	private Date wzDate; // 完钻日期
	private String h2s; // H2S含量
	private Long wzJs; // 完钻井深
	private String wzCc; // 位置层次

	public TdmisLocation() {
	}

	public TdmisLocation(String wzId) {
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

	public String getWzName() {
		return wzName;
	}

	public void setWzName(String wzName) {
		this.wzName = wzName;
	}

	public String getIsWj() {
		return isWj;
	}

	public void setIsWj(String isWj) {
		this.isWj = isWj;
	}

	public String getLeftPointer() {
		return leftPointer;
	}

	public void setLeftPointer(String leftPointer) {
		this.leftPointer = leftPointer;
	}

	public String getRightPointer() {
		return rightPointer;
	}

	public void setRightPointer(String rightPointer) {
		this.rightPointer = rightPointer;
	}

	public String getWellName() {
		return wellName;
	}

	public void setWellName(String wellName) {
		this.wellName = wellName;
	}

	public String getkName() {
		return kName;
	}

	public void setkName(String kName) {
		this.kName = kName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Long getSjJs() {
		return sjJs;
	}

	public void setSjJs(Long sjJs) {
		this.sjJs = sjJs;
	}

	public Date getKzDate() {
		return kzDate;
	}

	public void setKzDate(Date kzDate) {
		this.kzDate = kzDate;
	}

	public String getNjtx() {
		return njtx;
	}

	public void setNjtx(String njtx) {
		this.njtx = njtx;
	}

	public Date getWzDate() {
		return wzDate;
	}

	public void setWzDate(Date wzDate) {
		this.wzDate = wzDate;
	}

	public String getH2s() {
		return h2s;
	}

	public void setH2s(String h2s) {
		this.h2s = h2s;
	}

	public Long getWzJs() {
		return wzJs;
	}

	public void setWzJs(Long wzJs) {
		this.wzJs = wzJs;
	}

	public String getWzCc() {
		return wzCc;
	}

	public void setWzCc(String wzCc) {
		this.wzCc = wzCc;
	}

}
