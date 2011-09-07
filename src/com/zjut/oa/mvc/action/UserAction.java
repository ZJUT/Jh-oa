package com.zjut.oa.mvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.Success;

public class UserAction extends ActionAdapter {
	

	@Success(path="/WEB-INF/pages/freeze/index.jsp")
	@Fail(path="/WEB-INF/pages/anonymous/index.jsp")
	public String login(HttpServletRequest req, HttpServletResponse resp){
		String username=param(req,"username");
		if(StringUtils.isBlank(username)){
			setAttr(req,TIP_NAME_KEY,"请输入用户名");
			return FAIL;
		}
		if("lbb".equals(username) ||"李斌斌".equals(username)){
			return SUCCESS;
		}
		else{
			setAttr(req,TIP_NAME_KEY,"用户名错误");
			return FAIL;
		}
	}
}
