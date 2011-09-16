package com.zjut.oa.mvc.domain;

import com.zjut.oa.db.Model;

@SuppressWarnings("serial")
public class Permission extends Model {
	
	private int menuID;
	private int resourceID;
	private int optID;
	private String description;

	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}

	public int getResourceID() {
		return resourceID;
	}

	public void setResourceID(int resourceID) {
		this.resourceID = resourceID;
	}

	public int getOptID() {
		return optID;
	}

	public void setOptID(int optID) {
		this.optID = optID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Permission [menuID=" + menuID + ", resourceID=" + resourceID
				+ ", optID=" + optID + ", description=" + description + "]";
	}

}
