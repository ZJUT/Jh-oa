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
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.domain.Ke;

public class KeAction extends ActionAdapter {

	private static final Log log = LogFactory.getLog(KeAction.class);

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/ke/filter.jsp")
	public String filter(HttpServletRequest req, HttpServletResponse resp) {
		int userID = param(req, "userID", 0);

		String by = param(req, "by");
		String order = param(req, "order");

		Ke model = new Ke();
		model.setUserID(userID);

		if (userID != 0)
			model.setUserID(userID);

		setAttr(req, MODEL, model);

		StringBuilder filter = new StringBuilder();
		if (userID != 0) {
			filter.append(" where userID=" + userID);
		}

		if (StringUtils.isNotBlank(by)
				&& (by.equals("id") || by.equals("userID"))) {
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
		List<Ke> dataList = (List<Ke>) model.filterByPage(filter.toString(), p,
				pager.getCountPerPage());

		setAttr(req, CURRENT_PAGE_KEY, currentPage);
		setAttr(req, CURRENT_COUNT_PER_PAGE_KEY, countPerPage);
		setAttr(req, PAGER_KEY, pager);
		setAttr(req, MAX_PAGERSHOW_LENGTH_KEY, DEFAULT_MAX_PAGERSHOW_LENGTH);

		setAttr(req, DATA_LIST, dataList);

		return INPUT;
	}

	@Override
	@Result("/WEB-INF/pages/freeze/ke/viewAddMyself.jsp")
	public String viewAddMyself(HttpServletRequest req, HttpServletResponse resp) {

		String[] loginUser = ((String) getAttr(req.getSession(), LOGIN_USER_KEY))
				.split("&");
		String s_id = loginUser[0];

		Ke model = new Ke();
		model.setUserID(Integer.parseInt(s_id));

		setAttr(req, MODEL, model);

		return INPUT;
	}

	@Override
	public String addMyself(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return super.addMyself(req, resp);
	}

	@Override
	@Result("/WEB-INF/pages/freeze/ke/showMyself.jsp")
	public String showMyself(HttpServletRequest req, HttpServletResponse resp) {
		
		String[] loginUser = ((String) getAttr(req.getSession(), LOGIN_USER_KEY))
				.split("&");
		String s_id = loginUser[0];

		Ke model = new Ke();
		model.setUserID(Integer.parseInt(s_id));

		setAttr(req, MODEL, model);

		return INPUT;
	}

}
