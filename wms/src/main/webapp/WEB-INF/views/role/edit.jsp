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
<script type="text/javascript" src="/js/system/move.js"></script>
</head>
<body>
<s:form name="editForm" action="role_saveOrUpdate" namespace="/" id="editForm" theme="simple">
	<s:hidden name="role.id"/>
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">部门编辑</span>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">角色名称</td>
					<td class="ui_text_lt">
						<s:textfield name="role.name" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">角色代号</td>
					<td class="ui_text_lt">
						<s:textfield name="role.sn" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">角色</td>
					<td class="ui_text_lt">
						<table>
							<tr>
								<td>
									<s:select list="#permissions" listKey="id" listValue="name" multiple="true" cssClass="ui_multiselect01 all"/>
								</td>
								<td align="center">
									<input type="button" id="select" value="-->" class="left2right"/><br/>
									<input type="button" id="selectAll" value="==>" class="left2right"/><br/>
									<input type="button" id="deselect" value="<--" class="left2right"/><br/>
									<input type="button" id="deselectAll" value="<==" class="left2right"/>
								</td>
								<td>
									<s:select name="role.permissions.id" cssClass="ui_multiselect01 selected" multiple="true" list="role.permissions" listKey="id" listValue="name"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>				
				<tr>
					<td class="ui_text_rt" width="140">菜单</td>
					<td class="ui_text_lt">
						<table>
							<tr>
								<td>
									<s:select list="#menus" listKey="id" listValue="name" multiple="true" cssClass="ui_multiselect01 all_menus"/>
								</td>
								<td align="center">
									<input type="button" id="mselect" value="-->" class="left2right"/><br/>
									<input type="button" id="mselectAll" value="==>" class="left2right"/><br/>
									<input type="button" id="mdeselect" value="<--" class="left2right"/><br/>
									<input type="button" id="mdeselectAll" value="<==" class="left2right"/>
								</td>
								<td>
									<s:select name="role.systemMenus.id" cssClass="ui_multiselect01 selected_menus" multiple="true" list="role.systemMenus" listKey="id" listValue="name"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>				
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" value="重置" id="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</s:form>
</body>
</html>