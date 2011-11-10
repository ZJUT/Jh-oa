package com.zjut.oa.mvc.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;

import com.zjut.oa.db.Pager;
import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.Constant;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.None;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.Team;
import com.zjut.oa.mvc.domain.User;
import com.zjut.oa.mvc.domain.strengthen.TeamTogether;
import com.zjut.oa.tool.UploadTool;

public class TeamAction extends ActionAdapter {

	private static final Log log = LogFactory.getLog(TeamAction.class);

	@Override
	@Result("/WEB-INF/pages/freeze/team/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {

		User user = new User();
		setAttr(req, PAGE_TEAM_USER_LIST_KEY, user.listAll());

		return INPUT;
	}

	@SuppressWarnings({ "rawtypes", "rawtypes", "unchecked" })
	@None
	public String uploadFile(HttpServletRequest req, HttpServletResponse resp) {
		String savePath = req.getSession().getServletContext().getRealPath("/")
				+ UploadTool.TEAM_SAVE_DIR_NAME + "/";
		String saveUrl = req.getContextPath() + "/"
				+ UploadTool.TEAM_SAVE_DIR_NAME + "/";
		String[] fileTypes = UploadTool.IMAGE_ALLOW_FILE_SUFFIX;
		long maxSize = UploadTool.IMAGE_ALLOW_MAX_FILE_SIZE;

		String fileExt = "";
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e1) {
			log.error("获取PrintWriter对象发生异常", e1.getCause());
			return NONE;
		}
		if (!ServletFileUpload.isMultipartContent(req)) {
			out.println(UploadTool.getErrorJson("请选择文件"));
			return NONE;
		}
		File uploadDir = new File(savePath);
		if (!uploadDir.exists())
			uploadDir.mkdir();
		if (!uploadDir.isDirectory()) {
			out.println(UploadTool.getErrorJson("上传目录不存在"));
			return NONE;
		}
		if (!uploadDir.canWrite()) {
			out.println(UploadTool.getErrorJson("上传目录没有写权限"));
			return NONE;
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e1) {
			log.error("解析请求对象发生异常", e1.getCause());
			out.println(UploadTool.getErrorJson("获取文件异常"));
			return NONE;
		}
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			if (!item.isFormField()) {
				if (item.getSize() > maxSize) {
					out.println(UploadTool.getErrorJson("上传文件大小超过限制,最大为["
							+ maxSize + "]"));
					return NONE;
				}
				fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
						.toLowerCase();
				if (!Arrays.<String> asList(fileTypes).contains(fileExt)) {
					out.println(UploadTool.getErrorJson("上传文件扩展名是不允许的扩展名"));
					return NONE;
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
				} catch (Exception e) {
					out.println(UploadTool.getErrorJson("上传文件失败"));
					return NONE;
				}
				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + newFileName);
				out.println(obj.toJSONString());
				log.info("Upload headimage Successful , Headimage path -> "
						+ saveUrl + newFileName);
			}
		}
		return NONE;
	}

	@Override
	@Success(path = "/WEB-INF/pages/freeze/team/viewAdd.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/team/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		int userID = param(req, "userID", -1);
		String headimage = param(req, "headimage");

		Team model = new Team();
		model.setUserID(userID);
		model.setHeadimage(headimage);

		User user = new User();
		setAttr(req, PAGE_TEAM_USER_LIST_KEY, user.listAll());

		setAttr(req, MODEL, model);

		if (userID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择用户");
			return FAIL;
		}
		if (model.existProperty("userID", userID)) {
			setAttr(req, TIP_NAME_KEY, "此用户已在管理团队中，如需要更改请选择编辑功能");
			return FAIL;
		}
		if (StringUtils.isBlank(headimage)) {
			setAttr(req, TIP_NAME_KEY, "请上传用户头像");
			return FAIL;
		}

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "添加管理团队成员成功");
			model.setUserID(-1);
			model.setHeadimage("");

			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "添加管理团队成员失败");
			return FAIL;
		}

	}

	@Result("/WEB-INF/pages/freeze/team/filter.jsp")
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Team model = new Team();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		if (id == 0) {
			setAttr(req, TIP_NAME_KEY, "非法ID值");
		} else {
			model.setId(id);
			model = model.get(id);
			User user = new User();
			user = user.get(model.getUserID());
			// TODO more relative
			if (model.delete()) {
				setAttr(req, TIP_NAME_KEY, "成功删除[" + user.getUsername() + "]");
			} else {
				setAttr(req, TIP_NAME_KEY, "删除管理团队成员[" + user.getUsername()
						+ "]失败");
			}
		}
		return this.filter(req, resp);
	}

	@Override
	@Result("/WEB-INF/pages/freeze/team/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);
		Team model = new Team();
		model = model.get(id);

		User user = new User();

		setAttr(req, MODEL, model);
		setAttr(req, PAGE_TEAM_USER_LIST_KEY, user.listAll());

		return INPUT;
	}

	@Override
	@Success(path = "/WEB-INF/pages/freeze/team/viewModify.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/team/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		int userID = param(req, "userID", -1);
		String headimage = param(req, "headimage");

		Team model = new Team();
		model = model.get(id);

		User user = new User();
		setAttr(req, PAGE_TEAM_USER_LIST_KEY, user.listAll());

		if (userID == model.getUserID()
				&& StringUtils.equals(headimage, model.getHeadimage())) {
			setAttr(req, TIP_NAME_KEY, "无任何变更");
			setAttr(req, MODEL, model);
			return FAIL;
		}
		model.setUserID(userID);
		model.setHeadimage(headimage);

		setAttr(req, MODEL, model);

		if (userID == -1) {
			setAttr(req, TIP_NAME_KEY, "请先选择用户");
			return FAIL;
		}
		if (StringUtils.isBlank(headimage)) {
			setAttr(req, TIP_NAME_KEY, "请先上传用户头像");
			return FAIL;
		}

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "编辑管理团队成员成功");
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "编辑管理团队成员失败");
			return FAIL;
		}
	}

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/team/filter.jsp")
	public String filter(HttpServletRequest req, HttpServletResponse resp) {
		int userID = param(req, "userID", 0);

		String by = param(req, "by");
		String order = param(req, "order");

		Team model = new Team();
		model.setUserID(userID);

		setAttr(req, MODEL, model);

		StringBuilder filter = new StringBuilder();

		if (userID != 0) {
			filter.append(" where userID=" + userID);
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
		List<Team> dataList = (List<Team>) model.filterByPage(
				filter.toString(), p, pager.getCountPerPage());

		List<TeamTogether> ttList = new ArrayList<TeamTogether>();
		for (Team team : dataList) {
			TeamTogether tt = new TeamTogether();

			User user = new User();
			user = user.get(team.getUserID());

			tt.setId(team.getId());
			tt.setUser(user);
			tt.setHeadimage(team.getHeadimage());

			ttList.add(tt);
		}

		setAttr(req, CURRENT_PAGE_KEY, currentPage);
		setAttr(req, CURRENT_COUNT_PER_PAGE_KEY, countPerPage);
		setAttr(req, PAGER_KEY, pager);
		setAttr(req, MAX_PAGERSHOW_LENGTH_KEY, DEFAULT_MAX_PAGERSHOW_LENGTH);

		setAttr(req, DATA_LIST, ttList);

		return INPUT;
	}

}
