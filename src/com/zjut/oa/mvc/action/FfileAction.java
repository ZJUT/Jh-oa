package com.zjut.oa.mvc.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import com.zjut.oa.mvc.domain.Ffile;
import com.zjut.oa.mvc.domain.Ffile;
import com.zjut.oa.mvc.domain.User;
import com.zjut.oa.tool.CalendarTool;
import com.zjut.oa.tool.UploadTool;

public class FfileAction extends ActionAdapter {

	private static final Log log = LogFactory.getLog(FfileAction.class);

	@Result("/WEB-INF/pages/freeze/ffile/show.jsp")
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		String id = param(req, "id");

		Ffile model = new Ffile();
		if (StringUtils.isNotBlank(id)) {
			model.setId(Long.parseLong(id));
			model = model.get(Long.parseLong(id));
		}

		User user = new User();
		user = user.get(model.getUserID());

		setAttr(req, PAGE_FFILE_USER_MODEL_KEY, user);
		setAttr(req, MODEL, model);

		return INPUT;
	}

	@Result("/WEB-INF/pages/freeze/ffile/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {
		return INPUT;
	}

	@SuppressWarnings({ "rawtypes", "rawtypes", "unchecked" })
	@None
	public String uploadFile(HttpServletRequest req, HttpServletResponse resp) {
		String savePath = req.getSession().getServletContext().getRealPath("/")
				+ UploadTool.FILE_SAVE_DIR_NAME + "/";
		String saveUrl = req.getContextPath() + "/"
				+ UploadTool.FILE_SAVE_DIR_NAME + "/";
		String[] fileTypes = UploadTool.FILE_ALLOW_FILE_SUFFIX;
		long maxSize = UploadTool.FILE_ALLOW_MAX_FILE_SIZE;

		String fileExt = "";
		long size = 0;
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
					size = uploadedFile.length();
				} catch (Exception e) {
					out.println(UploadTool.getErrorJson("上传文件失败"));
					return NONE;
				}
				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("size", size);
				obj.put("suffix", fileExt);
				obj.put("url", saveUrl + newFileName);
				out.println(obj.toJSONString());
				log.info("Upload attached Successful , File path -> " + saveUrl
						+ newFileName + ", Size -> " + size + ", suffix -> "
						+ fileExt);
			}
		}
		return NONE;

	}

	@Override
	@Success(path = "/WEB-INF/pages/freeze/ffile/viewAdd.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/ffile/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		String showname = param(req, "showname");
		String filename = param(req, "filename");
		String suffix = param(req, "suffix");
		int size = param(req, "size", 0);
		int userID = param(req, "userID", 0);

		Ffile model = new Ffile();
		model.setFilename(filename);
		model.setShowname(showname);
		model.setUserID(userID);
		model.setSize(size);
		model.setSuffix(suffix);

		setAttr(req, MODEL, model);

		if (StringUtils.isBlank(showname)) {
			setAttr(req, TIP_NAME_KEY, "请输入文件显示名称");
			return FAIL;
		}
		if (StringUtils.isBlank(filename)) {
			setAttr(req, TIP_NAME_KEY, "请先选择要发布的文件");
			return FAIL;
		}
		if (userID == 0) {
			setAttr(req, TIP_NAME_KEY, "请先登录");
			return FAIL;
		}
		model.setAddtime(CalendarTool.now());

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "发布文件成功");
			model.setFilename("");
			model.setShowname("");
			model.setSize(0);
			model.setSuffix("");
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "发布文件失败");
			return FAIL;
		}
	}

	@Result("/WEB-INF/pages/freeze/ffile/filter.jsp")
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Ffile model = new Ffile();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		if (id == 0) {
			setAttr(req, TIP_NAME_KEY, "非法ID值");
		} else {
			model.setId(id);
			if (model.delete()) {
				setAttr(req, TIP_NAME_KEY, "成功删除[" + model.getShowname() + "]");
			} else {
				setAttr(req, TIP_NAME_KEY, "删除文件[" + model.getShowname()
						+ "]失败");
			}
		}
		return this.filter(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/ffile/filterMyself.jsp")
	public String deleteMyself(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Ffile model = new Ffile();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		if (id == 0) {
			setAttr(req, TIP_NAME_KEY, "非法ID值");
		} else {
			model.setId(id);
			if (model.delete()) {
				setAttr(req, TIP_NAME_KEY, "成功删除[" + model.getShowname() + "]");
			} else {
				setAttr(req, TIP_NAME_KEY, "删除文件[" + model.getShowname()
						+ "]失败");
			}
		}
		return this.filterMyself(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/ffile/filter.jsp")
	public String filter(HttpServletRequest req, HttpServletResponse resp) {
		String showname = param(req, "showname");

		String by = param(req, "by");
		String order = param(req, "order");

		Ffile model = new Ffile();
		model.setShowname(showname);

		setAttr(req, MODEL, model);

		StringBuilder filter = new StringBuilder();
		if (StringUtils.isNotBlank(showname)) {
			filter.append(" where showname like '%" + showname + "%'");
		}

		if (StringUtils.isNotBlank(by)
				&& (by.equals("id") || by.equals("filename"))) {
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
		List<Ffile> dataList = (List<Ffile>) model.filterByPage(
				filter.toString(), p, pager.getCountPerPage());

		setAttr(req, CURRENT_PAGE_KEY, currentPage);
		setAttr(req, CURRENT_COUNT_PER_PAGE_KEY, countPerPage);
		setAttr(req, PAGER_KEY, pager);
		setAttr(req, MAX_PAGERSHOW_LENGTH_KEY, DEFAULT_MAX_PAGERSHOW_LENGTH);

		setAttr(req, DATA_LIST, dataList);

		return INPUT;
	}

	@Result("/WEB-INF/pages/freeze/ffile/filter.jsp")
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp) {
		String[] deleteId = params(req, "deleteId");
		if (deleteId.length == 0) {
			setAttr(req, TIP_NAME_KEY, "请选择要删除的文件");
			return this.filter(req, resp);
		}
		Ffile model = new Ffile();
		int[] results = model.batchDelete(deleteId);
		log.debug("batchDelete results[0]: " + results[0]);
		if (results.length > 0 && results[0] > 0) {
			setAttr(req, TIP_NAME_KEY, "成功删除" + results[0] + "个文件");
		}
		return this.filter(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/ffile/viewAddMyself.jsp")
	public String viewAddMyself(HttpServletRequest req, HttpServletResponse resp) {
		return INPUT;
	}

	@Success(path = "/WEB-INF/pages/freeze/ffile/viewAddMyself.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/ffile/viewAddMyself.jsp")
	public String addMyself(HttpServletRequest req, HttpServletResponse resp) {
		String showname = param(req, "showname");
		String filename = param(req, "filename");
		String suffix = param(req, "suffix");
		int size = param(req, "size", 0);
		int userID = param(req, "userID", 0);

		Ffile model = new Ffile();
		model.setFilename(filename);
		model.setShowname(showname);
		model.setUserID(userID);
		model.setSize(size);
		model.setSuffix(suffix);

		setAttr(req, MODEL, model);

		if (StringUtils.isBlank(showname)) {
			setAttr(req, TIP_NAME_KEY, "请输入文件显示名称");
			return FAIL;
		}
		if (StringUtils.isBlank(filename)) {
			setAttr(req, TIP_NAME_KEY, "请先选择要发布的文件");
			return FAIL;
		}
		if (userID == 0) {
			setAttr(req, TIP_NAME_KEY, "请先登录");
			return FAIL;
		}
		model.setAddtime(CalendarTool.now());

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "发布文件成功");
			model.setFilename("");
			model.setShowname("");
			model.setSize(0);
			model.setSuffix("");
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "发布文件失败");
			return FAIL;
		}
	}

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/ffile/filterMyself.jsp")
	public String filterMyself(HttpServletRequest req, HttpServletResponse resp) {
		String showname = param(req, "showname");
		// 会话用户
		String[] loginUser = ((String) getAttr(req.getSession(), LOGIN_USER_KEY))
				.split("&");
		String userID = loginUser[0];

		String by = param(req, "by");
		String order = param(req, "order");

		Ffile model = new Ffile();
		model.setShowname(showname);

		setAttr(req, MODEL, model);

		StringBuilder filter = new StringBuilder();
		if (StringUtils.isNotBlank(showname)) {
			filter.append(" where showname like '%" + showname + "%'");
		}

		if (StringUtils.isBlank(showname) && StringUtils.isNotBlank(userID)) {
			filter.append(" where userID =" + userID);
		} else if (StringUtils.isNotBlank(showname)
				&& StringUtils.isNotBlank(userID)) {
			filter.append(" and userID =" + userID);
		}

		if (StringUtils.isNotBlank(by)
				&& (by.equals("id") || by.equals("filename"))) {
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
		List<Ffile> dataList = (List<Ffile>) model.filterByPage(
				filter.toString(), p, pager.getCountPerPage());

		setAttr(req, CURRENT_PAGE_KEY, currentPage);
		setAttr(req, CURRENT_COUNT_PER_PAGE_KEY, countPerPage);
		setAttr(req, PAGER_KEY, pager);
		setAttr(req, MAX_PAGERSHOW_LENGTH_KEY, DEFAULT_MAX_PAGERSHOW_LENGTH);

		setAttr(req, DATA_LIST, dataList);

		return INPUT;
	}

	@Result("/WEB-INF/pages/freeze/ffile/filterMyself.jsp")
	public String batchDeleteMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		String[] deleteId = params(req, "deleteId");
		if (deleteId.length == 0) {
			setAttr(req, TIP_NAME_KEY, "请选择要删除的文件");
			return this.filterMyself(req, resp);
		}
		Ffile model = new Ffile();
		int[] results = model.batchDelete(deleteId);
		log.debug("batchDelete results[0]: " + results[0]);
		if (results.length > 0 && results[0] > 0) {
			setAttr(req, TIP_NAME_KEY, "成功删除" + results[0] + "个文件");
		}
		return this.filterMyself(req, resp);
	}

}
