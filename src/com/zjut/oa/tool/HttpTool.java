package com.zjut.oa.tool;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjut.oa.db.Pager;
import com.zjut.oa.mvc.core.Constant;
import com.zjut.oa.mvc.domain.News;

public final class HttpTool {

	private static final Log log = LogFactory.getLog(HttpTool.class);

	private static final HttpTool INSTANCE = new HttpTool();

	private HttpTool() {
		// Singleton
	}

	public static HttpTool getInstance() {
		return INSTANCE;
	}

	public String getUri(HttpServletRequest req) {
		String uri = req.getRequestURI();
//		log.info("***source: " + uri);
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
		log.info("*********************** filter uri: " + uri);
		return uri;
	}

	public List<News> getTop6NewsList() {
		StringBuilder top6news = new StringBuilder();
		News news = new News();
		int currentPage = 1;
		int countPerPage = 6;
		int totalCount = news.totalCount(top6news.toString());
		Pager pager = new Pager(currentPage, countPerPage, totalCount);
		// 读取部分数据
		@SuppressWarnings("unchecked")
		List<News> top6newsList = (List<News>) news.filterByPage(
				top6news.toString(), currentPage, pager.getCountPerPage());
		return top6newsList;
	}

}
