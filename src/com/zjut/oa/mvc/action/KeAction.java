package com.zjut.oa.mvc.action;

import java.util.ArrayList;
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
import com.zjut.oa.mvc.domain.Department;
import com.zjut.oa.mvc.domain.Ke;
import com.zjut.oa.mvc.domain.User;
import com.zjut.oa.mvc.domain.strengthen.FreeKeTogether;
import com.zjut.oa.mvc.domain.strengthen.KeTogether;

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

		List<KeTogether> ktList = new ArrayList<KeTogether>();
		for (Ke k : dataList) {
			User u = new User();
			u = u.get(k.getUserID());

			KeTogether kt = new KeTogether();

			kt.setId(k.getId());
			kt.setUser(u);
			kt.setKe(k);

			ktList.add(kt);
		}

		setAttr(req, CURRENT_PAGE_KEY, currentPage);
		setAttr(req, CURRENT_COUNT_PER_PAGE_KEY, countPerPage);
		setAttr(req, PAGER_KEY, pager);
		setAttr(req, MAX_PAGERSHOW_LENGTH_KEY, DEFAULT_MAX_PAGERSHOW_LENGTH);

		setAttr(req, DATA_LIST, ktList);

		return INPUT;
	}

	@Result("/WEB-INF/pages/freeze/ke/filter.jsp")
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp) {
		String[] deleteId = params(req, "deleteId");
		if (deleteId.length == 0) {
			setAttr(req, TIP_NAME_KEY, "请选择要删除的课表");
			return this.filter(req, resp);
		}
		Ke model = new Ke();
		int[] results = model.batchDelete(deleteId);
		log.debug("batchDelete results[0]: " + results[0]);
		if (results.length > 0 && results[0] > 0) {
			setAttr(req, TIP_NAME_KEY, "成功删除" + results[0] + "个课表");
		}
		return this.filter(req, resp);
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

	@Result("/WEB-INF/pages/freeze/ke/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {

		User user = new User();

		setAttr(req, PAGE_KE_USERLIST_KEY, user.listAll());

		return INPUT;
	}

	@Result("/WEB-INF/pages/freeze/ke/viewFindFreeTime.jsp")
	public String viewFindFreeTime(HttpServletRequest req,
			HttpServletResponse resp) {

		Department department = new Department();
		setAttr(req, PAGE_KE_DEPARTMENTLIST_KEY, department.listAll());

		return INPUT;
	}

	@Success(path = "/WEB-INF/pages/freeze/ke/freeTimeDisplay.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/ke/viewFindFreeTime.jsp")
	public String findFreeTime(HttpServletRequest req, HttpServletResponse resp) {
		String departmentID = param(req, "departmentID");

		StringBuilder kevalue = new StringBuilder();

		int[][] kevalue_arr = new int[11][7];
		System.out.println("空课查询－打印接受的课表参数 Start");
		boolean hasanydo = false;
		for (int i = 1; i <= 11; i++) {
			for (int j = 1; j <= 7; j++) {
				String param = "kevalue_" + i + "_" + j;
				int tmp_kevalue = param(req, param, 0);
				if (tmp_kevalue != 0)
					hasanydo = true;
				kevalue_arr[i - 1][j - 1] = tmp_kevalue;
				System.out.print(kevalue_arr[i - 1][j - 1] + ",");
				kevalue.append(kevalue_arr[i - 1][j - 1]);
			}
			System.out.println();
		}
		System.out.println("空课查询－打印接受的课表参数 End");

		Ke model = new Ke();
		setAttr(req, PAGE_KE_DEPARTMENTID_KEY, departmentID);
		
		// 未操作
		if (!hasanydo) {
			setAttr(req, TIP_NAME_KEY, "请勾选需要查询的空课时间点");

			Department department = new Department();
			setAttr(req, PAGE_KE_DEPARTMENTLIST_KEY, department.listAll());

			return FAIL;
		}

		List<FreeKeTogether> fktList = (List<FreeKeTogether>) model
				.findFreeKe(req);

		setAttr(req, PAGE_KE_FREETIMELIST_KEY, fktList);
		// 当前查询空课值
		setAttr(req, PAGE_KE_KEVALUE_KEY, kevalue);

		return SUCCESS;
	}

	@Success(path = "/WEB-INF/pages/freeze/ke/show.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/ke/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		int userID = param(req, "userID", 0);

		StringBuilder kevalue = new StringBuilder();

		int[][] kevalue_arr = new int[11][7];
		System.out.println("打印接受的课表参数 Start");
		boolean hasDoany = false;
		for (int i = 1; i <= 11; i++) {
			for (int j = 1; j <= 7; j++) {
				String param = "kevalue_" + i + "_" + j;
				int tmp_kevalue = param(req, param, 0);
				if (tmp_kevalue != 0)
					hasDoany = true;
				kevalue_arr[i - 1][j - 1] = tmp_kevalue;
				System.out.print(kevalue_arr[i - 1][j - 1] + ",");
				kevalue.append(kevalue_arr[i - 1][j - 1]);
			}
			System.out.println();
		}
		System.out.println("打印接受的课表参数 End");

		Ke model = new Ke();
		model.setUserID(userID);
		model.setKevalue(kevalue.toString());

		setAttr(req, MODEL, model);

		User user = new User();
		if (userID != 0)
			user = user.get(userID);

		setAttr(req, PAGE_KE_USER_MODEL_KEY, user);

		setAttr(req, PAGE_KE_USERLIST_KEY, user.listAll());

		if (userID == 0 || userID == -1) {
			setAttr(req, TIP_NAME_KEY, "请先选择用户");
			return FAIL;
		}

		if (model.existProperty("userID", userID)) {
			setAttr(req, TIP_NAME_KEY, "该用户课表已存在");
			return FAIL;
		}

		if (!hasDoany) {
			setAttr(req, TIP_NAME_KEY, "请先勾选课表后再添加");
			return FAIL;
		}

		user = user.get(userID);

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "添加[" + user.getUsername() + "]的课表成功");
			setAttr(req, MODEL, model);
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "添加课表失败");
			return FAIL;
		}
	}

	@Override
	@Result("/WEB-INF/pages/freeze/ke/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Ke model = new Ke();
		if (id != 0) {
			model = model.get(id);
		}

		User user = new User();

		setAttr(req, PAGE_KE_USERLIST_KEY, user.listAll());

		setAttr(req, MODEL, model);

		return INPUT;
	}

	@Override
	@Success(path = "/WEB-INF/pages/freeze/ke/show.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/ke/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		StringBuilder kevalue = new StringBuilder();

		int[][] kevalue_arr = new int[11][7];
		System.out.println("打印接受的课表参数 Start");
		boolean hasDoany = false;
		for (int i = 1; i <= 11; i++) {
			for (int j = 1; j <= 7; j++) {
				String param = "kevalue_" + i + "_" + j;
				int tmp_kevalue = param(req, param, 0);
				if (tmp_kevalue != 0)
					hasDoany = true;
				kevalue_arr[i - 1][j - 1] = tmp_kevalue;
				System.out.print(kevalue_arr[i - 1][j - 1] + ",");
				kevalue.append(kevalue_arr[i - 1][j - 1]);
			}
			System.out.println();
		}
		System.out.println("打印接受的课表参数 End");

		Ke model = new Ke();
		model.setId(id);
		model = model.get(id);

		User user = new User();

		setAttr(req, PAGE_KE_USERLIST_KEY, user.listAll());

		String pre_kevalue = model.getKevalue();

		setAttr(req, MODEL, model);

		if (!hasDoany) {
			setAttr(req, TIP_NAME_KEY, "请先勾选课表后再提交");
			return FAIL;
		}

		if (StringUtils.equals(pre_kevalue, kevalue.toString())) {
			setAttr(req, TIP_NAME_KEY, "无任何修改");
			return FAIL;
		}

		model.setKevalue(kevalue.toString());
		setAttr(req, MODEL, model);

		// 加载当前用户
		user = user.get(model.getUserID());

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "编辑[" + user.getUsername() + "]的课表成功");
			setAttr(req, PAGE_KE_USER_MODEL_KEY, user);
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "编辑用户课表失败");
			return FAIL;
		}
	}

	@Override
	@Success(path = "/WEB-INF/pages/freeze/ke/showMyself.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/ke/viewAddMyself.jsp")
	public String addMyself(HttpServletRequest req, HttpServletResponse resp) {
		StringBuilder kevalue = new StringBuilder();

		int[][] kevalue_arr = new int[11][7];
		System.out.println("打印接受的课表参数 Start");
		boolean hasDoany = false;
		for (int i = 1; i <= 11; i++) {
			for (int j = 1; j <= 7; j++) {
				String param = "kevalue_" + i + "_" + j;
				int tmp_kevalue = param(req, param, 0);
				if (tmp_kevalue != 0)
					hasDoany = true;
				kevalue_arr[i - 1][j - 1] = tmp_kevalue;
				System.out.print(kevalue_arr[i - 1][j - 1] + ",");
				kevalue.append(kevalue_arr[i - 1][j - 1]);
			}
			System.out.println();
		}
		System.out.println("打印接受的课表参数 End");

		if (!hasDoany) {
			setAttr(req, TIP_NAME_KEY, "请先勾选课表后再添加");
			return FAIL;
		}

		String[] loginUser = ((String) getAttr(req.getSession(), LOGIN_USER_KEY))
				.split("&");
		String s_id = loginUser[0];

		Ke model = new Ke();
		model.setUserID(Integer.parseInt(s_id));
		model.setKevalue(kevalue.toString());

		if (model.existProperty("userID", s_id)) {
			setAttr(req, TIP_NAME_KEY, "您早些时候已经添加了课表，如需修改点击\"修改课表\"链接!");
			return FAIL;
		}
		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "您已成功添加课表");
			setAttr(req, MODEL, model);
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "您添加课表失败");
			return FAIL;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Result("/WEB-INF/pages/freeze/ke/showMyself.jsp")
	public String showMyself(HttpServletRequest req, HttpServletResponse resp) {

		String[] loginUser = ((String) getAttr(req.getSession(), LOGIN_USER_KEY))
				.split("&");
		String s_id = loginUser[0];

		Ke model = new Ke();
		model.setUserID(Integer.parseInt(s_id));

		List<Ke> hasKeOfUserID = (List<Ke>) model.filter(" where userID="
				+ s_id);
		if (hasKeOfUserID.size() == 1) {
			model = hasKeOfUserID.get(0);
		}

		setAttr(req, MODEL, model);

		return INPUT;
	}

	@Result("/WEB-INF/pages/freeze/ke/show.jsp")
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		Ke model = new Ke();
		if (id != 0) {
			model = model.get(id);
		}

		User user = new User();
		user = user.get(model.getUserID());

		setAttr(req, PAGE_KE_USER_MODEL_KEY, user);
		setAttr(req, MODEL, model);

		return INPUT;
	}

	@Override
	@Success(path = "/WEB-INF/pages/freeze/ke/showMyself.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/ke/viewModifyMyself.jsp")
	public String modifyMyself(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);
		StringBuilder kevalue = new StringBuilder();

		int[][] kevalue_arr = new int[11][7];
		System.out.println("打印接受的课表参数 Start");
		boolean hasDoany = false;
		for (int i = 1; i <= 11; i++) {
			for (int j = 1; j <= 7; j++) {
				String param = "kevalue_" + i + "_" + j;
				int tmp_kevalue = param(req, param, 0);
				if (tmp_kevalue != 0)
					hasDoany = true;
				kevalue_arr[i - 1][j - 1] = tmp_kevalue;
				System.out.print(kevalue_arr[i - 1][j - 1] + ",");
				kevalue.append(kevalue_arr[i - 1][j - 1]);
			}
			System.out.println();
		}
		System.out.println("打印接受的课表参数 End");

		Ke model = new Ke();
		model.setId(id);
		model = model.get(id);

		String pre_kevalue = model.getKevalue();

		setAttr(req, MODEL, model);

		if (!hasDoany) {
			setAttr(req, TIP_NAME_KEY, "请先勾选课表后再提交");
			return FAIL;
		}

		if (StringUtils.equals(pre_kevalue, kevalue.toString())) {
			setAttr(req, TIP_NAME_KEY, "无任何修改");
			return FAIL;
		}

		model.setKevalue(kevalue.toString());
		setAttr(req, MODEL, model);

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "您已成功编辑课表");
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "您编辑课表失败");
			return FAIL;
		}
	}

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/ke/viewModifyMyself.jsp")
	public String viewModifyMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		String[] loginUser = ((String) getAttr(req.getSession(), LOGIN_USER_KEY))
				.split("&");
		String s_id = loginUser[0];

		Ke model = new Ke();
		model.setUserID(Integer.parseInt(s_id));

		List<Ke> hasKeOfUserID = (List<Ke>) model.filter(" where userID="
				+ s_id);
		if (hasKeOfUserID.size() == 1) {
			model = hasKeOfUserID.get(0);
		}

		setAttr(req, MODEL, model);

		return INPUT;
	}

}
