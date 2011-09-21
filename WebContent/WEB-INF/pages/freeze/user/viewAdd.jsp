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
<title> 添加新用户 </title>
</head>
<body>
<c:set var="tip" value="${requestScope.tip}"></c:set>
<c:set var="model" value="${requestScope.model }"></c:set>
<c:set var="academyList" value="${requestScope.academyList }"></c:set>
<c:set var="departmentList" value="${requestScope.departmentList }"></c:set>
<c:set var="locationList" value="屏峰,朝晖,之江" />
<c:set var="islockList" value="0,1" />

<div class="crumb">
	<div class="adduser-title">添加新用户</div>
	<div class="backNav"><a href="action/user/filter">返回用户列表</a></div>
	<div class="clear"></div>
</div>
<div class="box">
<c:if test="${ not empty tip}">
<div class="optTip">提示：<span class="msg">${tip}</span></div>
</c:if>
<form name="adduserForm" id="adduserForm" action="action/user/add" method="post">
<table width="100%">
	<tr>
		<td>
			<label for="uid" class="common-label">学号</label>
			<input type="text" id="uid" name="uid" class="uid" value="${model.uid }"/>
		</td>
		<td>
			<label for="cornet" class="common-label">短号</label>
			<input type="text" id="cornet" name="cornet" class="cornet" value="${model.cornet }" />
		</td>
	</tr>
	<tr>
		<td>
			<label for="username" class="common-label">姓名</label>
			<input type="text" id="username" name="username" class="username" value="${model.username }"/>
		</td>
		<td>
			<label for="telephone" class="common-label">手机号码</label>
			<input type="text" id="telephone" name="telephone" class="telephone" value="${model.telephone }" />
		</td>
	</tr>
	<tr>
		<td>
			<label for="password" class="common-label">密码</label>
			<input type="password" id="password" name="password" class="password" />
		</td>
		<td>
			<label for="academyID" class="common-label">学院</label>
			<select id="academyID" name="academyID" class="academyID">
				<option value="-1">==== 请选择学院  ====</option>
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
		</td>
	</tr>
	<tr>
		<td>
			<label for="email" class="common-label">Email地址</label>
			<input type="text" id="email" name="email" class="email" value="${model.email }" />
		</td>
		<td>
			<label for="major" class="common-label">专业班级</label>
			<input type="text" id="major" name="major" class="major" value="${model.major }" />
		</td>
	</tr>
	<tr>
		<td>
			<label for="islock" class="common-label">状态</label>
			<select id="islock" name="islock" class="islock">
				<option value="-1">==== 请选择状态  ====</option>
				<c:forEach var="islock" items="${islockList }">
					<c:choose>
						<c:when test="${islock == model.islock }">
							<option value="${islock }" selected="selected">${islock ==0 ? "可用" :"锁定" }</option>
						</c:when>
						<c:otherwise>
							<option value="${islock }">${islock==0 ? "可用":"锁定" }</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</td>
		<td>
			<label for="location" class="common-label">所在校区</label>
			<select id="location" name="location" class="location">
				<option value="-1">==== 请选择所在校区  ====</option>
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
		</td>
	</tr>
	<tr>
		<td>
			<label for="departmentID" class="common-label">所属部门</label>
			<select id="departmentID" name="departmentID" class="departmentID">
				<option value="-1">==== 请选择部门  ====</option>
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
		</td>
		<td>
			<label for="dormitory" class="common-label">宿舍</label>
			<input type="text" id="dormitory" name="dormitory" class="dormitory" value="${model.dormitory }" />
		</td>
	</tr>
</table>
<div class="data-operator-bar top-border">
	<input type="submit" value="添加" class="bt bt-adduser" />
	<input type="reset" value="取消" class="bt bt-cancle" />
	<div class="clear"></div>
</div>
</form>
</div>
<script type="text/javascript">
$(function(){
	
	if($('#uid').val()==''){
		$('#uid').focus();
	}
	else if($('#uid').val()!=''&& $('#username').val()==''){
		$('#username').focus();
	}
	else if($('#uid').val()!=''&& $('#username').val()!=''&& $('#password').val()==''){
		$('#password').focus();
	}
	else if($('#uid').val()!=''&& $('#username').val()!=''&& $('#password').val()!=''&& $('#email').val()==''){
		$('#email').focus();
	}
	else if($('#uid').val()!=''&& $('#username').val()!=''&& $('#password').val()!=''&& $('#email').val()!='' && $('#cornet').val()==''){
		$('#cornet').focus();
	}
	else if($('#uid').val()!=''&& $('#username').val()!=''&& $('#password').val()!=''&& $('#email').val()!='' && $('#cornet').val()!='' && $('#departmentID').val() == -1){
		$('#departmentID').focus();
	}
	/*
	else if($('#uid').val()!=''&& $('#username').val()!=''&& $('#email').val()!='' && $('#cornet').val()!='' && $('#telephone').val()==''){
		$('#telephone').focus();
	}
	else if($('#uid').val()!=''&& $('#username').val()!=''&& $('#email').val()!='' && $('#cornet').val()!='' && $('#telephone').val()!='' && $('#academyID').val()==-1){
		$('#academyID').focus();
	}
	else if($('#uid').val()!=''&& $('#username').val()!=''&& $('#email').val()!='' && $('#cornet').val()!='' && $('#telephone').val()!='' && $('#academyID').val()!=-1 && $('#major').val()==''){
		$('#major').focus();
	}
	else if($('#uid').val()!=''&& $('#username').val()!=''&& $('#email').val()!='' && $('#cornet').val()!='' && $('#telephone').val()!='' && $('#academyID').val()!=-1 && $('#major').val()!='' && $('#location').val()==-1 ){
		$('#location').focus();
	}
	else if($('#uid').val()!=''&& $('#username').val()!=''&& $('#email').val()!='' && $('#cornet').val()!='' && $('#telephone').val()!='' && $('#academyID').val()!=-1 && $('#major').val()!='' && $('#location').val()!=-1 && $('#dormitory').val() ==''){
		$('#dormitory').focus();
	}
	*/
	else if($('#uid').val()!=''&& $('#username').val()!=''&& $('#password').val()!=''&& $('#email').val()!='' && $('#cornet').val()!='' && $('#departmentID').val()!=-1 /*&& $('#telephone').val()!='' && $('#academyID').val()!=-1 && $('#major').val()!='' && $('#location').val()!=-1 && $('#dormitory').val() !=''*/&& $('#islock').val()== -1){
		$('#islock').focus();
	}
	else{
		$('#uid').select();
	}
});
</script>
</body>
</html>