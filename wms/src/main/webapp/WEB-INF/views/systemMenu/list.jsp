<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/jquery/plugins/artDialog/jquery.artDialog.source.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>PSS-部门管理</title>
<style>
	.alt td{ background:black !important;}
</style>
<script type="text/javascript">
	$(function(){
		$(".btn_input").click(function(){
			window.location.href = $(this).data("url");
		});
	})
	
</script>
</head>
<body>
	<s:actionerror/>
	<s:form id="searchForm" namespace="/" action="systemMenu_list" theme="simple">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_bottom">
							
							<s:url namespace="/" action="systemMenu_edit" var="parent_url">
								<s:param name="bq.parentId" value="bq.parentId"></s:param>
							</s:url>
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-url='<s:property value="#parent_url"/>'/> 
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
				当前：<s:a namespace="/" action="systemMenu_list">
					根目录  
				</s:a>
				<s:iterator value="#parentList"> >
				 	<s:a namespace="/" action="systemMenu_list">
				 		<s:param name="bq.parentId" value="id"/>
						<s:property value="name"/>
					</s:a>
				</s:iterator>
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>菜单名称</th>
							<th>URL</th>
							<th>菜单编码</th>
							<th>父级菜单</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageQuery.queryResult">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" data-id="<s:property value="id"/>"/></td>
									<td><s:property value="id"/></td>
									<td><s:property value="name"/></td>
									<td><s:property value="url"/></td>
									<td><s:property value="sn"/></td>
									<td><s:property value="parent.name"/></td>
									<td>
										<s:a namespace="/" action="systemMenu_edit">
											<s:param name="systemMenu.id" value="id"/>
											<s:param name="bq.parentId" value="bq.parentId"/>
										编辑</s:a>
										<s:url namespace="/" action="systemMenu_delete" var="urldata">
											<s:param name="systemMenu.id" value="id"></s:param>
										</s:url>
										<a href="" class="btn_delete" data-url='<s:property value="#urldata"/>'>删除</a>
										<s:a namespace="/" action="systemMenu_list"><s:param name="bq.parentId" value="id"/>查看子菜单</s:a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
			<jsp:include page="/WEB-INF/views/comments/page.jsp"></jsp:include>
		</div>
	</s:form>
</body>
</html>
