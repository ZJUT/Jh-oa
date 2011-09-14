package com.zjut.oa.mvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.Role;

public class RoleAction extends ActionAdapter {

	@Override
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.show(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/role/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {
		
		return INPUT;
	}

	@Success(path = "/action/role/list", isAction = true)
	@Fail(path = "/WEB-INF/pages/freeze/role/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		String rolename = param(req, "rolename");
		
		Role model=new Role();
		model.setRolename(rolename);
		
		setAttr(req, MODEL, model);
		
		if (StringUtils.isBlank(rolename)) {
			setAttr(req, TIP_NAME_KEY, "请输入角色名");
			return FAIL;
		}
		
		if(model.existProperty("rolename", rolename)){
			setAttr(req,TIP_NAME_KEY,"已存在此角色");
			return FAIL;
		}
		
		if (model.save() > 0) {
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "添加新角色失败");
			return FAIL;
		}
	}
	
	@Result("/WEB-INF/pages/freeze/role/list.jsp")
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int id=param(req,"id",0);
		
		Role model=new Role();
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
				setAttr(req,TIP_NAME_KEY,"成功删除角色["+model.getRolename()+"]");
			}
			else{
				setAttr(req,TIP_NAME_KEY,"删除角色["+model.getRolename()+"]失败");
			}
		}
		return this.list(req, resp);
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

	@Result("/WEB-INF/pages/freeze/role/list.jsp")
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		Role model=new Role();
		@SuppressWarnings("unchecked")
		List<Role> roleList=(List<Role>)model.listAll();
		
		setAttr(req,DATA_LIST,roleList);
		
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

	@Override
	public String showMyself(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.showMyself(req, resp);
	}

	@Override
	public String viewModifyMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.viewModifyMyself(req, resp);
	}

	@Override
	public String modifyMyself(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.modifyMyself(req, resp);
	}

	@Override
	public String viewFilterMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.viewFilterMyself(req, resp);
	}

	@Override
	public String filterMyself(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.filterMyself(req, resp);
	}

	@Override
	public String listMyself(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.listMyself(req, resp);
	}

	@Override
	public String listByPageMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.listByPageMyself(req, resp);
	}

	@Override
	public String batchDeleteMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.batchDeleteMyself(req, resp);
	}

}
