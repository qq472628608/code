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

<title>商品</title>
<style>
	.alt td{ background:black !important;}
</style>
<script type="text/javascript">
	$(function(){
		$(".btn_checked").click(function(){
			window.opener.getJson($(this).data("json"));
			/* window.opener.document.querySelector("input[tag=name]").value=$(this).data("json").name;
			window.opener.document.querySelector("input[tag=pid]").value=$(this).data("json").id;
			window.opener.document.querySelector("span[tag=brand]").innerHTML=$(this).data("json").brand.name;
			window.opener.document.querySelector("input[tag=costPrice]").value=$(this).data("json").costPrice; */
			window.close();
		});
	});
</script>
</head>
<body>
	<s:actionerror/>
	<s:form id="searchForm" namespace="/" action="product_showProduct" theme="simple">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							名称/介绍
							<s:textfield name="bq.keyword" cssClass="ui_input_txt02"/>
							品牌
							<s:select list="#brands" listValue="name" listKey="id" name="bq.brandId" headerKey="-1" headerValue="请选择" cssClass="ui_select01"/>&nbsp;
							价格
							<s:textfield name="bq.min" cssClass="ui_input_txt02"/>
							-
							<s:textfield name="bq.max" cssClass="ui_input_txt02"/>
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
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>图片</th>
							<th>商品名</th>
							<th>商品代号</th>
							<th>进价</th>
							<th>售价</th>
							<th>品牌</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageQuery.queryResult">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" data-id="<s:property value="id"/>"/></td>
									<td><s:property value="id"/></td>
									<td>
										<a href="<s:property value="imagePath"/>" data-fancybox data-caption="<s:property value="name"/>">
											<img src="<s:property value="smallImagePath"/>" width="50px">
										</a>
									</td>
									<td><s:property value="name"/></td>
									<td><s:property value="sn"/></td>
									<td><s:property value="costPrice"/></td>
									<td><s:property value="salePrice"/></td>
									<td><s:property value="brand.name"/></td>
									<td>
										<input type="button" value="选择此商品" class="ui_input_btn01 btn_checked" data-json="<s:property value="productJson"/>"/>
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
