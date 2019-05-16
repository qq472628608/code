<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<link href="/js/jquery/plugins/fancybox/jquery.fancybox.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/jquery/plugins/fancybox/jquery.fancybox.js"></script>
<script type="text/javascript" src="/js/jquery/plugins/artDialog/jquery.artDialog.source.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>

<title>即时库存报表</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<s:form id="searchForm" namespace="/" action="productStock_list" theme="simple">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							名称
							<s:textfield name="bq.keyword" cssClass="ui_input_txt02"/>
							品牌
							<s:select list="#brands" listValue="name" listKey="id" name="bq.brandId" headerKey="-1" headerValue="请选择" cssClass="ui_select01"/>&nbsp;
							仓库
							<s:select list="#depots" listValue="name" listKey="id" name="bq.depotId" headerKey="-1" headerValue="请选择" cssClass="ui_select01"/>&nbsp;
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th>货品</th>
							<th>仓库</th>
							<th>品牌</th>
							<th>库存价格</th>
							<th>库存数量</th>
							<th>库存总金额</th>
						</tr>
						<tbody>
							<s:iterator value="#pageQuery.queryResult">
								<tr>
									<td><s:property value="product.name"/></td>
									<td><s:property value="depot.name"/></td>
									<td><s:property value="product.brand.name"/></td>
									<td><s:property value="price"/></td>
									<td><s:property value="storeNumber"/></td>
									<td><s:property value="amount"/></td>
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
