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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.alibaba.fastjson.annotation.JSONField;
import com.ylbms.base.location.model.TdmisLocationFullName;
import com.ylbms.common.model.BaseModel;
import com.ylbms.common.utils.excel.annotation.ExcelField;
import com.ylbms.system.model.Dict;

/**
 * 修改内容：修改主键策略
 * 
 * @author zhangjl
 * @version 1.0
 * @date 2013-6-8
 * @editor: JackLiang
 * @date 2013-06-19 15:45:20
 * @date 2013年8月7日 15:11:37 修改添加EXCEL导入导出注解
 */

@Entity
@Table(name = "ylbms_bas_single_info")
@DynamicUpdate(true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SingleInfo extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String mid;// 单件虚拟编号
	private String owercode;// 物资编码
	private String wzname;// 物资名称
	private SpectypeInfo spectype;// 规格型号
	private TdmisLocationFullName location;// 当前位置
	private StateInfo state;// 当前状态
	private Dict classId;// 资产种类
	private Dict factory;// 生产厂家
	private String factoryCode;// 出厂编号
	private Date jdtime;// 检测日期
	private String scunit;// 所属单位
	private Date yxTime;// 有效日期
	private Date bfTime;// 报废日期
	private Date qyTime;// 启用日期
	private Dict typeId;// 计量类别
	private Float zqd;// 精确度
	private Integer userTimes;// 使用次数
	private Float price;// 价格
	private String isnyqj;// 是否能源器具
	private Dict clfw;// 测量范围
	private String isqj;// 是否强检
	private String isCheck; // 是否有检测数据
	private Date chcDate;// 出厂日期
	private String form;// ABC形式
	private String gdzcCode;// 固定资产编码
	private String txCode;// 条码号
	private String azLocation;// 安装位置
	// private String isAnz; // 是否安装 其中1表示安装了，0表示没有安装
	private Date grDate;// 购入日期
	private String shdw;// 四位定号
	private String other;// 其他指标
	private String creater;// 录入人员
	private Date createDate;// 录入时间
	private String status;// 状态信息
	private String remark;// 备注信息

	public SingleInfo() {
		this.status = DEL_FLAG_NORMAL;
		this.isCheck = CHECKED_NO;
	}

	public SingleInfo(String mid) {
		this.mid = mid;
	}

	// getter setter
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "com.ylbms.base.single.model.SingleInfoPK")
	@NotNull(message = "主键信息不可以为空！")
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	@Column(nullable = false)
	@ExcelField(title = "物资自编码")
	public String getOwercode() {
		return owercode;
	}

	public void setOwercode(String owercode) {
		this.owercode = owercode;
	}

	@Column(nullable = false)
	@ExcelField(title = "物资名称", sort = 1)
	public String getWzname() {
		return wzname;
	}

	public void setWzname(String wzname) {
		this.wzname = wzname;
	}

	/**
	 * "规划型号信息
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "spectype", nullable = false, referencedColumnName = "speId")
	@Fetch(FetchMode.JOIN)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@ExcelField(title = "规划型号信息", value = "spectype.speName", sort = 2)
	public SpectypeInfo getSpectype() {
		return spectype;
	}

	public void setSpectype(SpectypeInfo spectype) {
		this.spectype = spectype;
	}

	/**
	 * 当前位置
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "location")
	@Fetch(FetchMode.JOIN)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@ExcelField(title = "当前位置", align = 3, sort = 3, value = "location.fullName")
	public TdmisLocationFullName getLocation() {
		return location;
	}

	public void setLocation(TdmisLocationFullName location) {
		this.location = location;
	}

	/**
	 * 状态信息映射
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull(message = "状态信息不能为空")
	@ExcelField(title = "当前状态", sort = 4, value = "state.stateName")
	public StateInfo getState() {
		return state;
	}

	public void setState(StateInfo state) {
		this.state = state;
	}

	/**
	 * 资产种类映射
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classId", referencedColumnName = "id")
	@Where(clause = "type='class_type'")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@ExcelField(title = "资产种类", sort = 5, value = "classId.value")
	public Dict getClassId() {
		return classId;
	}

	public void setClassId(Dict classId) {
		this.classId = classId;
	}

	/**
	 * 字典关系映射
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "factory", referencedColumnName = "id")
	@Where(clause = "type='factory_type'")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@ExcelField(title = "生产厂家", value = "factory.value", sort = 6)
	public Dict getFactory() {
		return factory;
	}

	public void setFactory(Dict factory) {
		this.factory = factory;
	}

	@Column
	@ExcelField(title = "出厂编码", sort = 7)
	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	@Column(nullable = false, name = "jd_time")
	@JSONField(format = "yyyy-mm-dd")
	@ExcelField(title = "检定日期", type = 1, sort = 8)
	public Date getJdtime() {
		return jdtime;
	}

	public void setJdtime(Date jdtime) {
		this.jdtime = jdtime;
	}

	@Column(nullable = false, name = "sc_unit")
	@ExcelField(title = "所属单位", sort = 9, type = 2)
	public String getScunit() {
		return scunit;
	}

	public void setScunit(String scunit) {
		this.scunit = scunit;
	}

	@Column(nullable = false, name = "yx_Time")
	@JSONField(format = "yyyy-mm-dd")
	@Temporal(TemporalType.DATE)
	@ExcelField(title = "有效日期", sort = 10)
	public Date getYxTime() {
		return yxTime;
	}

	@Column(name = "yx_Time")
	public void setYxTime(Date yxTime) {
		this.yxTime = yxTime;
	}

	/**
	 * 报废日期
	 * 
	 * @return
	 */
	@Column(name = "bf_time")
	@Temporal(TemporalType.DATE)
	@JSONField(format = "yyyy-mm-dd")
	public Date getBfTime() {
		return bfTime;
	}

	public void setBfTime(Date bfTime) {
		this.bfTime = bfTime;
	}

	@Column(nullable = false, name = "qy_Time")
	@JSONField(format = "yyyy-mm-dd")
	public Date getQyTime() {
		return qyTime;
	}

	public void setQyTime(Date qyTime) {
		this.qyTime = qyTime;
	}

	/**
	 * 计量类别
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_Id", referencedColumnName = "id")
	@Where(clause = "type='jlnb_type'")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Dict getTypeId() {
		return typeId;
	}

	public void setTypeId(Dict typeId) {
		this.typeId = typeId;
	}

	/**
	 * 精确度
	 * 
	 * @return
	 */
	@Column(nullable = false)
	@ExcelField(title = "精确度", type = 2, sort = 11)
	public Float getZqd() {
		return zqd;
	}

	public void setZqd(Float zqd) {
		this.zqd = zqd;
	}

	@Column(nullable = false)
	@ExcelField(title = "使用次数", sort = 12, type = 1)
	public Integer getUserTimes() {
		return userTimes;
	}

	public void setUserTimes(Integer userTimes) {
		this.userTimes = 0;
	}

	/**
	 * 
	 * 价格
	 * 
	 * @return
	 */
	@Column(nullable = false)
	@ExcelField(title = "价格", sort = 12)
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * 是否能源器具
	 * 
	 * @return
	 */
	public String getIsnyqj() {
		return isnyqj;
	}

	public void setIsnyqj(String isnyqj) {
		this.isnyqj = isnyqj;
	}

	/**
	 * 测量范围MPa
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clfw", referencedColumnName = "id")
	@Where(clause = "type='clfw_type'")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@ExcelField(title = "测量范围MPa", sort = 13,value="clfw.value")
	public Dict getClfw() {
		return clfw;
	}

	public void setClfw(Dict clfw) {
		this.clfw = clfw;
	}

	public String getIsqj() {
		return isqj;
	}

	public void setIsqj(String isqj) {
		this.isqj = isqj;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	/**
	 * 出厂日期
	 * 
	 * @return
	 */
	@Column(name = "chc_Date")
	@ExcelField(title = "出厂日期", type = 2, sort = 14)
	public Date getChcDate() {
		return chcDate;
	}

	public void setChcDate(Date chcDate) {
		this.chcDate = chcDate;
	}

	/**
	 * ABC形式
	 * 
	 * @return
	 */
	@ExcelField(title = "形式(ABC)", sort = 15)
	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	/**
	 * 固定资产编码
	 * 
	 * @return
	 */
	@Column(name = "gdzc_Code")
	@ExcelField(title = "固定资产编码", sort = 16)
	public String getGdzcCode() {
		return gdzcCode;
	}

	public void setGdzcCode(String gdzcCode) {
		this.gdzcCode = gdzcCode;
	}

	@Column(name = "tx_Code")
	public String getTxCode() {
		return txCode;
	}

	public void setTxCode(String txCode) {
		this.txCode = txCode;
	}

	/**
	 * 安装位置
	 * 
	 * @return
	 */
	@Column(name = "az_Location")
	@ExcelField(title = "安装位置", type = 1, sort = 17)
	public String getAzLocation() {
		return azLocation;
	}

	public void setAzLocation(String azLocation) {
		this.azLocation = azLocation;
	}

	// public String getIsAnz() {
	// return isAnz;
	// }
	//
	// public void setIsAnz(String isAnz) {
	// this.isAnz = isAnz;
	// }

	/**
	 * 购入日期
	 * 
	 * @return
	 */
	@Column(name = "gr_Date")
	@ExcelField(title = "购入日期", type = 2, sort = 18)
	public Date getGrDate() {
		return grDate;
	}

	public void setGrDate(Date grDate) {
		this.grDate = grDate;
	}

	/**
	 * 四位定号
	 * 
	 * @return
	 */
	public String getShdw() {
		return shdw;
	}

	public void setShdw(String shdw) {
		this.shdw = shdw;
	}

	/**
	 * 其他
	 * 
	 * @return
	 */
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

	/**
	 * 备注信息
	 * 
	 * @return
	 */
	@ExcelField(title = "备注信息", sort = 25)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
