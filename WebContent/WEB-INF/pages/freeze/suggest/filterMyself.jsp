<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.zjut.oa.db.*"%>
<%@ page import="com.zjut.oa.mvc.action.*"%>
<%@ page import="com.zjut.oa.mvc.core.*"%>
<%@ page import="com.zjut.oa.mvc.domain.*"%>
<%@ page import="com.zjut.oa.mvc.filter.*"%>
<%@ page import="com.zjut.oa.tool.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"></base>
<title> 我发布的所有反馈 </title>
</head>
<body>
<c:set var="currentPage" value="${requestScope.currentPage}"></c:set>
<c:set var="countPerPage" value="${requestScope.countPerPage}"></c:set>
<c:set var="pager" value="${requestScope.pager}"></c:set>
<c:set var="maxPagerShowLength" value="${requestScope.maxPagerShowLength}"></c:set>
<c:set var="countPerPageList" value="20,40,80,160" />

<c:set var="dataList" value="${requestScope.dataList }"></c:set>

<div class="crumb">
	<div class="addsuggest-title">我的反馈</div>
	<div class="backNav"><a href="action/global/manager">返回管理首页</a></div>
	<div class="clear"></div>
</div>
<div class="quick-action">
	<a href="action/suggest/viewAddMyself" class="button-like"><span class="add-suggest">反馈对系统的意见或所发现的问题</span></a>
</div>
<div class="box">
	<c:if test="${ not empty tip}">
		<div class="optTip">提示：<span class="msg">${tip}</span></div>
	</c:if>
	<c:choose>
		<c:when test="${empty dataList }">
			<div class="no-data">无任何数据</div>
		</c:when>
		<c:otherwise>
			<form action="action/suggest/batchDeleteMyself">
				<!-- 批量删除 -->
				<input type="hidden" name="by" value="${by }" />
				<input type="hidden" name="order" value="${order }" />
				<input type="hidden" name="page" value="${page }" />
				<input type="hidden" name="countPerPage" value="${pager.countPerPage }" />
				
				<table class="dataTable">
					<colgroup>
						<col width="5%" />
						<col width="55%" />
						<col width="25%" />
						<col width="15%" />
					</colgroup>
					<tr>
						<th>删?</th>
						<th>内容</th>
						<th>反馈时间</th>
						<th>反馈状态</th>
					</tr>
					<c:forEach var="suggest" items="${dataList }">
					<tr>
						<td>
							<input type="checkbox" name="deleteId" value="${suggest.id }" class="common-checkbox"/>
						</td>
						<td>
							<a href="action/suggest/showMyself?id=${suggest.id }" title="查看此反馈详细" class="detail-suggest">
							<c:choose>
								<c:when test="${fn:length(suggest.stext) > 20}">
									${fn:substring(suggest.stext,0,20)}...
								</c:when>
								<c:otherwise>
									${suggest.stext }
								</c:otherwise>
							</c:choose>
							</a>
						</td>
						<td><fmt:formatDate value="${suggest.addtime }" type="both"/></td>
						<td>
							<c:choose>
								<c:when test="${suggest.replyUserID == 0 }">
									暂未回复
								</c:when>
								<c:otherwise>
									管理员已回复
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					</c:forEach>
				</table>
				<div class="data-operator-bar top-border">
					<a href="javascript:void(0);" class="selectAll" title="全选">全选</a>
					<a href="javascript:void(0);" class="selectNone" title="全不选">全不选</a>
					<a href="javascript:void(0);" class="selectReverse" title="反选">反选</a>
					<input type="submit" value="删除" class="bt" />
				</div>
				<!-- 分页条 start -->
				<div id="pageBar">
					<div id="barL" class="l">
						<!-- 搜索式分页 -->
						<!-- 首页 -->
						<c:choose>
							<c:when test="${pager.currentPage > 1 }">
							<a href="action/suggest/filterMyself?by=${by }&order=${order }&page=1&countPerPage=${pager.countPerPage}"
								class="page-slice first" title="首页">&lt;&lt;</a>
							</c:when>
							<c:otherwise>
								<span class="page-slice-disabled first">&lt;&lt;</span>
							</c:otherwise>
						</c:choose> 
						<!-- 上一页 -->
						<c:choose>
							<c:when test="${pager.currentPage > 1 }">
								<a href="action/suggest/filterMyself?by=${by }&order=${order }&page=${pager.currentPage - 1 }&countPerPage=${pager.countPerPage}"
									class="page-slice prevent" title="上一页">&lt;</a>
							</c:when>
							<c:otherwise>
								<span class="page-slice-disabled prevent">&lt;</span>
							</c:otherwise>
						</c:choose> 
						<c:choose>
							<c:when test="${pager.totalPage == 1}">
								<span class="page-slice-disabled">${1 }</span>
							</c:when>
							<c:when
								test="${(pager.totalPage > maxPagerShowLength) && (pager.currentPage + maxPagerShowLength-1 <= pager.totalPage) && pager.currentPage <= maxPagerShowLength}">
								<c:forEach var="p" begin="1"
									end="${maxPagerShowLength }" step="1">
									<c:choose>
										<c:when test="${p==pager.currentPage }">
											<span class="currentPage">${p }</span>
										</c:when>
										<c:otherwise>
											<a href="action/suggest/filterMyself?by=${by }&order=${order }&page=${p }&countPerPage=${pager.countPerPage}"
												class="page-slice" title="第${p }页">${p }</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
							<c:when
								test="${(pager.totalPage > maxPagerShowLength) && (pager.currentPage + maxPagerShowLength-1 <= pager.totalPage)}">
								<c:forEach var="p" begin="${pager.currentPage}"
									end="${pager.currentPage + maxPagerShowLength-1}" step="1">
									<c:choose>
										<c:when test="${p==pager.currentPage }">
											<span class="currentPage">${p }</span>
										</c:when>
										<c:otherwise>
											<a href="action/suggest/filterMyself?by=${by }&order=${order }&page=${p }&countPerPage=${pager.countPerPage}"
												class="page-slice" title="第${p }页">${p }</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
							<c:when
								test="${(pager.totalPage > maxPagerShowLength) && (pager.currentPage + maxPagerShowLength-1 > pager.totalPage)}">
								<c:forEach var="p" begin="${pager.totalPage-maxPagerShowLength + 1}"
									end="${ pager.totalPage}" step="1">
									<c:choose>
										<c:when test="${p==pager.currentPage }">
											<span class="currentPage">${p }</span>
										</c:when>
										<c:otherwise>
											<a href="action/suggest/filterMyself?by=${by }&order=${order }&page=${p }&countPerPage=${pager.countPerPage}"
												class="page-slice" title="第${p }页">${p }</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach var="p" begin="1" end="${pager.totalPage }" step="1">
									<c:choose>
										<c:when test="${p==pager.currentPage }">
											<span class="currentPage">${p }</span>
										</c:when>
										<c:otherwise>
											<a href="action/suggest/filterMyself?by=${by }&order=${order }&page=${p }&countPerPage=${pager.countPerPage}"
												class="page-slice" title="第${p }页">${p }</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:otherwise>
						</c:choose> 
						<!-- 下一页 -->
						<c:choose>
							<c:when test="${pager.currentPage < pager.totalPage }">
								<a href="action/suggest/filterMyself?by=${by }&order=${order }&page=${pager.currentPage + 1}&countPerPage=${pager.countPerPage}"
									 class="page-slice next" title="下一页">&gt;</a>
							</c:when>
							<c:otherwise>
								<span class="page-slice-disabled next">&gt;</span>
							</c:otherwise>
						</c:choose>
						<!-- 尾页 -->
						<c:choose>
							<c:when test="${pager.currentPage < pager.totalPage }">
								<a href="action/suggest/filterMyself?by=${by }&order=${order }&page=${pager.totalPage}&countPerPage=${pager.countPerPage}"
									 class="page-slice last" title="尾页">&gt;&gt;</a>
							</c:when>
							<c:otherwise>
								<span class="page-slice-disabled last">&gt;&gt;</span>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="barR" class="r">
						${pager.currentPage }/${pager.totalPage }页，共${pager.totalCount}条
						<select id="countPerPage" name="countPerPage">
							<c:forEach var="cpp" items="${countPerPageList }">
							<c:choose>
								<c:when test="${cpp==countPerPage }">
									<option value="${cpp }" selected="selected">${cpp }</option>
								</c:when>
								<c:otherwise>
									<option value="${cpp }">${cpp }</option>
								</c:otherwise>
							</c:choose>
							</c:forEach>
						</select>
						<input type="button" value="GO" class="go-bt" id="action/suggest/filterMyself?by=${by }&order=${order }&page=${pager.currentPage}${queryCondition}"/>
					</div>
					<div class="clear"></div>
				</div>
				<!-- 分页 end -->
			</form>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>