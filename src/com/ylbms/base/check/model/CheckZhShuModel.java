package com.ylbms.base.check.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ylbms.common.model.BaseModel;
import com.ylbms.system.model.User;

/**
 * 检定证书实体类
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-24
 */
@Entity
@Table(name = "ylbms_jc_zhshInfo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CheckZhShuModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	private Long zId; // ID
	private String zCode; // 证书编码
	private String zTitle;// 证书名称
	private String sjUnit; // 送检单位
	private String basis;// 检定依据
	private String result;// 结论
	private String pzren;// 批准人
	private String veriRen;// 核验员
	private String jdRen;// 检定人员
	private Date jdDate; //
	private Date yxDate; //
	private JmylbModel jmbInfo; // 精密表信息
	private Date createDate;
	private User createUser;
	private String status;
	private String remark;

	public CheckZhShuModel() {
		this.status = DEL_FLAG_NORMAL;
	}

	// getter setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_jd_zhshInfo")
	@SequenceGenerator(name = "seq_jd_zhshInfo", sequenceName = "seq_jd_zhshInfo")
	public Long getzId() {
		return zId;
	}

	public void setzId(Long zId) {
		this.zId = zId;
	}

	public String getzCode() {
		return zCode;
	}

	public void setzCode(String zCode) {
		this.zCode = zCode;
	}

	public String getzTitle() {
		return zTitle;
	}

	public void setzTitle(String zTitle) {
		this.zTitle = zTitle;
	}

	public String getSjUnit() {
		return sjUnit;
	}

	public void setSjUnit(String sjUnit) {
		this.sjUnit = sjUnit;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPzren() {
		return pzren;
	}

	public void setPzren(String pzren) {
		this.pzren = pzren;
	}

	public String getVeriRen() {
		return veriRen;
	}

	public void setVeriRen(String veriRen) {
		this.veriRen = veriRen;
	}

	public String getJdRen() {
		return jdRen;
	}

	public void setJdRen(String jdRen) {
		this.jdRen = jdRen;
	}

	@Column(name = "jd_date")
	public Date getJdDate() {
		return jdDate;
	}

	public void setJdDate(Date jdDate) {
		this.jdDate = jdDate;
	}

	@Column(name = "yx_Date")
	public Date getYxDate() {
		return yxDate;
	}

	public void setYxDate(Date yxDate) {
		this.yxDate = yxDate;
	}

	@Column(name = "jmb_Code", nullable = false)
	public JmylbModel getJmbInfo() {
		return jmbInfo;
	}

	public void setJmbInfo(JmylbModel jmbInfo) {
		this.jmbInfo = jmbInfo;
	}

	@Column(name = "create_Date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "create_user")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
