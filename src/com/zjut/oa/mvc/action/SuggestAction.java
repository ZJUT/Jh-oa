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
import com.zjut.oa.mvc.domain.Suggest;
import com.zjut.oa.mvc.domain.User;
import com.zjut.oa.tool.CalendarTool;

public class SuggestAction extends ActionAdapter {

	private static final Log log = LogFactory.getLog(SuggestAction.class);

	@Result("/WEB-INF/pages/freeze/suggest/show.jsp")
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		String id = param(req, "id");

		Suggest model = new Suggest();
		if (StringUtils.isNotBlank(id)) {
			model.setId(Long.parseLong(id));
			model = model.get(Long.parseLong(id));
		}
		// 加载反馈者信息
		User user = new User();
		user = user.get(model.getUserID());
		setAttr(req, PAGE_SUGGEST_USER_MODEL_KEY, user);

		// 加载回复者信息
		if (model.getReplyUserID() != 0) {
			User reply = new User();
			reply = reply.get(model.getReplyUserID());
			setAttr(req, PAGE_SUGGEST_REPLY_MODEL_KEY, reply);
		}

		setAttr(req, MODEL, model);

		return INPUT;
	}

	@Override
	@Result("/WEB-INF/pages/freeze/suggest/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		String id = param(req, "id");

		Suggest model = new Suggest();
		if (StringUtils.isNotBlank(id)) {
			model.setId(Long.parseLong(id));
			model = model.get(Long.parseLong(id));
		}

		setAttr(req, MODEL, model);
		return INPUT;
	}

	@Override
	@Success(path = "/WEB-INF/pages/freeze/suggest/viewModify.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/suggest/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		String reply = param(req, "reply");
		int replyUserID = param(req, "replyUserID", 0);

		Suggest model = new Suggest();
		if (id != 0)
			model = model.get(id);
		model.setReply(reply);
		if (replyUserID != 0)
			model.setReplyUserID(replyUserID);
		setAttr(req, MODEL, model);

		if (model.getUserID() == replyUserID) {
			setAttr(req, TIP_NAME_KEY, "自己的反馈自己回复有意思嘛，你？驳回~~~");
			return FAIL;
		}
		if (StringUtils.isBlank(reply)) {
			setAttr(req, TIP_NAME_KEY, "请输入回复内容");
			return FAIL;
		}

		if(StringUtils.isNotBlank(reply) && StringUtils.equals(model.getReply(), reply)){
			setAttr(req, TIP_NAME_KEY, "回复内容未修改");
			return FAIL;
		}
		
		if (replyUserID == 0) {
			setAttr(req, TIP_NAME_KEY, "请先登录");
			return FAIL;
		}

		
		
		model.setReplytime(CalendarTool.now());

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "回复反馈成功");
			model.setReply("");
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "回复反馈失败");
			return FAIL;
		}

	}

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/suggest/filter.jsp")
	public String filter(HttpServletRequest req, HttpServletResponse resp) {
		String stext = param(req, "stext");

		String by = param(req, "by");
		String order = param(req, "order");

		Suggest model = new Suggest();
		model.setStext(stext);

		setAttr(req, MODEL, model);

		StringBuilder filter = new StringBuilder();

		if (StringUtils.isNotBlank(stext)) {
			filter.append(" where stext like '%" + stext + "%'");
		}

		if (StringUtils.isNotBlank(by)
				&& (by.equals("id") || by.equals("stext") || by
						.equals("addtime"))) {
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
		List<Suggest> dataList = (List<Suggest>) model.filterByPage(
				filter.toString(), p, pager.getCountPerPage());

		setAttr(req, CURRENT_PAGE_KEY, currentPage);
		setAttr(req, CURRENT_COUNT_PER_PAGE_KEY, countPerPage);
		setAttr(req, PAGER_KEY, pager);
		setAttr(req, MAX_PAGERSHOW_LENGTH_KEY, DEFAULT_MAX_PAGERSHOW_LENGTH);

		setAttr(req, DATA_LIST, dataList);

		return INPUT;
	}

	@Result("/WEB-INF/pages/freeze/suggest/showMyself.jsp")
	public String showMyself(HttpServletRequest req, HttpServletResponse resp) {
		String id = param(req, "id");

		Suggest model = new Suggest();
		if (StringUtils.isNotBlank(id)) {
			model.setId(Long.parseLong(id));
			model = model.get(Long.parseLong(id));
		}
		// 加载反馈者信息
		User user = new User();
		user = user.get(model.getUserID());
		setAttr(req, PAGE_SUGGEST_USER_MODEL_KEY, user);

		// 加载回复者信息
		if (model.getReplyUserID() != 0) {
			User reply = new User();
			reply = reply.get(model.getReplyUserID());
			setAttr(req, PAGE_SUGGEST_REPLY_MODEL_KEY, reply);
		}

		setAttr(req, MODEL, model);

		return INPUT;
	}

	@Override
	@Result("/WEB-INF/pages/freeze/suggest/viewAddMyself.jsp")
	public String viewAddMyself(HttpServletRequest req, HttpServletResponse resp) {
		return INPUT;
	}

	@Success(path = "/WEB-INF/pages/freeze/suggest/viewAddMyself.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/suggest/viewAddMyself.jsp")
	public String addMyself(HttpServletRequest req, HttpServletResponse resp) {
		String content = param(req, "content");
		String stext = param(req, "stext");
		int userID = param(req, "userID", 0);

		Suggest model = new Suggest();
		model.setContent(content);
		model.setStext(stext);
		if (userID != 0)
			model.setUserID(userID);
		// 设置回复内容为空，回复人为不存在的用户
		model.setReply("");
		model.setReplyUserID(0);

		setAttr(req, MODEL, model);

		if (StringUtils.isBlank(content)) {
			setAttr(req, TIP_NAME_KEY, "请输入反馈内容");
			return FAIL;
		}
		if (userID == 0) {
			setAttr(req, TIP_NAME_KEY, "请先登录");
			return FAIL;
		}

		model.setAddtime(CalendarTool.now());

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "谢谢您的反馈!");
			model.setContent("");
			model.setStext("");
			return SUCCESS;
		} else {
			setAttr(req,
					TIP_NAME_KEY,
					"发布反馈失败,您可以选择发送邮件给系统开发者(<a href='mailto:qingtian16265@gmail.com' title='发送邮件给晴天'>现在就发邮件给晴天?</a>)");
			return FAIL;
		}
	}

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/suggest/filterMyself.jsp")
	public String filterMyself(HttpServletRequest req, HttpServletResponse resp) {
		String stext = param(req, "stext");
		// 会话用户
		String[] loginUser = ((String) getAttr(req.getSession(), LOGIN_USER_KEY))
				.split("&");
		String userID = loginUser[0];

		String by = param(req, "by");
		String order = param(req, "order");

		Suggest model = new Suggest();
		model.setStext(stext);

		setAttr(req, MODEL, model);

		StringBuilder filter = new StringBuilder();

		if (StringUtils.isNotBlank(stext)) {
			filter.append(" where stext like '%" + stext + "%'");
		}
		
		if (StringUtils.isBlank(stext) && StringUtils.isNotBlank(userID)) {
			filter.append(" where userID =" + userID);
		} else if (StringUtils.isNotBlank(stext)
				&& StringUtils.isNotBlank(userID)) {
			filter.append(" and userID =" + userID);
		}

		if (StringUtils.isNotBlank(by)
				&& (by.equals("id") || by.equals("stext") || by
						.equals("addtime"))) {
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
		List<Suggest> dataList = (List<Suggest>) model.filterByPage(
				filter.toString(), p, pager.getCountPerPage());

		setAttr(req, CURRENT_PAGE_KEY, currentPage);
		setAttr(req, CURRENT_COUNT_PER_PAGE_KEY, countPerPage);
		setAttr(req, PAGER_KEY, pager);
		setAttr(req, MAX_PAGERSHOW_LENGTH_KEY, DEFAULT_MAX_PAGERSHOW_LENGTH);

		setAttr(req, DATA_LIST, dataList);

		return INPUT;
	}

	@Result("/WEB-INF/pages/freeze/suggest/filterMyself.jsp")
	public String batchDeleteMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		String[] deleteId = params(req, "deleteId");
		if (deleteId.length == 0) {
			setAttr(req, TIP_NAME_KEY, "请选择要删除的反馈");
			return this.filterMyself(req, resp);
		}
		Suggest model = new Suggest();
		int[] results = model.batchDelete(deleteId);
		log.debug("batchDelete results[0]: " + results[0]);
		if (results.length > 0 && results[0] > 0) {
			setAttr(req, TIP_NAME_KEY, "成功删除" + results[0] + "条反馈");
		}
		return this.filterMyself(req, resp);
	}

}
