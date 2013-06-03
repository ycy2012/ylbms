package com.ylbms.system.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.alibaba.fastjson.annotation.JSONField;
import com.ylbms.common.model.BaseModel;
import com.ylbms.common.orm.IdEntity;

@Entity
@Table(name = "ylbms_sys_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Integer TYPE_ADMIN = 0;

	public static final Integer TYPE_GENERAL = 1;

	private Long id;

	private String loginName;

	private String password;

	private String fullname;

	private String usertype; // 0为管理员

	private Date createDate;

	private String loginIP;

	private Date loginDate;

	private String enabled; // 1 0

	private String remark;

	private Org org;

	private Set<Role> roles = new HashSet<Role>();

	private Set<Authority> authorities = new HashSet<Authority>();

	public User() {
	}

	public User(Integer usertype, String enabled) {
		this.usertype = NO;
		this.enabled = DEL_FLAG_NORMAL;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sys_user")
	@SequenceGenerator(name = "seq_sys_user", sequenceName = "seq_sys_user")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "login_name", unique = true, nullable = false)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "password", unique = false, nullable = true)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "userName", unique = false, nullable = true)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "create_date", unique = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "login_ip", unique = false, nullable = true)
	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	@Column(name = "login_date", unique = false, nullable = true)
	@JSONField(format = "yyyy-mm-dd")
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	@Column(name = "status", unique = false, nullable = true)
	public String getEnabled() {
		return enabled == null ? "1" : enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@ManyToMany
	@JoinTable(name = "ylbms_sys_ROLE_USER", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	@Fetch(FetchMode.SUBSELECT)
	public Set<Role> getRoles() {
		if (roles == null) {
			roles = new HashSet<Role>();
		}
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToMany
	@JoinTable(name = "ylbms_sys_USER_AUTHORITY", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	@Fetch(FetchMode.SUBSELECT)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Authority> getAuthorities() {
		if (authorities == null) {
			authorities = new HashSet<Authority>();
		}
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	@ManyToOne
	@JoinColumn(name = "org", nullable = true)
	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	@Column(name = "user_type", unique = false, nullable = true)
	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	@Column(name = "remark", nullable = true, length = 50)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Transient
	public boolean isAdmin() {
		return isAdmin(this.usertype);
	}

	@Transient
	public static boolean isAdmin(String userType) {
		return userType != null && userType.equals("0");
	}
}