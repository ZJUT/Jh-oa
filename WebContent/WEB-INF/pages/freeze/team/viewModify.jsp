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
<title> 编辑管理团队成员 </title>
</head>
<body>
<c:set var="tip" value="${requestScope.tip}"></c:set>
<c:set var="model" value="${requestScope.model }"></c:set>
<c:set var="userList" value="${requestScope.userList }"></c:set>


<div class="crumb">
	<div class="addteam-title">编辑管理团队成员</div>
	<div class="backNav"><a href="action/team/filter">返回用户角色列表</a></div>
	<div class="clear"></div>
</div>
<div class="box">
<c:if test="${ not empty tip}">
<div class="optTip">提示：<span class="msg">${tip}</span></div>
</c:if>

<div style="position:relative;">
	<div class="uploadContainer">
	<iframe name="uploadIframe" id="uploadIframe" style="display:none;"></iframe>
	<form name="uploadFileForm" id="uploadFileForm" action="action/team/uploadFile" method="post" enctype="multipart/form-data" target="uploadIframe">
	<div class="formItem clear">
		成员头像<input type="file" name="selectfile" id="selectfile" />
		<span id="wait">正在上传文件...</span>
	</div>
	</form>
	</div>

	<form name="modifyteamForm" id="modifyteamForm" action="action/team/modify" method="post">
		<div class="formItem">
			<label for="userID" class="common-label">用户</label>
			<select id="userID" name="userID">
				<option value="-1">== 请选择用户 ==</option>
				<c:choose>
					<c:when test="${ empty userList }">
						<option value="0">无</option>
					</c:when>
					<c:otherwise>
						<c:forEach var="user" items="${userList }">
							<c:choose>
								<c:when test="${model.userID== user.id }">
									<option value="${user.id }" selected="selected">${user.uid } -- ${user.username }</option>
								</c:when>
								<c:otherwise>
									<option value="${user.id }">${user.uid } -- ${user.username }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>		
			</select>
		</div>
		<c:choose>
			<c:when test="${not empty model.headimage }">
				<div class="formItem alreadyUpload">
					<img src="${model.headimage }" alt="headimage" class="headimageIcon" />
					<span class="uploadTitle">已上传的成员头像：</span> <span class="flist">${model.headimage }</span><span class="cleanit"><a href="javascript:void(0);" title="丢弃此文件" class="lostit"><span>丢弃</span></a></span>
				</div>
			</c:when>
			<c:otherwise>
				<div class="formItem alreadyUpload" style="display:none;">
					<img src="common/images/no-headimage.png" alt="headimage" class="headimageIcon" />
					<span class="uploadTitle">已上传的成员头像：</span> <span class="flist">${model.headimage }</span><span class="cleanit"><a href="javascript:void(0);" title="丢弃此文件" class="lostit"><span>丢弃</span></a></span>
				</div>	
			</c:otherwise>
		</c:choose>
		<input type="hidden" id="headimage" name="headimage" value="${ model.headimage}" />
		<input type="hidden" name="id" value="${model.id }" />
		<div class="formItem">
			<input type="submit" value="编辑" class="bt bt-addteam" />
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
					$('#headimage').val(obj.url);
					$('#wait').html('上传成功!');

					//更新列表
					$('.flist').html(obj.url);
					$('.headimageIcon').attr('src',obj.url);
					
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
		$('.headimageIcon').attr('src','common/images/no-headimage.png');
		$('#headimage').val('');
	});
	
	
	if($('#userID').val()=='-1'){
		$('#userID').focus();
	}
	else if($('#userID').val()!='-1' && $('#headimage').val()==''){
		$('#headimage').focus();
	}
	else{
		$('#userID').select();
	}
});
</script>
</body>
</html>