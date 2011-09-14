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
<c:set var="loginUser" value="${sessionScope.loginUser }" ></c:set>
<c:set var="username" value="${fn:split(loginUser,'&')[2] }" ></c:set>
<c:set var="uid" value="${fn:split(loginUser,'&')[1] }" ></c:set>
<c:set var="userID" value="${fn:split(loginUser,'&')[0] }" ></c:set>

	<div id="header" style="margin-bottom:2px;">
		<div class="logo">
			<h1>
				<a href="index.jsp"><img src="common/images/logo.png" alt="首页" /></a>
			</h1>
		</div>
		<div class="toplink">
			<c:if test="${not empty loginUser }">
				[${username}, <a href="action/global/anonymous_logout">退出</a>]
				<a href="action/global/manager" >管理后台模板</a>
				<a href="javascript:void(0)" class="switch-skin" >切换到宽屏</a>
				|
			</c:if>
			<a href="action/global/anonymous_history">足迹</a>
			<a href="javascript:void(0);" class="moreLink">产品</a>
			<div class="downmenu">
				<a href="http://bbs.zjut.com" target="_blank">论坛</a>
				<a href="http://www.zjut.com" target="_blank">资讯</a>
				<a href="http://u.zjut.com" target="_blank">家园</a>
				<a href="http://down.zjut.com" target="_blank">下载</a>
				<a href="http://shop.zjut.com" target="_blank">商铺</a>
				<a href="http://go.zjut.com" target="_blank">导航</a>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<script type="text/javascript">
		$(function(){
			$('.moreLink').toggle(function(){
				$(this).addClass('more_on');
				$('.downmenu').show();
			},function(){
				$(this).removeClass('more_on');
				$('.downmenu').hide();
			});
		});
	</script>