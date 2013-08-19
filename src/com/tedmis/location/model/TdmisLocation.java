package com.tedmis.location.model;

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
//@Entity
//@Table(name = "tetdmis.bas_位置表")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdmisLocation extends BaseModel {

	private static final long serialVersionUID = 1L;
	private String wzId; // 位置编号
	private String wzName; // 位置名称
	private String isWj; // 是否完井
	private String leftPoint; // 左指针
	private String rightPoint; // 右指针
	private String wellName; // 井号
	private String kNumber; // 勘号
	private String teamName; // 队号
	private Long sjJs; // 设计井深
	private Date kzDate; // 开钻日期
	private String njtx; // 泥浆体系
	private Date wzDate; // 完钻日期
	private String h2s; // H2S含量
	private Long wzJs; // 完钻井深
	private String wzCc; // 位置层次

	public TdmisLocation(String wzId) {
		this.wzId = wzId;
	}

	// getter setter

	@Id
	@Column(name = "位置编号")
	public String getWzId() {
		return wzId;
	}

	public void setWzId(String wzId) {
		this.wzId = wzId;
	}

	@Column(name = "位置名称")
	public String getWzName() {
		return wzName;
	}

	public void setWzName(String wzName) {
		this.wzName = wzName;
	}

	@Column(name = "是否完井")
	public String getIsWj() {
		return isWj;
	}

	public void setIsWj(String isWj) {
		this.isWj = isWj;
	}

	@Column(name = "左指针")
	public String getLeftPoint() {
		return leftPoint;
	}

	public void setLeftPoint(String leftPoint) {
		this.leftPoint = leftPoint;
	}

	@Column(name = "右指针")
	public String getRightPoint() {
		return rightPoint;
	}

	public void setRightPoint(String rightPoint) {
		this.rightPoint = rightPoint;
	}

	@Column(name = "井号")
	public String getWellName() {
		return wellName;
	}

	public void setWellName(String wellName) {
		this.wellName = wellName;
	}

	@Column(name = "勘号")
	public String getkNumber() {
		return kNumber;
	}

	public void setkNumber(String kNumber) {
		this.kNumber = kNumber;
	}

	@Column(name = "队号")
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Column(name = "设计井深")
	public Long getSjJs() {
		return sjJs;
	}

	public void setSjJs(Long sjJs) {
		this.sjJs = sjJs;
	}

	@Column(name = "开钻日期")
	public Date getKzDate() {
		return kzDate;
	}

	public void setKzDate(Date kzDate) {
		this.kzDate = kzDate;
	}

	@Column(name = "泥浆体系")
	public String getNjtx() {
		return njtx;
	}

	public void setNjtx(String njtx) {
		this.njtx = njtx;
	}

	@Column(name = "完钻日期")
	public Date getWzDate() {
		return wzDate;
	}

	public void setWzDate(Date wzDate) {
		this.wzDate = wzDate;
	}

	@Column(name = "H2S含量")
	public String getH2s() {
		return h2s;
	}

	public void setH2s(String h2s) {
		this.h2s = h2s;
	}

	@Column(name = "完钻井深")
	public Long getWzJs() {
		return wzJs;
	}

	public void setWzJs(Long wzJs) {
		this.wzJs = wzJs;
	}

	@Column(name = "位置层次")
	public String getWzCc() {
		return wzCc;
	}

	public void setWzCc(String wzCc) {
		this.wzCc = wzCc;
	}

}
