package com.zjut.oa.mvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.Result;

public class TestAction extends ActionAdapter {

	@Result("/WEB-INF/pages/test.jsp")
	public String index(HttpServletRequest req, HttpServletResponse resp){
		return INPUT;
	}
}
