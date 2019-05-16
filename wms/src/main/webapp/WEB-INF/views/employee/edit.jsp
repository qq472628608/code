<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title>信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/jquery/plugins/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript" src="/js/employee/validate.js"></script>
<script type="text/javascript" src="/js/system/move.js"></script>
</head>
<body>
<s:form name="editForm" action="employee_saveOrUpdate" namespace="/" id="editForm" theme="simple">
	<s:hidden name="employee.id"/>
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">用户编辑</span>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">用户名</td>
					<td class="ui_text_lt">
						<s:textfield name="employee.name" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<s:if test="employee.id == null">
				<tr>
					<td class="ui_text_rt" width="140">密码</td>
					<td class="ui_text_lt">
						<s:textfield name="employee.password" id="password" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">验证密码</td>
					<td class="ui_text_lt">
						<s:textfield name="validate_password" type="password" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				</s:if>
				<tr>
					<td class="ui_text_rt" width="140">Email</td>
					<td class="ui_text_lt">
						<s:textfield name="employee.email" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">年龄</td>
					<td class="ui_text_lt">
						<s:textfield name="employee.age" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">所属部门</td>
					<td class="ui_text_lt">
						<s:select name="employee.dept.id" list="#depts" listKey="id" listValue="name" cssClass="ui_select01"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">超级管理员</td>
					<td class="ui_text_lt">
						<input type="checkbox" name="" class="ui_checkbox01"></input>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">角色</td>
					<td class="ui_text_lt">
						<table>
							<tr>
								<td>
									<s:select list="#roles" listValue="name" listKey="id" multiple="true" cssClass="ui_multiselect01 all"/>
								</td>
								<td align="center">
									<input type="button" id="select" value="-->" class="left2right"/><br/>
									<input type="button" id="selectAll" value="==>" class="left2right"/><br/>
									<input type="button" id="deselect" value="<--" class="left2right"/><br/>
									<input type="button" id="deselectAll" value="<==" class="left2right"/>
								</td>
								<td>
									<s:select list="employee.roles" name="employee.roles.id" listKey="id" listValue="name" multiple="true" cssClass="ui_multiselect01 selected"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</s:form>
</body>
</html>