package com.zjut.oa.mvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zjut.oa.db.Pager;
import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.Constant;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.domain.Role;
import com.zjut.oa.mvc.domain.User;
import com.zjut.oa.mvc.domain.Userrole;

public class UserroleAction extends ActionAdapter {

	@Override
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		return super.show(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/userrole/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {
		
		User user=new User();
		Role role=new Role();
		
		setAttr(req,PAGE_USERROLE_USERLIST_KEY,user.listAll());
		setAttr(req,PAGE_USERROLE_ROLELIST_KEY,role.listAll());
		
		return INPUT;
	}

	@Override
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		return super.add(req, resp);
	}

	@Override
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		return super.delete(req, resp);
	}

	@Override
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		return super.viewModify(req, resp);
	}

	@Override
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		return super.modify(req, resp);
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

		setAttr(req, CURRENT_PAGE_KEY, currentPage);
		setAttr(req, CURRENT_COUNT_PER_PAGE_KEY, countPerPage);
		setAttr(req, PAGER_KEY, pager);
		setAttr(req, MAX_PAGERSHOW_LENGTH_KEY, DEFAULT_MAX_PAGERSHOW_LENGTH);

		setAttr(req, DATA_LIST, dataList);

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
