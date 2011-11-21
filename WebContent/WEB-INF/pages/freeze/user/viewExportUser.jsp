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
<c:set var="jobList" value="${requestScope.jobList}"></c:set>
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
	<label for="savefilename" class="common-label">Excel文件名</label>
	<input type="text" name="savefilename" id="savefilename" class="savefilename" /> 
</div>
<div class="formItem">
	<label for="academyID" class="common-label">所属学院</label>
	<select name="academyID" id="academyID">
		<option value="-1">==== 全部学院 ====</option>
		<c:forEach var="academy" items="${academyList }">
			<c:choose>
				<c:when test="${academy.id==model.academyID }">
					<option value="${academy.id }" selected="selected">${academy.academyname }</option>
				</c:when>
				<c:otherwise>
					<option value="${academy.id }">${academy.academyname }</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
</div>
<div class="formItem">
	<label for="departmentID" class="common-label">所在部门</label>
	<select name="departmentID" id="departmentID">
		<option value="-1">==== 全部部门 ====</option>
		<c:forEach var="department" items="${departmentList }">
			<c:choose>
				<c:when test="${department.id==model.departmentID }">
					<option value="${department.id }" selected="selected">${department.departmentname }</option>
				</c:when>
				<c:otherwise>
					<option value="${department.id }">${department.departmentname }</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
</div>
<div class="formItem">
	<label for="jobID" class="common-label">职务</label>
	<select name="jobID" id="jobID">
		<option value="-1">==== 全部职务 ====</option>
		<c:forEach var="job" items="${jobList }">
			<c:choose>
				<c:when test="${job.id==model.jobID }">
					<option value="${job.id }" selected="selected">${job.jobname }</option>
				</c:when>
				<c:otherwise>
					<option value="${job.id }">${job.jobname }</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
</div>
<div class="formItem">
	<label for="location" class="common-label">所在校区</label>
	<select name="location" id="location">
		<option value="-1">==== 全部校区 ====</option>
		<c:forEach var="location" items="${locationList }">
			<c:choose>
				<c:when test="${location==model.location }">
					<option value="${location }" selected="selected">${location }</option>
				</c:when>
				<c:otherwise>
					<option value="${location }">${location }</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
</div>
<div class="formItem">
	<label for="islock" class="common-label">用户状态</label>
	<select name="islock" id="islock">
		<option value="-1">==== 全部状态 ====</option>
		<c:forEach var="islock" items="${islockList }">
			<c:choose>
				<c:when test="${islock==model.islock }">
					<option value="${islock }" selected="selected">${islock==1 ? "锁定" :"可用" }</option>
				</c:when>
				<c:otherwise>
					<option value="${islock }">${islock==1 ? "锁定" :"可用" }</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
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
	if($('#savefilename').val()==''){
		$('#savefilename').focus();
	}
});
</script>
</body>
</html>