package com.ylbms.base.check.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.ylbms.common.model.BaseModel;
import com.ylbms.system.model.User;

/**
 * 检定证书实体类
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-24
 */
//@Entity
//@Table(name = "ylbms_jc_zhshInfo")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CheckZhShuModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String zId; // 证书编号
	private String zTitle;// 证书名称
	private String sjUnit; // 送检单位
	private String basis;// 检定依据
	private String result;// 结论
	private User pzren;// 批准人
	private User veriRen;// 核验员
	private User jdRen;// 检定人员
	private Date jdDate;
	private Date yxDate;
	

}
