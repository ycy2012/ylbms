package com.ylbms.base.location.model;

import java.util.Date;
import java.util.List;

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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.ylbms.common.model.BaseModel;
import com.ylbms.system.model.User;

/**
 * location 实体类
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-6
 */
@Entity
@Table(name = "ylbms_base_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Location extends BaseModel {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Location parent;

	private String parentIds;

	private String locationName; // 当前的信息

	private String allName;// 全称信息
	
	private Date createDate;

	private User user; // 添加人员信息

	private int sort; // order信息

	private String status; // 状态信息

	private List<Location> childList = Lists.newArrayList();// 拥有子菜单列表

	// 构造
	public Location() {
		this.status = DEL_FLAG_NORMAL;
	}

	public Location(Long id) {
		this.id = id;
	}

	// getter setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BASE_LOCATION")
	@SequenceGenerator(name = "SEQ_BASE_LOCATION", sequenceName = "SEQ_BASE_LOCATION")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Location getParent() {
		return parent;
	}

	public void setParent(Location parent) {
		this.parent = parent;
	}

	@Length(min = 1, max = 255)
	@Column(name = "PARENT_IDS")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@Column(name = "current_name", unique = true)
	@NotNull
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Column(name="ALL_Name")
	public String getAllName() {
		return allName;
	}

	public void setAllName(String allName) {
		this.allName = allName;
	}

	@Column(name = "create_date", unique = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Length(min = 1, max = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = DEL_FLAG_NORMAL;
	}

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "parent")
	@Where(clause = "status='" + DEL_FLAG_NORMAL + "'")
	@OrderBy(value = "sort")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Location> getChildList() {
		return childList;
	}

	public void setChildList(List<Location> childList) {
		this.childList = childList;
	}

}
