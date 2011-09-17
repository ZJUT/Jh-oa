package com.zjut.oa.mvc.domain;

import com.zjut.oa.db.Model;

@SuppressWarnings("serial")
public class Rolepermission extends Model {

	private int roleID;
	private int permissionID;

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public int getPermissionID() {
		return permissionID;
	}

	public void setPermissionID(int permissionID) {
		this.permissionID = permissionID;
	}

	@Override
	public String toString() {
		return "Rolepermission [roleID=" + roleID + ", permissionID="
				+ permissionID + "]";
	}

}
