package com.zjut.oa.mvc.domain;

import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.db.Model;

public class Ffile extends Model {

	private static final Log log = LogFactory.getLog(Ffile.class);

	private String filename;
	private String showname;
	private Timestamp addtime;
	private int userID;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getShowname() {
		return showname;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "Ffile [filename=" + filename + ", showname=" + showname
				+ ", addtime=" + addtime + ", userID=" + userID + "]";
	}

}
