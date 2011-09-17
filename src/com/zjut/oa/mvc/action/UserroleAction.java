package com.zjut.oa.mvc.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zjut.oa.db.Pager;
import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.Constant;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.Role;
import com.zjut.oa.mvc.domain.User;
import com.zjut.oa.mvc.domain.Userrole;
import com.zjut.oa.mvc.domain.strengthen.UserRoleTogether;

public class UserroleAction extends ActionAdapter {

	@Override
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		return super.show(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/userrole/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {

		User user = new User();
		Role role = new Role();

		setAttr(req, PAGE_USERROLE_USERLIST_KEY, user.listAll());
		setAttr(req, PAGE_USERROLE_ROLELIST_KEY, role.listAll());

		return INPUT;
	}

	@SuppressWarnings("unchecked")
	@Success(path = "/WEB-INF/pages/freeze/userrole/viewAdd.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/userrole/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		int userID = param(req, "userID", 0);
		int roleID = param(req, "roleID", 0);

		Userrole model = new Userrole();
		model.setUserID(userID);
		model.setRoleID(roleID);

		User user = new User();
		Role role = new Role();

		setAttr(req, PAGE_USERROLE_USERLIST_KEY, user.listAll());
		setAttr(req, PAGE_USERROLE_ROLELIST_KEY, role.listAll());

		setAttr(req, MODEL, model);

		if (userID == 0 || userID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择用户");
			return FAIL;
		}
		if (roleID == 0 || roleID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择角色");
			return FAIL;
		}

		user.setId(userID);
		user = user.get(userID);

		role.setId(roleID);
		role = role.get(roleID);

		List<Userrole> all = (List<Userrole>) model.filter(" where userID="
				+ userID);
		Userrole isExist = (all.size() == 1) ? all.get(0) : null;
		if (isExist != null) {
			int pre_roleID = isExist.getRoleID();
			role.setId(pre_roleID);
			role = role.get(pre_roleID);
			String pre_rolename = role.getRolename();
			setAttr(req, TIP_NAME_KEY, "分配用户角色失败; [" + user.getUsername()
					+ "]已分配有[" + pre_rolename + "]的角色，每个用户只能分配一个角色!");
			return FAIL;
		} else {
			if (model.save() > 0) {
				setAttr(req, TIP_NAME_KEY, "分配[" + user.getUsername() + "]["
						+ role.getRolename() + "]角色成功");
				model.setUserID(-1);
				model.setRoleID(-1);
				return SUCCESS;
			} else {
				setAttr(req, TIP_NAME_KEY, "分配用户[" + user.getUsername() + "]["
						+ role.getRolename() + "]角色失败");
				return FAIL;
			}
		}
	}

	@Override
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		return super.delete(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/userrole/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Userrole model = new Userrole();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		User user = new User();
		Role role = new Role();

		setAttr(req, PAGE_USERROLE_USERLIST_KEY, user.listAll());
		setAttr(req, PAGE_USERROLE_ROLELIST_KEY, role.listAll());

		setAttr(req, MODEL, model);

		return INPUT;
	}

	@SuppressWarnings("unchecked")
	@Success(path = "/WEB-INF/pages/freeze/userrole/viewModify.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/userrole/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);
		int userID = param(req, "userID", 0);
		int roleID = param(req, "roleID", 0);

		Userrole model = new Userrole();

		User user = new User();
		Role role = new Role();

		setAttr(req, PAGE_USERROLE_USERLIST_KEY, user.listAll());
		setAttr(req, PAGE_USERROLE_ROLELIST_KEY, role.listAll());

		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		int pre_userID = model.getUserID();
		int pre_roleID = model.getRoleID();

		if (pre_userID == 0) {
			setAttr(req, TIP_NAME_KEY, "加载用户角色失败");
			return FAIL;
		}

		if (pre_userID == userID && pre_roleID == roleID) {
			setAttr(req, TIP_NAME_KEY, "无任何修改");
			model.setUserID(pre_userID);
			model.setRoleID(pre_roleID);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (userID == 0 || userID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择用户");
			model.setUserID(pre_userID);
			model.setRoleID(pre_roleID);
			setAttr(req, MODEL, model);
			return FAIL;
		}
		if (roleID == 0 || roleID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择角色");
			model.setUserID(pre_userID);
			model.setRoleID(pre_roleID);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		model.setUserID(userID);
		model.setRoleID(roleID);
		setAttr(req, MODEL, model);

		user.setId(userID);
		user = user.get(userID);

		role.setId(roleID);
		role = role.get(roleID);

		List<Userrole> all = (List<Userrole>) model.filter(" where userID="
				+ userID);
		Userrole userExist = (all.size() == 1) ? all.get(0) : null;
		if (userExist != null && userExist.getId() != id) {
			int userExist_userID = userExist.getUserID();
			user.setId(userExist_userID);
			user = user.get(userExist_userID);

			int userExist_roleID = userExist.getRoleID();
			role.setId(userExist_roleID);
			role = role.get(userExist_roleID);

			setAttr(req, TIP_NAME_KEY, "重新分配用户角色失败; [" + user.getUsername()
					+ "]已分配有[" + role.getRolename() + "]的角色，每个用户只能分配一个角色!");
			model.setUserID(pre_userID);
			model.setRoleID(pre_roleID);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		all = (List<Userrole>) model.filter(" where userID=" + userID
				+ " and roleID=" + roleID);
		Userrole isExist = (all.size() == 1) ? all.get(0) : null;
		if (isExist != null && isExist.getId() != id) {
			setAttr(req, TIP_NAME_KEY, "重新分配用户角色失败; 已分配给[" + user.getUsername()
					+ "][" + role.getRolename() + "]的角色");
			model.setUserID(pre_userID);
			model.setRoleID(pre_roleID);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (model.save() > 0) {
			StringBuilder tip = new StringBuilder();
			tip.append("编辑用户角色成功; ");
			if (pre_userID != userID) {
				user.setId(pre_userID);
				user = user.get(pre_userID);
				String pre_username = user.getUsername();

				user.setId(userID);
				user = user.get(userID);
				String username = user.getUsername();

				tip.append("用户[" + pre_username + "]->[" + username + "]; ");
			}
			if (pre_roleID != roleID) {
				role.setId(pre_roleID);
				role = role.get(pre_roleID);
				String pre_rolename = role.getRolename();

				role.setId(roleID);
				role = role.get(roleID);
				String rolename = role.getRolename();

				tip.append("角色[" + pre_rolename + "]->[" + rolename + "]; ");
			}
			setAttr(req, TIP_NAME_KEY, tip.toString());
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "编辑用户角色失败");
			return FAIL;
		}
	}

	@Override
	public String viewFilter(HttpServletRequest req, HttpServletResponse resp) {
		return super.viewFilter(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/userrole/filter.jsp")
	public String filter(HttpServletRequest req, HttpServletResponse resp) {
		int userID = param(req, "userID", 0);
		int roleID = param(req, "roleID", 0);

		String by = param(req, "by");
		String order = param(req, "order");

		Userrole model = new Userrole();
		model.setUserID(userID);
		model.setRoleID(roleID);

		setAttr(req, MODEL, model);

		StringBuilder filter = new StringBuilder();
		if (userID != 0) {
			filter.append(" where userID =" + userID);
		}

		if (userID != 0 && roleID != 0) {
			filter.append(" and roleID  =" + roleID);
		} else if (userID == 0 && roleID != 0) {
			filter.append(" where roleID  =" + roleID);
		}

		if (StringUtils.isNotBlank(by)
				&& (by.equals("id") || by.equals("userID") || by
						.equals("roleID"))) {
			if (StringUtils.isNotBlank(order)
					&& (order.equals("asc") || order.equals("desc"))) {
				filter.append(" order by " + by + " " + order);
			} else {
				filter.append(" order by " + by + " asc");
			}
		} else {
			filter.append(" order by id asc");
		}

		// 前台分页
		int p = Constant.DEFAULT_CURRENT_PAGE;
		int countPerPage = Constant.DEFAULT_COUNT_PER_PAGE;
		try {
			p = param(req, "page", Constant.DEFAULT_CURRENT_PAGE);
			if (p < 1)
				p = Constant.DEFAULT_CURRENT_PAGE;
		} catch (NumberFormatException e) {
			p = Constant.DEFAULT_CURRENT_PAGE;
		}
		try {
			countPerPage = param(req, "countPerPage",
					Constant.DEFAULT_COUNT_PER_PAGE);
		} catch (NumberFormatException e) {
			countPerPage = Constant.DEFAULT_COUNT_PER_PAGE;
		}
		int currentPage = p;
		int totalCount = model.totalCount(filter.toString());
		Pager pager = new Pager(currentPage, countPerPage, totalCount);
		// 针对可能的原访问页数大于实际总页数，此处重置下
		if (currentPage > pager.getTotalPage())
			currentPage = p = pager.getTotalPage();
		// 读取部分数据
		List<Userrole> dataList = (List<Userrole>) model.filterByPage(
				filter.toString(), p, pager.getCountPerPage());

		// 填充组合对象
		List<UserRoleTogether> allDataList = new ArrayList<UserRoleTogether>();
		for (Userrole userrole : dataList) {
			int tmp_userID = userrole.getUserID();
			int tmp_roleID = userrole.getRoleID();

			User prepare_user = new User();
			prepare_user = prepare_user.get(tmp_userID);

			Role prepare_role = new Role();
			prepare_role = prepare_role.get(tmp_roleID);

			UserRoleTogether urt = new UserRoleTogether();
			urt.setId(userrole.getId());
			urt.setUser(prepare_user);
			urt.setRole(prepare_role);

			allDataList.add(urt);
		}

		setAttr(req, CURRENT_PAGE_KEY, currentPage);
		setAttr(req, CURRENT_COUNT_PER_PAGE_KEY, countPerPage);
		setAttr(req, PAGER_KEY, pager);
		setAttr(req, MAX_PAGERSHOW_LENGTH_KEY, DEFAULT_MAX_PAGERSHOW_LENGTH);

		setAttr(req, DATA_LIST, allDataList);

		return INPUT;
	}

	@Override
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		return super.list(req, resp);
	}

	@Override
	public String listByPage(HttpServletRequest req, HttpServletResponse resp) {
		return super.listByPage(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/userrole/filter.jsp")
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp) {
		String[] deleteId = params(req, "deleteId");
		if (deleteId.length == 0) {
			setAttr(req, TIP_NAME_KEY, "请选择要撤消的用户角色");
			return this.filter(req, resp);
		}
		Userrole model = new Userrole();
		int[] results = model.batchDelete(deleteId);
		log.debug("batchDelete results[0]: " + results[0]);
		if (results.length > 0 && results[0] > 0) {
			setAttr(req, TIP_NAME_KEY, "成功撤消" + results[0] + "位用户的角色");
		}
		return this.filter(req, resp);
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
