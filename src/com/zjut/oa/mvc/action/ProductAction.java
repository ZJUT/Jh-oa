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

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.None;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.Product;
import com.zjut.oa.tool.CalendarTool;
import com.zjut.oa.tool.UploadTool;

public class ProductAction extends ActionAdapter {

	private static final Log log = LogFactory.getLog(ProductAction.class);

	@Override
	@Result("/WEB-INF/pages/freeze/product/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {
		return INPUT;
	}

	@SuppressWarnings({ "rawtypes", "rawtypes", "unchecked" })
	@None
	public String uploadFile(HttpServletRequest req, HttpServletResponse resp) {
		String savePath = req.getSession().getServletContext().getRealPath("/")
				+ UploadTool.PRODUCT_SAVE_DIR_NAME + "/";
		String saveUrl = req.getContextPath() + "/"
				+ UploadTool.PRODUCT_SAVE_DIR_NAME + "/";
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
				log.info("Upload logo Successful , Product path -> " + saveUrl
						+ newFileName);
			}
		}
		return NONE;
	}

	@Override
	@Success(path = "/WEB-INF/pages/freeze/product/viewAdd.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/product/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		String logo = param(req, "logo");
		String name = param(req, "name");
		String introduce = param(req, "introduce");
		String link = param(req, "link");

		Product model = new Product();
		model.setLogo(logo);
		model.setName(name);
		model.setIntroduce(introduce);
		model.setLink(link);

		setAttr(req, MODEL, model);

		if (StringUtils.isBlank(name)) {
			setAttr(req, TIP_NAME_KEY, "请先输入产品名称");
			return FAIL;
		}
		if (StringUtils.isBlank(logo)) {
			setAttr(req, TIP_NAME_KEY, "请先上传产品LOGO");
			return FAIL;
		}
		if (StringUtils.isBlank(introduce)) {
			setAttr(req, TIP_NAME_KEY, "请先输入产品简介");
			return FAIL;
		}
		if (StringUtils.isBlank(link)) {
			setAttr(req, TIP_NAME_KEY, "请先输入产品链接地址");
			return FAIL;
		}

		model.setAddtime(CalendarTool.now());

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "发布产品成功");
			model.setLogo("");
			model.setName("");
			model.setIntroduce("");
			model.setLink("");
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "发布产品失败");
			return FAIL;
		}

	}

	@Result("/WEB-INF/pages/freeze/product/list.jsp")
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Product model = new Product();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		if (id == 0) {
			setAttr(req, TIP_NAME_KEY, "非法ID值");
		} else {
			model.setId(id);
			// TODO more relative
			if (model.delete()) {
				setAttr(req, TIP_NAME_KEY, "成功删除[" + model.getName() + "]");
			} else {
				setAttr(req, TIP_NAME_KEY, "删除产品[" + model.getName() + "]失败");
			}
		}
		return this.list(req, resp);
	}

	@Override
	@Result("/WEB-INF/pages/freeze/product/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);
		Product model = new Product();
		model = model.get(id);

		setAttr(req, MODEL, model);

		return INPUT;
	}

	@Override
	@Success(path = "/WEB-INF/pages/freeze/product/viewModify.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/product/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		String logo = param(req, "logo");
		String name = param(req, "name");
		String introduce = param(req, "introduce");
		String link = param(req, "link");

		Product model = new Product();
		model = model.get(id);

		setAttr(req, MODEL, model);

		if (StringUtils.equals(name, model.getName())
				&& StringUtils.equals(logo, model.getLogo())
				&& StringUtils.equals(introduce, model.getIntroduce())
				&& StringUtils.equals(link, model.getLink())) {
			setAttr(req, TIP_NAME_KEY, "无任何变更");
			return FAIL;
		}
		if (StringUtils.isBlank(name)) {
			setAttr(req, TIP_NAME_KEY, "请先输入产品名称");
			return FAIL;
		}
		if (StringUtils.isBlank(logo)) {
			setAttr(req, TIP_NAME_KEY, "请先上传产品LOGO");
			return FAIL;
		}
		if (StringUtils.isBlank(introduce)) {
			setAttr(req, TIP_NAME_KEY, "请先输入产品简介");
			return FAIL;
		}
		if (StringUtils.isBlank(link)) {
			setAttr(req, TIP_NAME_KEY, "请先输入产品链接地址");
			return FAIL;
		}

		model.setLogo(logo);
		model.setName(name);
		model.setIntroduce(introduce);
		model.setLink(link);
		model.setAddtime(CalendarTool.now());

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "编辑产品成功");
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "编辑产品失败");
			return FAIL;
		}

	}

	@Result("/WEB-INF/pages/freeze/product/list.jsp")
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		Product model = new Product();
		@SuppressWarnings("unchecked")
		List<Product> list = (List<Product>) model.listAll();

		setAttr(req, DATA_LIST, list);

		return INPUT;
	}

}
