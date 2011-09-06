package com.zjut.oa.mvc.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 所有Action的抽象父类。实现于接口 {@link Constant}
 * <ul>
 * <li>提供了工具方法，简化编码</li>
 * <li>
 * 定义了子类的基本增删改查基本模板方法（以view为前缀的为视图表单,其他为表单行为）,如
 * <p>
 * 1. show->展示对象信息
 * </p>
 * <p>
 * 2. viewAdd->添加对象表单
 * </p>
 * <p>
 * 3. add->添加对象
 * </p>
 * <p>
 * 4. delete->删除对象
 * </p>
 * <p>
 * 5. viewModify->编辑对象表单
 * </p>
 * <p>
 * 6. modify->编辑对象
 * </p>
 * <p>
 * 7. viewFilter->条件过滤表单
 * </p>
 * <p>
 * 8. filter->分页条件过滤对象
 * </p>
 * <p>
 * 9. list->不分页对象
 * </p>
 * <p>
 * 10. listByPage->分页对象
 * </p>
 * </li>
 * </ul>
 * 
 * 
 * @author qingtian
 * 
 *         2011-2-23 下午08:07:14
 */
public abstract class AbstractAction extends Action implements Constant {

	public static final Log log = LogFactory.getLog(AbstractAction.class);

	protected static final String NONE="none";
	protected static final String INPUT="input";
	protected static final String SUCCESS = "success";
	protected static final String FAIL = "fail";

	/**
	 * Action初始化<br />
	 * public 修饰符必须的！不然子类必须覆盖
	 * 
	 * @param context
	 */
	public void init() {
		log.debug(" invoke init()...");
	}

	/**
	 * Action销毁
	 * 
	 */
	public void destroy() {
		log.debug(" invoke destroy()...");
	}

	/**
	 * 得到Action小写名称,如：TestAction -> test
	 * 
	 * @return
	 */
	public String getActionName() {
		String actionName = getClass().getSimpleName();
		String name = actionName.substring(0, actionName.indexOf("Action"));
		return StringUtils.lowerCase(name);
	}

	/**
	 * 得到Action的调用的URI前缀,子类可以直接使用此方法取得专用URI前缀,其后跟具体视图文件名
	 * 
	 * @return {@link#pagePrefix} + {@link#getActionName} + "/" <br />
	 *         例如：/WEB-INF/pages/user/
	 */
	public String getActionUriPrefix() {
		return pagePrefix() + getActionName() + "/";
	}

	/**
	 * 转发页位置 {@link#INNER_PREFIX}
	 * 
	 * @return
	 */
	public String pagePrefix() {
		return INNER_PREFIX;
	}

	// TODO 子类实现: view 开头的为页面表单页
	public abstract String show(HttpServletRequest req, HttpServletResponse resp);

	public abstract String viewAdd(HttpServletRequest req,
			HttpServletResponse resp);

	public abstract String add(HttpServletRequest req, HttpServletResponse resp);

	public abstract String delete(HttpServletRequest req,
			HttpServletResponse resp);

	public abstract String viewModify(HttpServletRequest req,
			HttpServletResponse resp);

	public abstract String modify(HttpServletRequest req,
			HttpServletResponse resp);

	public abstract String viewFilter(HttpServletRequest req,
			HttpServletResponse resp);

	public abstract String filter(HttpServletRequest req,
			HttpServletResponse resp);

	public abstract String list(HttpServletRequest req, HttpServletResponse resp);

	public abstract String listByPage(HttpServletRequest req,
			HttpServletResponse resp);
	
}
