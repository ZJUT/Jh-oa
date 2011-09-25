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
<title> 精弘大事记  </title>
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

<div id="wrap">
	<%@ include file="/include/header.jsp" %>
	<div class="history-product-user">
		<div class="common-title">
			<h2>大事记</h2>
		</div>
		<div class="subNav">
			<span>大事记</span>
			<a href="javascript:void(0);">产品</a>
			<a href="javascript:void(0);">核心团队</a>
		</div>
		<div class="common-panel">
			<div class="inner-content-panel">
				<div class="quick-tool">
					<div class="quick-tool-title">大事记一览: </div>
					<div class="quick-tool-control">
						<select name="year" id="year">
							<option value="2011">2011</option>
						</select>
						年发生的所有大事件
					</div>
					<div class="clear"></div>
				</div>
				<div class="one-month-event">
					<h2>${model.title }</h2>
					<div >${model.content }</div>
					<div class="">${model.modifytime }</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/include/footer.jsp" %>
</div>
</body>
</html>