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
<title> 权限 </title>
</head>
<body>
<c:set var="currentPage" value="${requestScope.currentPage}"></c:set>
<c:set var="countPerPage" value="${requestScope.countPerPage}"></c:set>
<c:set var="pager" value="${requestScope.pager}"></c:set>
<c:set var="maxPagerShowLength" value="${requestScope.maxPagerShowLength}"></c:set>
<c:set var="countPerPageList" value="20,40,80,160" />

<c:set var="dataList" value="${requestScope.dataList }"></c:set>

<div class="crumb">
	<div class="addpermission-title">权限</div>
	<div class="backNav"><a href="action/global/manager">返回管理首页</a></div>
	<div class="clear"></div>
</div>
<div class="quick-action">
	<a href="action/permission/viewAdd" class="button-like"><span class="add-permission">添加权限</span></a>
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
			<form action="action/permission/batchDelete">
				<!-- 批量删除 -->
				<input type="hidden" name="by" value="${by }" />
				<input type="hidden" name="order" value="${order }" />
				<input type="hidden" name="page" value="${page }" />
				<input type="hidden" name="countPerPage" value="${pager.countPerPage }" />
				
				<table class="dataTable">
					<colgroup>
						<col width="5%" />
						<col width="20%" />
						<col width="20%" />
						<col width="20%" />
						<col width="20%" />
						<col width="15%" />
					</colgroup>
					<tr>
						<th>删?</th>
						<th>菜单名</th>
						<th>资源名</th>
						<th>操作描述</th>
						<th>权限描述</th>
						<th>操作</th>
					</tr>
					<c:forEach var="permissiontogether" items="${dataList }">
					<c:set var="menu" value="${permissiontogether.menu }"></c:set>
					<c:set var="resource" value="${permissiontogether.resource }"></c:set>
					<c:set var="operator" value="${permissiontogether.operator }"></c:set>
					<tr>
						<td>
							<input type="checkbox" name="deleteId" value="${permissiontogether.id }" class="common-checkbox"/>
						</td>
						<td>${menu.menuname }</td>
						<td>${resource.resourcename }</td>
						<td>${operator.optname }</td>
						<td>${permissiontogether.description }</td>
						<td>
							<a href="action/permission/viewModify?id=${permissiontogether.id }" class="modify">编辑</a>
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
							<a href="action/permission/filter?by=${by }&order=${order }&page=1&countPerPage=${pager.countPerPage}"
								class="page-slice first" title="首页">&lt;&lt;</a>
							</c:when>
							<c:otherwise>
								<span class="page-slice-disabled first">&lt;&lt;</span>
							</c:otherwise>
						</c:choose> 
						<!-- 上一页 -->
						<c:choose>
							<c:when test="${pager.currentPage > 1 }">
								<a href="action/permission/filter?by=${by }&order=${order }&page=${pager.currentPage - 1 }&countPerPage=${pager.countPerPage}"
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
											<a href="action/permission/filter?by=${by }&order=${order }&page=${p }&countPerPage=${pager.countPerPage}"
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
											<a href="action/permission/filter?by=${by }&order=${order }&page=${p }&countPerPage=${pager.countPerPage}"
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
											<a href="action/permission/filter?by=${by }&order=${order }&page=${p }&countPerPage=${pager.countPerPage}"
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
											<a href="action/permission/filter?by=${by }&order=${order }&page=${p }&countPerPage=${pager.countPerPage}"
												class="page-slice" title="第${p }页">${p }</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:otherwise>
						</c:choose> 
						<!-- 下一页 -->
						<c:choose>
							<c:when test="${pager.currentPage < pager.totalPage }">
								<a href="action/permission/filter?by=${by }&order=${order }&page=${pager.currentPage + 1}&countPerPage=${pager.countPerPage}"
									 class="page-slice next" title="下一页">&gt;</a>
							</c:when>
							<c:otherwise>
								<span class="page-slice-disabled next">&gt;</span>
							</c:otherwise>
						</c:choose>
						<!-- 尾页 -->
						<c:choose>
							<c:when test="${pager.currentPage < pager.totalPage }">
								<a href="action/permission/filter?by=${by }&order=${order }&page=${pager.totalPage}&countPerPage=${pager.countPerPage}"
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
						<input type="button" value="GO" class="go-bt" id="action/permission/filter?by=${by }&order=${order }&page=${pager.currentPage}${queryCondition}"/>
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