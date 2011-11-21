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
<title> 空课查询结果 </title>
</head>
<body>
<c:set var="fktList" value="${requestScope.fktList }"></c:set>

<div class="crumb">
	<div class="addke-title">空课查询结果</div>
	<div class="backNav"><a href="action/ke/filter">返回课表列表</a></div>
	<div class="clear"></div>
</div>
<div class="quick-action">
</div>
<div class="box">
	<c:if test="${ not empty tip}">
		<div class="optTip">提示：<span class="msg">${tip}</span></div>
	</c:if>
	<c:choose>
		<c:when test="${empty fktList }">
			<div class="no-data">无任何数据</div>
		</c:when>
		<c:otherwise>
			<c:forEach var="freeketogether" items="${fktList }">
				<c:set var="ke" value="${freeketogether.ke }"></c:set>
				<c:set var="usertogether" value="${freeketogether.usertogether }"></c:set>
				<c:set var="user" value="${usertogether.user }"></c:set>
				<c:set var="academy" value="${usertogether.academy }"></c:set>
				<c:set var="department" value="${usertogether.department }"></c:set>
				<c:set var="job" value="${usertogether.job }"></c:set>
				<c:set var="total" value="${freeketogether.total }"></c:set>
				<div class="toggleBar">
					<div class="tbTitle">${department.departmentname }共${total }人有空</div>
					<div class="tbOpt"><a href="javascript" class="toggleDetail">空闲情况</a></div>
					<div class="clear"></div>
				</div>
				<div class="toggleArea">
					a
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>