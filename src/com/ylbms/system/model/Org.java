package com.ylbms.system.model;

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

import com.ylbms.common.model.BaseModel;

@Entity
@Table(name = "ylbms_sys_ORG")
public class Org extends BaseModel {
	private static final long serialVersionUID = 7297765946510001885L;

	public static final Long ROOT_ORG_ID = 0l;

	private Long id;

	private Org parentOrg;

	private String name;

	private String active;

	private String fullname;

	private String description;

	private String type;

	private Set<User> users = new HashSet<User>();

	private Set<Org> orgs = new HashSet<Org>();

	public Org() {
	}

	public Org(Long id) {
		this.id = id;
	}

	// getter setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_sys_org", sequenceName = "seq_sys_org",allocationSize=1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "org_name", unique = false, nullable = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "active", unique = false, nullable = true)
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Column(name = "fullname", unique = false, nullable = true)
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "description", unique = false, nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "type", unique = false, nullable = true)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(mappedBy = "org", cascade = { CascadeType.REFRESH,
			CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(mappedBy = "parentOrg", cascade = CascadeType.ALL)
	public Set<Org> getOrgs() {
		return orgs;
	}

	public void setOrgs(Set<Org> orgs) {
		this.orgs = orgs;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentOrg", nullable = true)
	public Org getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(Org parentOrg) {
		this.parentOrg = parentOrg;
	}
}
