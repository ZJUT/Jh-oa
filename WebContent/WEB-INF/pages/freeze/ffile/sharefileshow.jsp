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
<title> 文件详细详情 </title>
</head>
<body>
<c:set var="loginUser" value="${sessionScope.loginUser }" ></c:set>
<c:set var="username" value="${fn:split(loginUser,'&')[2] }" ></c:set>
<c:set var="uid" value="${fn:split(loginUser,'&')[1] }" ></c:set>
<c:set var="userID" value="${fn:split(loginUser,'&')[0] }" ></c:set>

<c:set var="tip" value="${requestScope.tip}"></c:set>
<c:set var="model" value="${requestScope.model }"></c:set>
<c:set var="user" value="${requestScope.user }"></c:set>

<div class="crumb">
	<div class="showffile-title">${model.showname }</div>
	<div class="backNav"><a href="action/global/shareFile">返回公共文件列表</a></div>
	<div class="clear"></div>
</div>
<div class="box">
	<table class="dataTableDisplay">
		<colgroup>
			<col width="15%" />
			<col width="85%" />
		</colgroup>
		<tr>
			<th>上传人</th>
			<td>${user.username}</td>
		</tr>
		<tr>
			<th>实际文件名</th>
			<td style="position:relative;">
				${model.filename}
				<a href="${model.filename }" class="download" style="position:absolute;right:10px;">立即下载</a>
			</td>
		</tr>
		<tr>
			<th>显示文件名</th>
			<td>${model.showname}</td>
		</tr>
		<tr>
			<th>大小</th>
			<td>${model.size}</td>
		</tr>
		<tr>
			<th>后缀</th>
			<td>${model.suffix}</td>
		</tr>
		<tr>
			<th>发布时间</th>
			<td><fmt:formatDate value="${model.addtime}" type="both"/></td>
		</tr>
	</table>
	<span id="currentFileID" style="display:none;">${model.id }</span>
	<div class="commentContainer">
		<h2>评论列表</h2>
		<div class="commentListContainer">
			正在加载评论列表...
		</div>
		<div class="addCommentContainer">
			<h2>发表评论</h2>
			<textarea id="comment-content"></textarea><span id="comment-tip" style="display:none;"></span>
			<input type="hidden" id="fileID" name="fileID" value="${model.id }" />
			<input type="hidden" id="userID" name="userID" value="${userID }" />
			<input type="button" value="添加评论" class="bt bt-comment" style="float:none;display:block;margin:5px;" />
			
		</div>
	</div>
</div>
<script type="text/javascript">

function clickForDeleteFunction(){
	$('#dialog').jqmShow();
	var self=$(this);
	$('.confirmDelete').unbind('click');
	$('.confirmDelete').bind('click',function(){
		var action=self.attr('id').split(',')[0];
		var id=self.attr('id').split(',')[1];
		$.ajax({
			url : action,
			type : 'get',
			data : {
				id : id
			},
			success:function(res){
				$('#dialog').jqmHide();
				var obj=eval('('+res+')');
				if(obj.error==1){
					alert(obj+','+obj.message);
					$('#comment-tip').html(obj.message);
					$('#comment-tip').show();
				}
				else{
					$('#comment_'+id).remove();
					
					$('#comment-tip').html(obj.message);
					$('#comment-tip').show();
				}
				
				if($('.commentListContainer').children().length==0){
					$('.commentListContainer').html('<div class="no-data">无任何数据</div>');
				}
			},
			error:function(){
				alert('删除评论发生错误，请重试!');
			}
		});
	});
	
}

$(function(){
	$.ajax({
		url : 'action/global/ajaxCommentFilter',
		type : 'get',
		data : {
			fileID : $('#currentFileID').html()
		},
		success : function(html){
			$('.commentListContainer').html(html);
			
			$('.commentItem').hover(function(){
				$(this).addClass('commentOn');	
			},function(){
				$(this).removeClass('commentOn');	
			});
			
			$('.deleteComment').unbind('click');
			$('.deleteComment').bind('click',clickForDeleteFunction);
		},
		error : function(){
			alert('评论载入异常,请稍候再试!');
		}
	});
	//发表评论
	$('.bt-comment').click(function(){
		var content=$('#comment-content').val();
		if(content.trim()==''){
			$('#comment-tip').html('请先输入评论内容');
			$('#comment-tip').show();
			$('#comment-content').val('');
			$('#comment-content').focus();
		}
		else{
			$.ajax({
				url : 'action/global/ajaxCommentAdd',
				type : 'post',
				data : {
					content : $('#comment-content').val().trim(),
					userID : $('#userID').val(),
					fileID : $('#fileID').val()
				},
				success : function(res){
					var obj=eval('('+res+')');
					if(obj.error==1){
						$('#comment-tip').html(obj.message);
					}
					else{
						var comment=obj.comment;
						var id=comment.split(',')[0];
						var content=comment.split(',')[1];
						var username=comment.split(',')[2];
						//var fileID=comment.split(',')[3];
						var addtime=comment.split(',')[4];
						$('#comment-tip').html(obj.message);
						$('#comment-content').val('');
						$('#comment-content').focus();
						var onecomment='';
						onecomment+='<div id="comment_'+id+'" class="commentItem">';
						onecomment+='<div class="commentStatus">';
						onecomment+='<span class="cs_username">'
						onecomment+='<b>'+username+'</b> 于 '+addtime+' 评论';
						onecomment+='</span>';
						onecomment+='<span class="cs_opt">';
						onecomment+='<a href="javascript:void(0);" id="action/global/ajaxCommentDelete,'+id+'" class="deleteComment jqModal">删除</a>';
						onecomment+='</span>';
						onecomment+='<span class="clear"></span>';
						onecomment+='</div>';
						onecomment+='<div class="commentContent">';
						onecomment+=content;
						onecomment+='</div>';
						onecomment+='</div>';
						
						if($('.no-data').length){
							$('.commentListContainer').html(onecomment);
						}
						else{
							$('.commentListContainer').html($('.commentListContainer').html()+onecomment);
						}
						
						$('.deleteComment').unbind('click');
						$('.deleteComment').bind('click',clickForDeleteFunction);
						
						$('.commentItem').hover(function(){
							$(this).addClass('commentOn');	
						},function(){
							$(this).removeClass('commentOn');	
						});
					}
				},
				error : function(){
					alert('发表评论发生错误，请重试!');
				}
			});
			
		}
	});
	
});
</script>
</body>
</html>