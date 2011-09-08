<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.zjut.oa.db.*" %>
<%@ page import="com.zjut.oa.mvc.action.*" %>
<%@ page import="com.zjut.oa.mvc.core.*" %>
<%@ page import="com.zjut.oa.mvc.domain.*" %>
<%@ page import="com.zjut.oa.mvc.filter.*" %>
<%@ page import="com.zjut.oa.tool.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>"></base> 
<title> <decorator:title default="模板默认标题" /> </title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="shortcut icon" type="image/ico" href="website.ico">
<script language="javascript" type="text/javascript" src="common/js/jquery.min.js"></script>
<script language="javascript" type="text/javascript" src="common/js/common.js"></script>
<script charset="UTF-8" type="text/javascript" src="kindeditor/kindeditor-min.js"></script>
<link rel="stylesheet" type="text/css" href="common/css/common.css">
</head>
<body>
<div id="page">
	<%@ include file="/include/managerHeader.jsp" %>
	<div id="manager">
		<div class="menuContainer">
			<h2>全部功能菜单</h2>
			<div class="menuItem">
				<h3><a href="action/news/filter" >动态</a></h3>
			</div>
		</div>
		<div class="bodyContainer">
			<decorator:body />
		</div>
	</div>
	<div id="footer">
		 精弘版权所有  © 2011  <a href="mailto:qingtian16265@gmail.com" title="给晴天发邮件">晴天</a>
	</div>
</div>
</body>
</html>