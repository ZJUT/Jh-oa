package com.zjut.oa.mvc.domain;

import com.zjut.oa.db.Model;

@SuppressWarnings("serial")
public class Menu extends Model {

	private String menuname;

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	@Override
	public String toString() {
		return "Menu [menuname=" + menuname + "]";
	}

}
