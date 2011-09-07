package com.zjut.oa.mvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.Result;

public class GlobalAction extends ActionAdapter {

	@Result("/WEB-INF/pages/anonymous/index.jsp")
	public String index(HttpServletRequest req, HttpServletResponse resp){
		
		return INPUT;
	}
	
	@Result("/WEB-INF/pages/freeze/index.jsp")
	public String manager(HttpServletRequest req, HttpServletResponse resp){
		
		return INPUT;
	}
}
