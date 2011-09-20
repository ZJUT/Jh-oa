package com.zjut.oa.mvc.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.db.Pager;
import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.Constant;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.Menu;
import com.zjut.oa.mvc.domain.Operator;
import com.zjut.oa.mvc.domain.Permission;
import com.zjut.oa.mvc.domain.Resource;
import com.zjut.oa.mvc.domain.strengthen.PermissionTogether;

public class PermissionAction extends ActionAdapter {

	private static final Log log = LogFactory.getLog(PermissionAction.class);
	
	@Result("/WEB-INF/pages/freeze/permission/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {

		Menu menu = new Menu();
		Resource resource = new Resource();
		Operator operator = new Operator();

		setAttr(req, PAGE_PERMISSION_MENULIST_KEY, menu.listAll());
		setAttr(req, PAGE_PERMISSION_RESOURCELIST_KEY, resource.listAll());
		setAttr(req, PAGE_PERMISSION_OPERATORLIST_KEY, operator.listAll());

		return INPUT;
	}

	@SuppressWarnings("unchecked")
	@Success(path = "/WEB-INF/pages/freeze/permission/viewAdd.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/permission/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		int menuID = param(req, "menuID", 0);
		int resourceID = param(req, "resourceID", 0);
		int optID = param(req, "optID", 0);
		String description = param(req, "description");

		Permission model = new Permission();
		model.setMenuID(menuID);
		model.setResourceID(resourceID);
		model.setOptID(optID);
		model.setDescription(description);

		Menu menu = new Menu();
		Resource resource = new Resource();
		Operator operator = new Operator();

		setAttr(req, PAGE_PERMISSION_MENULIST_KEY, menu.listAll());
		setAttr(req, PAGE_PERMISSION_RESOURCELIST_KEY, resource.listAll());
		setAttr(req, PAGE_PERMISSION_OPERATORLIST_KEY, operator.listAll());

		setAttr(req, MODEL, model);

		if (menuID == 0 || menuID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择菜单");
			return FAIL;
		}
		if (resourceID == 0 || resourceID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择资源");
			return FAIL;
		}
		if (optID == 0 || optID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择操作");
			return FAIL;
		}
		if (StringUtils.isBlank(description)) {
			setAttr(req, TIP_NAME_KEY, "请输入描述");
			return FAIL;
		}

		menu.setId(menuID);
		menu = menu.get(menuID);

		resource.setId(resourceID);
		resource = resource.get(resourceID);

		operator.setId(optID);
		operator = operator.get(optID);

		List<Permission> all = (List<Permission>) model.filter(" where menuID="
				+ menuID + " and resourceID=" + resourceID + " and optID="
				+ optID);
		Permission isExist = (all.size() == 1) ? all.get(0) : null;
		if (isExist != null) {
			setAttr(req,
					TIP_NAME_KEY,
					"添加权限失败; 已存在[" + menu.getMenuname() + "]下对["
							+ resource.getResourcename() + "]的["
							+ operator.getOptname()
							+ "]权限; 想要修改权限描述请在列表页面点击\"编辑\"链接!");
			return FAIL;
		}

		if (model.save() > 0) {
			setAttr(req,
					TIP_NAME_KEY,
					"添加[" + menu.getMenuname() + "]下对["
							+ resource.getResourcename() + "]的["
							+ operator.getOptname() + "]的权限成功");
//			model.setMenuID(-1);
//			model.setResourceID(-1);
//			model.setOptID(-1);
//			model.setDescription("");
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "添加权限失败");
			return FAIL;
		}

	}

	@Result("/WEB-INF/pages/freeze/permission/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Permission model = new Permission();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		Menu menu = new Menu();
		Resource resource = new Resource();
		Operator operator = new Operator();

		setAttr(req, PAGE_PERMISSION_MENULIST_KEY, menu.listAll());
		setAttr(req, PAGE_PERMISSION_RESOURCELIST_KEY, resource.listAll());
		setAttr(req, PAGE_PERMISSION_OPERATORLIST_KEY, operator.listAll());

		setAttr(req, MODEL, model);

		return INPUT;
	}

	@SuppressWarnings("unchecked")
	@Success(path = "/WEB-INF/pages/freeze/permission/viewModify.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/permission/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);
		int menuID = param(req, "menuID", 0);
		int resourceID = param(req, "resourceID", 0);
		int optID = param(req, "optID", 0);
		String description = param(req, "description");

		Permission model = new Permission();

		Menu menu = new Menu();
		Resource resource = new Resource();
		Operator operator = new Operator();

		setAttr(req, PAGE_PERMISSION_MENULIST_KEY, menu.listAll());
		setAttr(req, PAGE_PERMISSION_RESOURCELIST_KEY, resource.listAll());
		setAttr(req, PAGE_PERMISSION_OPERATORLIST_KEY, operator.listAll());

		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		int pre_menuID = model.getMenuID();
		int pre_resourceID = model.getResourceID();
		int pre_optID = model.getOptID();
		String pre_description = model.getDescription();

		if (pre_menuID == 0) {
			setAttr(req, TIP_NAME_KEY, "加载权限失败");
			return FAIL;
		}

		if (pre_menuID == menuID && pre_resourceID == resourceID
				&& pre_optID == optID
				&& StringUtils.equals(pre_description, description)) {
			setAttr(req, TIP_NAME_KEY, "无任何修改");
			model.setMenuID(pre_menuID);
			model.setResourceID(pre_resourceID);
			model.setOptID(pre_optID);
			model.setDescription(pre_description);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (menuID == 0 || menuID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择菜单");
			model.setMenuID(pre_menuID);
			model.setResourceID(pre_resourceID);
			model.setOptID(pre_optID);
			model.setDescription(pre_description);
			setAttr(req, MODEL, model);
			return FAIL;
		}
		if (resourceID == 0 || resourceID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择资源");
			model.setMenuID(pre_menuID);
			model.setResourceID(pre_resourceID);
			model.setOptID(pre_optID);
			model.setDescription(pre_description);
			setAttr(req, MODEL, model);
			return FAIL;
		}
		if (optID == 0 || optID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择操作");
			model.setMenuID(pre_menuID);
			model.setResourceID(pre_resourceID);
			model.setOptID(pre_optID);
			model.setDescription(pre_description);
			setAttr(req, MODEL, model);
			return FAIL;
		}
		if (StringUtils.isBlank(description)) {
			setAttr(req, TIP_NAME_KEY, "请输入描述");
			model.setMenuID(pre_menuID);
			model.setResourceID(pre_resourceID);
			model.setOptID(pre_optID);
			model.setDescription(pre_description);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		model.setMenuID(menuID);
		model.setResourceID(resourceID);
		model.setOptID(optID);
		model.setDescription(description);
		setAttr(req, MODEL, model);

		menu.setId(menuID);
		menu = menu.get(menuID);

		resource.setId(resourceID);
		resource = resource.get(resourceID);

		operator.setId(optID);
		operator = operator.get(optID);

		List<Permission> all = (List<Permission>) model.filter(" where menuID="
				+ menuID + " and resourceID=" + resourceID + " and optID="
				+ optID);
		Permission isExist = (all.size() == 1) ? all.get(0) : null;
		if (isExist != null && isExist.getId() != id) {
			int exist_menuID = isExist.getMenuID();
			menu.setId(exist_menuID);
			menu = menu.get(exist_menuID);
			String exist_menuname = menu.getMenuname();

			int exist_resourceID = isExist.getResourceID();
			resource.setId(exist_resourceID);
			resource = resource.get(exist_resourceID);
			String exist_resourcename = resource.getResourcename();

			int exist_optID = isExist.getOptID();
			operator.setId(exist_optID);
			operator = operator.get(exist_optID);
			String exist_optname = operator.getOptname();
			setAttr(req, TIP_NAME_KEY, "已存在[" + exist_menuname + "]下对["
					+ exist_resourcename + "][" + exist_optname + "]的权限");
			return FAIL;
		}

		if (model.save() > 0) {
			StringBuilder tip = new StringBuilder();
			tip.append("编辑权限成功; ");
			if (pre_menuID != menuID) {
				menu.setId(pre_menuID);
				menu = menu.get(pre_menuID);
				String pre_menuname = menu.getMenuname();

				menu.setId(menuID);
				menu = menu.get(menuID);
				String menuname = menu.getMenuname();

				tip.append("菜单[" + pre_menuname + "]->[" + menuname + "]; ");
			}
			if (pre_resourceID != resourceID) {
				resource.setId(pre_resourceID);
				resource = resource.get(pre_resourceID);
				String pre_resourcename = resource.getResourcename();

				resource.setId(resourceID);
				resource = resource.get(resourceID);
				String resourcename = resource.getResourcename();

				tip.append("资源[" + pre_resourcename + "]->[" + resourcename
						+ "]; ");
			}
			if (pre_optID != optID) {
				operator.setId(pre_optID);
				operator = operator.get(pre_optID);
				String pre_optname = operator.getOptname();

				operator.setId(optID);
				operator = operator.get(optID);
				String optname = operator.getOptname();

				tip.append("操作[" + pre_optname + "]->[" + optname + "]; ");
			}
			if (!StringUtils.equals(pre_description, description)) {
				tip.append("描述[" + pre_description + "]->[" + description
						+ "]; ");
			}
			setAttr(req, TIP_NAME_KEY, tip.toString());
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "编辑权限失败");
			return FAIL;
		}
	}

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/permission/filter.jsp")
	public String filter(HttpServletRequest req, HttpServletResponse resp) {
		int menuID = param(req, "menuID", 0);
		int resourceID = param(req, "resourceID", 0);
		int optID = param(req, "optID", 0);

		String by = param(req, "by");
		String order = param(req, "order");

		Permission model = new Permission();
		model.setMenuID(menuID);
		model.setResourceID(resourceID);
		model.setOptID(optID);

		setAttr(req, MODEL, model);

		StringBuilder filter = new StringBuilder();
		if (menuID != 0) {
			filter.append(" where menuID =" + menuID);
		}

		if (menuID != 0 && resourceID != 0) {
			filter.append(" and resourceID  =" + resourceID);
		} else if (menuID == 0 && resourceID != 0) {
			filter.append(" where resourceID  =" + resourceID);
		}

		if ((menuID != 0 || resourceID != 0) && optID != 0) {
			filter.append(" and optID  =" + optID);
		} else if (menuID == 0 && resourceID == 0 && optID != 0) {
			filter.append(" where optID  =" + optID);
		}

		if (StringUtils.isNotBlank(by)
				&& (by.equals("id") || by.equals("menuID")
						|| by.equals("resourceID") || by.equals("optID"))) {
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
		List<Permission> dataList = (List<Permission>) model.filterByPage(
				filter.toString(), p, pager.getCountPerPage());

		// 填充组合对象
		List<PermissionTogether> allDataList = new ArrayList<PermissionTogether>();
		for (Permission permission : dataList) {
			int tmp_menuID = permission.getMenuID();
			int tmp_resourceID = permission.getResourceID();
			int tmp_optID = permission.getOptID();

			Menu prepare_menu = new Menu();
			prepare_menu = prepare_menu.get(tmp_menuID);

			Resource prepare_resource = new Resource();
			prepare_resource = prepare_resource.get(tmp_resourceID);

			Operator prepare_operator = new Operator();
			prepare_operator = prepare_operator.get(tmp_optID);

			PermissionTogether pt = new PermissionTogether();
			pt.setId(permission.getId());
			pt.setMenu(prepare_menu);
			pt.setResource(prepare_resource);
			pt.setOperator(prepare_operator);
			pt.setDescription(permission.getDescription());

			allDataList.add(pt);
		}
		setAttr(req, CURRENT_PAGE_KEY, currentPage);
		setAttr(req, CURRENT_COUNT_PER_PAGE_KEY, countPerPage);
		setAttr(req, PAGER_KEY, pager);
		setAttr(req, MAX_PAGERSHOW_LENGTH_KEY, DEFAULT_MAX_PAGERSHOW_LENGTH);

		setAttr(req, DATA_LIST, allDataList);

		return INPUT;
	}

	@Result("/WEB-INF/pages/freeze/permission/filter.jsp")
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp) {
		String[] deleteId = params(req, "deleteId");
		if (deleteId.length == 0) {
			setAttr(req, TIP_NAME_KEY, "请选择要删除的权限");
			return this.filter(req, resp);
		}
		Permission model = new Permission();
		int[] results = model.batchDelete(deleteId);
		log.debug("batchDelete results[0]: " + results[0]);
		if (results.length > 0 && results[0] > 0) {
			setAttr(req, TIP_NAME_KEY, "成功删除" + results[0] + "个权限");
		}
		return this.filter(req, resp);
	}

}
