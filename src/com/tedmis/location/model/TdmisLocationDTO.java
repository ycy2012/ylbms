package com.tedmis.location.model;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * location treeDTO
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-8-19
 */
public class TdmisLocationDTO implements Serializable {

	private static final long serialVersionUID = -1451840926586226668L;
	private String wzId; // 位置编号
	private String wzName; // 位置名称
	private String leftPoint; // 左指针
	private String rightPoint; // 右指针
	private String wellName; // 井号
	private String kNumber; // 勘号
	private String teamName; // 队号
	private String wzCc; // 位置层次

	private List<TdmisLocationDTO> childList = Lists.newArrayList();

	//
	public TdmisLocationDTO() {
	}

	public TdmisLocationDTO(String wzId) {
		this.wzId = wzId;
	}

	// setter getter
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

	public String getLeftPoint() {
		return leftPoint;
	}

	public void setLeftPoint(String leftPoint) {
		this.leftPoint = leftPoint;
	}

	public String getRightPoint() {
		return rightPoint;
	}

	public void setRightPoint(String rightPoint) {
		this.rightPoint = rightPoint;
	}

	public String getWellName() {
		return wellName;
	}

	public void setWellName(String wellName) {
		this.wellName = wellName;
	}

	public String getkNumber() {
		return kNumber;
	}

	public void setkNumber(String kNumber) {
		this.kNumber = kNumber;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getWzCc() {
		return wzCc;
	}

	public void setWzCc(String wzCc) {
		this.wzCc = wzCc;
	}

	public List<TdmisLocationDTO> getChildList() {
		return childList;
	}

	public void setChildList(List<TdmisLocationDTO> childList) {
		this.childList = childList;
	}

}
