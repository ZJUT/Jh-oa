package com.zjut.oa.mvc.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.db.Pager;
import com.zjut.oa.mvc.core.ActionAdapter;
import com.zjut.oa.mvc.core.Constant;
import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.None;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;
import com.zjut.oa.mvc.domain.Academy;
import com.zjut.oa.mvc.domain.Department;
import com.zjut.oa.mvc.domain.Job;
import com.zjut.oa.mvc.domain.User;
import com.zjut.oa.mvc.domain.strengthen.UserTogether;
import com.zjut.oa.tool.CalendarTool;
import com.zjut.oa.tool.JExcelTool;

public class UserAction extends ActionAdapter {

	private static final Log log = LogFactory.getLog(UserAction.class);

	@Result("/WEB-INF/pages/freeze/user/show.jsp")
	public String show(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		User model = new User();
		if (id != 0) {
			model = model.get(id);
		}

		Academy academy = new Academy();
		setAttr(req, PAGE_USER_ACADEMYLIST_KEY, academy.listAll());

		Department department = new Department();
		setAttr(req, PAGE_USER_DEPARTMENTLIST_KEY, department.listAll());

		Job job = new Job();
		setAttr(req, PAGE_USER_JOBLIST_KEY, job.listAll());

		setAttr(req, MODEL, model);

		return INPUT;

	}

	@Result("/WEB-INF/pages/freeze/user/showMyself.jsp")
	public String showMyself(HttpServletRequest req, HttpServletResponse resp) {
		// 设置会话状态
		String[] loginUser = ((String) getAttr(req.getSession(), LOGIN_USER_KEY))
				.split("&");
		String s_id = loginUser[0];

		int id = Integer.parseInt(s_id);

		User model = new User();
		if (id != 0) {
			model = model.get(id);
		}

		Academy academy = new Academy();
		setAttr(req, PAGE_USER_ACADEMYLIST_KEY, academy.listAll());

		Department department = new Department();
		setAttr(req, PAGE_USER_DEPARTMENTLIST_KEY, department.listAll());

		Job job = new Job();
		setAttr(req, PAGE_USER_JOBLIST_KEY, job.listAll());
		setAttr(req, MODEL, model);

		return INPUT;

	}

	@Result("/WEB-INF/pages/freeze/user/viewAdd.jsp")
	public String viewAdd(HttpServletRequest req, HttpServletResponse resp) {

		Academy academy = new Academy();
		setAttr(req, PAGE_USER_ACADEMYLIST_KEY, academy.listAll());

		Department department = new Department();
		setAttr(req, PAGE_USER_DEPARTMENTLIST_KEY, department.listAll());

		Job job = new Job();
		setAttr(req, PAGE_USER_JOBLIST_KEY, job.listAll());

		return INPUT;
	}

	@Success(path = "/WEB-INF/pages/freeze/user/viewAdd.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/user/viewAdd.jsp")
	public String add(HttpServletRequest req, HttpServletResponse resp) {
		String uid = param(req, "uid");
		String username = param(req, "username");
		String password = param(req, "password");

		String email = param(req, "email");
		String cornet = param(req, "cornet");
		String telephone = param(req, "telephone");
		int academyID = param(req, "academyID", -1);
		String major = param(req, "major");
		String location = param(req, "location");
		String dormitory = param(req, "dormitory");

		int departmentID = param(req, "departmentID", -1);
		int jobID = param(req, "jobID", -1);

		String bbs = param(req, "bbs");

		int islock = param(req, "islock", 0);

		String introduce = param(req, "introduce");
		String simpleinfo = param(req, "simpleinfo");

		User model = new User();
		model.setUid(uid);
		model.setUsername(username);
		model.setPassword(password);
		model.setEmail(email);
		model.setCornet(cornet);
		model.setTelephone(telephone);
		if (academyID != -1)
			model.setAcademyID(academyID);
		model.setMajor(major);
		if (!StringUtils.equals(location, "-1"))
			model.setLocation(location);
		else {
			model.setLocation("");
		}
		model.setDormitory(dormitory);

		if (departmentID != -1)
			model.setDepartmentID(departmentID);

		if (jobID != -1)
			model.setJobID(jobID);

		model.setIslock(islock);
		model.setBbs(bbs);
		model.setIntroduce(introduce);
		model.setSimpleinfo(simpleinfo);

		Academy academy = new Academy();
		setAttr(req, PAGE_USER_ACADEMYLIST_KEY, academy.listAll());

		Department department = new Department();
		setAttr(req, PAGE_USER_DEPARTMENTLIST_KEY, department.listAll());

		Job job = new Job();
		setAttr(req, PAGE_USER_JOBLIST_KEY, job.listAll());

		setAttr(req, MODEL, model);

		if (StringUtils.isBlank(uid)) {
			setAttr(req, TIP_NAME_KEY, "请输入学号");
			return FAIL;
		}
		if (model.existProperty("uid", uid)) {
			setAttr(req, TIP_NAME_KEY, "学号[" + uid + "]已存在");
			return FAIL;
		}

		if (StringUtils.isBlank(username)) {
			setAttr(req, TIP_NAME_KEY, "请输入姓名");
			return FAIL;
		}
		if (StringUtils.isBlank(password)) {
			setAttr(req, TIP_NAME_KEY, "请输入密码");
			return FAIL;
		}
		if (StringUtils.isBlank(email)) {
			setAttr(req, TIP_NAME_KEY, "请输入Email地址");
			return FAIL;
		}

		if (StringUtils.isBlank(cornet)) {
			setAttr(req, TIP_NAME_KEY, "请输入短号");
			return FAIL;
		}
		if (departmentID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择所属部门");
			return FAIL;
		}
		if (jobID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择职务");
			return FAIL;
		}
		// if (StringUtils.isBlank(telephone)) {
		// setAttr(req, TIP_NAME_KEY, "请输入手机号码");
		// return FAIL;
		// }
		if (academyID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择学院");
			return FAIL;
		}
		//
		// if (StringUtils.isBlank(major)) {
		// setAttr(req, TIP_NAME_KEY, "请输入专业班级");
		// return FAIL;
		// }
		//
		// if (StringUtils.isBlank(location)) {
		// setAttr(req, TIP_NAME_KEY, "请选择所在校区");
		// return FAIL;
		// }
		//
		// if (StringUtils.isBlank(dormitory)) {
		// setAttr(req, TIP_NAME_KEY, "请输入宿舍");
		// return FAIL;
		// }

		if (islock == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择状态");
			return FAIL;
		}
		Timestamp now = CalendarTool.now();
		model.setAddtime(now);
		model.setModifytime(now);

		if (model.save() > 0) {
			setAttr(req, TIP_NAME_KEY, "添加用户[" + username + "]成功");
			model.setUid("");
			model.setUsername("");
			model.setPassword("");

			model.setEmail("");
			model.setCornet("");
			model.setTelephone("");
			model.setAcademyID(-1);
			model.setMajor("");
			model.setLocation("");
			model.setDormitory("");
			model.setDepartmentID(-1);
			model.setJobID(-1);
			model.setIslock(0);
			model.setBbs("");

			model.setIntroduce("");
			model.setSimpleinfo("");

			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "添加用户失败");
			return FAIL;
		}

	}

	@Result("/WEB-INF/pages/freeze/user/viewModify.jsp")
	public String viewModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);

		User model = new User();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		Academy academy = new Academy();
		setAttr(req, PAGE_USER_ACADEMYLIST_KEY, academy.listAll());

		Department department = new Department();
		setAttr(req, PAGE_USER_DEPARTMENTLIST_KEY, department.listAll());

		Job job = new Job();
		setAttr(req, PAGE_USER_JOBLIST_KEY, job.listAll());

		setAttr(req, MODEL, model);

		return INPUT;

	}

	@Result("/WEB-INF/pages/freeze/user/viewModifyMyself.jsp")
	public String viewModifyMyself(HttpServletRequest req,
			HttpServletResponse resp) {
		// 设置会话状态
		String[] loginUser = ((String) getAttr(req.getSession(), LOGIN_USER_KEY))
				.split("&");
		String s_id = loginUser[0];

		int id = Integer.parseInt(s_id);

		User model = new User();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		Academy academy = new Academy();
		setAttr(req, PAGE_USER_ACADEMYLIST_KEY, academy.listAll());

		Department department = new Department();
		setAttr(req, PAGE_USER_DEPARTMENTLIST_KEY, department.listAll());

		Job job = new Job();
		setAttr(req, PAGE_USER_JOBLIST_KEY, job.listAll());

		setAttr(req, MODEL, model);

		return INPUT;

	}

	@Success(path = "/WEB-INF/pages/freeze/user/viewModify.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/user/viewModify.jsp")
	public String modify(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);
		String uid = param(req, "uid");
		String username = param(req, "username");
		String password = param(req, "password");

		String email = param(req, "email");
		String cornet = param(req, "cornet");
		String telephone = param(req, "telephone");
		int academyID = param(req, "academyID", -1);
		String major = param(req, "major");
		String location = param(req, "location");
		String dormitory = param(req, "dormitory");

		int departmentID = param(req, "departmentID", -1);
		int jobID = param(req, "jobID", -1);

		String bbs = param(req, "bbs");

		int islock = param(req, "islock", -1);
		String introduce = param(req, "introduce");
		String simpleinfo = param(req, "simpleinfo");

		Academy academy = new Academy();
		setAttr(req, PAGE_USER_ACADEMYLIST_KEY, academy.listAll());

		Department department = new Department();
		setAttr(req, PAGE_USER_DEPARTMENTLIST_KEY, department.listAll());

		Job job = new Job();
		setAttr(req, PAGE_USER_JOBLIST_KEY, job.listAll());

		User model = new User();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		String pre_uid = model.getUid();
		String pre_username = model.getUsername();
		String pre_password = model.getPassword();

		String pre_email = model.getEmail();
		String pre_cornet = model.getCornet();
		String pre_telephone = model.getTelephone();
		int pre_academyID = model.getAcademyID();
		String pre_major = model.getMajor();
		String pre_location = model.getLocation();
		String pre_dormitory = model.getDormitory();

		int pre_departmentID = model.getDepartmentID();
		int pre_jobID = model.getJobID();

		String pre_bbs = model.getBbs();

		int pre_islock = model.getIslock();
		String pre_introduce = model.getIntroduce();
		String pre_simpleinfo = model.getSimpleinfo();

		if (StringUtils.isBlank(pre_uid)) {
			setAttr(req, TIP_NAME_KEY, "加载用户失败");

			model.setUid(pre_uid);
			model.setUsername(pre_username);

			model.setEmail(pre_email);
			model.setCornet(pre_cornet);
			model.setTelephone(pre_telephone);
			model.setAcademyID(pre_academyID);
			model.setMajor(pre_major);
			model.setLocation(pre_location);
			model.setDormitory(pre_dormitory);
			model.setDepartmentID(pre_departmentID);
			model.setJobID(pre_jobID);
			model.setIslock(pre_islock);
			model.setBbs(pre_bbs);
			model.setIntroduce(pre_introduce);
			model.setSimpleinfo(pre_simpleinfo);

			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (StringUtils.equals(pre_uid, uid)
				&& StringUtils.equals(pre_username, username)
				&& StringUtils.equals(pre_password, password)
				&& StringUtils.equals(pre_email, email)
				&& StringUtils.equals(pre_cornet, cornet)
				&& StringUtils.equals(pre_telephone, telephone)
				&& pre_academyID == academyID
				&& StringUtils.equals(pre_major, major)
				&& StringUtils.equals(pre_location, location)
				&& StringUtils.equals(pre_dormitory, dormitory)
				&& pre_departmentID == departmentID && pre_islock == islock
				&& StringUtils.equals(pre_bbs, bbs)
				&& StringUtils.equals(pre_introduce, introduce)
				&& StringUtils.equals(pre_simpleinfo, simpleinfo)
				&& pre_jobID == jobID) {
			setAttr(req, TIP_NAME_KEY, "无任何修改");
			model.setUid(pre_uid);
			model.setUsername(pre_username);

			model.setEmail(pre_email);
			model.setCornet(pre_cornet);
			model.setTelephone(pre_telephone);
			model.setAcademyID(pre_academyID);
			model.setMajor(pre_major);
			model.setLocation(pre_location);
			model.setDormitory(pre_dormitory);

			model.setDepartmentID(pre_departmentID);
			model.setJobID(pre_jobID);

			model.setIslock(pre_islock);
			model.setBbs(pre_bbs);
			model.setIntroduce(pre_introduce);
			model.setSimpleinfo(pre_simpleinfo);

			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (StringUtils.isBlank(uid)) {
			setAttr(req, TIP_NAME_KEY, "请输入学号");
			model.setUid(pre_uid);
			model.setUsername(pre_username);

			model.setEmail(pre_email);
			model.setCornet(pre_cornet);
			model.setTelephone(pre_telephone);
			model.setAcademyID(pre_academyID);
			model.setMajor(pre_major);
			model.setLocation(pre_location);
			model.setDormitory(pre_dormitory);

			model.setDepartmentID(pre_departmentID);
			model.setJobID(pre_jobID);
			model.setIslock(pre_islock);
			model.setBbs(pre_bbs);
			model.setIntroduce(pre_introduce);
			model.setSimpleinfo(pre_simpleinfo);

			setAttr(req, MODEL, model);
			return FAIL;
		}
		if (StringUtils.isBlank(username)) {
			setAttr(req, TIP_NAME_KEY, "请输入姓名");
			model.setUid(pre_uid);
			model.setUsername(pre_username);

			model.setEmail(pre_email);
			model.setCornet(pre_cornet);
			model.setTelephone(pre_telephone);
			model.setAcademyID(pre_academyID);
			model.setMajor(pre_major);
			model.setLocation(pre_location);
			model.setDormitory(pre_dormitory);

			model.setDepartmentID(pre_departmentID);
			model.setJobID(pre_jobID);

			model.setIslock(pre_islock);
			model.setBbs(pre_bbs);
			model.setIntroduce(pre_introduce);
			model.setSimpleinfo(pre_simpleinfo);

			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (StringUtils.isBlank(email)) {
			setAttr(req, TIP_NAME_KEY, "请输入Email地址");
			model.setUid(pre_uid);
			model.setUsername(pre_username);

			model.setEmail(pre_email);
			model.setCornet(pre_cornet);
			model.setTelephone(pre_telephone);
			model.setAcademyID(pre_academyID);
			model.setMajor(pre_major);
			model.setLocation(pre_location);
			model.setDormitory(pre_dormitory);

			model.setDepartmentID(pre_departmentID);
			model.setJobID(pre_jobID);

			model.setIslock(pre_islock);
			model.setBbs(pre_bbs);
			model.setIntroduce(pre_introduce);
			model.setSimpleinfo(pre_simpleinfo);

			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (StringUtils.isBlank(cornet)) {
			setAttr(req, TIP_NAME_KEY, "请输入短号");
			model.setUid(pre_uid);
			model.setUsername(pre_username);

			model.setEmail(email);
			model.setCornet(pre_cornet);
			model.setTelephone(pre_telephone);
			model.setAcademyID(pre_academyID);
			model.setMajor(pre_major);
			model.setLocation(pre_location);
			model.setDormitory(pre_dormitory);

			model.setDepartmentID(pre_departmentID);
			model.setJobID(pre_jobID);
			model.setIslock(pre_islock);
			model.setBbs(pre_bbs);
			model.setIntroduce(pre_introduce);
			model.setSimpleinfo(pre_simpleinfo);

			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (academyID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择学院");
			model.setUid(pre_uid);
			model.setUsername(pre_username);

			model.setEmail(email);
			model.setCornet(pre_cornet);
			model.setTelephone(pre_telephone);
			model.setAcademyID(pre_academyID);
			model.setMajor(pre_major);
			model.setLocation(pre_location);
			model.setDormitory(pre_dormitory);

			model.setDepartmentID(pre_departmentID);
			model.setJobID(pre_jobID);
			model.setIslock(pre_islock);
			model.setBbs(pre_bbs);
			model.setIntroduce(pre_introduce);
			model.setSimpleinfo(pre_simpleinfo);

			setAttr(req, MODEL, model);
			return FAIL;
		}
		if (model.existProperty("uid", uid)
				&& !StringUtils.equals(pre_uid, uid)) {
			setAttr(req, TIP_NAME_KEY, "学号[" + uid + "]已存在");
			model.setUid(pre_uid);
			model.setUsername(pre_username);

			model.setEmail(pre_email);
			model.setCornet(pre_cornet);
			model.setTelephone(pre_telephone);
			model.setAcademyID(pre_academyID);
			model.setMajor(pre_major);
			model.setLocation(pre_location);
			model.setDormitory(pre_dormitory);

			model.setDepartmentID(pre_departmentID);
			model.setJobID(pre_jobID);

			model.setIslock(pre_islock);
			model.setBbs(pre_bbs);
			model.setIntroduce(pre_introduce);
			model.setSimpleinfo(pre_simpleinfo);

			setAttr(req, MODEL, model);
			return FAIL;
		}

		model.setUid(uid);
		model.setUsername(username);
		if (!StringUtils.equals(pre_password, password)
				&& StringUtils.isNotBlank(password))
			model.setPassword(password);

		model.setEmail(email);
		model.setCornet(cornet);
		model.setTelephone(telephone);
		if (academyID != -1)
			model.setAcademyID(academyID);
		model.setMajor(major);
		if (!StringUtils.equals(location, "-1"))
			model.setLocation(location);
		else {
			model.setLocation("");
		}
		model.setDormitory(dormitory);

		if (departmentID != -1)
			model.setDepartmentID(departmentID);
		if (jobID != -1)
			model.setJobID(jobID);
		model.setIslock(islock);
		model.setBbs(bbs);

		model.setIntroduce(introduce);
		model.setSimpleinfo(simpleinfo);

		model.setModifytime(CalendarTool.now());
		setAttr(req, MODEL, model);

		if (model.save() > 0) {
			StringBuilder tip = new StringBuilder();
			tip.append("编辑用户成功; ");
			if (!StringUtils.equals(pre_uid, uid))
				tip.append("学号[" + pre_uid + "]->[" + uid + "]; ");
			if (!StringUtils.equals(pre_username, username))
				tip.append("姓名[" + pre_username + "]->[" + username + "]; ");
			if (!StringUtils.equals(pre_password, password)
					&& !StringUtils.isBlank(password))
				tip.append("密码[" + pre_password + "]->[" + password + "]; ");

			if (!StringUtils.equals(pre_email, email)
					&& !StringUtils.isBlank(email))
				tip.append("邮箱地址[" + pre_email + "]->[" + email + "]; ");
			if (!StringUtils.equals(pre_cornet, cornet)
					&& !StringUtils.isBlank(cornet))
				tip.append("短号[" + pre_cornet + "]->[" + cornet + "]; ");
			if (!StringUtils.equals(pre_telephone, telephone)
					&& !StringUtils.isBlank(telephone))
				tip.append("手机号码[" + pre_telephone + "]->[" + telephone + "]; ");

			String pre_academyname = "";
			String academyname = "";
			if (pre_academyID != -1 && pre_academyID != 0) {
				academy = academy.get(pre_academyID);
				pre_academyname = academy.getAcademyname();
			}
			if (academyID != -1 && academyID != 0) {
				academy = academy.get(academyID);
				academyname = academy.getAcademyname();
			}

			if (pre_academyID != academyID && academyID != -1)
				tip.append("所在学院[" + pre_academyname + "]->[" + academyname
						+ "]; ");
			if (!StringUtils.equals(pre_major, major))
				tip.append("专业班级[" + pre_major + "]->[" + major + "]; ");
			if (!StringUtils.equals(pre_location, location)
					&& !StringUtils.equals(location, "-1"))
				tip.append("所在校区[" + pre_location + "]->[" + location + "]; ");
			if (!StringUtils.equals(pre_dormitory, dormitory))
				tip.append("宿舍[" + pre_dormitory + "]->[" + dormitory + "]; ");
			if (!StringUtils.equals(pre_bbs, bbs))
				tip.append("论坛ID[" + pre_bbs + "]->[" + bbs + "]; ");

			String pre_jobname = "";
			String jobname = "";
			if (pre_jobID != -1 && pre_jobID != 0) {
				job = job.get(pre_jobID);
				pre_jobname = job.getJobname();
			}
			if (jobID != -1 && jobID != 0) {
				job = job.get(jobID);
				jobname = job.getJobname();
			}
			if (pre_jobID != jobID && jobID != -1)
				tip.append("职务[" + pre_jobname + "]->[" + jobname + "]; ");

			if (!StringUtils.equals(pre_introduce, introduce))
				tip.append("简介[" + pre_introduce + "]->[" + introduce + "]; ");

			String pre_departmentname = "";
			String departmentname = "";
			if (pre_departmentID != -1 && pre_departmentID != 0) {
				department = department.get(pre_departmentID);
				pre_departmentname = department.getDepartmentname();
			}
			if (departmentID != -1 && departmentID != 0) {
				department = department.get(departmentID);
				departmentname = department.getDepartmentname();
			}
			if (pre_departmentID != departmentID && departmentID != -1)
				tip.append("所属部门[" + pre_departmentname + "]->["
						+ departmentname + "]; ");

			if (pre_islock != islock)
				tip.append("状态[" + pre_islock + "]->[" + islock + "]; ");

			setAttr(req, TIP_NAME_KEY, tip.toString());
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "编辑用户[" + pre_uid + "]失败");
			return FAIL;
		}
	}

	@Success(path = "/WEB-INF/pages/freeze/user/viewModifyMyself.jsp")
	@Fail(path = "/WEB-INF/pages/freeze/user/viewModifyMyself.jsp")
	public String modifyMyself(HttpServletRequest req, HttpServletResponse resp) {
		int id = param(req, "id", 0);
		String uid = param(req, "uid");
		String username = param(req, "username");
		String password = param(req, "password");

		String email = param(req, "email");
		String cornet = param(req, "cornet");
		String telephone = param(req, "telephone");
		int academyID = param(req, "academyID", -1);
		String major = param(req, "major");
		String location = param(req, "location");
		String dormitory = param(req, "dormitory");

		int departmentID = param(req, "departmentID", -1);
		int jobID = param(req, "jobID", -1);
		String bbs = param(req, "bbs");
		int islock = param(req, "islock", -1);
		String introduce = param(req, "introduce");
		String simpleinfo = param(req, "simpleinfo");

		Academy academy = new Academy();
		setAttr(req, PAGE_USER_ACADEMYLIST_KEY, academy.listAll());

		Department department = new Department();
		setAttr(req, PAGE_USER_DEPARTMENTLIST_KEY, department.listAll());

		Job job = new Job();
		setAttr(req, PAGE_USER_JOBLIST_KEY, job.listAll());

		User model = new User();
		if (id != 0) {
			model.setId(id);
			model = model.get(id);
		}

		String pre_uid = model.getUid();
		String pre_username = model.getUsername();
		String pre_password = model.getPassword();

		String pre_email = model.getEmail();
		String pre_cornet = model.getCornet();
		String pre_telephone = model.getTelephone();
		int pre_academyID = model.getAcademyID();
		String pre_major = model.getMajor();
		String pre_location = model.getLocation();
		String pre_dormitory = model.getDormitory();

		int pre_departmentID = model.getDepartmentID();
		int pre_jobID = model.getJobID();

		String pre_bbs = model.getBbs();
		String pre_introduce = model.getIntroduce();
		String pre_simpleinfo = model.getSimpleinfo();

		int pre_islock = model.getIslock();

		if (StringUtils.isBlank(pre_uid)) {
			setAttr(req, TIP_NAME_KEY, "加载用户失败");

			model.setUid(pre_uid);
			model.setUsername(pre_username);

			model.setEmail(pre_email);
			model.setCornet(pre_cornet);
			model.setTelephone(pre_telephone);
			model.setAcademyID(pre_academyID);
			model.setMajor(pre_major);
			model.setLocation(pre_location);
			model.setDormitory(pre_dormitory);
			model.setDepartmentID(pre_departmentID);
			model.setJobID(pre_jobID);
			model.setIslock(pre_islock);
			model.setBbs(pre_bbs);

			model.setIntroduce(pre_introduce);
			model.setSimpleinfo(pre_simpleinfo);

			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (StringUtils.equals(pre_uid, uid)
				&& StringUtils.equals(pre_username, username)
				&& StringUtils.equals(pre_password, password)
				&& StringUtils.equals(pre_email, email)
				&& StringUtils.equals(pre_cornet, cornet)
				&& StringUtils.equals(pre_telephone, telephone)
				&& pre_academyID == academyID
				&& StringUtils.equals(pre_major, major)
				&& StringUtils.equals(pre_location, location)
				&& StringUtils.equals(pre_dormitory, dormitory)
				&& pre_departmentID == departmentID && pre_islock == islock
				&& StringUtils.equals(pre_bbs, bbs)
				&& StringUtils.equals(pre_introduce, introduce)
				&& StringUtils.equals(pre_simpleinfo, simpleinfo)
				&& pre_jobID == jobID) {
			setAttr(req, TIP_NAME_KEY, "无任何修改");
			model.setUid(pre_uid);
			model.setUsername(pre_username);

			model.setEmail(pre_email);
			model.setCornet(pre_cornet);
			model.setTelephone(pre_telephone);
			model.setAcademyID(pre_academyID);
			model.setMajor(pre_major);
			model.setLocation(pre_location);
			model.setDormitory(pre_dormitory);

			model.setDepartmentID(pre_departmentID);
			model.setJobID(pre_jobID);
			model.setBbs(pre_bbs);
			model.setIslock(pre_islock);
			model.setIntroduce(pre_introduce);
			model.setSimpleinfo(pre_simpleinfo);

			setAttr(req, MODEL, model);
			return FAIL;
		}

		// if (StringUtils.isBlank(uid)) {
		// setAttr(req, TIP_NAME_KEY, "请输入学号");
		// model.setUid(pre_uid);
		// model.setUsername(pre_username);
		//
		// model.setEmail(pre_email);
		// model.setCornet(pre_cornet);
		// model.setTelephone(pre_telephone);
		// model.setAcademyID(pre_academyID);
		// model.setMajor(pre_major);
		// model.setLocation(pre_location);
		// model.setDormitory(pre_dormitory);
		//
		// model.setDepartmentID(pre_departmentID);
		//
		// model.setIslock(pre_islock);
		//
		// setAttr(req, MODEL, model);
		// return FAIL;
		// }
		if (StringUtils.isBlank(username)) {
			setAttr(req, TIP_NAME_KEY, "请输入姓名");
			model.setUid(pre_uid);
			model.setUsername(pre_username);

			model.setEmail(pre_email);
			model.setCornet(pre_cornet);
			model.setTelephone(pre_telephone);
			model.setAcademyID(pre_academyID);
			model.setMajor(pre_major);
			model.setLocation(pre_location);
			model.setDormitory(pre_dormitory);

			model.setDepartmentID(pre_departmentID);
			model.setJobID(pre_jobID);
			model.setBbs(pre_bbs);
			model.setIslock(pre_islock);
			model.setIntroduce(pre_introduce);
			model.setSimpleinfo(pre_simpleinfo);

			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (StringUtils.isBlank(email)) {
			setAttr(req, TIP_NAME_KEY, "请输入Email地址");
			model.setUid(pre_uid);
			model.setUsername(pre_username);

			model.setEmail(pre_email);
			model.setCornet(pre_cornet);
			model.setTelephone(pre_telephone);
			model.setAcademyID(pre_academyID);
			model.setMajor(pre_major);
			model.setLocation(pre_location);
			model.setDormitory(pre_dormitory);

			model.setDepartmentID(pre_departmentID);
			model.setJobID(pre_jobID);
			model.setBbs(pre_bbs);
			model.setIslock(pre_islock);
			model.setIntroduce(pre_introduce);
			model.setSimpleinfo(pre_simpleinfo);

			setAttr(req, MODEL, model);
			return FAIL;
		}

		if (StringUtils.isBlank(cornet)) {
			setAttr(req, TIP_NAME_KEY, "请输入短号");
			model.setUid(pre_uid);
			model.setUsername(pre_username);

			model.setEmail(email);
			model.setCornet(pre_cornet);
			model.setTelephone(pre_telephone);
			model.setAcademyID(pre_academyID);
			model.setMajor(pre_major);
			model.setLocation(pre_location);
			model.setDormitory(pre_dormitory);

			model.setDepartmentID(pre_departmentID);
			model.setJobID(pre_jobID);
			model.setBbs(pre_bbs);

			model.setIslock(pre_islock);
			model.setIntroduce(pre_introduce);
			model.setSimpleinfo(pre_simpleinfo);

			setAttr(req, MODEL, model);
			return FAIL;
		}
		if (academyID == -1) {
			setAttr(req, TIP_NAME_KEY, "请选择学院");
			model.setUid(pre_uid);
			model.setUsername(pre_username);

			model.setEmail(email);
			model.setCornet(pre_cornet);
			model.setTelephone(pre_telephone);
			model.setAcademyID(pre_academyID);
			model.setMajor(pre_major);
			model.setLocation(pre_location);
			model.setDormitory(pre_dormitory);

			model.setDepartmentID(pre_departmentID);
			model.setJobID(pre_jobID);
			model.setIslock(pre_islock);
			model.setBbs(pre_bbs);
			model.setIntroduce(pre_introduce);
			model.setSimpleinfo(pre_simpleinfo);

			setAttr(req, MODEL, model);
			return FAIL;
		}
		// if (model.existProperty("uid", uid)
		// && !StringUtils.equals(pre_uid, uid)) {
		// setAttr(req, TIP_NAME_KEY, "学号[" + uid + "]已存在");
		// model.setUid(pre_uid);
		// model.setUsername(pre_username);
		//
		// model.setEmail(pre_email);
		// model.setCornet(pre_cornet);
		// model.setTelephone(pre_telephone);
		// model.setAcademyID(pre_academyID);
		// model.setMajor(pre_major);
		// model.setLocation(pre_location);
		// model.setDormitory(pre_dormitory);
		//
		// model.setDepartmentID(pre_departmentID);
		//
		// model.setIslock(pre_islock);
		//
		// setAttr(req, MODEL, model);
		// return FAIL;
		// }

		model.setUid(uid);
		model.setUsername(username);
		if (!StringUtils.equals(pre_password, password)
				&& StringUtils.isNotBlank(password))
			model.setPassword(password);

		model.setEmail(email);
		model.setCornet(cornet);
		model.setTelephone(telephone);
		if (academyID != -1)
			model.setAcademyID(academyID);
		if (jobID != -1)
			model.setJobID(jobID);
		model.setMajor(major);
		if (!StringUtils.equals(location, "-1"))
			model.setLocation(location);
		else {
			model.setLocation("");
		}
		model.setDormitory(dormitory);

		if (departmentID != -1)
			model.setDepartmentID(departmentID);

		model.setBbs(bbs);
		model.setIslock(islock);
		model.setIntroduce(introduce);
		model.setSimpleinfo(simpleinfo);

		model.setModifytime(CalendarTool.now());
		setAttr(req, MODEL, model);

		if (model.save() > 0) {
			StringBuilder tip = new StringBuilder();
			tip.append("编辑个人资料成功; ");
			// if (!StringUtils.equals(pre_uid, uid))
			// tip.append("学号[" + pre_uid + "]->[" + uid + "]; ");
			if (!StringUtils.equals(pre_username, username))
				tip.append("姓名[" + pre_username + "]->[" + username + "]; ");
			if (!StringUtils.equals(pre_password, password)
					&& !StringUtils.isBlank(password))
				tip.append("密码[" + pre_password + "]->[" + password + "]; ");

			if (!StringUtils.equals(pre_email, email)
					&& !StringUtils.isBlank(email))
				tip.append("邮箱地址[" + pre_email + "]->[" + email + "]; ");
			if (!StringUtils.equals(pre_cornet, cornet)
					&& !StringUtils.isBlank(cornet))
				tip.append("短号[" + pre_cornet + "]->[" + cornet + "]; ");
			if (!StringUtils.equals(pre_telephone, telephone)
					&& !StringUtils.isBlank(telephone))
				tip.append("手机号码[" + pre_telephone + "]->[" + telephone + "]; ");

			String pre_academyname = "";
			String academyname = "";
			if (pre_academyID != -1 && pre_academyID != 0) {
				academy = academy.get(pre_academyID);
				pre_academyname = academy.getAcademyname();
			}
			if (academyID != -1 && academyID != 0) {
				academy = academy.get(academyID);
				academyname = academy.getAcademyname();
			}

			if (pre_academyID != academyID && academyID != -1)
				tip.append("所在学院[" + pre_academyname + "]->[" + academyname
						+ "]; ");
			if (!StringUtils.equals(pre_major, major))
				tip.append("专业班级[" + pre_major + "]->[" + major + "]; ");
			if (!StringUtils.equals(pre_location, location)
					&& !StringUtils.equals(location, "-1"))
				tip.append("所在校区[" + pre_location + "]->[" + location + "]; ");
			if (!StringUtils.equals(pre_dormitory, dormitory))
				tip.append("宿舍[" + pre_dormitory + "]->[" + dormitory + "]; ");
			if (!StringUtils.equals(pre_bbs, bbs))
				tip.append("论坛ID[" + pre_bbs + "]->[" + bbs + "]; ");

			// String pre_jobname = "";
			// String jobname = "";
			// if (pre_jobID != -1 && pre_jobID != 0) {
			// job = job.get(pre_jobID);
			// pre_jobname = job.getJobname();
			// }
			// if (jobID != -1 && jobID != 0) {
			// job = job.get(jobID);
			// jobname = job.getJobname();
			// }
			// if (pre_jobID != jobID && jobID != -1)
			// tip.append("职务[" + pre_jobname + "]->[" + jobname + "]; ");
			//
			if (!StringUtils.equals(pre_introduce, introduce))
				tip.append("简介[" + pre_introduce + "]->[" + introduce + "]; ");
			// String pre_departmentname = "";
			// String departmentname = "";
			// if (pre_departmentID != -1 && pre_departmentID != 0) {
			// department = department.get(pre_departmentID);
			// pre_departmentname = department.getDepartmentname();
			// }
			// if (departmentID != -1 && departmentID != 0) {
			// department = department.get(departmentID);
			// departmentname = department.getDepartmentname();
			// }
			// if (pre_departmentID != departmentID && departmentID != -1)
			// tip.append("所属部门[" + pre_departmentname + "]->["
			// + departmentname + "]; ");

			// if (pre_islock != islock)
			// tip.append("状态[" + pre_islock + "]->[" + islock + "]; ");

			setAttr(req, TIP_NAME_KEY, tip.toString());
			return SUCCESS;
		} else {
			setAttr(req, TIP_NAME_KEY, "编辑个人资料[" + pre_uid + "]失败");
			return FAIL;
		}
	}

	@SuppressWarnings("unchecked")
	@Result("/WEB-INF/pages/freeze/user/filter.jsp")
	public String filter(HttpServletRequest req, HttpServletResponse resp) {
		String uid = param(req, "uid");
		String username = param(req, "username");
		String email = param(req, "email");
		String cornet = param(req, "cornet");
		String telephone = param(req, "telephone");
		int academyID = param(req, "academyID", 0);
		int departmentID = param(req, "departmentID", 0);
		int jobID = param(req, "jobID", 0);
		String major = param(req, "major");
		String location = param(req, "location");
		String dormitory = param(req, "dormitory");
		String bbs = param(req, "bbs");
		int islock = param(req, "islock", 0);

		String by = param(req, "by");
		String order = param(req, "order");

		User model = new User();
		model.setUid(uid);
		model.setUsername(username);
		model.setEmail(email);
		model.setCornet(cornet);
		model.setTelephone(telephone);
		if (academyID != 0)
			model.setAcademyID(academyID);
		if (departmentID != 0)
			model.setDepartmentID(departmentID);
		if (jobID != 0)
			model.setJobID(jobID);
		model.setMajor(major);
		model.setLocation(location);
		model.setDormitory(dormitory);
		model.setBbs(bbs);
		model.setIslock(islock);

		setAttr(req, MODEL, model);

		Academy academy = new Academy();
		setAttr(req, PAGE_USER_ACADEMYLIST_KEY, academy.listAll());

		Department department = new Department();
		setAttr(req, PAGE_USER_DEPARTMENTLIST_KEY, department.listAll());

		Job job = new Job();
		setAttr(req, PAGE_USER_JOBLIST_KEY, job.listAll());

		StringBuilder filter = new StringBuilder();
		if (StringUtils.isNotBlank(uid)) {
			filter.append(" where uid like '%" + uid + "%'");
		}

		if (StringUtils.isNotBlank(uid) && StringUtils.isNotBlank(username)) {
			filter.append(" and username like '%" + username + "%'");
		} else if (StringUtils.isBlank(uid) && StringUtils.isNotBlank(username)) {
			filter.append(" where username like '%" + username + "%'");
		}

		if ((StringUtils.isNotBlank(uid) || StringUtils.isNotBlank(username))
				&& StringUtils.isNotBlank(email)) {
			filter.append(" and email like '%" + email + "%'");
		} else if (StringUtils.isBlank(uid) && StringUtils.isBlank(username)
				&& StringUtils.isNotBlank(email)) {
			filter.append(" where email like '%" + email + "%'");
		}

		if ((StringUtils.isNotBlank(uid) || StringUtils.isNotBlank(username) || StringUtils
				.isNotBlank(email)) && StringUtils.isNotBlank(cornet)) {
			filter.append(" and cornet like '%" + cornet + "%'");
		} else if (StringUtils.isBlank(uid) && StringUtils.isBlank(username)
				&& StringUtils.isBlank(email) && StringUtils.isNotBlank(cornet)) {
			filter.append(" where cornet like '%" + cornet + "%'");
		}

		if ((StringUtils.isNotBlank(uid) || StringUtils.isNotBlank(username)
				|| StringUtils.isNotBlank(email) || StringUtils
				.isNotBlank(cornet)) && StringUtils.isNotBlank(telephone)) {
			filter.append(" and telephone like '%" + telephone + "%'");
		} else if (StringUtils.isBlank(uid) && StringUtils.isBlank(username)
				&& StringUtils.isBlank(email) && StringUtils.isBlank(cornet)
				&& StringUtils.isNotBlank(telephone)) {
			filter.append(" where telephone like '%" + telephone + "%'");
		}

		// 学院
		if ((StringUtils.isNotBlank(uid) || StringUtils.isNotBlank(username)
				|| StringUtils.isNotBlank(email)
				|| StringUtils.isNotBlank(cornet) || StringUtils
				.isNotBlank(telephone)) && academyID != 0) {
			filter.append(" and academyID = " + academyID);
		} else if (StringUtils.isBlank(uid) && StringUtils.isBlank(username)
				&& StringUtils.isBlank(email) && StringUtils.isBlank(cornet)
				&& StringUtils.isBlank(telephone) && academyID != 0) {
			filter.append(" where academyID = " + academyID);
		}

		if ((StringUtils.isNotBlank(uid) || StringUtils.isNotBlank(username)
				|| StringUtils.isNotBlank(email)
				|| StringUtils.isNotBlank(cornet)
				|| StringUtils.isNotBlank(telephone) || academyID != 0)
				&& StringUtils.isNotBlank(major)) {
			filter.append(" and major like '%" + major + "%'");
		} else if (StringUtils.isBlank(uid) && StringUtils.isBlank(username)
				&& StringUtils.isBlank(email) && StringUtils.isBlank(cornet)
				&& StringUtils.isBlank(telephone) && academyID == 0
				&& StringUtils.isNotBlank(major)) {
			filter.append(" where major like '%" + major + "%'");
		}

		if ((StringUtils.isNotBlank(uid) || StringUtils.isNotBlank(username)
				|| StringUtils.isNotBlank(email)
				|| StringUtils.isNotBlank(cornet)
				|| StringUtils.isNotBlank(telephone) || academyID != 0 || StringUtils
				.isNotBlank(major))
				&& StringUtils.isNotBlank(location)
				&& !StringUtils.equals(location, "0")) {
			filter.append(" and location like '%" + location + "%'");
		} else if (StringUtils.isBlank(uid) && StringUtils.isBlank(username)
				&& StringUtils.isBlank(email) && StringUtils.isBlank(cornet)
				&& StringUtils.isBlank(telephone) && academyID == 0
				&& StringUtils.isBlank(major)
				&& StringUtils.isNotBlank(location)
				&& !StringUtils.equals(location, "0")) {
			filter.append(" where location like '%" + location + "%'");
		}

		// 宿舍
		if ((StringUtils.isNotBlank(uid) || StringUtils.isNotBlank(username)
				|| StringUtils.isNotBlank(email)
				|| StringUtils.isNotBlank(cornet)
				|| StringUtils.isNotBlank(telephone) || academyID != 0
				|| StringUtils.isNotBlank(major) || StringUtils
				.isNotBlank(location)) && StringUtils.isNotBlank(dormitory)) {
			filter.append(" and dormitory like '%" + dormitory + "%'");
		} else if (StringUtils.isBlank(uid) && StringUtils.isBlank(username)
				&& StringUtils.isBlank(email) && StringUtils.isBlank(cornet)
				&& StringUtils.isBlank(telephone) && academyID == 0
				&& StringUtils.isBlank(major) && StringUtils.isBlank(location)
				&& StringUtils.isNotBlank(dormitory)) {
			filter.append(" where dormitory like '%" + dormitory + "%'");
		}

		// 状态，默认是检索可用
		if ((StringUtils.isNotBlank(uid) || StringUtils.isNotBlank(username)
				|| StringUtils.isNotBlank(email)
				|| StringUtils.isNotBlank(cornet)
				|| StringUtils.isNotBlank(telephone) || academyID != 0
				|| StringUtils.isNotBlank(major)
				|| StringUtils.isNotBlank(location) || StringUtils
				.isNotBlank(dormitory)) && (islock == 0 || islock == 1)) {
			filter.append(" and islock = " + islock);
		} else if (StringUtils.isBlank(uid) && StringUtils.isBlank(username)
				&& StringUtils.isBlank(email) && StringUtils.isBlank(cornet)
				&& StringUtils.isBlank(telephone) && academyID == 0
				&& StringUtils.isBlank(major) && StringUtils.isBlank(location)
				&& StringUtils.isBlank(dormitory)
				&& (islock == 0 || islock == 1)) {
			filter.append(" where islock = " + islock);
		}

		if ((StringUtils.isNotBlank(uid) || StringUtils.isNotBlank(username)
				|| StringUtils.isNotBlank(email)
				|| StringUtils.isNotBlank(cornet)
				|| StringUtils.isNotBlank(telephone) || academyID != 0
				|| StringUtils.isNotBlank(major)
				|| StringUtils.isNotBlank(location)
				|| StringUtils.isNotBlank(dormitory) || (islock == 0 || islock == 1))
				&& departmentID != 0) {
			filter.append(" and departmentID = " + departmentID);
		} else if (StringUtils.isBlank(uid) && StringUtils.isBlank(username)
				&& StringUtils.isBlank(email) && StringUtils.isBlank(cornet)
				&& StringUtils.isBlank(telephone) && academyID == 0
				&& StringUtils.isBlank(major) && StringUtils.isBlank(location)
				&& StringUtils.isBlank(dormitory)
				&& (islock != 0 && islock != 1) && departmentID != 0) {
			filter.append(" where departmentID = " + departmentID);
		}

		if ((StringUtils.isNotBlank(uid) || StringUtils.isNotBlank(username)
				|| StringUtils.isNotBlank(email)
				|| StringUtils.isNotBlank(cornet)
				|| StringUtils.isNotBlank(telephone) || academyID != 0
				|| StringUtils.isNotBlank(major)
				|| StringUtils.isNotBlank(location)
				|| StringUtils.isNotBlank(dormitory)
				|| (islock == 0 || islock == 1) || departmentID == 0)
				&& StringUtils.isNotBlank(bbs)) {
			filter.append(" and bbs like '%" + bbs + "%'");
		} else if (StringUtils.isBlank(uid) && StringUtils.isBlank(username)
				&& StringUtils.isBlank(email) && StringUtils.isBlank(cornet)
				&& StringUtils.isBlank(telephone) && academyID == 0
				&& StringUtils.isBlank(major) && StringUtils.isBlank(location)
				&& StringUtils.isBlank(dormitory)
				&& (islock != 0 && islock != 1) && departmentID != 0
				&& StringUtils.isNotBlank(bbs)) {
			filter.append(" where bbs like '%" + bbs + "%'");
		}

		if ((StringUtils.isNotBlank(uid) || StringUtils.isNotBlank(username)
				|| StringUtils.isNotBlank(email)
				|| StringUtils.isNotBlank(cornet)
				|| StringUtils.isNotBlank(telephone) || academyID != 0
				|| StringUtils.isNotBlank(major)
				|| StringUtils.isNotBlank(location)
				|| StringUtils.isNotBlank(dormitory)
				|| (islock == 0 || islock == 1) || departmentID == 0 || StringUtils
				.isNotBlank(bbs)) && jobID != 0) {
			filter.append(" and jobID = " + jobID);
		} else if (StringUtils.isBlank(uid) && StringUtils.isBlank(username)
				&& StringUtils.isBlank(email) && StringUtils.isBlank(cornet)
				&& StringUtils.isBlank(telephone) && academyID == 0
				&& StringUtils.isBlank(major) && StringUtils.isBlank(location)
				&& StringUtils.isBlank(dormitory)
				&& (islock != 0 && islock != 1) && departmentID != 0
				&& StringUtils.isBlank(bbs) && jobID != 0) {
			filter.append(" where jobID = " + jobID);
		}

		if (StringUtils.isNotBlank(by)
				&& (by.equals("id") || by.equals("uid")
						|| by.equals("username") || by.equals("email")
						|| by.equals("addtime") || by.equals("modifytime"))) {
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
		List<User> dataList = (List<User>) model.filterByPage(
				filter.toString(), p, pager.getCountPerPage());

		List<UserTogether> utList = new ArrayList<UserTogether>();
		for (User u : dataList) {

			Department d = new Department();
			d = d.get(u.getDepartmentID());

			Job j = new Job();
			j = j.get(u.getJobID());

			UserTogether ut = new UserTogether();
			ut.setId(u.getId());
			ut.setUser(u);
			ut.setDepartment(d);
			ut.setJob(j);

			utList.add(ut);
		}
		setAttr(req, CURRENT_PAGE_KEY, currentPage);
		setAttr(req, CURRENT_COUNT_PER_PAGE_KEY, countPerPage);
		setAttr(req, PAGER_KEY, pager);
		setAttr(req, MAX_PAGERSHOW_LENGTH_KEY, DEFAULT_MAX_PAGERSHOW_LENGTH);

		setAttr(req, DATA_LIST, utList);

		return INPUT;
	}

	@Result("/WEB-INF/pages/freeze/user/filter.jsp")
	public String batchDelete(HttpServletRequest req, HttpServletResponse resp) {
		String[] deleteId = params(req, "deleteId");
		if (deleteId.length == 0) {
			setAttr(req, TIP_NAME_KEY, "请选择要删除的用户");
			return this.filter(req, resp);
		}
		// 删除自己检测?
		HttpSession session = req.getSession();
		String[] loginUser = StringUtils.split(
				(String) getAttr(session, Constant.LOGIN_USER_KEY), "&");
		String id = loginUser[0];
		boolean hasMyself = false;
		for (String current_id : deleteId) {
			if (StringUtils.equals(current_id, id)) {
				hasMyself = true;
				break;
			}
		}
		if (hasMyself) {
			setAttr(req, TIP_NAME_KEY, "怎么可以删除自己，您傻了吧！驳回~");
			return this.filter(req, resp);
		}

		User model = new User();
		int[] results = model.batchDelete(deleteId);
		log.debug("batchDelete results[0]: " + results[0]);
		if (results.length > 0 && results[0] > 0) {
			setAttr(req, TIP_NAME_KEY, "成功删除" + results[0] + "个用户");
		}
		return this.filter(req, resp);
	}

	@Result("/WEB-INF/pages/freeze/user/viewExportUser.jsp")
	public String viewExportUser(HttpServletRequest req,
			HttpServletResponse resp) {

		Academy academy = new Academy();
		setAttr(req, PAGE_USER_ACADEMYLIST_KEY, academy.listAll());

		Department department = new Department();
		setAttr(req, PAGE_USER_DEPARTMENTLIST_KEY, department.listAll());

		Job job = new Job();
		setAttr(req, PAGE_USER_JOBLIST_KEY, job.listAll());

		return INPUT;
	}

	@None
	@Fail(path = "/WEB-INF/pages/freeze/user/viewExportUser.jsp")
	public String exportUser(HttpServletRequest req, HttpServletResponse resp) {
		String savefilename = param(req, "savefilename");
		int academyID = param(req, "academyID", -1);
		int departmentID = param(req, "departmentID", -1);
		int jobID = param(req, "jobID", -1);
		String location = param(req, "location", "-1");
		int islock = param(req, "islock", -1);

		User model = new User();
		model.setAcademyID(academyID);
		model.setDepartmentID(departmentID);
		model.setJobID(jobID);
		model.setLocation(location);
		model.setIslock(islock);

		setAttr(req, MODEL, model);

		Academy academy = new Academy();
		setAttr(req, PAGE_USER_ACADEMYLIST_KEY, academy.listAll());

		Department department = new Department();
		setAttr(req, PAGE_USER_DEPARTMENTLIST_KEY, department.listAll());

		Job job = new Job();
		setAttr(req, PAGE_USER_JOBLIST_KEY, job.listAll());

		if (StringUtils.isBlank(savefilename)) {
			setAttr(req, TIP_NAME_KEY, "请输入Excel的文件名，被导出的文件名将以此命名");
			return FAIL;
		}

		int hasSuffix = savefilename.lastIndexOf(".");
		if (hasSuffix > 0) {
			String pre = savefilename.substring(0, hasSuffix);
			String suffix = savefilename.substring(hasSuffix);
			if (!StringUtils.equals(suffix, ".xls")) {
				savefilename = pre + ".xls";
			}
		} else
			savefilename += ".xls";

		List<UserTogether> utList = (List<UserTogether>) model
				.exportUserListBy(academyID, departmentID, jobID, location,
						islock);

		log.info(utList.size());

		// 响应生成excel文件
		JExcelTool.exportUserToOutputStream(savefilename, utList, resp);

		return NONE;
	}
}
