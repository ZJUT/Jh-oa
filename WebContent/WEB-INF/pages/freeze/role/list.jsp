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
<title> 所有角色</title>
</head>
<body>
<c:set var="dataList" value="${requestScope.dataList }"></c:set>

<div class="crumb">
	<div class="addrole-title">角色</div>
	<div class="backNav"><a href="action/global/manager">返回管理首页</a></div>
	<div class="clear"></div>
</div>
<div class="quick-action">
	<a href="action/role/viewAdd" class="button-like"><span class="add-role">添加新角色</span></a>
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
						<col width="20%" />
						<col width="80%" />
					</colgroup>
					<tr>
						<th>角色名</th>
						<th>操作</th>
					</tr>
					<c:forEach var="roletogether" items="${dataList }">
					<c:set var="role" value="${roletogether.role }"></c:set>
					<tr>
						<td>
							${role.rolename}							
						</td>
						<td>
							<a href="action/role/viewModify?id=${role.id }" class="modify">编辑</a>
							<a href="javascript:void(0);" id="action/role/delete?id=${role.id} " class="delete jqModal">删除</a>
							<a href="action/rolepermission/filter?roleID=${role.id }" class="viewpermission">查看权限</a>
							<c:choose>
								<c:when  test="${roletogether.hasDistribute }">
									<a href="action/rolepermission/viewModify?roleID=${role.id }" class="redistributepermission">重新分配角色权限</a>
									<a href="action/rolepermission/delete?roleID=${role.id }" id="action/rolepermission/delete?roleID=${role.id} " class="delete jqModal">删除角色权限</a>
								</c:when>
								<c:otherwise>
									<a href="action/rolepermission/viewAdd?roleID=${role.id }" class="distributepermission">分配角色权限</a>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					</c:forEach>
				</table>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>