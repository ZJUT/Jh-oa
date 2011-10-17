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
<title> 发布新文件 </title>
</head>
<body>
<c:set var="loginUser" value="${sessionScope.loginUser }" ></c:set>
<c:set var="username" value="${fn:split(loginUser,'&')[2] }" ></c:set>
<c:set var="uid" value="${fn:split(loginUser,'&')[1] }" ></c:set>
<c:set var="userID" value="${fn:split(loginUser,'&')[0] }" ></c:set>

<c:set var="tip" value="${requestScope.tip}"></c:set>
<c:set var="model" value="${requestScope.model }"></c:set>

<div class="crumb">
	<div class="addffile-title">发布新文件</div>
	<div class="backNav"><a href="action/ffile/filter">返回文件列表</a></div>
	<div class="clear"></div>
</div>
<div class="box">
<c:if test="${ not empty tip}">
<div class="optTip">提示：<span class="msg">${tip}</span></div>
</c:if>
<form name="addffileForm" id="addffileForm" action="action/ffile/add" method="post">
<div class="formItem">
	<input type="text" id="showname" name="showname" class="showname" value="${model.showname }"/>
</div>
<div class="formItem">
	<input type="file" name="selectfile" id="selectfile" />
	<input type="button" id="upload-bt"  value="上传" />
</div>
<input type="hidden" name="filename" value="${model.filename }" />
<input type="hidden" name="userID" value="${userID }" />
<div class="formItem">
	<input type="submit" value="发布" class="bt bt-addffile" />
</div>
</form>
</div>
<script type="text/javascript">
$(function(){
	$('#selectfile').change(function(){
		alert('change');
	});
	$('#upload-bt').click(function(){
		alert('upload');
	});
	if($('#showname').val()==''){
		$('#showname').focus();
	}
	
});

</script>
</body>
</html>