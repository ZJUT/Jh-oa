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
import com.zjut.oa.mvc.domain.Role;
import com.zjut.oa.mvc.domain.Rolepermission;
import com.zjut.oa.mvc.domain.strengthen.RoleTogether;

public class RoleAction extends ActionAdapter {

	@Result("/WEB-INF/pages/freeze/role/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {

		return INPUT;
	}

	@Success(path = "/action/role/list", isAction = true)
	@Fail(path = "/WEB-INF/pages/freeze/role/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		String rolename = param(req, "rolename");

		Role model = new Role();
		model.setRolename(rolename);

		setAttr(req, MODEL, model);

		if (StringUtils.isBlank(rolename)) {
			setAttr(req, TIP_NAME_KEY, "请输入角色名");
			return FAIL;
		}

		if (model.existProperty("rolename", rolename)) {
			setAttr(req, TIP_NAME_KEY, "已存在此角色");
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
		int id = param(req, "id", 0);

		Role model = new Role();
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
				setAttr(req, TIP_NAME_KEY, "成功删除角色[" + model.getRolename()
						+ "]");
			} else {
				setAttr(req, TIP_NAME_KEY, "删除角色[" + model.getRolename()
						+ "]失败");
			}
		}
		return this.list(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/role/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Role model = new Role();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		setAttr(req, MODEL, model);

		return INPUT;

	}

	@Result("/WEB-INF/pages/freeze/role/list.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/role/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);
		String rolename = param(req, "rolename");

		Role model = new Role();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		String pre_rolename = model.getRolename();

		if (StringUtils.isBlank(pre_rolename)) {
			setAttr(req, TIP_NAME_KEY, "加载角色失败");
			return FAIL;
		}

		if (StringUtils.equals(pre_rolename, rolename)) {
			setAttr(req, TIP_NAME_KEY, "无任何修改");
			model.setRolename(pre_rolename);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (StringUtils.isBlank(rolename)) {
			setAttr(req, TIP_NAME_KEY, "请输入角色名");
			model.setRolename(pre_rolename);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		model.setRolename(rolename);
		setAttr(req, MODEL, model);

		if (model.existProperty("rolename", rolename)) {
			setAttr(req, TIP_NAME_KEY, "此角色名已存在");
			return FAIL;
		}

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "由[" + pre_rolename + "]更改为[" + rolename
					+ "]");
			return this.list(req, resp);
		} else {
			setAttr(req, TIP_NAME_KEY, "编辑角色[" + pre_rolename + "]失败");
			return FAIL;
		}
	}

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/role/list.jsp")
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		Role model = new Role();
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>) model.listAll();

		List<RoleTogether> allRoleList = new ArrayList<RoleTogether>();
		Rolepermission rolepermission = new Rolepermission();
		List<Rolepermission> rpList = null;

		for (Role role : roleList) {

			RoleTogether rt = new RoleTogether();
			rt.setId(role.getId());
			rt.setRole(role);

			rpList = (List<Rolepermission>) rolepermission
					.filter(" where roleID=" + role.getId());
			boolean hasDistribute = rpList.size() > 0 ? true : false;
			rt.setHasDistribute(hasDistribute);

			allRoleList.add(rt);
		}

		setAttr(req, DATA_LIST, allRoleList);

		return INPUT;
	}

}
