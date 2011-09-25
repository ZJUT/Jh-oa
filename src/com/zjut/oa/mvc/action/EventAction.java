package com.zjut.oa.mvc.action;

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
import com.zjut.oa.mvc.domain.Event;
import com.zjut.oa.tool.CalendarTool;

public class EventAction extends ActionAdapter {

	private static final Log log=LogFactory.getLog(EventAction.class);
	
	
	@Result("/WEB-INF/pages/freeze/event/show.jsp")
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		String id = param(req, "id");

		Event model = new Event();
		if (StringUtils.isNotBlank(id)) {
			model.setId(Long.parseLong(id));
			model = model.get(Long.parseLong(id));
		}

		setAttr(req, MODEL, model);

		return INPUT;
	}

	@Result("/WEB-INF/pages/freeze/event/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {
		return INPUT;
	}

	@Success(path = "/action/event/filter", isAction = true)
	@Fail(path = "/WEB-INF/pages/freeze/event/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		String title = param(req, "title");
		String content = param(req, "content");

		Event model = new Event();
		model.setTitle(title);
		model.setContent(content);

		setAttr(req, MODEL, model);

		if (StringUtils.isBlank(title)) {
			setAttr(req, TIP_NAME_KEY, "请输入标题");
			return FAIL;
		}
		if (StringUtils.isBlank(content)) {
			setAttr(req, TIP_NAME_KEY, "请输入内容");
			return FAIL;
		}

		model.setModifytime(CalendarTool.now());

		if (model.save() > 0) {
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "发布大事件失败");
			return FAIL;
		}
	}

	@Result("/WEB-INF/pages/freeze/event/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Event model = new Event();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		setAttr(req, MODEL, model);

		return INPUT;
	}

	@Success(path = "/action/event/filter", isAction = true)
	@Fail(path = "/WEB-INF/pages/freeze/event/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);
		String title = param(req, "title");
		String content = param(req, "content");

		Event model = new Event();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}
		model.setTitle(title);
		model.setContent(content);

		setAttr(req, MODEL, model);

		if (StringUtils.isBlank(title)) {
			setAttr(req, TIP_NAME_KEY, "请输入标题");
			return FAIL;
		}
		if (StringUtils.isBlank(content)) {
			setAttr(req, TIP_NAME_KEY, "请输入内容");
			return FAIL;
		}

		model.setModifytime(CalendarTool.now());

		if (model.save() > 0) {
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "编辑事件失败");
			return FAIL;
		}
	}

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/event/filter.jsp")
	public String filter(HttpServletRequest req, HttpServletResponse resp) {
		String title = param(req, "title");

		String by = param(req, "by");
		String order = param(req, "order");

		Event model = new Event();
		model.setTitle(title);

		setAttr(req, MODEL, model);

		StringBuilder filter = new StringBuilder();
		if (StringUtils.isNotBlank(title)) {
			filter.append(" where title='" + title + "'");
		}

		if (StringUtils.isNotBlank(by)
				&& (by.equals("id") || by.equals("title") || by
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
		List<Event> dataList = (List<Event>) model.filterByPage(
				filter.toString(), p, pager.getCountPerPage());

		setAttr(req, CURRENT_PAGE_KEY, currentPage);
		setAttr(req, CURRENT_COUNT_PER_PAGE_KEY, countPerPage);
		setAttr(req, PAGER_KEY, pager);
		setAttr(req, MAX_PAGERSHOW_LENGTH_KEY, DEFAULT_MAX_PAGERSHOW_LENGTH);

		setAttr(req, DATA_LIST, dataList);

		return INPUT;
	}

	@Result("/WEB-INF/pages/freeze/event/filter.jsp")
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp) {
		String[] deleteId = params(req, "deleteId");
		if (deleteId.length == 0) {
			setAttr(req, TIP_NAME_KEY, "请选择要删除的大事件");
			return this.filter(req, resp);
		}
		Event model = new Event();
		int[] results = model.batchDelete(deleteId);
		log.debug("batchDelete results[0]: " + results[0]);
		if (results.length > 0 && results[0] > 0) {
			setAttr(req, TIP_NAME_KEY, "成功删除" + results[0] + "条大事件");
		}
		return this.filter(req, resp);
	}

}
