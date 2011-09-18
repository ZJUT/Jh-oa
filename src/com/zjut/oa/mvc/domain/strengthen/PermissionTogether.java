package com.zjut.oa.mvc.domain.strengthen;

import java.io.Serializable;

import com.zjut.oa.mvc.domain.Menu;
import com.zjut.oa.mvc.domain.Operator;
import com.zjut.oa.mvc.domain.Resource;

@SuppressWarnings("serial")
public class PermissionTogether implements Serializable{

	private long id;
	private Menu menu;
	private Resource resource;
	private Operator operator;
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
