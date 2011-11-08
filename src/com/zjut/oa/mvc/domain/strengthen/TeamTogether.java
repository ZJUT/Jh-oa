package com.zjut.oa.mvc.domain.strengthen;

import com.zjut.oa.mvc.domain.User;

public class TeamTogether {

	private long id;
	private User user;
	private String headimage;

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

	public String getHeadimage() {
		return headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}

	@Override
	public String toString() {
		return "TeamTogether [id=" + id + ", headimage=" + headimage + "]";
	}

}
