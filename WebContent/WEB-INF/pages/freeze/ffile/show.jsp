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
<title> 文件详细详情 </title>
</head>
<body>
<c:set var="tip" value="${requestScope.tip}"></c:set>
<c:set var="model" value="${requestScope.model }"></c:set>
<c:set var="user" value="${requestScope.user }"></c:set>

<div class="crumb">
	<div class="showffile-title">${model.showname }</div>
	<div class="backNav"><a href="action/ffile/filter">返回文件列表</a></div>
	<div class="clear"></div>
</div>
<div class="box">
	<table class="dataTableDisplay">
		<colgroup>
			<col width="15%" />
			<col width="85%" />
		</colgroup>
		<tr>
			<th>上传人</th>
			<td>${user.username}</td>
		</tr>
		<tr>
			<th>实际文件名</th>
			<td style="position:relative;">
				${model.filename}
				<a href="${model.filename }" class="download" style="position:absolute;right:10px;">立即下载</a>
			</td>
		</tr>
		<tr>
			<th>显示文件名</th>
			<td>${model.showname}</td>
		</tr>
		<tr>
			<th>大小</th>
			<td>${model.size}</td>
		</tr>
		<tr>
			<th>后缀</th>
			<td>${model.suffix}</td>
		</tr>
		<tr>
			<th>发布时间</th>
			<td><fmt:formatDate value="${model.addtime}" type="both"/></td>
		</tr>
	</table>
	<div class="commentContainer">
		<h2>评论列表</h2>
		<div class="commentListContainer">
			正在加载评论列表...
		</div>
		<div class="addCommentContainer">
			<textarea id="comment-content"></textarea>
			<input type="button" value="添加评论" class="bt bt-comment" style="float:none;display:block;margin:5px;" />
		</div>
	</div>
</div>
</body>
</html>