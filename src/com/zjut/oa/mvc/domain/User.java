package com.zjut.oa.mvc.domain;

import java.sql.Timestamp;

import com.zjut.oa.db.Model;

@SuppressWarnings("serial")
public class User extends Model {

	private String uid;
	private String username;
	private String password;

	private int roleID;
	private Timestamp addtime;
	private Timestamp modifytime;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public Timestamp getAddtime() {
		return addtime;
	}

	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}

	public Timestamp getModifytime() {
		return modifytime;
	}

	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password="
				+ password + ", roleID=" + roleID + ", addtime=" + addtime
				+ ", modifytime=" + modifytime + "]";
	}

}
