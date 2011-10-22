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
<title> 发布新文件 </title>
</head>
<body>
<c:set var="loginUser" value="${sessionScope.loginUser }" ></c:set>
<c:set var="username" value="${fn:split(loginUser,'&')[2] }" ></c:set>
<c:set var="uid" value="${fn:split(loginUser,'&')[1] }" ></c:set>
<c:set var="userID" value="${fn:split(loginUser,'&')[0] }" ></c:set>

<c:set var="tip" value="${requestScope.tip}"></c:set>
<c:set var="model" value="${requestScope.model }"></c:set>

<div class="crumb">
	<div class="addffile-title">发布新文件</div>
	<div class="backNav"><a href="action/ffile/filter">返回文件列表</a></div>
	<div class="clear"></div>
</div>
<div class="box">
<c:if test="${ not empty tip}">
<div class="optTip">提示：<span class="msg">${tip}</span></div>
</c:if>
<div style="position:relative;">
	<div class="uploadContainer">
	<iframe name="uploadIframe" id="uploadIframe" style="display:none;"></iframe>
	<form name="uploadFileForm" id="uploadFileForm" action="action/ffile/uploadFile" method="post" enctype="multipart/form-data" target="uploadIframe">
	<div class="formItem clear">
		<input type="file" name="selectfile" id="selectfile" />
		<span id="wait">正在上传文件...</span>
	</div>
	</form>
	</div>

	<form name="addffileForm" id="addffileForm" action="action/ffile/add" method="post">
	<div class="formItem">
		<label for="showname" class="common-label">文件名</label>
		<input type="text" id="showname" name="showname" class="showname" value="${model.showname }"/>
	</div>
	<input type="hidden" id="filename" name="filename" value="${model.filename }" />
	<input type="hidden" id="size" name="size" value="${model.size }" />
	<input type="hidden" id="suffix" name="suffix" value="${model.suffix }" />
	<input type="hidden" name="userID" value="${userID }" />
	<c:choose>
	<c:when test="${not empty model.filename }">
	<div class="formItem alreadyUpload">
			<span class="uploadTitle">已上传的文件：</span> <span class="flist">${model.filename }</span><span class="cleanit"><a href="javascript:void(0);" title="丢弃此文件" class="lostit"><span>丢弃</span></a></span>
	</div>
	</c:when>
	<c:otherwise>
	<div class="formItem alreadyUpload" style="display:none;">
			<span class="uploadTitle">已上传的文件：</span> <span class="flist">${model.filename }</span><span class="cleanit"><a href="javascript:void(0);" title="丢弃此文件" class="lostit"><span>丢弃</span></a></span>
	</div>	
	</c:otherwise>
	</c:choose>
	<div class="formItem">
		<input type="submit" value="发布" class="bt bt-addffile" />
	</div>
	</form>
	</div>
</div>
<script type="text/javascript">
$(function(){

	$('#selectfile').change(function(){
		$('#uploadFileForm').submit();
		$('#wait').html('正在上传文件...');
		$('#wait').show();
		var time=null;
		time=setTimeout(function(){
			var result=$('#uploadIframe')[0].contentWindow.window.document.body.innerHTML;
			//alert(result);
			if(result!=''){
				clearTimeout(time);
				var obj=window.eval('('+result+')');
				console.log(result);
				//alert((typeof obj)+','+obj);
				$('#filename').val(obj.url);
				$('#size').val(obj.size);
				$('#suffix').val(obj.suffix);
				$('#wait').html('上传成功!');

				//更新列表
				$('.flist').html(obj.url);
				$('.alreadyUpload').show();
				$('.cleanit').show();
			}
		},100);
	});
	
	//丢弃文件点击事件
	$('.lostit').click(function(){
		$('.cleanit').hide();
		$('.flist').html('暂未上传任何文件，请上传');
		$('#filename').val('');
		$('#size').val('');
		$('#suffix').val('');
	});
	
	if($('#showname').val()==''){
		$('#showname').focus();
	}
	
});

</script>
</body>
</html>