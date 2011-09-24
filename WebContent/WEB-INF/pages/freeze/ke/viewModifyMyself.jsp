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
<title> 编辑个人课表 </title>
</head>
<body>
<c:set var="tip" value="${requestScope.tip}"></c:set>
<c:set var="model" value="${requestScope.model }"></c:set>

<div class="crumb">
	<div class="addke-title">编辑个人课表</div>
	<div class="backNav"><a href="action/global/manager">返回管理首页</a></div>
	<div class="clear"></div>
</div>
<div class="box">
	<c:if test="${ not empty tip}">
	<div class="optTip">提示：<span class="msg">${tip}</span></div>
	</c:if>
	<c:choose>
		<c:when test="${ empty model.kevalue }">
		<div class="data-operator-bar top-border">
			您尚未添加任何课表, 请尽快完善课表! <a href="action/ke/viewAddMyself">立即添加课表</a>
			<div class="clear"></div>
		</div>
		</c:when>
		<c:otherwise>
		<form name="modifykeForm" id="modifykeForm" action="action/ke/modifyMyself" method="post">
		<table class="dataTableDisplay">
			<colgroup>
				<col width="4%" />
				<col width="12%" />
				<col width="12%" />
				<col width="12%" />
				<col width="12%" />
				<col width="12%" />
				<col width="4%" />
				<col width="10%" />
				<col width="10%" />
			</colgroup>
			<tr>
				<th></th>
				<th class="center">周一</th>
				<th class="center">周二</th>
				<th class="center">周三</th>
				<th class="center">周四</th>
				<th class="center">周五</th>
				<th></th>
				<th class="center">周六</th>
				<th class="center">周日</th>
			</tr>
			<c:set var="kevalue" value="${model.kevalue }"></c:set>
			<c:set var="index" value="-1"></c:set>
			<c:forEach var="i" begin="1" end="11" step="1">
				<tr>
					<c:forEach var="j" begin="0" end="7" step="1">
					<c:set var="current_ke" value="${fn:substring(kevalue, index, index+1) }"></c:set>
					<c:choose>
						<c:when test="${j == 0 }">
						<th><input type="checkbox" id="${i }" class="selectOneLine" /></th>
						</c:when>
						<c:when test="${j == 5 }">
							<c:choose>
								<c:when test="${current_ke == '0' }">
								<td class="center"><label class="kelabel"><input type="checkbox" name="kevalue_${i}_${j}" id="kevalue_${i}_${j}" class="ke_item keline_${i }" value="1" /></label></td>
								</c:when>
								<c:otherwise>
								<td class="center itemselect"><label class="kelabel"><input type="checkbox" name="kevalue_${i}_${j}" id="kevalue_${i}_${j}" class="ke_item keline_${i }" value="1" checked="checked" /></label></td>
								</c:otherwise>
							</c:choose>
						<th></th>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${current_ke == '0' }">
								<td class="center"><label class="kelabel"><input type="checkbox" name="kevalue_${i}_${j}" id="kevalue_${i}_${j}" class="ke_item keline_${i }" value="1" /></label></td>
								</c:when>
								<c:otherwise>
								<td class="center itemselect"><label class="kelabel"><input type="checkbox" name="kevalue_${i}_${j}" id="kevalue_${i}_${j}" class="ke_item keline_${i }" value="1" checked="checked"/></label></td>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
					<c:set var="index" value="${index+1 }"></c:set>
					</c:forEach>
					<c:set var="index" value="${index-1 }"></c:set>
				</tr>	
				<c:if test="${i==4 || i==8 }">
				<tr>
					<th></th>
					
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>

					<th></th>
					<th></th>
				</tr>
				</c:if>
			</c:forEach>
		</table>
		<input type="hidden" name="id" value="${model.id }" />
		<div class="data-operator-bar top-border">
			<input type="submit" value="编辑" class="bt bt-addke" />
			<div class="clear"></div>
		</div>
		</form>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>