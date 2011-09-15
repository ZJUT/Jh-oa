package com.zjut.oa.mvc.domain;

import com.zjut.oa.db.Model;

@SuppressWarnings("serial")
public class Operator extends Model {

	private String optname;
	private String optvalue;
	
	public String getOptname() {
		return optname;
	}
	public void setOptname(String optname) {
		this.optname = optname;
	}
	public String getOptvalue() {
		return optvalue;
	}
	public void setOptvalue(String optvalue) {
		this.optvalue = optvalue;
	}
	@Override
	public String toString() {
		return "Operator [optname=" + optname + ", optvalue=" + optvalue + "]";
	}
	
}
