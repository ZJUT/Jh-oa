package com.zjut.oa.mvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.domain.News;

public class NewsAction extends ActionAdapter {

	@Override
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		return super.show(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/news/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {

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
	@Result("/WEB-INF/pages/freeze/news/filter.jsp")
	public String filter(HttpServletRequest req, HttpServletResponse resp) {
		String title = param(req, "title");
		String stext = param(req, "stext");
		String userID = param(req, "userID");
		String orderby = param(req, "orderby");
		String order = param(req, "order");

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
				&& StringUtils.isNotBlank(userID)) {
			filter.append(" and userID=" + userID);
		} else if ((StringUtils.isBlank(title) && StringUtils.isBlank(stext))
				&& StringUtils.isNotBlank(userID)) {
			filter.append(" where userID=" + userID);
		}

		if (StringUtils.isNotBlank(orderby)
				&& (orderby.equals("id") || orderby.equals("title")
						|| orderby.equals("stext") || orderby.equals("userID") || orderby
						.equals("addtime"))) {
			if (StringUtils.isNotBlank(order)
					&& (order.equals("asc") || order.equals("desc"))) {
				filter.append(" order by " + orderby + " " + order);
			} else {
				filter.append(" order by " + orderby + " asc");
			}
		} else {
			filter.append(" order by id asc");
		}

		News model = new News();
		model.setTitle(title);
		model.setStext(stext);
		if(StringUtils.isNotBlank(userID))
			model.setUserID(Integer.parseInt(userID));
		
		List<News> dataList = (List<News>) model.listAll();
		
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
}
