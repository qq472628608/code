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
	<s:form id="searchForm" namespace="/" action="department_list" theme="simple">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_bottom">
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="http://localhost:8080/department_edit"/> 
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>部门名称</th>
							<th>部门代号</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageQuery.queryResult">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" data-id="<s:property value="id"/>"/></td>
									<td><s:property value="id"/></td>
									<td><s:property value="name"/></td>
									<td><s:property value="sn"/></td>
									<td>
										<s:a namespace="/" action="department_edit"><s:param name="department.id" value="id"/>编辑</s:a>
										<s:url namespace="/" action="department_delete" var="urldata">
											<s:param name="department.id" value="id"></s:param>
										</s:url>
										<a href="" class="btn_delete" data-url='<s:property value="#urldata"/>'>删除</a>
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
