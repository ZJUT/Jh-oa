package com.zjut.oa.mvc.domain.strengthen;

import java.io.Serializable;

import com.zjut.oa.mvc.domain.Role;

@SuppressWarnings("serial")
public class RolePermissionTogether implements Serializable {

	private long id;
	private Role role;
	private PermissionTogether permissiontogether;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public PermissionTogether getPermissiontogether() {
		return permissiontogether;
	}

	public void setPermissiontogether(PermissionTogether permissiontogether) {
		this.permissiontogether = permissiontogether;
	}

	@Override
	public String toString() {
		return "RolePermissionTogether [id=" + id + ", role=" + role
				+ ", permissiontogether=" + permissiontogether + "]";
	}

}
