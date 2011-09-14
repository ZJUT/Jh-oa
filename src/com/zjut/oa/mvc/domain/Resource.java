package com.zjut.oa.mvc.domain;

import com.zjut.oa.db.Model;

@SuppressWarnings("serial")
public class Resource extends Model {

	private String resourcevalue;
	private String resourcename;

	public String getResourcevalue() {
		return resourcevalue;
	}

	public void setResourcevalue(String resourcevalue) {
		this.resourcevalue = resourcevalue;
	}

	public String getResourcename() {
		return resourcename;
	}

	public void setResourcename(String resourcename) {
		this.resourcename = resourcename;
	}

	@Override
	public String toString() {
		return "Resource [resourcevalue=" + resourcevalue + ", resourcename="
				+ resourcename + "]";
	}

}
