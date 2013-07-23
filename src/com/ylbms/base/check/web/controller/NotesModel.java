package com.ylbms.base.check.web.controller;

import java.util.List;

import com.ylbms.base.check.model.CheckNotesInfo;

/**
 * 
 * @author JackLiang
 * @version 1.0
 * @date 2013-7-23
 */
public class NotesModel {

	private List<CheckNotesInfo> notes;

	public List<CheckNotesInfo> getNotes() {
		return notes;
	}

	public void setNotes(List<CheckNotesInfo> notes) {
		this.notes = notes;
	}

}
