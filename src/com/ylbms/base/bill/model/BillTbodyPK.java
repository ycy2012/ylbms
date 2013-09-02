package com.ylbms.base.bill.model;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ylbms.base.single.model.SingleInfo;

/**
 * 单据单件明细之复合主键
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-6-19
 */
public class BillTbodyPK implements Serializable {

	private static final long serialVersionUID = -1190986010439330142L;

	private SingleInfo mid;
	private BillHeadModel billId;

	public BillTbodyPK() {
	}

	// setter getter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mid", referencedColumnName = "mid", unique = false, nullable = false, insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public SingleInfo getMid() {
		return mid;
	}

	public void setMid(SingleInfo mid) {
		this.mid = mid;
	}

	@ManyToOne
	@JoinColumn(name = "dj_id", nullable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public BillHeadModel getBillId() {
		return billId;
	}

	public void setBillId(BillHeadModel billId) {
		this.billId = billId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mid == null) ? 0 : mid.hashCode());
		result = prime * result + ((billId == null) ? 0 : billId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || (obj instanceof BillTbodyPK == false)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		BillTbodyPK other = (BillTbodyPK) obj;
		if (mid == null && other.mid != null || !mid.equals(other.mid)) {
			return false;
		}
		if (billId == null && other.billId != null
				|| !billId.equals(other.billId)) {
			return false;
		}

		return true;
	}
}
