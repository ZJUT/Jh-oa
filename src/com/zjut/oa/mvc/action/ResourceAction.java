package com.zjut.oa.mvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.Resource;

public class ResourceAction extends ActionAdapter {

	private static final Log log = LogFactory.getLog(ResourceAction.class);

	@Result("/WEB-INF/pages/freeze/resource/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {

		return INPUT;
	}

	@Success(path = "/WEB-INF/pages/freeze/resource/viewAdd.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/resource/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		String resourcename = param(req, "resourcename");
		String resourcevalue = param(req, "resourcevalue");

		Resource model = new Resource();
		model.setResourcename(resourcename);
		model.setResourcevalue(resourcevalue);

		setAttr(req, MODEL, model);

		if (StringUtils.isBlank(resourcename)) {
			setAttr(req, TIP_NAME_KEY, "请输入资源描述");
			return FAIL;
		}
		if (StringUtils.isBlank(resourcevalue)) {
			setAttr(req, TIP_NAME_KEY, "请输入资源值");
			return FAIL;
		}
		if (model.existProperty("resourcevalue", resourcevalue)) {
			setAttr(req, TIP_NAME_KEY, "资源值[" + resourcevalue + "]已存在");
			return FAIL;
		}

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "添加新资源[" + resourcename + "]成功");
			model.setResourcename("");
			model.setResourcevalue("");
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "添加新资源失败");
			return FAIL;
		}
	}

	@Result("/WEB-INF/pages/freeze/resource/list.jsp")
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Resource model = new Resource();
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
				setAttr(req, TIP_NAME_KEY, "成功删除[" + model.getResourcename()
						+ "]");
			} else {
				setAttr(req, TIP_NAME_KEY, "删除资源[" + model.getResourcename()
						+ "]失败");
			}
		}
		return this.list(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/resource/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Resource model = new Resource();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		setAttr(req, MODEL, model);

		return INPUT;

	}

	@Result("/WEB-INF/pages/freeze/resource/list.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/resource/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);
		String resourcename = param(req, "resourcename");
		String resourcevalue = param(req, "resourcevalue");

		Resource model = new Resource();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		String pre_resourcename = model.getResourcename();
		String pre_resourcevalue = model.getResourcevalue();

		if (StringUtils.isBlank(pre_resourcename)
				|| StringUtils.isBlank(pre_resourcevalue)) {
			setAttr(req, TIP_NAME_KEY, "加载资源失败");
			return FAIL;
		}

		if (StringUtils.equals(pre_resourcename, resourcename)
				&& StringUtils.equals(pre_resourcevalue, resourcevalue)) {
			setAttr(req, TIP_NAME_KEY, "无任何修改");
			model.setResourcename(pre_resourcename);
			model.setResourcevalue(pre_resourcevalue);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (StringUtils.isBlank(resourcename)) {
			setAttr(req, TIP_NAME_KEY, "请输入资源描述");
			model.setResourcename(pre_resourcename);
			model.setResourcevalue(pre_resourcevalue);
			setAttr(req, MODEL, model);
			return FAIL;
		}
		if (StringUtils.isBlank(resourcevalue)) {
			setAttr(req, TIP_NAME_KEY, "请输入资源值");
			model.setResourcename(pre_resourcename);
			model.setResourcevalue(pre_resourcevalue);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		model.setResourcename(resourcename);
		model.setResourcevalue(resourcevalue);
		setAttr(req, MODEL, model);

		if (model.existProperty("resourcevalue", resourcevalue)
				&& StringUtils.equals(pre_resourcename, resourcename)) {
			setAttr(req, TIP_NAME_KEY, "资源值[" + resourcevalue + "]已存在");
			return FAIL;
		}

		if (model.save() > 0) {
			StringBuilder tip = new StringBuilder();
			if (!StringUtils.equals(pre_resourcename, resourcename))
				tip.append("资源描述由[" + pre_resourcename + "]更改为[" + resourcename
						+ "] ");
			if (!StringUtils.equals(pre_resourcevalue, resourcevalue))
				tip.append("资源值由[" + pre_resourcevalue + "]更改为["
						+ resourcevalue + "] ");
			setAttr(req, TIP_NAME_KEY, tip.toString());
			return this.list(req, resp);
		} else {
			setAttr(req, TIP_NAME_KEY, "编辑资源[" + pre_resourcename + "]失败");
			return FAIL;
		}
	}

	@Result("/WEB-INF/pages/freeze/resource/list.jsp")
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		Resource model = new Resource();
		@SuppressWarnings("unchecked")
		List<Resource> resourceList = (List<Resource>) model.listAll();

		setAttr(req, DATA_LIST, resourceList);

		return INPUT;
	}

}
