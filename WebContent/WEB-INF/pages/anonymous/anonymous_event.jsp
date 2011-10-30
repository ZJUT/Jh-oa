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
<c:set var="dataList" value="${requestScope.dataList }"></c:set>
<c:set var="currentYear" value="${requestScope.currentYear }"></c:set>
<c:set var="yearList" value="${requestScope.yearList }"></c:set>

<div id="wrap">
	<%@ include file="/include/header.jsp" %>
	<div class="history-product-user">
		<div class="common-title">
			<h2>我们的历史</h2>
		</div>
		<div class="subNav">
			<span>历史</span>
			<a href="javascript:void(0);">产品</a>
			<a href="javascript:void(0);">团队</a>
		</div>
		<div class="common-panel">
			<div class="inner-content-panel">
				<div class="quick-tool">
					<div class="quick-tool-title">&nbsp;</div>
					<div class="quick-tool-control">
						按时间浏览
						<select name="year" class="year">
							<c:forEach var="year" items="${yearList }">
								<c:choose>
									<c:when test="${currentYear == year }">
										<option value="${year }" selected="selected">${year }</option>
									</c:when>
									<c:otherwise>
										<option value="${year }">${year }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
					<div class="clear"></div>
				</div>
				<c:choose>
					<c:when test="${empty dataList }">
					<div class="no-data">无</div>
					</c:when>
					<c:otherwise>
						<c:set var="cacheMonth" value=""></c:set>
						
						<c:forEach var="eventtogether" items="${dataList }">
						<c:set var="event" value="${eventtogether.event}"></c:set>
						<c:set var="year" value="${eventtogether.year}"></c:set>
						<c:set var="month" value="${eventtogether.month}"></c:set>

						<c:choose>
							<c:when test="${cacheMonth != month}">
								<div class="month-title">${month }月 ${year }</div>
								<c:set var="cacheMonth" value="${month }"></c:set>
								<c:set var="endMonth" value="true"></c:set>
								<div class="short-line">
									<span class="event-time-info"><fmt:formatDate value="${event.modifytime }" type="date" pattern="MM/dd/yyyy"></fmt:formatDate></span> <a href="action/global/anonymous_event_show?id=${event.id}">${event.title }</a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="short-line">
									<span class="event-time-info"><fmt:formatDate value="${event.modifytime }" type="date" pattern="MM/dd/yyyy"></fmt:formatDate></span> <a href="action/global/anonymous_event_show?id=${event.id}">${event.title }</a>
								</div>
							</c:otherwise>
						</c:choose>
						</c:forEach>
						<div class="bottom-year-select-container">
						按时间浏览
						<select name="year" class="year">
							<c:forEach var="year" items="${yearList }">
								<c:choose>
									<c:when test="${currentYear == year }">
										<option value="${year }" selected="selected">${year }</option>
									</c:when>
									<c:otherwise>
										<option value="${year }">${year }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						</div>
						<div class="jh-contact">
							精弘办公室地址：东1、东4、东17架空层、养贤府317 
							<span class="jh-number">联系电话：0571-85290XXX</span>
						</div>
					</c:otherwise>
				</c:choose>
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