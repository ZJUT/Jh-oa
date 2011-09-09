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
<title> 发布新动态 </title>
</head>
<body>
<c:set var="loginUser" value="${sessionScope.loginUser }" ></c:set>
<c:set var="username" value="${fn:split(loginUser,'&')[2] }" ></c:set>
<c:set var="uid" value="${fn:split(loginUser,'&')[1] }" ></c:set>
<c:set var="userID" value="${fn:split(loginUser,'&')[0] }" ></c:set>

<c:set var="tip" value="${requestScope.tip}"></c:set>
<c:set var="model" value="${requestScope.model }"></c:set>

<div class="crumb">
	<div class="addnews-title">发布新动态</div>
	<div class="backNav"><a href="javascript:void(0);">返回</a></div>
	<div class="clear"></div>
</div>
<div class="box">
<c:if test="${ not empty tip}">
<div class="optTip">提示：<span class="msg">${tip}</span></div>
</c:if>
<form name="addnewsForm" id="addnewsForm" action="action/news/add" method="post">
<div class="formItem">
	<input type="text" id="title" name="title" class="news-title" value="${model.title }"/>
</div>
<div class="formItem">
	<textarea id="kindEditor" name="content" cols="100" rows="8" class="editor">${model.content }</textarea>
</div>
<input type="hidden" name="username" value="${username }" />
<input type="hidden" name="stext" id="stext" value="${model.stext }"/>
<div class="formItem">
	<input type="submit" value="发布" class="bt bt-addnews" />
</div>
</form>
</div>
<script type="text/javascript">
KE.init({
	id : 'kindEditor',
	items: [
	'bold','italic','underline','|',
	'textcolor','bgcolor','|',
	'fontname','fontsize','|',
	'insertorderedlist','insertunorderedlist','|',
	'removeformat','|',
	'link','unlink','|',
	'image','|','fullscreen','source'
	],
	afterCreate : function(){
		KE.html('kindEditor',$('#kindEditor').val());
	}
});
$(function(){
	KE.create('kindEditor');
	//设置纯文本到隐藏域，随表单一起提交
	$('#addnewsForm').submit(function(){
		$('#stext').val(KE.text('kindEditor'));
	});
	if($('#title').val()==''){
		$('#title').focus();
	}
	else if($('#title').val()!='' && KE.isEmpty('kindEditor')){
		KE.focus('kindEditor');
	}
	else{
		$('#title').select();
	}
});

</script>
</body>
</html>