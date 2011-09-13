<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<script language="javascript" type="text/javascript" src="common/js/jquery.cookie.js"></script>
<script language="javascript" type="text/javascript" src="common/js/common.js"></script>
<link rel="stylesheet" type="text/css" href="common/css/common.css">
</head>
<body>
<c:set var="top6newsList" value="${requestScope.dataList }"></c:set>
<c:set var="autologin" value="${requestScope.autologin }"></c:set>

<div id="wrap">
	<%@ include file="/include/header.jsp" %>
	<div id="content">
		<div class="main">
			<div class="banner">
				<a href="#nogo"></a>
			</div>
			<div class="news">
				<ul>
					<c:choose>
						<c:when test="${fn:length(top6newsList)==0 }">
							无任何动态
						</c:when>
						<c:otherwise>
						<c:forEach var="news" items="${top6newsList }">
							<li>
								<span title="${news.title }">
								<c:choose>
									<c:when test="${fn:length(news.title) > 20 }">
										${fn:substring(news.title,0,20) } ...
									</c:when>
									<c:otherwise>
										${news.title }
									</c:otherwise>
								</c:choose>
								[<fmt:formatDate value="${news.addtime }" type="both" />]
								</span>
								<a href="action/global/anonymous_news_show?id=${news.id }" class="show_detail">查看详情</a>
							</li>
						</c:forEach>	
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<a href="#nogo" class="recently"></a>
			<div class="login">
				<h2>登录</h2>
				<div class="panel">
					<c:if test="${not empty requestScope.tip }">
						<div class="loginTip">提示：<span class="msg">${requestScope.tip}</span></div>
					</c:if>
					<form action="action/global/anonymous_login" method="post">
						<div class="fi">
							<label class="lb" for="uid">学　号</label>
							<input type="text" tabindex="1" id="uid" name="uid" class="ipt" value="${requestScope.model.uid }"  />
						</div>
						<div class="fi">
							<label class="lb" for="password">密　码</label>
							<input type="password" tabindex="2" id="password" name="password" class="ipt" value="${requestScope.model.password }" />
							<a href="action/global/viewForgetpwd" class="forgetpwd" target="_blank" tabindex="5" title="找回密码">忘记密码?</a>
						</div>
						<div class="fi fi-nolb">
							<label for="autologin">
								<c:choose>
									<c:when test="${autologin=='true' }">
										<input type="checkbox" tabindex="3"  name="autologin" id="autologin" value="true" checked="checked"/>两周内记住用户名
									</c:when>
									<c:otherwise>
										<input type="checkbox" tabindex="3"  name="autologin" id="autologin" value="true"/>两周内记住用户名
									</c:otherwise>
								</c:choose>
							</label>
						</div>
						<div class="fi fi-nolb">
							<input type="submit" tabindex="4"  class="bt bt-login" value="登　录" />
						</div>
					</form>
					<div class="ext">
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp" %>
</div>
<script type="text/javascript">
$(function(){
	//登录数据读取与设置
	var uid = $.cookie(Constant.LOGIN_UID_KEY) || '';

	if(uid!='')
		$('#uid').val(uid);

	console.log('Load auto_login: uid['+uid+']');
	
	//焦点
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