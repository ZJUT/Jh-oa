/**
 * 
 */
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
import com.zjut.oa.mvc.domain.Academy;

/**
 * 
 * 
 * @author qingtian
 * 
 *         2011-9-19 下午05:24:08
 */
public class AcademyAction extends ActionAdapter {

	private static final Log log=LogFactory.getLog(AcademyAction.class);
	
	@Result("/WEB-INF/pages/freeze/academy/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {
		return INPUT;
	}

	@Success(path = "/WEB-INF/pages/freeze/academy/viewAdd.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/academy/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		String academyname = param(req, "academyname");

		Academy model = new Academy();
		model.setAcademyname(academyname);

		setAttr(req, MODEL, model);

		if (StringUtils.isBlank(academyname)) {
			setAttr(req, TIP_NAME_KEY, "请输入学院名");
			return FAIL;
		}

		if (model.existProperty("academyname", academyname)) {
			setAttr(req, TIP_NAME_KEY, "已存在此学院");
			return FAIL;
		}

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "添加新学院[" + academyname + "]成功");
			model.setAcademyname("");
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "添加新学院失败");
			return FAIL;
		}
	}

	@Result("/WEB-INF/pages/freeze/academy/list.jsp")
	public String delete(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Academy model = new Academy();
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
				setAttr(req, TIP_NAME_KEY, "成功删除[" + model.getAcademyname() + "]");
			} else {
				setAttr(req, TIP_NAME_KEY, "删除学院[" + model.getAcademyname()
						+ "]失败");
			}
		}
		return this.list(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/academy/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Academy model = new Academy();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		setAttr(req, MODEL, model);

		return INPUT;

	}

	@Result("/WEB-INF/pages/freeze/academy/list.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/academy/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);
		String academyname = param(req, "academyname");

		Academy model = new Academy();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		String pre_academyname = model.getAcademyname();

		if (StringUtils.isBlank(pre_academyname)) {
			setAttr(req, TIP_NAME_KEY, "加载学院失败");
			return FAIL;
		}

		if (StringUtils.equals(pre_academyname, academyname)) {
			setAttr(req, TIP_NAME_KEY, "无任何修改");
			model.setAcademyname(pre_academyname);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (StringUtils.isBlank(academyname)) {
			setAttr(req, TIP_NAME_KEY, "请输入学院名");
			model.setAcademyname(pre_academyname);
			setAttr(req, MODEL, model);
			return FAIL;
		}

		model.setAcademyname(academyname);
		setAttr(req, MODEL, model);

		if (model.existProperty("academyname", academyname)) {
			setAttr(req, TIP_NAME_KEY, "此学院名已存在");
			model.setAcademyname(pre_academyname);
			return FAIL;
		}

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "由[" + pre_academyname + "]更改为[" + academyname
					+ "]");
			return this.list(req, resp);
		} else {
			setAttr(req, TIP_NAME_KEY, "编辑学院[" + pre_academyname + "]失败");
			return FAIL;
		}
	}

	@Result("/WEB-INF/pages/freeze/academy/list.jsp")
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		Academy model = new Academy();
		@SuppressWarnings("unchecked")
		List<Academy> academyList = (List<Academy>) model.listAll();

		setAttr(req, DATA_LIST, academyList);

		return INPUT;
	}

}
