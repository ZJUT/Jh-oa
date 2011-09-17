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
<title> 重新分配角色权限 </title>
</head>
<body>
<c:set var="tip" value="${requestScope.tip}"></c:set>
<c:set var="rpListForRoleID" value="${requestScope.rpListForRoleID }"></c:set>
<c:set var="roleList" value="${requestScope.roleList }"></c:set>
<c:set var="permissionTogetherList" value="${requestScope.permissionTogetherList }"></c:set>


<div class="crumb">
	<div class="addrolepermission-title">重新分配角色权限</div>
	<div class="backNav"><a href="action/role/list">返回角色列表</a></div>
	<div class="clear"></div>
</div>
<div class="box">
<c:if test="${ not empty tip}">
<div class="optTip">提示：<span class="msg">${tip}</span></div>
</c:if>
<form name="modifyrolepermissionForm" id="modifyrolepermissionForm" action="action/rolepermission/modify" method="post">
<div class="formItem">
	<label for="roleID" class="common-label">角色:</label>
	<c:forEach var="role" items="${roleList }">
		<c:if test="${model.roleID == role.id || requestScope.roleID == role.id}">
			${role.rolename }
		</c:if>
	</c:forEach>
	<input type="hidden" name="roleID" value="${requestScope.roleID }" />
	<%-- 
	<select id="roleID" name="roleID">
		<option value="-1">== 请选择角色 ==</option>
		<c:choose>
			<c:when test="${ empty roleList }">
				<option value="0">无</option>
			</c:when>
			<c:otherwise>
				<c:forEach var="role" items="${roleList }">
					<c:choose>
						<c:when test="${model.roleID == role.id || requestScope.roleID == role.id}">
							<option value="${role.id }" selected="selected">${role.rolename }</option>
						</c:when>
						<c:otherwise>
							<option value="${role.id }">${role.rolename }</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>		
	</select>
	 --%>
</div>
<div class="formItem">
	<div class="permissionBox">
	<%-- 状态标识 --%>
	<c:set var="resourceCache" value=""></c:set>
	
	<c:forEach var="permissionTogether" items="${permissionTogetherList }">
		<c:set var="menu" value="${permissionTogether.menu }"></c:set>
		<c:set var="resource" value="${permissionTogether.resource }"></c:set>
		<c:set var="operator" value="${permissionTogether.operator }"></c:set>
		<c:set var="isChecked" value="false"></c:set>
		<c:forEach var="rp" items="${rpListForRoleID }">
			<c:if test="${rp.permissionID == permissionTogether.id }">
				<c:set var="isChecked" value="true"></c:set>
			</c:if>
		</c:forEach>
		<c:choose>
			<c:when test="${resourceCache != resource.resourcename  }">
				<c:set var="resourceCache" value="${resource.resourcename }"></c:set>
				<h2>${resource.resourcename }</h2>
				<span class="permissionItem"><label for="${menu.id}-${resource.id}-${operator.id}">
					<c:choose>
						<c:when test="${isChecked }">
							<input type="checkbox" id="${menu.id}-${resource.id}-${operator.id}" name="permissionID"  value="${permissionTogether.id }" class="common-checkbox-1" checked="checked" /> ${operator.optname }
						</c:when>
						<c:otherwise>
							<input type="checkbox" id="${menu.id}-${resource.id}-${operator.id}" name="permissionID"  value="${permissionTogether.id }" class="common-checkbox-1" /> ${operator.optname }
						</c:otherwise>
					</c:choose>
				</label></span> 
			</c:when>
			<c:otherwise>
				<span class="permissionItem"><label for="${menu.id}-${resource.id}-${operator.id}">
					<c:choose>
						<c:when test="${isChecked }">
							<input type="checkbox" id="${menu.id}-${resource.id}-${operator.id}" name="permissionID"  value="${permissionTogether.id }" class="common-checkbox-1" checked="checked" /> ${operator.optname }
						</c:when>
						<c:otherwise>
							<input type="checkbox" id="${menu.id}-${resource.id}-${operator.id}" name="permissionID"  value="${permissionTogether.id }" class="common-checkbox-1" /> ${operator.optname }
						</c:otherwise>
					</c:choose> 
				</label></span> 
			</c:otherwise>
		</c:choose>
		
	</c:forEach>
	</div>
</div>
<div class="formItem">
	<input type="submit" value="重新分配" class="bt bt-addrolepermission" />
</div>
</form>
</div>
<script type="text/javascript">
$(function(){
	if($('#roleID').val()=='-1'){
		$('#roleID').focus();
	}
});
</script>
</body>
</html>