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
<title> 团队成员风采  </title>
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
<c:set var="model" value="${requestScope.model }"></c:set>
<c:set var="user" value="${model.user }"></c:set>
<c:set var="department" value="${requestScope.department }"></c:set>

<div id="wrap">
	<%@ include file="/include/header.jsp" %>
	<div class="history-product-user">
		<div class="common-title">
			<h2>我们的管理团队</h2>
		</div>
		<div class="subNav">
			<a href="action/global/anonymous_event">历史</a>
			<a href="action/global/anonymous_product">产品</a>
			<span>团队</span>
		</div>
		<div class="common-panel">
			<div class="inner-content-panel">
				<div class="one-team">
					<h2><span><a href="action/global/anonymous_team">返回团队列表</a></span></h2>
					<div class="one-team-content">
						<div class="headimageContainer">
							<p><img src="${model.headimage}" alt="headimage" class="logoIcon" /></p>
							<p>${department.departmentname }</p>
							</div>					
						<div class="simpleinfoContainer">
							<div class="usernameCt">${user.username }</div>
							<div class="innerSimpleinfo">${user.simpleinfo }</div>
						</div>
						<div class="clear"></div>
					</div>
				</div>
				<div class="jh-contact">
					精弘办公室地址：东1、东4、东17架空层、养贤府317 
					<span class="jh-number">联系电话：0571-85290XXX</span>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp" %>
</div>
<script type="text/javascript">
$(function(){
	$('.year').change(function(){
		goUrl('action/global/anonymous_event?year='+$(this).val());
	});
});
</script>
</body>
</html>