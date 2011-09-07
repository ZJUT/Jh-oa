package com.zjut.oa.mvc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.User;

public class UserAction extends ActionAdapter {
	

	@Success(path="/action/global/manager",isAction=true)
	@Fail(path="/WEB-INF/pages/anonymous/index.jsp")
	public String login(HttpServletRequest req, HttpServletResponse resp){
		String uid=param(req,"uid");
		String password=param(req,"password");
		
		User model=new User();
		model.setUid(uid);
		model.setPassword(password);
		
		setAttr(req,MODEL,model);
		
		if(StringUtils.isBlank(uid)){
			setAttr(req,TIP_NAME_KEY,"请输入学号");
			return FAIL;
		}
		if(StringUtils.isBlank(password)){
			setAttr(req,TIP_NAME_KEY,"请输入密码");
			return FAIL;
		}
		
		if(!model.existProperty("uid", uid)){
			setAttr(req,TIP_NAME_KEY,"该学号不存在");
			return FAIL;
		}
		
		if(model.exist(uid, password)){
			setAttr(req.getSession(),LOGIN_USER_KEY,model.getUid());
			return SUCCESS;
		}
		else{
			setAttr(req,TIP_NAME_KEY,"密码错误");
			return FAIL;
		}
	}
	
	@Result("/WEB-INF/pages/anonymous/index.jsp")
	public String logout(HttpServletRequest req,HttpServletResponse resp){
		rmAttr(req.getSession(), LOGIN_USER_KEY);
		setAttr(req, TIP_NAME_KEY, "成功注销");
		return INPUT;
	}
}
