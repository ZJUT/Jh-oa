package com.zjut.oa.mvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.Menu;

public class MenuAction extends ActionAdapter{

	@Override
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.show(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/menu/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {
		
		return INPUT;
	}

	@Success(path = "/action/menu/list", isAction = true)
	@Fail(path = "/WEB-INF/pages/freeze/menu/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		String menuname = param(req, "menuname");
		
		Menu model=new Menu();
		model.setMenuname(menuname);
		
		setAttr(req, MODEL, model);
		
		if (StringUtils.isBlank(menuname)) {
			setAttr(req, TIP_NAME_KEY, "请输入菜单名");
			return FAIL;
		}
		
		if (model.save() > 0) {
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "添加新菜单失败");
			return FAIL;
		}
	}

	@Override
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.delete(req, resp);
	}

	@Override
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.viewModify(req, resp);
	}

	@Override
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.modify(req, resp);
	}

	@Override
	public String viewFilter(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.viewFilter(req, resp);
	}

	@Override
	public String filter(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.filter(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/menu/list.jsp")
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		Menu model=new Menu();
		@SuppressWarnings("unchecked")
		List<Menu> menuList=(List<Menu>)model.listAll();
		
		setAttr(req,DATA_LIST,menuList);
		
		return INPUT;
	}

	@Override
	public String listByPage(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.listByPage(req, resp);
	}

	@Override
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.batchDelete(req, resp);
	}

}
