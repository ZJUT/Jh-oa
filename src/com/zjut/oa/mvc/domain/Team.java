package com.zjut.oa.mvc.domain;

import com.zjut.oa.db.Model;

@SuppressWarnings("serial")
public class Team extends Model {

	private int userID;
	private String headimage;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getHeadimage() {
		return headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}

	@Override
	public String toString() {
		return "Team [userID=" + userID + ", headimage=" + headimage + "]";
	}

}
