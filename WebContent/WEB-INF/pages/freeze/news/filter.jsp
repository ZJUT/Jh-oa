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
<title> 所有动态 </title>
</head>
<body>
<c:set var="dataList" value="${requestScope.dataList }"></c:set>
<div class="crumb">动态</div>
<div class="quick-action">
	<a href="action/news/viewAdd" class="button-like"><span class="add-news">发布新动态</span></a>
</div>
<div class="box">
	<c:choose>
		<c:when test="${not empty dataList }">
			<table class="dataTable">
				<tr>
					<th>标题</th>
					<th>发布人</th>
					<th>添加时间</th>
				</tr>
				<c:forEach var="news" items="${dataList }">
				<tr>
					<td>${news.title }</td>
					<td>${news.userID }</td>
					<td>${news.addtime }</td>
				</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<div class="no-data">无任何数据</div>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>