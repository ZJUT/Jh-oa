package com.zjut.oa.tool;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.mvc.core.Constant;

public final class HttpTool {

	private static final Log log=LogFactory.getLog(HttpTool.class);
	
	private static final HttpTool INSTANCE=new HttpTool();
	
	private HttpTool(){
		//Singleton
	}
	
	public static HttpTool getInstance(){
		return INSTANCE;
	}
	
	public  String getUri(HttpServletRequest req) {
		String uri = req.getRequestURI();
		log.info("**source uri** : "+uri);
		int index1 = uri.indexOf("/", 2);
		if (index1 > 0) {
			uri = uri.substring(index1);
		}
		return uri;
	}
	
	public String getUriFromContextPath(HttpServletRequest req) {
		String uri = req.getRequestURI();
		if (uri.indexOf(req.getContextPath()) < 0) {
			return Constant.URI_IS_WRONG;
		}
		int index1 = uri.indexOf("/", 2);
		if (index1 > 0) {
			uri = uri.substring(index1);
		}
		return uri;
	}
}
