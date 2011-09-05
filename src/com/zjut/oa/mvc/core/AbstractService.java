package com.zjut.oa.mvc.core;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <b>抽象服务类</b><br />
 * 应用场景：为请求响应控制器提供统一模板
 * 
 * @author qingtian
 *
 * 2011-4-21 下午03:06:44
 */
@SuppressWarnings("serial")
public abstract class AbstractService extends HttpServlet {

	private static final Log log = LogFactory.getLog(AbstractService.class);

	@Override
	public void init() throws ServletException{
		log.debug(" Init ");
	}

	@Override
	public void destroy() {
		log.debug(" Destroy ");
	}

	/**
	 * 转发
	 * 
	 * @param req
	 * @param resp
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	public void forward(HttpServletRequest req, HttpServletResponse resp,
			String url) throws ServletException, IOException {
		RequestDispatcher dipatcher = req.getRequestDispatcher(url);
		dipatcher.forward(req, resp);
	}

	/**
	 * 重定向
	 * 
	 * @param req
	 * @param resp
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	public void redirect(HttpServletRequest req, HttpServletResponse resp,
			String url) throws ServletException, IOException {
		resp.sendRedirect(url);
	}

	/**
	 * <b>获取应用根目录</b>，例如：/AutoData<br />
	 * 应用场景：直接返回根目录的相应视图
	 * 
	 * @param req
	 * @return
	 */
	public String getWebroot(HttpServletRequest req) {
		return req.getContextPath();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.process(req, resp, false);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.process(req, resp, true);
	}

	/**
	 * 中心控制器的抽象模板方法
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void process(HttpServletRequest req,
			HttpServletResponse resp, boolean isPost) throws ServletException,
			IOException;

	/**
	 * <b>具体业务逻辑处理</b><br />
	 * 返回值：业务处理结果状态：
	 * <ul>
	 * <li>true表示业务处理正常，</li>
	 * <li>false表示业务处理失败（可能是发生异常、路径有误、业务逻辑类无法加载等）</li>
	 * </ul>
	 * 
	 * @param req
	 * @param resp
	 * @param isPost
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	protected abstract boolean _process(HttpServletRequest req,
			HttpServletResponse resp, boolean isPost) throws ServletException,
			IOException, InstantiationException, IllegalAccessException;
	
}
