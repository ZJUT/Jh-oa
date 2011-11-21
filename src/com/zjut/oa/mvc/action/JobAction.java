package com.zjut.oa.mvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.Job;

public class JobAction extends ActionAdapter {

	@Result("/WEB-INF/pages/freeze/job/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {

		return INPUT;
	}

	@Success(path = "/WEB-INF/pages/freeze/job/viewAdd.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/job/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		String jobname = param(req, "jobname");

		Job model = new Job();
		model.setJobname(jobname);

		setAttr(req, MODEL, model);

		if (StringUtils.isBlank(jobname)) {
			setAttr(req, TIP_NAME_KEY, "请输入职务名");
			return FAIL;
		}

		if (model.existProperty("jobname", jobname)) {
			setAttr(req, TIP_NAME_KEY, "已存在此职务");
			return FAIL;
		}

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "添加新职务[" + jobname + "]成功");
			model.setJobname("");
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "添加新职务失败");
			return FAIL;
		}
	}

	@Result("/WEB-INF/pages/freeze/job/list.jsp")
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Job model = new Job();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		if (id == 0) {
			setAttr(req, TIP_NAME_KEY, "非法ID值");
		} else {
			model.setId(id);
			if (model.delete()) {
				setAttr(req, TIP_NAME_KEY, "成功删除[" + model.getJobname() + "]");
			} else {
				setAttr(req, TIP_NAME_KEY, "删除职务[" + model.getJobname() + "]失败");
			}
		}
		return this.list(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/job/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Job model = new Job();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		setAttr(req, MODEL, model);

		return INPUT;

	}

	@Result("/WEB-INF/pages/freeze/job/list.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/job/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);
		String jobname = param(req, "jobname");

		Job model = new Job();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		String pre_jobname = model.getJobname();

		if (StringUtils.isBlank(pre_jobname)) {
			setAttr(req, TIP_NAME_KEY, "加载职务失败");
			return FAIL;
		}

		if (StringUtils.equals(pre_jobname, jobname)) {
			setAttr(req, TIP_NAME_KEY, "无任何修改");
			model.setJobname(pre_jobname);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (StringUtils.isBlank(jobname)) {
			setAttr(req, TIP_NAME_KEY, "请输入职务名");
			model.setJobname(pre_jobname);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		model.setJobname(jobname);
		setAttr(req, MODEL, model);

		if (model.existProperty("jobname", jobname)) {
			setAttr(req, TIP_NAME_KEY, "此职务名已存在");
			model.setJobname(pre_jobname);
			return FAIL;
		}

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "由[" + pre_jobname + "]更改为[" + jobname
					+ "]");
			return this.list(req, resp);
		} else {
			setAttr(req, TIP_NAME_KEY, "编辑职务[" + pre_jobname + "]失败");
			return FAIL;
		}
	}

	@Result("/WEB-INF/pages/freeze/job/list.jsp")
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		Job model = new Job();
		@SuppressWarnings("unchecked")
		List<Job> jobList = (List<Job>) model.listAll();

		setAttr(req, DATA_LIST, jobList);

		return INPUT;
	}
}
