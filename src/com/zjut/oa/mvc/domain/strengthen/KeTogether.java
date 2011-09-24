package com.zjut.oa.mvc.domain.strengthen;

import com.zjut.oa.mvc.domain.Ke;
import com.zjut.oa.mvc.domain.User;

public class KeTogether {

	private long id;
	private User user;
	private Ke ke;
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
	public Ke getKe() {
		return ke;
	}
	public void setKe(Ke ke) {
		this.ke = ke;
	}
	@Override
	public String toString() {
		return "KeTogether [id=" + id + ", user=" + user + ", ke=" + ke + "]";
	}
	
}
