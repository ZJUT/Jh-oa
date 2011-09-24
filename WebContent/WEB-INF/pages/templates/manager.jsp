<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.zjut.oa.db.*" %>
<%@ page import="com.zjut.oa.mvc.action.*" %>
<%@ page import="com.zjut.oa.mvc.core.*" %>
<%@ page import="com.zjut.oa.mvc.domain.*" %>
<%@ page import="com.zjut.oa.mvc.filter.*" %>
<%@ page import="com.zjut.oa.tool.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>"></base> 
<title> <decorator:title default="模板默认标题" /> </title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="shortcut icon" type="image/ico" href="website.ico">
<script language="javascript" type="text/javascript" src="common/js/jquery.min.js"></script>
<script language="javascript" type="text/javascript" src="common/js/jquery.cookie.js"></script>
<script language="javascript" type="text/javascript" src="common/js/common.js"></script>
<script language="javascript" type="text/javascript" src="common/js/jqModal.js"></script>
<script charset="UTF-8" type="text/javascript" src="kindeditor/kindeditor.js"></script>
<link rel="stylesheet" type="text/css" href="common/css/common.css">
<link rel="stylesheet" type="text/css" href="common/css/jqModal.css">
</head>
<body>
<c:set var="rptList" value="${sessionScope.userPermission }"></c:set>

<div id="page">
	<%@ include file="/include/managerHeader.jsp" %>
	<div id="manager">
		<div class="menuContainer">
			<h2>全部功能菜单</h2>
			<!-- 
			<div class="menuItem">
				<h3><a href="action/menu/list" >菜单</a></h3>
				<h3><a href="action/resource/list" >资源</a></h3>
				<h3><a href="action/operator/list" >操作</a></h3>
				
				<div style="margin-top:10px;">
				<h3><a href="action/user/filter" >用户</a></h3>
				<h3><a href="action/news/filter" >动态</a></h3>
				</div>

				<div style="margin-top:10px;">
				<h3><a href="action/role/list" >角色</a></h3>
				<h3><a href="action/userrole/filter" >用户角色</a></h3>
				<h3><a href="action/permission/filter" >权限</a></h3>
				<h3><a href="action/rolepermission/filter" >角色权限</a></h3>
				<h3><a href="action/permission/filter?id=1" >测试权限</a></h3>
				</div>
			</div>
			 -->
			<div class="menuItem">
				<h3><a href="action/global/manager">返回管理主页</a></h3>
			</div>
			
			<h2>个人中心</h2>
			<div class="menuItem">
				<h3><a href="action/user/showMyself">我的详细资料</a><span class="notblock">[<a href="action/user/viewModifyMyself">修改</a>]</span></h3> 
				<h3><a href="action/ke/showMyself">我的课表</a><span class="notblock">[<a href="action/ke/viewModifyMyself">修改</a>]</span></h3> 
			</div>
			
			<h2>其他功能</h2>
			<%-- 构建菜单 --%>
			<div class="menuItem">
				<c:set var="hasMenu" value="false"></c:set>
				
				<c:set var="menuCache" value=""></c:set>
				<c:forEach var="rolepermissiontogether" items="${rptList }" >
					<c:set var="role" value="${rolepermissiontogether.role }"></c:set>
					<c:set var="permissiontogether" value="${rolepermissiontogether.permissiontogether }"></c:set>
					<c:set var="menu" value="${permissiontogether.menu }"></c:set>
					<c:set var="resource" value="${permissiontogether.resource }"></c:set>
					<c:set var="operator" value="${permissiontogether.operator }"></c:set>
					<c:choose>
						<c:when test="${menuCache !=menu.menuname }">
							<c:set var="menuCache" value="${menu.menuname }"></c:set>
							
							<c:if test="${operator.optvalue =='list' || operator.optvalue == 'filter' }">
								<h3><a href="action/${resource.resourcevalue }/${operator.optvalue}" >${menu.menuname }</a></h3>					
								<c:set var="hasMenu" value="true"></c:set>
							</c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${operator.optvalue =='list' || operator.optvalue == 'filter' }">
								<h3><a href="action/${resource.resourcevalue }/${operator.optvalue}" >${menu.menuname }</a></h3>					
								<c:set var="hasMenu" value="true"></c:set>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${!hasMenu }">
				<div class="nomenu"><h3>无</h3></div>
				</c:if>
			</div>
		</div>
		<div class="bodyContainer">
			<decorator:body />
		</div>
		<div class="clear"></div>
	</div>
	<div id="footer" style="margin-top:0px;">
		 精弘版权所有  © 2011  <a href="mailto:qingtian16265@gmail.com" title="给晴天发邮件">晴天</a>
	</div>
</div>
<!-- 确认删除－弹窗 -->
<div id="dialog" class="common-dialog-container">
	<div class="outter-border" style="margin:0px auto;border-color:#6f6f6f;">
		<div class="inner-container">
			<div class="dialogTitle">
				<b>确认删除</b>
				<a href="javascript:void(0);" class="jqmClose" title="关闭">关闭</a>
			</div>
			<div class="dialogCt">
				<div class="dialog-icon-container">
					<div class="icon-outter">
						<img src="common/images/delete-icon.png" alt="删除"/>
					</div>
				</div>
				<div class="dialog-body-container">
					<p><b>您确认想删除嘛？</b></p>
					<div class="dialog-seperate-line"></div>
					<p>点击“确认删除”后，此数据记录将被永久删除，无法恢复！</p>
				</div>
				<div class="clear"></div>
			</div>
			<div class="dialogBottom">
				<input type="button" class="common-bt confirmDelete" value="确认删除"/>
				<input type="button" class="common-cancel-bt jqmClose" value="取消"/>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		 $('#dialog').jqm();
		 $('.delete').click(function(){
			var self=$(this);
			$('.confirmDelete').unbind('click');
			$('.confirmDelete').bind('click',function(){
				goUrl(self.attr('id'));
			});
			
		 });
	});
</script>
</body>
</html>