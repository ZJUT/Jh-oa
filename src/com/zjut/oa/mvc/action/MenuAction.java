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
		
		if(model.existProperty("menuname", menuname)){
			setAttr(req, TIP_NAME_KEY, "已存在此菜单");
			return FAIL;
		}
		
		if (model.save() > 0) {
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "添加新菜单失败");
			return FAIL;
		}
	}

	@Result("/WEB-INF/pages/freeze/menu/list.jsp")
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int id=param(req,"id",0);
		
		Menu model=new Menu();
		if(id!=0){
			model.setId(id);
			model=model.get(id);
		}
		
		if(id==0){
			setAttr(req, TIP_NAME_KEY, "非法ID值");
		}
		else{
			model.setId(id);
			//TODO more relative
			if(model.delete()){
				setAttr(req,TIP_NAME_KEY,"成功删除["+model.getMenuname()+"]");
			}
			else{
				setAttr(req,TIP_NAME_KEY,"删除菜单["+model.getMenuname()+"]失败");
			}
		}
		return this.list(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/menu/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		int id=param(req,"id",0);
		
		Menu model=new Menu();
		if(id!=0){
			model.setId(id);
			model=model.get(id);
		}
		
		setAttr(req, MODEL,model);
		
		return INPUT;
		
	}

	@Result("/WEB-INF/pages/freeze/menu/list.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/menu/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id=param(req,"id",0);
		String menuname = param(req, "menuname");

		Menu model = new Menu();
		if(id!=0){
			model.setId(id);
			model=model.get(id);
		}
		
		String pre_menuname=model.getMenuname();
		
		if(StringUtils.isBlank(pre_menuname)){
			setAttr(req, TIP_NAME_KEY, "加载菜单失败");
			return FAIL;
		}
		
		if(StringUtils.equals(pre_menuname, menuname)){
			setAttr(req, TIP_NAME_KEY, "无任何修改");
			model.setMenuname(pre_menuname);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (StringUtils.isBlank(menuname)) {
			setAttr(req, TIP_NAME_KEY, "请输入菜单名");
			model.setMenuname(pre_menuname);
			setAttr(req, MODEL, model);
			return FAIL;
		}
		
		model.setMenuname(menuname);
		setAttr(req, MODEL, model);

		if(model.existProperty("menuname", menuname)){
			setAttr(req, TIP_NAME_KEY, "此菜单名已存在");
			return FAIL;
		}
		
		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "由["+pre_menuname+"]更改为["+menuname+"]");
			return this.list(req, resp);
		} else {
			setAttr(req, TIP_NAME_KEY, "编辑菜单["+pre_menuname+"]失败");
			return FAIL;
		}
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
