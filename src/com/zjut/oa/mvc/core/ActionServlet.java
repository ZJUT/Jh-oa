package com.zjut.oa.mvc.core;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.mvc.core.annotation.Fail;
import com.zjut.oa.mvc.core.annotation.Result;
import com.zjut.oa.mvc.core.annotation.Success;

/**
 * 中心控制器
 * 
 * @author qingtian
 * 
 *         2011-2-23 下午09:18:30
 */
@SuppressWarnings("serial")
public class ActionServlet extends AbstractService implements Constant {

	private static final Log log = LogFactory.getLog(ActionServlet.class);

	/** Action类所在包 */
	private List<String> action_packages = null;
	/** action及method缓存 */
	private final static HashMap<String, Object> actions = new HashMap<String, Object>();
	private final static HashMap<String, Object> methods = new HashMap<String, Object>();

	/**
	 * 初始化，设置Action类所在包及加载启动便可用的Action类
	 * 
	 * (non-Javadoc)
	 * 
	 * @throws InterruptedException
	 * @throws KeeperException
	 * 
	 * @see org.qingtian.cash.core.AbstractService#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		String packages = this.getInitParameter("packages");
		log.info("Param -> packages：" + packages);
		action_packages = Arrays.asList(StringUtils.split(packages, ','));
		String initial_actions = this.getInitParameter("initial_actions");
		log.info("Param -> initial_actions：" + initial_actions);
		if (initial_actions != null) {
			// 应用启动即需初始化action
			for (String action : StringUtils.split(initial_actions, ',')) {
				try {
					log.info("Direct init action -> " + action);
					loadAction(action);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 销毁Servlet，清理缓存
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.qingtian.cash.core.AbstractService#destroy()
	 */
	@Override
	public void destroy() {
		super.destroy();
		for (Object action : actions.values()) {
			try {
				Method dm = action.getClass().getMethod("destroy");
				if (dm != null) {
					dm.invoke(action);
					log.info(getClass().getName() + " destroy(): "
							+ action.getClass().getSimpleName());
				}
			} catch (NoSuchMethodException e) {
				log.warn(e.getCause());
			} catch (Exception e) {
				log.warn(e.getCause());
			}
		}
	}

	/**
	 * <b>中心控制器(控制请求与响应的流程)</b><br />
	 * 控制器得到控制权后便交由业务逻辑类处理，最后取回控制权完成页面响应
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.qingtian.cash.core.AbstractService#process(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, boolean)
	 */
	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp,
			boolean isPost) throws ServletException, IOException {
		try {
			if (_process(req, resp, isPost)) {
				String defaultGotoPage = super.getWebroot(req)
						+ Constant.ERROR_404_PAGE_LOCATION;
				this.beforeResponeProcess(req, resp, defaultGotoPage);
			} else {
				String defaultGotoPage = super.getWebroot(req)
						+ Constant.ERROR_500_PAGE_LOCATION;
				this.beforeResponeProcess(req, resp, defaultGotoPage);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
			log.warn(e.getCause());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.warn(e.getCause());
		}
	}

	/**
	 * <b>Action类完成业务逻辑处理</b><br />
	 * 第一步：获取请求URI，原始URI格式为: /应用名/action/业务逻辑Action类名/方法名<br />
	 * 第二步：转换URI格式为:/action/业务逻辑Action类名/方法名<br />
	 * 第三步：URI有效性校正，格式截取，分别得到Action类名，方法名<br />
	 * 第四步：通过反射加载Action实例<br />
	 * 第五步：通过反射调用 业务逻辑类的方法，并且方法参数必须带HttpServletRequest req, HttpServletResponse
	 * resp<br />
	 * <br />
	 * <b>期间未捕获到任何异常便表示业务逻辑处理成功完成，反之则失败</b>
	 * 
	 * @see org.qingtian.cash.core.AbstractService#_process(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, boolean)
	 */
	protected boolean _process(HttpServletRequest req,
			HttpServletResponse resp, boolean isPost) throws ServletException,
			IOException, InstantiationException, IllegalAccessException {
		String uri = getCorrentedUri(req);
		if (Constant.URI_IS_WRONG.equals(uri)) {
			log.error("Application URI need start with ["
					+ super.getWebroot(req) + "] ");
			return false;
		}
		// URI: /action/Test/t1 --> /action + /Xxxxx(业务逻辑类名[无论大小写最后都转为小写]) +
		// /xxxxx(方法名)
		String[] parts = StringUtils.split(uri, '/');
		if (parts.length < 2) {
			log.error("After Application URI was splited by '/' must be 2 params or more");
			return false;
		}
		// 加载action类 parts[1]=业务逻辑类名(大小写无妨)
		Object action = this.loadAction(parts[1]);
		if (action == null) {
			log.error(StringUtils.capitalize(parts[1]) + "Action is not found");
			return false;
		}
		// parts[2]=业务方法名
		String action_method_name = (parts.length > 2) ? parts[2] : "index";
		log.debug("Invoke method : " + action_method_name);
		Method m_action = this.getActionMethod(action, action_method_name);
		if (m_action == null) {
			log.error("[Class : " + parts[1] + ", Method: "
					+ action_method_name
					+ "] is null, go to write it hurriedly");
			return false;
		}
		// 调用业务方法处理
		try {
			String returnValue = (String) m_action.invoke(action, req, resp);
			// TODO 业务方法调用后页面如何响应待思考

			// 1. 简单action跳转[简单页面]
			// 2. 成功处理跳转[action异或是简单页面]
			// 3. 失败处理跳转[action异或是简单页面]

			Result result = null;
			Success success = null;
			Fail fail = null;
			boolean hasResult = false;
			boolean hasSuccess = false;
			boolean hasFail = false;
			// 通过注解获取响应页面
			if (m_action.isAnnotationPresent(Result.class)) {
				Annotation annotation = m_action.getAnnotation(Result.class);
				result = (Result) annotation;
				hasResult = true;
			}
			if (m_action.isAnnotationPresent(Success.class)) {
				Annotation annotation = m_action.getAnnotation(Success.class);
				success = (Success) annotation;
				hasSuccess = true;
			}
			if (m_action.isAnnotationPresent(Fail.class)) {
				Annotation annotation = m_action.getAnnotation(Fail.class);
				fail = (Fail) annotation;
				hasFail = true;
			}
			// 无任何结果注解
			if (!hasResult && !hasSuccess && !hasFail) {
				log.error("未对Action方法作任何响应结果页面的位置声明");
				return true;
			}

			// 根据返回值进行视图转发
			if (StringUtils.equals("input", returnValue)) {
				req.setAttribute(Constant.GOTO_PAGE, result);
			} else if (StringUtils.equals("success", returnValue)) {
				req.setAttribute(Constant.GOTO_PAGE, success);
			} else if (StringUtils.equals("fail", returnValue)) {
				req.setAttribute(Constant.GOTO_PAGE, fail);
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			log.warn(e.getCause());
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.warn(e.getCause());
			return false;
		}
		return true;
	}

	/**
	 * <b>获取除根路径外的URI串</b>(如：前(/qtCash/action/Test/t1) ----> 后(/action/Test/t1))<br />
	 * 如果原始URI不是以"/项目跟路径访问" 访问，则会直接返回 Configuration.URI_IS_WRONG<br />
	 * 
	 * @see org.Constant.autodata.mvc.core.Configuration#URI_IS_WRONG
	 * @param req
	 * @return
	 */
	private String getCorrentedUri(HttpServletRequest req) {
		String uri = req.getRequestURI();
		if (uri.indexOf(super.getWebroot(req)) < 0) {
			return Constant.URI_IS_WRONG;
		}
		int index1 = uri.indexOf("/", 2);
		if (index1 > 0) {
			uri = uri.substring(index1);
		}
		return uri;
	}

	/**
	 * 根据action_name来加载action，action_name规则为去Action后缀<br />
	 * action位置为：初始包名+"."+名字(首字母大写其他小写)+Action
	 * 
	 * @param action_name
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private Object loadAction(String action_name)
			throws InstantiationException, IllegalAccessException {
		Object action = actions.get(action_name);
		if (action == null) {
			for (String pkg : action_packages) {
				String cls = pkg + "." + StringUtils.capitalize(action_name)
						+ "Action";
				action = loadActionByFullName(action_name, cls);
				if (action != null) {
					log.info("First visit, init Action: " + action_name);
					break;
				}
			}
		} else {
			log.info("Direct hit : " + action_name + "Action");
		}
		return action;
	}

	/**
	 * 通过反射实例化Action对象，并调用init()
	 * 
	 * @param action_name
	 * @param cls
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private Object loadActionByFullName(String action_name, String cls)
			throws IllegalAccessException, InstantiationException {
		Object action = null;
		try {
			action = Class.forName(cls).newInstance();
			// Action类初始化init 方法调用
			try {
				Method action_init_method = action.getClass().getMethod("init");
				action_init_method.invoke(action);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				log.warn(e.getCause());
			} catch (InvocationTargetException exc) {
				exc.printStackTrace();
				log.warn(exc.getCause());
			}
			if (!actions.containsKey(action_name)) {
				synchronized (actions) {
					actions.put(action_name, action);
				}
			}
		} catch (ClassNotFoundException excp) {
			excp.printStackTrace();
			log.warn(excp.getCause());
		}
		return action;
	}

	/**
	 * 获取名为method的方法
	 * 
	 * @param action
	 * @param method
	 * @return
	 */
	private Method getActionMethod(Object action, String method) {
		String key = action.getClass().getSimpleName() + "." + method;
		Method m = (Method) methods.get(key);
		if (m != null)
			return m;
		for (Method m1 : action.getClass().getMethods()) {
			if (m1.getModifiers() == Modifier.PUBLIC
					&& m1.getName().equals(method)) {
				synchronized (methods) {
					methods.put(key, m1);
				}
				return m1;
			}
		}
		return null;
	}

	/**
	 * 响应前：解析视图位置并选择响应方式（重定向/转发）
	 * 
	 * @param req
	 * @param resp
	 * @param defaultGotoPage
	 * @throws ServletException
	 * @throws IOException
	 */
	private void beforeResponeProcess(HttpServletRequest req,
			HttpServletResponse resp, String defaultGotoPage)
			throws ServletException, IOException {
		Object o = req.getAttribute(GOTO_PAGE);
		String gotoPage = defaultGotoPage;
		boolean isAction = false;
		if (o instanceof Result) {
			Result result = (Result) o;
			gotoPage = result.value();
		} else if (o instanceof Success) {
			Success success = (Success) o;
			gotoPage = success.path();
			isAction = success.isAction();
		} else if (o instanceof Fail) {
			Fail fail = (Fail) o;
			gotoPage = fail.path();
			isAction = fail.isAction();
		}

		boolean isRedirect = (o == null || isAction) ? true : false;

		log.info(" process ok! target viewer: [" + gotoPage
				+ "] error redirect? (" + isRedirect + ")");
		if (isRedirect) {
			//动作转化路径,非/开头，自动添加/
			//动作示例：/action/user/list
			//简单文件示例：/WEB-INF/pages/index.jsp或/error/404.jsp
			if(isAction){
				if(gotoPage.startsWith("/"))
					gotoPage="../.."+gotoPage;
				else
					gotoPage="../../"+gotoPage;
				log.debug("isAction["+isAction+"],Change gotoPage to["+gotoPage+"]");
			}
			super.redirect(req, resp, gotoPage);
		} else
			super.forward(req, resp, gotoPage);
	}
}
