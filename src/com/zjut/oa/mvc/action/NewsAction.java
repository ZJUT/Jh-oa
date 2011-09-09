package com.zjut.oa.mvc.action;

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
import com.zjut.oa.mvc.domain.News;
import com.zjut.oa.tool.CalendarTool;

public class NewsAction extends ActionAdapter {

	@Result("/WEB-INF/pages/freeze/news/show.jsp")
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		String id = param(req, "id");

		News model = new News();
		if (StringUtils.isNotBlank(id)) {
			model.setId(Long.parseLong(id));
			model = model.get(Long.parseLong(id));
		}
		
		setAttr(req, MODEL, model);

		return INPUT;
	}

	@Result("/WEB-INF/pages/freeze/news/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {

		return INPUT;
	}

	@Success(path = "/action/news/filter", isAction = true)
	@Fail(path = "/WEB-INF/pages/freeze/news/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		String title = param(req, "title");
		String content = param(req, "content");
		String stext = param(req, "stext");
		String username = param(req, "username");

		News model = new News();
		model.setTitle(title);
		model.setContent(content);
		model.setStext(stext);
		model.setUsername(username);

		setAttr(req, MODEL, model);

		if (StringUtils.isBlank(title)) {
			setAttr(req, TIP_NAME_KEY, "请输入标题");
			return FAIL;
		}
		if (StringUtils.isBlank(content)) {
			setAttr(req, TIP_NAME_KEY, "请输入内容");
			return FAIL;
		}
		if (StringUtils.isBlank(username)) {
			setAttr(req, TIP_NAME_KEY, "请先登录");
			return FAIL;
		}

		model.setAddtime(CalendarTool.now());
		model.setModifytime(CalendarTool.now());

		if (model.save() > 0) {
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "发布动态失败");
			return FAIL;
		}
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
	@Result("/WEB-INF/pages/freeze/news/filter.jsp")
	public String filter(HttpServletRequest req, HttpServletResponse resp) {
		String title = param(req, "title");
		String stext = param(req, "stext");
		String username = param(req, "username");
		String by = param(req, "by");
		String order = param(req, "order");

		News model = new News();
		model.setTitle(title);
		model.setStext(stext);
		model.setUsername(username);

		setAttr(req, MODEL, model);

		StringBuilder filter = new StringBuilder();
		if (StringUtils.isNotBlank(title)) {
			filter.append(" where title='" + title + "'");
		}

		if (StringUtils.isNotBlank(title) && StringUtils.isNotBlank(stext)) {
			filter.append(" and stext like '%" + stext + "%'");
		} else if (StringUtils.isBlank(title) && StringUtils.isNotBlank(stext)) {
			filter.append(" where stext like '%" + stext + "%'");
		}

		if ((StringUtils.isNotBlank(title) || StringUtils.isNotBlank(stext))
				&& StringUtils.isNotBlank(username)) {
			filter.append(" and userID='" + username + "'");
		} else if ((StringUtils.isBlank(title) && StringUtils.isBlank(stext))
				&& StringUtils.isNotBlank(username)) {
			filter.append(" where userID='" + username + "'");
		}

		if (StringUtils.isNotBlank(by)
				&& (by.equals("id") || by.equals("title") || by.equals("stext")
						|| by.equals("username") || by.equals("addtime") || by
						.equals("modifytime"))) {
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
		List<News> dataList = (List<News>) model.filterByPage(
				filter.toString(), p, pager.getCountPerPage());

		setAttr(req, CURRENT_PAGE_KEY, currentPage);
		setAttr(req, CURRENT_COUNT_PER_PAGE_KEY, countPerPage);
		setAttr(req, PAGER_KEY, pager);
		setAttr(req, MAX_PAGERSHOW_LENGTH_KEY, DEFAULT_MAX_PAGERSHOW_LENGTH);

		setAttr(req, DATA_LIST, dataList);

		return INPUT;
	}

	public String list(HttpServletRequest req, HttpServletResponse resp) {
		return super.list(req, resp);
	}

	public String listByPage(HttpServletRequest req, HttpServletResponse resp) {
		return super.listByPage(req, resp);
	}

	@Result("/WEB-INF/pages/anonymous/history.jsp")
	public String history(HttpServletRequest req, HttpServletResponse resp) {
		return INPUT;
	}

	@Result("/WEB-INF/pages/freeze/news/filter.jsp")
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp) {
		String[] deleteId = params(req, "deleteId");
		if (deleteId.length == 0) {
			setAttr(req, TIP_NAME_KEY, "请选择要删除的配置数据");
			return this.filter(req, resp);
		}
		News model = new News();
		int[] results = model.batchDelete(deleteId);
		log.debug("batchDelete results[0]: " + results[0]);
		if (results.length > 0 && results[0] > 0) {
			setAttr(req, TIP_NAME_KEY, "成功删除" + results[0] + "条动态");
		}
		return this.filter(req, resp);
	}

}
