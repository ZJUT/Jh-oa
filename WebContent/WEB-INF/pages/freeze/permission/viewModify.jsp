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
<title> 编辑权限</title>
</head>
<body>
<c:set var="tip" value="${requestScope.tip}"></c:set>
<c:set var="model" value="${requestScope.model }"></c:set>
<c:set var="menuList" value="${requestScope.menuList }"></c:set>
<c:set var="resourceList" value="${requestScope.resourceList }"></c:set>
<c:set var="operatorList" value="${requestScope.operatorList }"></c:set>


<div class="crumb">
	<div class="addpermission-title">编辑权限</div>
	<div class="backNav"><a href="action/permission/filter">返回权限列表</a></div>
	<div class="clear"></div>
</div>
<div class="box">
<c:if test="${ not empty tip}">
<div class="optTip">提示：<span class="msg">${tip}</span></div>
</c:if>
<form name="modifypermissionForm" id="modifypermissionForm" action="action/permission/modify" method="post">
<div class="formItem">
	<label for="menuID" class="common-label">菜单</label>
	<select id="menuID" name="menuID">
		<option value="-1">== 请选择菜单 ==</option>
		<c:choose>
			<c:when test="${ empty menuList }">
				<option value="0">无</option>
			</c:when>
			<c:otherwise>
				<c:forEach var="menu" items="${menuList }">
					<c:choose>
						<c:when test="${model.menuID== menu.id }">
							<option value="${menu.id }" selected="selected">${menu.menuname }</option>
						</c:when>
						<c:otherwise>
							<option value="${menu.id }">${menu.menuname }</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>		
	</select>
</div>
<div class="formItem">
	<label for="resourceID" class="common-label">资源</label>
	<select id="resourceID" name="resourceID">
		<option value="-1">== 请选择资源 ==</option>
		<c:choose>
			<c:when test="${ empty resourceList }">
				<option value="0">无</option>
			</c:when>
			<c:otherwise>
				<c:forEach var="resource" items="${resourceList }">
					<c:choose>
						<c:when test="${model.resourceID== resource.id }">
							<option value="${resource.id }" selected="selected">${resource.resourcename }</option>
						</c:when>
						<c:otherwise>
							<option value="${resource.id }">${resource.resourcename }</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>		
	</select>
</div>
<div class="formItem">
	<label for="optID" class="common-label">操作</label>
	<select id="optID" name="optID">
		<option value="-1">== 请选择操作 ==</option>
		<c:choose>
			<c:when test="${ empty operatorList }">
				<option value="0">无</option>
			</c:when>
			<c:otherwise>
				<c:forEach var="operator" items="${operatorList }">
					<c:choose>
						<c:when test="${model.optID== operator.id }">
							<option value="${operator.id }" selected="selected">${operator.optname }[${operator.optvalue }]</option>
						</c:when>
						<c:otherwise>
							<option value="${operator.id }">${operator.optname }[${operator.optvalue }]</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>		
	</select>
</div>
<div class="formItem">
	<label for="description" class="common-label">描述</label>
	<input type="text" id="description" name="description" class="description" value="${model.description }" />
</div>
<input type="hidden" name="id" value="${model.id }" />
<div class="formItem">
	<input type="submit" value="完成编辑" class="bt bt-addpermission" />
</div>
</form>
</div>
<script type="text/javascript">
$(function(){
	if($('#menuID').val()=='-1'){
		$('#menuID').focus();
	}
	else if($('#menuID').val()!='-1' && $('#resourceID').val()=='-1'){
		$('#resourceID').focus();
	}
	else if($('#menuID').val()!='-1' && $('#resourceID').val()!='-1'  && $('#optID').val()=='-1'){
		$('#optID').focus();
	}
	else if($('#menuID').val()!='-1' && $('#resourceID').val()!='-1'  && $('#optID').val()!='-1'&& $('#description').val()==''){
		$('#description').focus();
	}
	else{
		$('#menuID').focus();
	}
});
</script>
</body>
</html>