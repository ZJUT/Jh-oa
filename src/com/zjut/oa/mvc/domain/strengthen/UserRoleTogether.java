package com.zjut.oa.mvc.domain.strengthen;

import com.zjut.oa.mvc.domain.Role;
import com.zjut.oa.mvc.domain.User;

public class UserRoleTogether {

	private long id;
	private User user;
	private Role role;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
