package com.zjut.oa.mvc.domain.strengthen;

import com.zjut.oa.mvc.domain.Role;

public class RolePermissionTogether {

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

}
