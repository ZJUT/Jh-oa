package com.zjut.oa.mvc.domain.strengthen;

import com.zjut.oa.mvc.domain.Academy;
import com.zjut.oa.mvc.domain.Department;
import com.zjut.oa.mvc.domain.Job;
import com.zjut.oa.mvc.domain.User;

public class UserTogether {

	private long id;
	private User user;
	private Academy academy;
	private Department department;
	private Job job;

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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "UserTogether [id=" + id + ", user=" + user + ", academy="
				+ academy + ", department=" + department + "]";
	}

}
