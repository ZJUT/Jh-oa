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
<title> 所有产品</title>
</head>
<body>
<c:set var="dataList" value="${requestScope.dataList }"></c:set>

<div class="crumb">
	<div class="addproduct-title">产品</div>
	<div class="backNav"><a href="action/global/manager">返回管理首页</a></div>
	<div class="clear"></div>
</div>
<div class="quick-action">
	<a href="action/product/viewAdd" class="button-like"><span class="add-product">发布新产品</span></a>
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
				<table class="dataTable">
					<colgroup>
						<col width="15%" />
						<col width="45%" />
						<col width="20%" />
						<col width="20%" />
					</colgroup>
					<tr>
						<th>产品名称</th>
						<th>链接地址</th>
						<th>发布时间</th>
						<th>操作</th>
					</tr>
					<c:forEach var="product" items="${dataList }">
					<tr>
						<td>
							<a href="${product.link}" title="查看产品主页">${product.name}</a>					
						</td>
						<td>
							${product.link}							
						</td>
						<td>
							 <fmt:formatDate value="${product.addtime }" type="both"/>						
						</td>
						<td>
							<a href="action/global/anonymous_product_show?id=${product.id }" class="view">查看</a>
							<a href="action/product/viewModify?id=${product.id }" class="modify">编辑</a>
							<a href="javascript:void(0);" id="action/product/delete?id=${product.id }" class="delete jqModal">删除</a>
						</td>
					</tr>
					</c:forEach>
				</table>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>