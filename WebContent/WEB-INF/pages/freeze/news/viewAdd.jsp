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
<div class="crumb">
	<div class="addnews-title">发布新动态</div>
	<div class="backNav"><a href="javascript:void(0);">返回</a></div>
	<div class="clear"></div>
</div>
<div class="box">
<form action="action/news/add" method="post">
<div class="formItem">
	<input type="text" id="title" name="title" class="news-title"/>
</div>
<div class="formItem">
	<textarea id="kindEditor" name="content" cols="100" rows="8" class="editor"></textarea>
</div>
<div class="formItem">
	<input type="submit" value="发布" class="bt bt-addnews" onmouseover="this.className+=' bt-hover'" onmousedown="this.className+=' bt-active'" onmouseout="this.className='bt'" onmouseup="this.className='bt'"/>
</div>
</form>
</div>
<script>
KE.show({
	id : 'kindEditor',
	items: [
	'bold','italic','underline','|',
	'textcolor','bgcolor','|',
	'fontname','fontsize','|',
	'insertorderedlist','insertunorderedlist','|',
	'removeformat','|',
	'link','unlink','|',
	'image','|','fullscreen','source'
	]
});
</script>
</body>
</html>