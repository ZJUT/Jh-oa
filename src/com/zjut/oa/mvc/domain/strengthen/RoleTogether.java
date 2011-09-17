package com.zjut.oa.mvc.domain.strengthen;

import com.zjut.oa.mvc.domain.Role;

public class RoleTogether {

	private long id;
	private Role role;
	private boolean hasDistribute;

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

	public boolean isHasDistribute() {
		return hasDistribute;
	}

	public void setHasDistribute(boolean hasDistribute) {
		this.hasDistribute = hasDistribute;
	}

}
