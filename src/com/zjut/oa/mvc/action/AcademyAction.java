/**
 * 
 */
package com.zjut.oa.mvc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.domain.Academy;

/**
 * 
 * 
 * @author qingtian
 * 
 *         2011-9-19 下午05:24:08
 */
public class AcademyAction extends ActionAdapter {

	@Override
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {
		return super.viewAdd(req, resp);
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

	@Result("/WEB-INF/pages/freeze/academy/list.jsp")
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		Academy model = new Academy();
		@SuppressWarnings("unchecked")
		List<Academy> academyList = (List<Academy>) model.listAll();

		setAttr(req, DATA_LIST, academyList);

		return INPUT;
	}

}
