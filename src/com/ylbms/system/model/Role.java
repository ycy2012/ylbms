/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ylbms.system.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.ylbms.common.model.BaseModel;

/**
 * 角色Entity
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-3
 */
@Entity
@Table(name = "ylbms_sys_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends BaseModel {

	private static final long serialVersionUID = 1L;
	private Long id; // 编号
	private String name; // 角色名称
	private User user; // 创建者
	private String delFlag; // 删除标记（0：正常；1：删除）

	private List<User> userList = Lists.newArrayList(); // 拥有用户列表
	private List<Menu> menuList = Lists.newArrayList(); // 拥有菜单列表

	// 构造函数
	public Role() {
		this.delFlag = DEL_FLAG_NORMAL;
	}

	public Role(Long id) {
		this.id = id;
	}

	public Role(Long id, String name) {
		this();
		this.id = id;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sys_role")
	@SequenceGenerator(name = "seq_sys_role", sequenceName = "seq_sys_role",allocationSize=1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Length(min = 1, max = 1)
	@Column(name = "del_flag")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ylbms_sys_user_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	@Where(clause = "status='" + DEL_FLAG_NORMAL + "'")
	@OrderBy("id")
	@Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ylbms_sys_role_menu", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id") })
	@Where(clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@OrderBy("id")
	@Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	/**
	 * 获取菜单Id列表
	 */
	@Transient
	public List<Long> getMenuIdList() {
		List<Long> menuIdList = Lists.newArrayList();
		for (Menu menu : menuList) {
			menuIdList.add(menu.getId());
		}
		return menuIdList;
	}

	@Transient
	public void setMenuIdList(List<Long> menuIdList) {
		menuList = Lists.newArrayList();
		for (Long menuId : menuIdList) {
			Menu menu = new Menu();
			menu.setId(menuId);
			menuList.add(menu);
		}
	}

	@Transient
	public String getMenuIds() {
		List<Long> nameIdList = Lists.newArrayList();
		for (Menu menu : menuList) {
			nameIdList.add(menu.getId());
		}
		return StringUtils.join(nameIdList, ",");
	}

	@Transient
	public void setMenuIds(String menuIds) {
		menuList = Lists.newArrayList();
		if (menuIds != null) {
			String[] ids = StringUtils.split(menuIds, ",");
			for (String menuId : ids) {
				Menu menu = new Menu();
				menu.setId(new Long(menuId));
				menuList.add(menu);
			}
		}
	}

	/**
	 * 获取权限字符串列表
	 */
	@Transient
	public List<String> getPermissions() {
		List<String> permissions = Lists.newArrayList();
		for (Menu menu : menuList) {
			if (menu.getPermission() != null
					&& !"".equals(menu.getPermission())) {
				permissions.add(menu.getPermission());
			}
		}
		return permissions;
	}

	@Transient
	public boolean isAdmin() {
		return isAdmin(this.id);
	}

	@Transient
	public static boolean isAdmin(Long id) {
		return id != null && id.equals(1L);
	}
}
