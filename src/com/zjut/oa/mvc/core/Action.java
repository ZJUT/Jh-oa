package com.zjut.oa.mvc.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

/**
 * 业务处理辅助工具方法类，如获取参数，设置和获取请求域、会话域参数、请求转发和重定向
 * 
 * @author qingtian
 *
 * 2011-5-11 上午11:42:48
 */
public class Action {
	
	protected static final String ENCODING = "UTF-8";

	/**
	 * 源串使用UTF-8编码
	 * 
	 * @param source
	 * @return
	 */
	protected String encode(String source) {
		String destination = null;
		try {
			destination = new String(source.getBytes("iso-8859-1"), ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			;// ignore
		}
		return destination;
	}
	/**
	 * 获取指定名称的参数值,没有则为""
	 * 
	 * @param req
	 * @param name
	 * @return
	 */
	protected String param(HttpServletRequest req, String name) {
		String v = (String) req.getParameter(name);
		String value = v != null ? v.trim() : "";
		try {
			value = new String(value.getBytes("iso-8859-1"), ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			;// ignore
		}
		return value;
	}
	
	/**
	 * 获取请求中指定名称参数值，不存在参数则设置默认值
	 * 
	 * @param req
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	protected String param(HttpServletRequest req, String name, String defaultValue) {
		String v = (String) req.getParameter(name);
		String value = v != null ? v.trim() : defaultValue;
		try {
			value = new String(value.getBytes("iso-8859-1"), ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			;// ignore
		}
		return value;
	}
	protected String[] params(HttpServletRequest req, String name) {
		String[] v = req.getParameterValues(name);
		return v == null ? new String[] {} : v;
	}

	
	protected double param(HttpServletRequest req, String name, double defaultValue) {
		String value = param(req, name);
		double t = defaultValue;
		try {
			t = Double.parseDouble(value);
		} catch (NumberFormatException e) {
			;// ignore
		}
		return t;
	}

	protected int param(HttpServletRequest req, String name, int defaultValue) {
		String value = param(req, name);
		return StringUtils.isNumeric(value) && value != null
				&& !"".equals(value) ? Integer.parseInt(value) : defaultValue;
	}

	/**
	 * 设置request指定名称指定值
	 * 
	 * @param req
	 * @param name
	 * @param value
	 */
	protected void setAttr(HttpServletRequest req, String name, Object value) {
		req.setAttribute(name, value);
	}

	/**
	 * 获取request指定名称的值
	 * 
	 * @param req
	 * @param name
	 */
	protected Object getAttr(HttpServletRequest req, String name) {
		Object v = (Object) req.getAttribute(name);
		return v == null ? "" : v;
	}

	/**
	 * 设置session指定属性指定属性值
	 * 
	 * @param session
	 * @param name
	 * @param value
	 */
	protected void setAttr(HttpSession session, String name, Object value) {
		session.setAttribute(name, value);
	}

	/**
	 * 获取session指定属性的值
	 * 
	 * @param session
	 * @param name
	 * @return
	 */
	protected Object getAttr(HttpSession session,String name){
		return session.getAttribute(name);
	}
	/**
	 * 删除session指定名称
	 * 
	 * @param session
	 * @param name
	 */
	protected void rmAttr(HttpSession session, String name) {
		session.removeAttribute(name);
		session.invalidate();
	}
	
	/**
	 * 转发
	 * 
	 * @param req
	 * @param resp
	 * @param targetUrl
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void actionForward(HttpServletRequest req, HttpServletResponse resp,
			String targetUrl) throws ServletException, IOException {
		req.getRequestDispatcher(targetUrl).forward(req, resp);
	}

	/**
	 * 重定向
	 * 
	 * @param req
	 * @param resp
	 * @param targetUrl
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void actionRedirect(HttpServletRequest req,
			HttpServletResponse resp, String targetUrl)
			throws ServletException, IOException {
		resp.sendRedirect(targetUrl);
	}
}
