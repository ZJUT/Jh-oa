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
<title> 添加新部门 </title>
</head>
<body>
<c:set var="tip" value="${requestScope.tip}"></c:set>
<c:set var="model" value="${requestScope.model }"></c:set>

<div class="crumb">
	<div class="adddepartment-title">添加新部门</div>
	<div class="backNav"><a href="action/department/list">返回部门列表</a></div>
	<div class="clear"></div>
</div>
<div class="box">
<c:if test="${ not empty tip}">
<div class="optTip">提示：<span class="msg">${tip}</span></div>
</c:if>
<form name="adddepartmentForm" id="adddepartmentForm" action="action/department/add" method="post">
<div class="formItem">
	<label for="departmentname" class="common-label">部门名</label>
	<input type="text" id="departmentname" name="departmentname" class="departmentname" value="${model.departmentname }"/>
</div>
<div class="formItem">
	<input type="submit" value="添加" class="bt bt-adddepartment" />
</div>
</form>
</div>
<script type="text/javascript">
$(function(){
	if($('#departmentname').val()==''){
		$('#departmentname').focus();
	}
	else{
		$('#departmentname').select();
	}
});
</script>
</body>
</html>