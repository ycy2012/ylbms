package com.ylbms.base.check.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ylbms.base.single.model.SingleInfo;

/**
 * 证书信息明细复合主键
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-29
 */
public class ZhShDetailIdPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private SingleInfo single;
	private ZhShuMasterModel master;

	public ZhShDetailIdPK() {
	}

	public ZhShDetailIdPK(SingleInfo single, ZhShuMasterModel master) {
		this.single = single;
		this.master = master;
	}

	// getter setter
	@ManyToOne
	@JoinColumn(name = "zId", nullable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public ZhShuMasterModel getMaster() {
		return master;
	}

	public void setMaster(ZhShuMasterModel master) {
		this.master = master;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "mid")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public SingleInfo getSingle() {
		return single;
	}

	public void setSingle(SingleInfo single) {
		this.single = single;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || (obj instanceof ZhShDetailIdPK == false)) {
			return false;
		}
		if (getClass() != obj.getClass())
			return false;

		if (this == obj) {
			return true;
		}
		ZhShDetailIdPK other = (ZhShDetailIdPK) obj;
		return new EqualsBuilder()
				.append(getSingle().getMid(), other.getSingle().getMid())
				.append(getMaster().getzId(), other.getMaster().getzId())
				.isEquals();
	}

}
