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
<title> 查看角色权限 </title>
</head>
<body>
<c:set var="rptListForRoleID" value="${requestScope.rptListForRoleID }"></c:set>
<c:set var="role" value="${requestScope.role }"></c:set>
<c:set var="listLength" value="${fn:length(rptListForRoleID) }" ></c:set>

<div class="crumb">
	<div class="viewrolepermission-title">查看角色权限</div>
	<div class="backNav"><a href="action/role/list">返回角色列表</a></div>
	<div class="clear"></div>
</div>
<div class="quick-action">
	<a href="action/rolepermission/viewAdd" class="button-like"><span class="add-rolepermission">分配角色权限</span></a>
</div>
<div class="box">
	<table class="dataTableDisplay-nohover">
		<colgroup>
			<col width="40%" />
			<col width="60%" />
		</colgroup>
		<tr>
			<th>[${role.rolename }]所拥有的权限有：</th>
		</tr>
		<%-- 状态标识 --%>
		<c:set var="resourceCache" value=""></c:set>
		<c:set var="resetNum" value="false"></c:set>
		<c:set var="num" value="1"></c:set>
		<tr>
		<td>
		<c:choose>
			<c:when test="${empty rptListForRoleID }">
				无
			</c:when>
			<c:otherwise>
			<c:forEach var="rolepermissiontogether" items="${rptListForRoleID }" varStatus="status">
				<c:set var="role" value="${rolepermissiontogether.role }"></c:set>
				<c:set var="permissionTogether" value="${rolepermissiontogether.permissiontogether }"></c:set>
				<c:set var="menu" value="${permissionTogether.menu }"></c:set>
				<c:set var="resource" value="${permissionTogether.resource }"></c:set>
				<c:set var="operator" value="${permissionTogether.operator }"></c:set>
				
				<c:choose>
					<c:when test="${resourceCache != resource.resourcename  }">
						<c:set var="resourceCache" value="${resource.resourcename }"></c:set>
						<c:set var="resetNum" value="true"></c:set>
						<c:choose>
							<c:when test="${resetNum }">
								<c:set var="num" value="1"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="num" value="${num+1 }"></c:set>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${status.index!=1 }">
								</div><div class="rolepermissionItem">
							</c:when>
							<c:otherwise>
								<div class="rolepermissionItem">
							</c:otherwise>
						</c:choose>
						<h2>${resource.resourcename }</h2>
						<span class="permissionItem">${operator.optname }[${operator.optvalue }]</span> 
					</c:when>
					<c:otherwise>
						<c:set var="resetNum" value="false"></c:set>
						<c:choose>
							<c:when test="${resetNum }">
								<c:set var="num" value="1"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="num" value="${num+1 }"></c:set>
							</c:otherwise>
						</c:choose>
						<span class="permissionItem">${operator.optname }[${operator.optvalue }]</span> 
					</c:otherwise>
				</c:choose>
			</c:forEach>	
			</c:otherwise>
		</c:choose>
		</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<th>
				<input type="button" value="重新分配" onclick="goUrl('action/rolepermission/viewModify?roleID=${role.id }');" class="bt bt-addrolepermission" />
			</th>
		</tr>
	</table>
</div>
</body>
</html>