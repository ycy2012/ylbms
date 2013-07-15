package com.ylbms.base.single.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FilterJoinTable;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.alibaba.fastjson.annotation.JSONField;
import com.ylbms.base.location.model.Location;
import com.ylbms.common.model.BaseModel;

/**
 * 修改内容：修改主键策略
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-8
 * @editor: JackLiang
 * @date 2013-06-19 15:45:20
 */

@Entity
@Table(name = "ylbms_bas_single_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SingleInfo extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String mid;// 单件虚拟编号
	private String owercode;// 物资编码
	private String wzname;// 物资名称
	private SpectypeInfo spectype;// 规格型号
	private Location location;// 当前位置
	private StateInfo state;// 当前状态
	private int classId;// 资产种类
	private int factory;// 生产厂家
	private String factoryCode;// 出厂编号
	private Date jdtime;// 检测日期
	private String scunit;// 所属单位
	private Date yxTime;// 有效日期
	private Date bfTime;// 报废日期
	private Date qyTime;// 启用日期
	private int typeId;// 计量类别
	private Float zqd;// 精确度
	private int userTimes;// 使用次数
	private Float price;// 价格
	private String isnyqj;// 是否能源器具
	private Float clfw;// 测量范围
	private String isqj;// 是否强检
	private Date chcDate;// 出厂日期
	private String form;// ABC形式
	private String gdzcCode;// 固定资产编码
	private String txCode;// 条码号
	private String azLocation;// 安装位置
	private String isAnz;  // 是否安装  其中1表示安装了，0表示没有安装
	private Date grDate;// 购入日期
	private String shdw;// 四位定号
	private String other;// 其他指标
	private String creater;// 录入人员
	private Date createDate;// 录入时间
	private String status;// 状态信息
	private String remark;// 备注信息
	
	public SingleInfo() {
		this.status = DEL_FLAG_NORMAL;
		this.isAnz=DEL_FLAG_NORMAL;
	}

	public SingleInfo(String mid) {
		this.mid = mid;
	}

	// getter setter
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "com.ylbms.base.single.model.SingleInfoPK")
	@Column(nullable = false)
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	@Column(nullable = false)
	public String getOwercode() {
		return owercode;
	}

	public void setOwercode(String owercode) {
		this.owercode = owercode;
	}

	@Column(nullable = false)
	public String getWzname() {
		return wzname;
	}

	public void setWzname(String wzname) {
		this.wzname = wzname;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade ={CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "spectype", nullable = false, referencedColumnName = "speId")
	@NotFound(action = NotFoundAction.IGNORE)
	@Fetch(FetchMode.JOIN)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public SpectypeInfo getSpectype() {
		return spectype;
	}

	public void setSpectype(SpectypeInfo spectype) {
		this.spectype = spectype;
	}

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="location")
	@Fetch(FetchMode.JOIN)
	@NotFound(action=NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@ManyToOne
	@JoinColumn(name="state")
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull(message="归属部门不能为空")
	public StateInfo getState() {
		return state;
	}

	public void setState(StateInfo state) {
		this.state = state;
	}

	@Column(nullable = false)
	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	@Column(nullable = false)
	public int getFactory() {
		return factory;
	}

	public void setFactory(int factory) {
		this.factory = factory;
	}

	@Column(nullable = false)
	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	@Column(nullable = false,name="jd_time")
	public Date getJdtime() {
		return jdtime;
	}

	public void setJdtime(Date jdtime) {
		this.jdtime = jdtime;
	}

	@Column(nullable = false,name="sc_unit")
	public String getScunit() {
		return scunit;
	}

	public void setScunit(String scunit) {
		this.scunit = scunit;
	}

	@Column(nullable = false,name="yx_Time")
	public Date getYxTime() {
		return yxTime;
	}

	@Column(name="yx_Time")
	public void setYxTime(Date yxTime) {
		this.yxTime = yxTime;
	}

	@Column(name="bf_time")
	@JSONField(format = "yyyy-mm-dd")
	public Date getBfTime() {
		return bfTime;
	}

	public void setBfTime(Date bfTime) {
		this.bfTime = bfTime;
	}

	@Column(nullable = false,name="qy_Time")
	public Date getQyTime() {
		return qyTime;
	}

	public void setQyTime(Date qyTime) {
		this.qyTime = qyTime;
	}

	@Column(nullable = false,name="type_Id")
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Column(nullable = false)
	public Float getZqd() {
		return zqd;
	}

	public void setZqd(Float zqd) {
		this.zqd = zqd;
	}

	@Column(nullable = false)
	public int getUserTimes() {
		return userTimes;
	}

	public void setUserTimes(int userTimes) {
		this.userTimes = 0;
	}

	@Column(nullable = false)
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getIsnyqj() {
		return isnyqj;
	}

	public void setIsnyqj(String isnyqj) {
		this.isnyqj = isnyqj;
	}

	public Float getClfw() {
		return clfw;
	}

	public void setClfw(Float clfw) {
		this.clfw = clfw;
	}

	public String getIsqj() {
		return isqj;
	}

	public void setIsqj(String isqj) {
		this.isqj = isqj;
	}

	@Column(name="chc_Date")
	public Date getChcDate() {
		return chcDate;
	}

	public void setChcDate(Date chcDate) {
		this.chcDate = chcDate;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	@Column(name="gdzc_Code")
	public String getGdzcCode() {
		return gdzcCode;
	}

	public void setGdzcCode(String gdzcCode) {
		this.gdzcCode = gdzcCode;
	}

	@Column(name="tx_Code")
	public String getTxCode() {
		return txCode;
	}

	public void setTxCode(String txCode) {
		this.txCode = txCode;
	}

	@Column(name="az_Location")
	public String getAzLocation() {
		return azLocation;
	}

	public void setAzLocation(String azLocation) {
		this.azLocation = azLocation;
	}

	public String getIsAnz() {
		return isAnz;
	}

	public void setIsAnz(String isAnz) {
		this.isAnz = isAnz;
	}

	@Column(name="gr_Date")
	public Date getGrDate() {
		return grDate;
	}

	public void setGrDate(Date grDate) {
		this.grDate = grDate;
	}

	public String getShdw() {
		return shdw;
	}

	public void setShdw(String shdw) {
		this.shdw = shdw;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
