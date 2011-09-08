package com.zjut.oa.mvc.core;

public interface Constant {
	
	String GOTO_PAGE = "gotoPage";
	String INNER_PREFIX = "/WEB-INF/pages/";
	String ERROR_404_PAGE_LOCATION="/error/404.jsp";
	String ERROR_500_PAGE_LOCATION="/error/500.jsp";
	
	/** actionservlet拦截到访问路径不是以项目路径根开头的返回值  */
	String URI_IS_WRONG="URI_IS_WRONG";
	String LOGIN_PAGE_LOCATION="/index.jsp";
	
	/** 页面响应中EL键  */
	String LOGIN_USER_KEY="loginUser";
	String TIP_NAME_KEY="tip";
	String MODEL="model";
	String DATA_LIST="dataList";
	
	
	
}