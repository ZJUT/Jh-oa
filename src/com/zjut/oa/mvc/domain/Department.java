package com.zjut.oa.mvc.domain;

import com.zjut.oa.db.Model;

public class Department extends Model {

	private String departmentname;

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	@Override
	public String toString() {
		return "Department [departmentname=" + departmentname + "]";
	}
	
}
