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
<title> 用户详细 </title>
</head>
<body>
<c:set var="tip" value="${requestScope.tip}"></c:set>
<c:set var="model" value="${requestScope.model }"></c:set>
<c:set var="academyList" value="${requestScope.academyList }"></c:set>

<div class="crumb">
	<div class="adduser-title">用户详细资料</div>
	<div class="backNav"><a href="action/user/filter">返回用户列表</a></div>
	<div class="clear"></div>
</div>
<div class="box">
	<table class="dataTableDisplay">
		<colgroup>
			<col width="15%" />
			<col width="35%" />
			<col width="15%" />
			<col width="35%" />
		</colgroup>
		<tr>
			<th>学号：</th>
			<td>${model.uid }</td>
			<th>姓名：</th>
			<td>${model.username}</td>
		</tr>
		<tr>
			<th>Email地址：</th>
			<td>${model.email }</td>
			<th>短号：</th>
			<td>${model.cornet}</td>
		</tr>
		<tr>
			<th>电话号码长号：</th>
			<td>${model.telephone }</td>
			<th>所属学院：</th>
			<td>
			<c:forEach var="academy" items="${academyList }">
				<c:if test="${academy.id == model.academyID }">
					${academy.academyname }					
				</c:if>
			</c:forEach>
			</td>
		</tr>
		<tr>
			<th>专业班级：</th>
			<td>${model.major }</td>
			<th>所在校区：</th>
			<td>${model.location}</td>
		</tr>
		<tr>
			<th>宿舍：</th>
			<td>${model.dormitory}</td>
			<th>状态：</th>
			<td>${model.islock==0 ? "可用" : "锁定" }</td>
		</tr>
		<tr>
			<th>添加时间：</th>
			<td><fmt:formatDate value="${model.addtime }" type="both"></fmt:formatDate></td>
			<th>最后编辑时间：</th>
			<td><fmt:formatDate value="${model.modifytime }" type="both"></fmt:formatDate></td>
		</tr>
	</table>
	<div class="data-operator-bar top-border">
		<input type="button" value="编辑" class="bt bt-adduser" onclick="javascript:goUrl('action/user/viewModify?id=${model.id}');"/>
		<div class="clear"></div>
	</div>
</div>
</body>
</html>