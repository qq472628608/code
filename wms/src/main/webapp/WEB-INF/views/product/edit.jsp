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
<s:form name="editForm" action="product_saveOrUpdate" namespace="/" theme="simple" id="editForm" method="post" enctype="multipart/form-data">
	<s:hidden name="product.id"/>
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">商品编辑</span>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">商品名称</td>
					<td class="ui_text_lt">
						<s:textfield name="product.name" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">商品代号</td>
					<td class="ui_text_lt">
						<s:textfield name="product.sn" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
				<tr>
					<td class="ui_text_rt" width="140">进价</td>
					<td class="ui_text_lt">
						<s:textfield name="product.costPrice" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
				<tr>
					<td class="ui_text_rt" width="140">售价</td>
					<td class="ui_text_lt">
						<s:textfield name="product.salePrice" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
				<tr>
					<td class="ui_text_rt" width="140">品牌</td>
					<td class="ui_text_lt">
						<s:select list="#brands" name="product.brand.id" listKey="id" listValue="name" cssClass="ui_select01"/>
					</td>
				</tr>
				<tr>
				<tr>
					<td class="ui_text_rt" width="140">图片</td>
					<td class="ui_text_lt">
						<s:file name="picture" cssClass="ui_file"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">介绍</td>
					<td class="ui_text_lt">
						<s:textarea name="product.intro" cssClass="ui_input_txtarea"/>
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