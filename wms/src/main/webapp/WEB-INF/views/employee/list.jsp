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
<script type="text/javascript" src="/js/employee/bathDelete.js"></script>
<title>员工</title>
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
	<s:form id="searchForm" namespace="/" action="employee_list" theme="simple">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							姓名/邮箱
							<s:textfield name="bq.keyword" cssClass="ui_input_txt02"/>
							所属部门
							<s:select list="#depts" listValue="name" listKey="id" name="bq.dept_id" headerKey="-1" headerValue="请选择" cssClass="ui_select01"/>
						</div>
						<div id="box_bottom">
							<input type="button" value="批量删除" class="ui_input_btn01 btn_bathDelete" data-url="/employee_bathDelete"/>
							<input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="http://localhost:8080/employee_edit"/> 
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
							<th>用户名</th>
							<th>EMAIL</th>
							<th>年龄</th>
							<th>所属部门</th>
							<th>角色</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageQuery.queryResult">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" data-id="<s:property value="id"/>"/></td>
									<td><s:property value="id"/></td>
									<td><s:property value="name"/></td>
									<td><s:property value="email"/></td>
									<td><s:property value="age"/></td>
									<td><s:property value="dept.name"/></td>
									<td><s:property value="roleName"/></td>
									<td>
										<s:a namespace="/" action="employee_edit"><s:param name="employee.id" value="id"/>编辑</s:a>
										<%-- <s:a namespace="/" action="employee_delete"><s:param name="employee.id" value="id"/>刪除</s:a> --%>
										<s:url namespace="/" action="employee_delete" var="urldata">
											<s:param name="employee.id" value="id"></s:param>
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
