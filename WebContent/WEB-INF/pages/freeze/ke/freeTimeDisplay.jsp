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
<title> 空课查询结果 </title>
</head>
<body>
<c:set var="fktList" value="${requestScope.fktList }"></c:set>
<c:set var="findFreeTime_kevalue" value="${requestScope.kevalue }"></c:set>
<c:set var="currentDepartmentID" value="${requestScope.departmentID }"></c:set>

<div class="crumb">
	<div class="addke-title">空课查询结果</div>
	<div class="backNav"><a href="action/ke/filter">返回课表列表</a></div>
	<div class="clear"></div>
</div>
<div class="box">
<div class="actionTip">温馨提醒：<span class="tip-words">当前查询条件将以黄色突出显示</span></div>
	<c:if test="${ not empty tip}">
		<div class="optTip">提示：<span class="msg">${tip}</span></div>
	</c:if>
	<c:choose>
		<c:when test="${empty fktList }">
			<div class="no-data">无任何数据</div>
		</c:when>
		<c:otherwise>
			<c:choose>
				<%-- 全精弘范围数据展示 --%>
				<c:when test="${currentDepartmentID=='0' }">
					<%-- 当前查询条件显示区域 --%>
					<div class="toggleArea">
						<div class="closeConditionDisplay">
							<a href="javascript:void(0);" class="closeTable">收起当前查询条件</a>
						</div>
						<table class="dataTableDisplay allJHUsers">
							<colgroup>
								<col width="13%" />
								<col width="11%" />
								<col width="11%" />
								<col width="11%" />
								<col width="11%" />
								<col width="11%" />
								<col width="10%" />
								<col width="11%" />
								<col width="11%" />
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
							<%-- 此处高亮显示查询时间点 --%>
							<c:set var="kevalue" value="${findFreeTime_kevalue }"></c:set>
							<c:set var="index" value="-1"></c:set>
							<c:forEach var="i" begin="1" end="11" step="1">
								<tr>
									<c:forEach var="j" begin="0" end="7" step="1">
									<c:set var="current_ke" value="${fn:substring(kevalue, index, index+1) }"></c:set>
									<c:choose>
										<c:when test="${j == 0 }">
										<th class="center">第${i }节</th>
										</c:when>
										<c:when test="${j == 5 }">
											<c:choose>
												<c:when test="${current_ke == '0' }">
												<td class="center">-</td>
												</c:when>
												<c:otherwise>
												<td class="center itemOutShow">有空</td>
												</c:otherwise>
											</c:choose>
										<th></th>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${current_ke == '0' }">
												<td class="center">-</td>
												</c:when>
												<c:otherwise>
												<td class="center itemOutShow">有空</td>
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
						<div class="closeConditionDisplay ccdBottom">
							<a href="javascript:void(0);" class="closeTable">收起当前查询条件</a>
						</div>
					</div>
					<%-- 按部门分区域展示全精弘的成员当前条件下的空闲时间情况　 --%>
					<c:forEach var="freeketogether" items="${fktList }">
						<c:set var="ke" value="${freeketogether.ke }"></c:set>
						<c:set var="usertogether" value="${freeketogether.usertogether }"></c:set>
						<c:set var="user" value="${usertogether.user }"></c:set>
						<c:set var="academy" value="${usertogether.academy }"></c:set>
						<c:set var="department" value="${usertogether.department }"></c:set>
						<c:set var="job" value="${usertogether.job }"></c:set>
						<c:set var="total" value="${freeketogether.total }"></c:set>
						<div class="toggleBar">
							<div class="tbTitle"><b>${department.departmentname }</b>共[<span>${total }</span>]人有空</div>
							<div class="tbOpt"><a href="javascript" class="toggleDetail focus">空闲情况</a></div>
							<div class="clear"></div>
						</div>
						<div class="freeTimeUserDisplay">
							<div class="ftudInner">
								<div class="one-person-line">${user.username }|${user.uid }<a href="action/ke/show?id=${ke.id }">查看课表情况</a></div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<%-- 单部门数据展示 --%>
				<c:otherwise>
					<c:forEach var="freeketogether" items="${fktList }">
						<c:set var="ke" value="${freeketogether.ke }"></c:set>
						<c:set var="usertogether" value="${freeketogether.usertogether }"></c:set>
						<c:set var="user" value="${usertogether.user }"></c:set>
						<c:set var="academy" value="${usertogether.academy }"></c:set>
						<c:set var="department" value="${usertogether.department }"></c:set>
						<c:set var="job" value="${usertogether.job }"></c:set>
						<c:set var="total" value="${freeketogether.total }"></c:set>
						<div class="toggleBar">
							<div class="tbTitle"><b>${department.departmentname }</b>共[<span>${total }</span>]人有空</div>
							<div class="tbOpt"><a href="javascript" class="toggleDetail focus">空闲情况</a></div>
							<div class="clear"></div>
						</div>
						<div class="freeTimeUserDisplay">
							<div class="ftudInner">
								<div class="one-person-line">${user.username }|${user.uid }<a href="action/ke/show?id=${ke.id }">查看课表情况</a></div>
							</div>
						</div>
					</c:forEach>
					<%-- 当前查询条件显示区域 --%>
					<div class="toggleArea">
						<table class="dataTableDisplay">
							<colgroup>
								<col width="13%" />
								<col width="11%" />
								<col width="11%" />
								<col width="11%" />
								<col width="11%" />
								<col width="11%" />
								<col width="10%" />
								<col width="11%" />
								<col width="11%" />
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
							<%-- 此处按照空课查询选择时间来显示 --%>
							<c:set var="kevalue" value="${findFreeTime_kevalue }"></c:set>
							<c:set var="index" value="-1"></c:set>
							<c:forEach var="i" begin="1" end="11" step="1">
								<tr>
									<c:forEach var="j" begin="0" end="7" step="1">
									<c:set var="current_ke" value="${fn:substring(kevalue, index, index+1) }"></c:set>
									<c:choose>
										<c:when test="${j == 0 }">
										<th class="center">第${i }节</th>
										</c:when>
										<c:when test="${j == 5 }">
											<c:choose>
												<c:when test="${current_ke == '0' }">
												<td class="center">-</td>
												</c:when>
												<c:otherwise>
												<td class="center itemOutShow">有空</td>
												</c:otherwise>
											</c:choose>
										<th></th>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${current_ke == '0' }">
												<td class="center">-</td>
												</c:when>
												<c:otherwise>
												<td class="center itemOutShow">有空</td>
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
					</div>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</div>
<script type="text/javascript">
	$(function(){
		$('.closeTable').toggle(function(){
			$('.allJHUsers').hide();
		},function(){
			$('.allJHUsers').show();
		});
	});
</script>
</body>
</html>