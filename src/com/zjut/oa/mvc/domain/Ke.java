package com.zjut.oa.mvc.domain;

import com.zjut.oa.db.Model;

@SuppressWarnings("serial")
public class Ke extends Model {

	private int userID;
	private String kevalue;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getKevalue() {
		return kevalue;
	}
	public void setKevalue(String kevalue) {
		this.kevalue = kevalue;
	}
	@Override
	public String toString() {
		return "Ke [userID=" + userID + ", kevalue=" + kevalue + "]";
	}
	
}
