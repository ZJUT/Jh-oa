package com.zjut.oa.mvc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.Constant;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.None;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.News;
import com.zjut.oa.mvc.domain.User;
import com.zjut.oa.tool.HttpTool;

public class GlobalAction extends ActionAdapter {

	@Result("/WEB-INF/pages/anonymous/index.jsp")
	public String anonymous_index(HttpServletRequest req,
			HttpServletResponse resp) {

		List<News> top6newsList = (List<News>) HttpTool.getInstance()
				.getTop6NewsList();

		setAttr(req, DATA_LIST, top6newsList);

		return INPUT;
	}

	@Success(path = "/action/global/manager", isAction = true)
	@Fail(path = "/WEB-INF/pages/anonymous/index.jsp")
	public String anonymous_login(HttpServletRequest req,
			HttpServletResponse resp) {
		String uid = param(req, "uid");
		String password = param(req, "password");
		String autologin= param(req,"autologin");
		
		User model = new User();
		model.setUid(uid);
		model.setPassword(password);

		setAttr(req,Constant.PAGE_LOGIN_AUTOLOGIN_KEY,autologin); // true or 空
		
		List<News> top6newsList = (List<News>) HttpTool.getInstance()
				.getTop6NewsList();

		setAttr(req, MODEL, model);
		setAttr(req, DATA_LIST, top6newsList);

		if (StringUtils.isBlank(uid)) {
			setAttr(req, TIP_NAME_KEY, "请输入学号");
			return FAIL;
		}
		if (StringUtils.isBlank(password)) {
			setAttr(req, TIP_NAME_KEY, "请输入密码");
			return FAIL;
		}

		if (!model.existProperty("uid", uid)) {
			setAttr(req, TIP_NAME_KEY, "该学号不存在");
			return FAIL;
		}

		if (model.exist(uid, password)) {
			//设置会话状态、用户登录状态cookie
			setAttr(req.getSession(), LOGIN_USER_KEY, model.getId() + "&"
					+ model.getUid() + "&" + model.getUsername());
			if(StringUtils.isNotBlank(autologin) && autologin.equals("true")){
				Cookie[] cookies=req.getCookies();
				boolean exist_uid=false;
//				boolean exist_password=false;
				for(Cookie cookie : cookies){
					if(cookie.getName().equals("login_uid_key")){
						cookie.setValue(uid);
						exist_uid=true;
					}
//					if(cookie.getName().equals("login_password_key")){
//						cookie.setValue(password);
//						exist_password=true;
//					}
				}
				
				if(!exist_uid){
					Cookie cookie_uid=new Cookie("login_uid_key",uid);
					cookie_uid.setPath("/");
					cookie_uid.setMaxAge(60*60*24*14);
					resp.addCookie(cookie_uid);
				}
//				if(!exist_password){
//					Cookie cookie_password=new Cookie("login_password_key",password);
//					cookie_password.setPath("/");
//					cookie_password.setMaxAge(60*60*24*14);
//					resp.addCookie(cookie_password);
//				}
			}
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "密码错误");
			return FAIL;
		}
	}

	@Result("/WEB-INF/pages/anonymous/index.jsp")
	public String anonymous_logout(HttpServletRequest req,
			HttpServletResponse resp) {
		rmAttr(req.getSession(), LOGIN_USER_KEY);
		setAttr(req, TIP_NAME_KEY, "成功注销");

		List<News> top6newsList = (List<News>) HttpTool.getInstance()
				.getTop6NewsList();
		setAttr(req, DATA_LIST, top6newsList);

		return INPUT;
	}

	@Result("/WEB-INF/pages/anonymous/anonymous_history.jsp")
	public String anonymous_history(HttpServletRequest req,
			HttpServletResponse resp) {
		return INPUT;
	}

	@Result("/WEB-INF/pages/anonymous/anonymous_news_show.jsp")
	public String anonymous_news_show(HttpServletRequest req,
			HttpServletResponse resp) {
		String id = param(req, "id");

		News model = new News();
		if (StringUtils.isNotBlank(id)) {
			model.setId(Long.parseLong(id));
			model = model.get(Long.parseLong(id));
		}

		setAttr(req, MODEL, model);

		return INPUT;
	}

	@Result("/WEB-INF/pages/freeze/index.jsp")
	public String manager(HttpServletRequest req, HttpServletResponse resp) {

		return INPUT;
	}

	@None
	public String uploadImg(HttpServletRequest req, HttpServletResponse resp) {
		try {
			PrintWriter out = resp.getWriter();
			StringBuilder output_json = new StringBuilder();
			output_json.append("{");
			output_json.append("\'error\':");
			output_json.append("0");
			output_json.append(",");
			output_json.append("\'url\':");
			output_json.append("\'zhanwei\'");
			output_json.append("}");

			out.print(output_json.toString());
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
			log.error("上传图像出错:" + e, e.getCause());
		}

		return NONE;
	}
}
