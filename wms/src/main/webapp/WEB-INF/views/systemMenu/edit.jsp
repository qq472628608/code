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
</head>
<body>
<s:form name="editForm" action="systemMenu_saveOrUpdate" namespace="/" id="editForm" theme="simple">
	<s:hidden name="systemMenu.id"/>
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">菜单编辑</span>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">父级菜单</td>
					<td class="ui_text_lt">
						<s:textfield value="%{parentMenu}" cssClass="ui_input_txt02" disabled="true"/>
						<s:hidden name="bq.parentId"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">菜单名称</td>
					<td class="ui_text_lt">
						<s:textfield name="systemMenu.name" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">URL</td>
					<td class="ui_text_lt">
						<s:textfield name="systemMenu.url" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">菜单编码</td>
					<td class="ui_text_lt">
						<s:textfield name="systemMenu.sn" cssClass="ui_input_txt02"/>
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