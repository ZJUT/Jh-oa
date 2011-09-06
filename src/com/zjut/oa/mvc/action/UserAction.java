package com.zjut.oa.mvc.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.User;

public class UserAction extends ActionAdapter {
	
	
	@Result("/WEB-INF/pages/user/login.jsp")
	public String viewLogin(HttpServletRequest req, HttpServletResponse resp){
		return INPUT;
	}
	
	@Success(path="action/test/index",isAction=true)
	@Fail(path="/WEB-INF/pages/user/login.jsp")
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
	
	@Result("/WEB-INF/pages/user/list.jsp")
	public String list(HttpServletRequest req, HttpServletResponse resp){
		List<User> list=new ArrayList<User>();
		for(int i=0;i<10;i++){
			User model=new User();
			model.setUsername("username"+i);
			model.setPassword("password"+i);
			list.add(model);
		}
		setAttr(req,DATA_LIST,list);
		return INPUT;
	}
}
