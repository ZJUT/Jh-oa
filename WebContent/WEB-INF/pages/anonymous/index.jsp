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
<title> 登录 </title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="shortcut icon" type="image/ico" href="website.ico">
<script language="javascript" type="text/javascript" src="common/js/jquery.min.js"></script>
<script language="javascript" type="text/javascript" src="common/js/common.js"></script>
<link rel="stylesheet" type="text/css" href="common/css/common.css">
</head>
<body>
<div id="wrap">
	<div id="header">
		<div class="logo">
			<h1>
				<a href="index.jsp"><img src="common/images/logo.png" alt="首页" /></a>
			</h1>
		</div>
		<div class="toplink">
			<c:if test="${not empty sessionScope.loginUser }">
				${sessionScope.loginUser} 
			</c:if>
			<a href="http://bbs.zjut.com" target="_blank">论坛</a>
			<a href="http://www.zjut.com" target="_blank">资讯</a>
			<a href="http://u.zjut.com" target="_blank">家园</a>
			<a href="http://down.zjut.com" target="_blank">下载</a>
			<a href="http://shop.zjut.com" target="_blank">商铺</a>
			<a href="http://go.zjut.com" target="_blank">导航</a>
		</div>
	</div>
	<div id="content">
		<div class="main">
			<div class="banner">
				<a href="#nogo"></a>
			</div>
			<div class="news">
				<ul>
					<li>新闻新闻新闻新闻新闻新闻新闻新闻新闻 <a href="#nogo" class="show_detail">详细</a></li>
					<li>新闻新闻新闻新闻新闻新闻新闻<a href="#nogo" class="show_detail">详细</a></li>
					<li>新闻新闻新闻新闻新闻新闻新闻新闻新闻新闻新闻新闻新闻<a href="#nogo" class="show_detail">详细</a></li>
				</ul>
			</div>
			<a href="#nogo" class="recently"></a>
			<div class="login">
				<h2>登录</h2>
				<div class="panel">
					<c:if test="${not empty requestScope.tip }">
						<div class="loginTip">提示：<span class="msg">${requestScope.tip}</span></div>
					</c:if>
					<form action="action/user/login" method="post">
						<div class="fi">
							<label class="lb" for="uid">学　号</label>
							<input type="text" tabindex="1" id="uid" name="uid" class="ipt" value="${requestScope.model.uid }" />
						</div>
						<div class="fi">
							<label class="lb" for="password">密　码</label>
							<input type="password" tabindex="2" id="password" name="password" class="ipt" value="${requestScope.model.password }" />
							<a href="action/global/viewForgetpwd" class="forgetpwd" target="_blank" tabindex="5" title="找回密码">忘记密码?</a>
						</div>
						<div class="fi fi-nolb">
							<label for="autologin">
								<input type="checkbox" tabindex="3"  name="autologin" id="autologin" />两周内自动登录
							</label>
						</div>
						<div class="fi fi-nolb">
							<input type="submit" tabindex="4"  class="bt bt-login" onmouseover="this.className+=' bt-login-hover'" onmousedown="this.className+=' bt-login-active'" onmouseout="this.className='bt bt-login'" onmouseup="this.className='bt bt-login'" value="登　录" />
						</div>
					</form>
					<div class="ext">
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		 精弘版权所有  © 2011  <a href="mailto:qingtian16265@gmail.com" title="给晴天发邮件">晴天</a>
	</div>
</div>
<script type="text/javascript">
$(function(){
	if($('#uid').val()==''){
		$('#uid').focus();
	}
	else if($('#uid').val()!='' && $('#password').val()==''){
		$('#password').focus();
	}
	else {
		$('#uid').select();		
	}
});
</script>
</body>
</html>