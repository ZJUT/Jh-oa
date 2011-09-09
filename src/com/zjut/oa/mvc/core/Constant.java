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
	
	int DEFAULT_CURRENT_PAGE=1;									/** 分页时默认当前页码 =1 */
	int DEFAULT_COUNT_PER_PAGE=2;								/** 分页时默认分页大小 =10 */
	int DEFAULT_MAX_PAGERSHOW_LENGTH=10;						/** 分页时左右分页区间大小 */
	String PAGER_KEY="pager";									/** 分页时分页键 */
	String MAX_PAGERSHOW_LENGTH_KEY="maxPagerShowLength";		/** 分页时左右分页区间键 */
	String CURRENT_PAGE_KEY="currentPage";						/** 分页时当前页键 */
	String CURRENT_COUNT_PER_PAGE_KEY="countPerPage";			/** 分页时当前分页大小键 */
	
}