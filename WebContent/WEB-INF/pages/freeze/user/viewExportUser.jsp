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
<title> 导出用户基本信息 </title>
</head>
<body>
<c:set var="tip" value="${requestScope.tip}"></c:set>
<c:set var="model" value="${requestScope.model }"></c:set>
<c:set var="academyList" value="${requestScope.academyList }"></c:set>
<c:set var="departmentList" value="${requestScope.departmentList }"></c:set>
<c:set var="locationList" value="屏峰,朝晖,之江" />
<c:set var="islockList" value="0,1" />

<div class="crumb">
	<div class="adduser-title">导出用户基本信息</div>
	<div class="backNav"><a href="action/user/filter">返回用户列表</a></div>
	<div class="clear"></div>
</div>
<div class="box">
<c:if test="${ not empty tip}">
<div class="optTip">提示：<span class="msg">${tip}</span></div>
</c:if>
<form name="exportuserForm" id="exportuserForm" action="action/user/exportUser" method="post">
<div class="formItem">
	
</div>
<div class="formItem">

</div>
<div class="data-operator-bar top-border">
	<input type="submit" value="导出Excel" class="bt bt-adduser" />
	<input type="reset" value="取消" class="bt bt-cancle" />
	<div class="clear"></div>
</div>
</form>
</div>
<script type="text/javascript">
$(function(){
	
});
</script>
</body>
</html>