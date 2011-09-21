package com.zjut.oa.mvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.Department;

public class DepartmentAction extends ActionAdapter {

	private static final Log log=LogFactory.getLog(DepartmentAction.class);

	@Result("/WEB-INF/pages/freeze/department/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {

		return INPUT;
	}

	@Success(path = "/WEB-INF/pages/freeze/department/viewAdd.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/department/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		String departmentname = param(req, "departmentname");

		Department model = new Department();
		model.setDepartmentname(departmentname);

		setAttr(req, MODEL, model);

		if (StringUtils.isBlank(departmentname)) {
			setAttr(req, TIP_NAME_KEY, "请输入部门名");
			return FAIL;
		}

		if (model.existProperty("departmentname", departmentname)) {
			setAttr(req, TIP_NAME_KEY, "已存在此部门");
			return FAIL;
		}

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "添加新部门[" + departmentname + "]成功");
			model.setDepartmentname("");
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "添加新部门失败");
			return FAIL;
		}
	}

	@Result("/WEB-INF/pages/freeze/department/list.jsp")
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Department model = new Department();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		if (id == 0) {
			setAttr(req, TIP_NAME_KEY, "非法ID值");
		} else {
			model.setId(id);
			// TODO more relative
			if (model.delete()) {
				setAttr(req, TIP_NAME_KEY, "成功删除[" + model.getDepartmentname() + "]");
			} else {
				setAttr(req, TIP_NAME_KEY, "删除部门[" + model.getDepartmentname()
						+ "]失败");
			}
		}
		return this.list(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/department/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Department model = new Department();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		setAttr(req, MODEL, model);

		return INPUT;

	}

	@Result("/WEB-INF/pages/freeze/department/list.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/department/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);
		String departmentname = param(req, "departmentname");

		Department model = new Department();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		String pre_departmentname = model.getDepartmentname();

		if (StringUtils.isBlank(pre_departmentname)) {
			setAttr(req, TIP_NAME_KEY, "加载部门失败");
			return FAIL;
		}

		if (StringUtils.equals(pre_departmentname, departmentname)) {
			setAttr(req, TIP_NAME_KEY, "无任何修改");
			model.setDepartmentname(pre_departmentname);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (StringUtils.isBlank(departmentname)) {
			setAttr(req, TIP_NAME_KEY, "请输入部门名");
			model.setDepartmentname(pre_departmentname);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		model.setDepartmentname(departmentname);
		setAttr(req, MODEL, model);

		if (model.existProperty("departmentname", departmentname)) {
			setAttr(req, TIP_NAME_KEY, "此部门名已存在");
			model.setDepartmentname(pre_departmentname);
			return FAIL;
		}

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "由[" + pre_departmentname + "]更改为[" + departmentname
					+ "]");
			return this.list(req, resp);
		} else {
			setAttr(req, TIP_NAME_KEY, "编辑部门[" + pre_departmentname + "]失败");
			return FAIL;
		}
	}

	@Result("/WEB-INF/pages/freeze/department/list.jsp")
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		Department model = new Department();
		@SuppressWarnings("unchecked")
		List<Department> departmentList = (List<Department>) model.listAll();

		setAttr(req, DATA_LIST, departmentList);

		return INPUT;
	}
	
	
}
