package com.ylbms.base.single.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.ylbms.common.model.BaseModel;

/**
 * 修改内容：修改主键策略
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
	private String spectype;// 规格型号
	private String location;// 当前位置
	private String state;// 当前状态
	private int classId;// 资产种类
	private int factory;// 生产厂家
	private String factoryCode;// 出厂编号
	private Date jd_time;// 检测日期
	private String sc_unit;// 所属单位
	private Date yx_Time;// 有效日期
	private Date bf_Time;// 报废日期
	private Date qy_Time;// 启用日期
	private int type_Id;// 计量类别
	private Float zqd;// 精确度
	private int userTimes;// 使用次数
	private Float price;// 价格
	private String isnyqj;// 是否能源器具
	private Float clfw;// 测量范围
	private String isqj;// 是否强检
	private Date chc_Date;// 出厂日期
	private String form;// ABC形式
	private String gdzc_Code;// 固定资产编码
	private String tx_Code;// 条码号
	private String az_Location;// 安装位置
	private Date gr_Date;// 购入日期
	private String shdw;// 四位定号
	private String other;// 其他指标
	private String creater;// 录入人员
	private Date createDate;// 录入时间
	private String status;// 状态信息
	private String remark;// 备注信息

	public SingleInfo() {
	}

	public SingleInfo(String mid) {
		this.mid = mid;
	}

	// getter setter
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_base_single_info")
//	@SequenceGenerator(name = "seq_base_single_info", sequenceName = "seq_base_single_info")
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

	@Column(nullable = false)
	public String getSpectype() {
		return spectype;
	}

	public void setSpectype(String spectype) {
		this.spectype = spectype;
	}

	@Column(nullable = false)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(nullable = false)
	public String getState() {
		return state;
	}

	public void setState(String state) {
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

	@Column(nullable = false)
	public Date getJd_time() {
		return jd_time;
	}

	public void setJd_time(Date jd_time) {
		this.jd_time = jd_time;
	}

	@Column(nullable = false)
	public String getSc_unit() {
		return sc_unit;
	}

	public void setSc_unit(String sc_unit) {
		this.sc_unit = sc_unit;
	}

	@Column(nullable = false)
	public Date getYx_Time() {
		return yx_Time;
	}

	public void setYx_Time(Date yx_Time) {
		this.yx_Time = yx_Time;
	}

	@Column(nullable = false)
	public Date getBf_Time() {
		return bf_Time;
	}

	public void setBf_Time(Date bf_Time) {
		this.bf_Time = bf_Time;
	}

	@Column(nullable = false)
	public Date getQy_Time() {
		return qy_Time;
	}

	public void setQy_Time(Date qy_Time) {
		this.qy_Time = qy_Time;
	}

	@Column(nullable = false)
	public int getType_Id() {
		return type_Id;
	}

	public void setType_Id(int type_Id) {
		this.type_Id = type_Id;
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

	public Date getChc_Date() {
		return chc_Date;
	}

	public void setChc_Date(Date chc_Date) {
		this.chc_Date = chc_Date;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getGdzc_Code() {
		return gdzc_Code;
	}

	public void setGdzc_Code(String gdzc_Code) {
		this.gdzc_Code = gdzc_Code;
	}

	public String getTx_Code() {
		return tx_Code;
	}

	public void setTx_Code(String tx_Code) {
		this.tx_Code = tx_Code;
	}

	public String getAz_Location() {
		return az_Location;
	}

	public void setAz_Location(String az_Location) {
		this.az_Location = az_Location;
	}

	public Date getGr_Date() {
		return gr_Date;
	}

	public void setGr_Date(Date gr_Date) {
		this.gr_Date = gr_Date;
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
