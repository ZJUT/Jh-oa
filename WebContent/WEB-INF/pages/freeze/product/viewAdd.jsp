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
<title> 发布新产品 </title>
</head>
<body>
<c:set var="loginUser" value="${sessionScope.loginUser }" ></c:set>
<c:set var="username" value="${fn:split(loginUser,'&')[2] }" ></c:set>
<c:set var="uid" value="${fn:split(loginUser,'&')[1] }" ></c:set>
<c:set var="userID" value="${fn:split(loginUser,'&')[0] }" ></c:set>

<c:set var="tip" value="${requestScope.tip}"></c:set>
<c:set var="model" value="${requestScope.model }"></c:set>

<div class="crumb">
	<div class="addproduct-title">发布新产品</div>
	<div class="backNav"><a href="action/product/list">返回产品列表</a></div>
	<div class="clear"></div>
</div>
<div class="box">
<c:if test="${ not empty tip}">
<div class="optTip">提示：<span class="msg">${tip}</span></div>
</c:if>
<div style="position:relative;">
	<div class="uploadContainer">
	<iframe name="uploadIframe" id="uploadIframe" style="display:none;"></iframe>
	<form name="uploadFileForm" id="uploadFileForm" action="action/product/uploadFile" method="post" enctype="multipart/form-data" target="uploadIframe">
	<div class="formItem clear">
		产品LOGO<input type="file" name="selectfile" id="selectfile" />
		<span id="wait">正在上传文件...</span>
	</div>
	</form>
	</div>

	<form name="addproductForm" id="addproductForm" action="action/product/add" method="post">
	<div class="formItem">
		<label for="name" class="common-label">产品名称</label>
		<input type="text" id="name" name="name" class="name" value="${model.name }"/>
	</div>
	<c:choose>
		<c:when test="${not empty model.logo }">
			<div class="formItem alreadyUpload">
				<img src="${model.logo }" alt="logo" class="logoIcon" />
				<span class="uploadTitle">已上传的产品LOGO：</span> <span class="flist">${model.logo }</span><span class="cleanit"><a href="javascript:void(0);" title="丢弃此文件" class="lostit"><span>丢弃</span></a></span>
			</div>
		</c:when>
		<c:otherwise>
			<div class="formItem alreadyUpload" style="display:none;">
				<img src="common/images/no-icon.png" alt="logo" class="logoIcon" />
				<span class="uploadTitle">已上传的产品LOGO：</span> <span class="flist">${model.logo }</span><span class="cleanit"><a href="javascript:void(0);" title="丢弃此文件" class="lostit"><span>丢弃</span></a></span>
			</div>	
		</c:otherwise>
	</c:choose>
	<div class="formItem">
		<textarea id="kindEditor" name="introduce"  class="editor" >${model.introduce}</textarea>
	</div>
	<div class="formItem">
		<label for="link" class="common-label">产品链接地址</label>
		<input type="text" id="link" name="link" class="link" value="${model.link}"/>
	</div>
	<input type="hidden" id="logo" name="logo" value="${model.logo }" />
	<div class="formItem">
		<input type="submit" value="发布" class="bt bt-addproduct" />
	</div>
	</form>
	</div>
</div>
<script type="text/javascript">
KE.init({
	id : 'kindEditor',
	resizeMode: 1,
	items: [
		'fontname', 'fontsize', '|',
		'textcolor', 'bgcolor','|',
		'bold', 'italic', 'underline','removeformat', '|',
		'justifyleft', 'justifycenter', 'justifyright', '|',
		'insertorderedlist','insertunorderedlist', '|',	
		'fullscreen','source'
	],
	afterCreate : function(){
		KE.html('kindEditor',$('#kindEditor').val());
	}
	
});
$(function(){
	KE.create('kindEditor');
	
	$('#selectfile').change(function(){
		$('#uploadFileForm').submit();
		$('#wait').html('正在上传文件...');
		$('#wait').show();
	
		var timer=null;
		var count=1;
		timer=setTimeout(function(){
			var result=$('#uploadIframe').contents().find('body').html();
			if(result!=''){
				console.log(count+',result: '+result);
				
				var obj=window.eval('('+result+')');
				
				if(obj.error==1){
					$('#wait').html(obj.message);
				}
				else{
					$('#logo').val(obj.url);
					$('#wait').html('上传成功!');

					//更新列表
					$('.flist').html(obj.url);
					$('.logoIcon').attr('src',obj.url);
					
					$('.alreadyUpload').show();
					$('.cleanit').show();
				}
				clearTimeout(timer);
			}
			else{
				console.log(count+',result is empty.');	
			}
			count+=1;
		}, 300);
		
	});
	
	//丢弃文件点击事件
	$('.lostit').click(function(){
		$('.cleanit').hide();
		$('.flist').html('暂未上传任何文件，请上传');
		$('.logoIcon').attr('src','common/images/no-icon.png');
		$('#logo').val('');
	});
	
	if($('#name').val()==''){
		$('#name').focus();
	}
	else if($('#name').val()!='' && $('#logo').val()==''){
		$('#logo').focus();
	}
	else if($('#name').val()!='' && $('#logo').val()!='' && KE.isEmpty('kindEditor') ){
		KE.focus('kindEditor');
	}
	else if($('#name').val()!='' && $('#logo').val()!='' && !KE.isEmpty('kindEditor') &&  $('#link').val()==''){
		$('#link').focus();
	}
});

</script>
</body>
</html>