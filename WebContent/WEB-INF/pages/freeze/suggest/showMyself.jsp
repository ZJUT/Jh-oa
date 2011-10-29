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
<title> 反馈详情 </title>
</head>
<body>
<c:set var="tip" value="${requestScope.tip}"></c:set>
<c:set var="model" value="${requestScope.model }"></c:set>
<c:set var="user" value="${requestScope.user }"></c:set>
<c:set var="reply" value="${requestScope.reply }"></c:set>

<div class="crumb">
	<div class="showsuggest-title">反馈详细</div>
	<div class="backNav"><a href="action/suggest/filterMyself">返回反馈列表</a></div>
	<div class="clear"></div>
</div>
<div class="box">
	<table class="dataTableDisplay">
		<colgroup>
			<col width="15%" />
			<col width="85%" />
		</colgroup>
		<tr>
			<th>反馈内容</th>
			<td>${model.content}</td>
		</tr>
		<tr>
			<th>反馈者</th>
			<td>${user.username}</td>
		</tr>
		<tr>
			<th>反馈时间</th>
			<td><fmt:formatDate value="${model.addtime}" type="both"/></td>
		</tr>
		<c:choose>
			<c:when test="${model.replyUserID==0 }">
				<tr>
					<th>回复情况</th>
					<td>
						<div class="no-data">暂未回复</div>
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<th>回复内容</th>
					<td>${model.reply }</td>
				</tr>
				<tr>
					<th>回复者</th>
					<td>${reply.username }</td>
				</tr>
				<tr>
					<th>回复时间</th>
					<td><fmt:formatDate value="${model.replytime}" type="both"/></td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
</div>
</body>
</html>