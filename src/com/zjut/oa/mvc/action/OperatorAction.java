package com.zjut.oa.mvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.Operator;

public class OperatorAction extends ActionAdapter {

	@Override
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		return super.show(req, resp);
	}


	@Result("/WEB-INF/pages/freeze/operator/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {
		
		return INPUT;
	}

	@Success(path = "/action/operator/list", isAction = true)
	@Fail(path = "/WEB-INF/pages/freeze/operator/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		String optname = param(req, "optname");
		String optvalue = param(req, "optvalue");
		
		Operator model=new Operator();
		model.setOptname(optname);
		model.setOptvalue(optvalue);
		
		setAttr(req, MODEL, model);
		
		if (StringUtils.isBlank(optname)) {
			setAttr(req, TIP_NAME_KEY, "请输入操作描述");
			return FAIL;
		}
		if (StringUtils.isBlank(optvalue)) {
			setAttr(req, TIP_NAME_KEY, "请输入操作值");
			return FAIL;
		}
		if(model.existProperty("optvalue", optvalue)){
			setAttr(req,TIP_NAME_KEY,"已存在此操作值");
			return FAIL;
		}
		
		if (model.save() > 0) {
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "添加新操作失败");
			return FAIL;
		}
	}

	@Result("/WEB-INF/pages/freeze/operator/list.jsp")
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int id=param(req,"id",0);
		
		Operator model=new Operator();
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
				setAttr(req,TIP_NAME_KEY,"成功删除操作["+model.getOptname()+"]");
			}
			else{
				setAttr(req,TIP_NAME_KEY,"删除操作["+model.getOptname()+"]失败");
			}
		}
		return this.list(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/operator/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		int id=param(req,"id",0);
		
		Operator model=new Operator();
		if(id!=0){
			model.setId(id);
			model=model.get(id);
		}
		
		setAttr(req, MODEL,model);
		
		return INPUT;
		
	}

	@Result("/WEB-INF/pages/freeze/operator/list.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/operator/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id=param(req,"id",0);
		String optname = param(req, "optname");
		String optvalue = param(req, "optvalue");

		Operator model = new Operator();
		if(id!=0){
			model.setId(id);
			model=model.get(id);
		}
		
		String pre_optname=model.getOptname();
		String pre_optvalue=model.getOptvalue();
		
		if(StringUtils.isBlank(pre_optname) || StringUtils.isBlank(pre_optvalue)){
			setAttr(req, TIP_NAME_KEY, "加载操作失败");
			return FAIL;
		}
		
		if(StringUtils.equals(pre_optname, optname) && StringUtils.equals(pre_optvalue, optvalue)  ){
			setAttr(req, TIP_NAME_KEY, "无任何修改");
			model.setOptname(pre_optname);
			model.setOptvalue(pre_optvalue);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (StringUtils.isBlank(optname)) {
			setAttr(req, TIP_NAME_KEY, "请输入操作描述");
			model.setOptname(pre_optname);
			model.setOptvalue(pre_optvalue);
			setAttr(req, MODEL, model);
			return FAIL;
		}
		if (StringUtils.isBlank(optvalue)) {
			setAttr(req, TIP_NAME_KEY, "请输入操作值");
			model.setOptname(pre_optname);
			model.setOptvalue(pre_optvalue);
			setAttr(req, MODEL, model);
			return FAIL;
		}
		
		model.setOptname(optname);
		model.setOptvalue(optvalue);
		setAttr(req, MODEL, model);

		if(model.existProperty("optvalue", optvalue)&& StringUtils.equals(pre_optname, optname)){
			setAttr(req, TIP_NAME_KEY, "操作值["+optvalue+"]已存在");
			return FAIL;
		}
		
		if (model.save() > 0) {
			StringBuilder tip=new StringBuilder();
			if(!StringUtils.equals(pre_optname, optname))
				tip.append("操作描述由["+pre_optname+"]更改为["+optname+"] ");
			if(!StringUtils.equals(pre_optvalue, optvalue))
				tip.append("操作值由["+pre_optvalue+"]更改为["+optvalue+"] ");
			setAttr(req, TIP_NAME_KEY, tip.toString());
			return this.list(req, resp);
		} else {
			setAttr(req, TIP_NAME_KEY, "编辑操作["+pre_optvalue+"]失败");
			return FAIL;
		}
	}


	@Override
	public String viewFilter(HttpServletRequest req, HttpServletResponse resp) {
		return super.viewFilter(req, resp);
	}

	@Override
	public String filter(HttpServletRequest req, HttpServletResponse resp) {
		return super.filter(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/operator/list.jsp")
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		Operator model=new Operator();
		@SuppressWarnings("unchecked")
		List<Operator> operatorList=(List<Operator>)model.listAll();
		
		setAttr(req,DATA_LIST,operatorList);
		
		return INPUT;
	}

	@Override
	public String listByPage(HttpServletRequest req, HttpServletResponse resp) {
		return super.listByPage(req, resp);
	}

	@Override
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp) {
		return super.batchDelete(req, resp);
	}

	@Override
	public String showMyself(HttpServletRequest req, HttpServletResponse resp) {
		return super.showMyself(req, resp);
	}

	@Override
	public String viewModifyMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		return super.viewModifyMyself(req, resp);
	}

	@Override
	public String modifyMyself(HttpServletRequest req, HttpServletResponse resp) {
		return super.modifyMyself(req, resp);
	}

	@Override
	public String viewFilterMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		return super.viewFilterMyself(req, resp);
	}

	@Override
	public String filterMyself(HttpServletRequest req, HttpServletResponse resp) {
		return super.filterMyself(req, resp);
	}

	@Override
	public String listMyself(HttpServletRequest req, HttpServletResponse resp) {
		return super.listMyself(req, resp);
	}

	@Override
	public String listByPageMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		return super.listByPageMyself(req, resp);
	}

	@Override
	public String batchDeleteMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		return super.batchDeleteMyself(req, resp);
	}
	
}
