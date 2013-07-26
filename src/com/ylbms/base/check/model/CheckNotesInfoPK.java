package com.ylbms.base.check.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ylbms.base.single.model.SingleInfo;

/**
 * 检定记录信息自定义主键
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-17
 */
public class CheckNotesInfoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private SingleInfo single;
	private CheckNotes checkNotes;

	// setter getter
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "mid")
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public SingleInfo getSingle() {
		return single;
	}

	public void setSingle(SingleInfo single) {
		this.single = single;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.REMOVE })
	@JoinColumn(name = "jd_id",nullable=false)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public CheckNotes getCheckNotes() {
		return checkNotes;
	}

	public void setCheckNotes(CheckNotes checkNotes) {
		this.checkNotes = checkNotes;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || (obj instanceof CheckNotesInfoPK == false)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		CheckNotesInfoPK other = (CheckNotesInfoPK) obj;
		if (single == null || other != null && !single.equals(other.single)) {
			return false;
		}
		if (checkNotes == null || other.checkNotes != null
				&& !checkNotes.equals(other.checkNotes)) {
			return false;
		}
		return true;
	}
}
