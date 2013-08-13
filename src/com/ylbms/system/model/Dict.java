package com.ylbms.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.ylbms.common.model.BaseModel;

/**
 * 字典Entity
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-4
 */
@Entity
@Table(name = "ylbms_sys_dict")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dict extends BaseModel {

	private static final long serialVersionUID = 1L;
	private Long id; // 编号
	private String label; // 值
	private String value; // 键
	private String type; // 类型
	private String desciption;// 描述
	private Integer sort; // 排序
	private String delFlag; // 删除标记（0：正常；1：删除）

	public Dict() {
		this.delFlag = DEL_FLAG_NORMAL;
	}

	public Dict(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sys_dict")
	@SequenceGenerator(name = "seq_sys_dict", sequenceName = "seq_sys_dict", allocationSize = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Length(min = 1, max = 100)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Length(min = 1, max = 100)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Length(min = 1, max = 100)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min = 0, max = 100)
	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	@NotNull(message = "序号不能为空")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Length(min = 1, max = 1)
	@Column(name = "del_flag")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}