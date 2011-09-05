package com.zjut.oa.mvc.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.db.DBManager;
import com.zjut.oa.tool.HttpTool;

/**
 * 全局过滤器，每次请求结束后关闭连接池资源<br />
 * 实现了{@link Filter}接口
 * 
 * @author qingtian
 * 
 *         2011-2-23 下午09:14:21
 */
public class GlobalFilter implements Filter {

	private static final Log log = LogFactory.getLog(GlobalFilter.class);
	/** 不过滤的访问路径 */
	private List<String> exclude = new ArrayList<String>();

	@Override
	public void init(FilterConfig config) throws ServletException {
		exclude = Arrays.asList(StringUtils.split(
				config.getInitParameter("exclude"), ','));
		log.info(" Init, exclude uri : " + exclude);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpreq = (HttpServletRequest) req;
		String uri = HttpTool.getInstance().getUri(httpreq);
		long begintime = System.currentTimeMillis();
		// 排除URI不过滤
		boolean isInclude = false;
		for (String action : exclude) {
			if (uri.startsWith(action)) {
				isInclude = true;
				break;
			}
		}
		if (isInclude) {
			chain.doFilter(req, resp);
		} else {
			chain.doFilter(req, resp);
			long endtime = System.currentTimeMillis();
			long duration = endtime - begintime;
			String respTimeStr = duration > 1000 ? duration / 1000 + " ms"
					: duration + " ms";
			log.info("-> request:" + uri + "--> cost:" + respTimeStr);
		}
		// 关闭数据库连接池连接
		DBManager.closeConnection();
	}

	@Override
	public void destroy() {

	}

}
