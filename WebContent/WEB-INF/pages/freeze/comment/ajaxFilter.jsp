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

<c:set var="dataList" value="${requestScope.dataList }"></c:set>

	<c:choose>
		<c:when test="${empty dataList }">
			<div class="no-data">无任何数据</div>
		</c:when>
		<c:otherwise>
			<c:forEach var="commenttogether" items="${dataList }">
			<c:set var="user" value="${commenttogether.user }"></c:set>
			<c:set var="file" value="${commenttogether.file }"></c:set>
				<div id="comment_${commenttogether.id }" class="commentItem">
					<div class="commentStatus">
						<span class="cs_username">
							 <b>${user.username }</b> 于 <fmt:formatDate value="${commenttogether.addtime }" type="both" /> 评论
						</span>
						<span class="cs_opt">
							<a href="javascript:void(0);" id="action/global/ajaxCommentDelete,${commenttogether.id }" class="deleteComment jqModal">删除</a>
						</span>
						<span class="clear"></span>
					</div>
					<div class="commentContent">
						${commenttogether.content }
					</div>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
