package com.zjut.oa.mvc.core;

public interface Constant {
	
	String GOTO_PAGE = "gotoPage";
	String INNER_PREFIX = "/WEB-INF/pages/";
	String ERROR_404_PAGE_LOCATION="/error/404.jsp";
	String ERROR_500_PAGE_LOCATION="/error/500.jsp";
	
	/** actionservlet拦截到访问路径不是以项目路径根开头的返回值  */
	String URI_IS_WRONG="URI_IS_WRONG";
	String LOGIN_PAGE_LOCATION="/index.jsp";
	String NO_PERMISSION_PAGE_LOCATION="shield.jsp";
	
	/** 页面响应中EL键  */
	String LOGIN_USER_KEY="loginUser";
	String USER_PERMISSION_KEY="userPermission";
	String TIP_NAME_KEY="tip";
	String MODEL="model";
	String DATA_LIST="dataList";
	
	int DEFAULT_CURRENT_PAGE=1;									/** 分页时默认当前页码 =1 */
	int DEFAULT_COUNT_PER_PAGE=20;								/** 分页时默认分页大小 =20 */
	int DEFAULT_MAX_PAGERSHOW_LENGTH=10;						/** 分页时左右分页区间大小 */
	String PAGER_KEY="pager";									/** 分页时分页键 */
	String MAX_PAGERSHOW_LENGTH_KEY="maxPagerShowLength";		/** 分页时左右分页区间键 */
	String CURRENT_PAGE_KEY="currentPage";						/** 分页时当前页键 */
	String CURRENT_COUNT_PER_PAGE_KEY="countPerPage";			/** 分页时当前分页大小键 */
	
	
	/**
	 * 页面响应所用值键
	 */
	
	/** 登录页面 */
	String PAGE_LOGIN_AUTOLOGIN_KEY="autologin";
	
	/** 大事件列表页面 */
	String PAGE_EVENT_CURRENT_YEAR_KEY="currentYear";
	String PAGE_EVENT_YEARLIST_KEY="yearList";
	
	/** 动态显示页面 */
	String PAGE_NEWS_DETAIL_USER_KEY="user";
	
	/** 用户 角色分配页面 */
	String PAGE_USERROLE_USERLIST_KEY="userList";
	String PAGE_USERROLE_ROLELIST_KEY="roleList";
	
	/** 权限添加页面 */
	String PAGE_PERMISSION_MENULIST_KEY="menuList";
	String PAGE_PERMISSION_RESOURCELIST_KEY="resourceList";
	String PAGE_PERMISSION_OPERATORLIST_KEY="operatorList";
	
	/** 角色权限 */
	String PAGE_ROLEPERMISSION_ROLEID_KEY="roleID";
	String PAGE_ROLEPERMISSION_ROLE_KEY="role";
	String PAGE_ROLEPERMISSION_ROLELIST_KEY="roleList";
	String PAGE_ROLEPERMISSION_PERMISSION_TOGETHER_LIST_KEY="permissionTogetherList";
	String PAGE_ROLEPERMISSION_DATALIST_KEY="rpListForRoleID";
	String PAGE_ROLEPERMISSION_ROLEPERMISSION_TOGETHER_FOR_ROLEID_KEY="rptListForRoleID";
	
	/** 用户添加页面 */
	String PAGE_USER_ACADEMYLIST_KEY="academyList";
	String PAGE_USER_DEPARTMENTLIST_KEY="departmentList";
	
	/** 添加课表 */
	String PAGE_KE_USERID_KEY="userID";
	String PAGE_KE_USERLIST_KEY ="userList";
	String PAGE_KE_USER_MODEL_KEY="user";
	
	/** 文件 */
	String PAGE_FFILE_USER_MODEL_KEY="user";
	
	/** 反馈 */
	String PAGE_SUGGEST_USER_MODEL_KEY="user";
	String PAGE_SUGGEST_REPLY_MODEL_KEY="reply";
	
	/** 管理团队 */
	String PAGE_TEAM_USER_LIST_KEY="userList";
	String PAGE_TEAM_DEPARTMENT_KEY="department";
}