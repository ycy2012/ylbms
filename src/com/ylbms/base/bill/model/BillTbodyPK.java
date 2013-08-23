package com.ylbms.base.bill.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
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

	private static final long serialVersionUID = 1L;

	private SingleInfo mid;
	private BillHeadModel billId;

	public BillTbodyPK() {
	}

	public BillTbodyPK(SingleInfo mid, BillHeadModel billId) {
		this.mid = mid;
		this.billId = billId;
	}

	// setter getter
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(name = "mid")
	@NotFound(action=NotFoundAction.EXCEPTION)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public SingleInfo getMid() {
		return mid;
	}

	public void setMid(SingleInfo mid) {
		this.mid = mid;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "dj_id")
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public BillHeadModel getBillId() {
		return billId;
	}

	public void setBillId(BillHeadModel billId) {
		this.billId = billId;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
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
		if (mid == null || other.mid != null && !mid.equals(other.mid)) {
			return false;
		}
		if (billId == null || other.billId != null
				&& !billId.equals(other.billId)
				|| (!billId.getDjId().equals(other.getBillId().getDjId()))) {
			return false;
		}

		return true;
	}
}
