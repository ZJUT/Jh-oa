package com.zjut.oa.mvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.Resource;
import com.zjut.oa.mvc.domain.Role;

public class ResourceAction extends ActionAdapter {

	@Override
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.show(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/resource/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {
		
		return INPUT;
	}

	@Success(path = "/action/resource/list", isAction = true)
	@Fail(path = "/WEB-INF/pages/freeze/resource/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		String resourcename = param(req, "resourcename");
		String resourcevalue = param(req, "resourcevalue");
		
		Resource model=new Resource();
		model.setResourcename(resourcename);
		model.setResourcevalue(resourcevalue);
		
		setAttr(req, MODEL, model);
		
		if (StringUtils.isBlank(resourcename)) {
			setAttr(req, TIP_NAME_KEY, "请输入资源名");
			return FAIL;
		}
		if (StringUtils.isBlank(resourcevalue)) {
			setAttr(req, TIP_NAME_KEY, "请输入资源值");
			return FAIL;
		}
		if(model.existProperty("resourcevalue", resourcevalue)){
			setAttr(req,TIP_NAME_KEY,"已存在此资源值");
			return FAIL;
		}
		
		if (model.save() > 0) {
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "添加新资源失败");
			return FAIL;
		}
	}

	@Result("/WEB-INF/pages/freeze/resource/list.jsp")
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int id=param(req,"id",0);
		
		Resource model=new Resource();
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
				setAttr(req,TIP_NAME_KEY,"成功删除资源["+model.getResourcename()+"]");
			}
			else{
				setAttr(req,TIP_NAME_KEY,"删除资源["+model.getResourcename()+"]失败");
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

	@Result("/WEB-INF/pages/freeze/resource/list.jsp")
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		Resource model=new Resource();
		@SuppressWarnings("unchecked")
		List<Resource> resourceList=(List<Resource>)model.listAll();
		
		setAttr(req,DATA_LIST,resourceList);
		
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
