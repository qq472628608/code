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
<script type="text/javascript" src="/js/jquery/plugins/artDialog/jquery.artDialog.source.js?skin=blue"></script>
<script type="text/javascript" src="/js/jquery/plugins/my97/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript">
	$(function(){
		$(".btn_chartSerch").click(function(){
			$("#searchForm").submit();
		});
		$("input[name='oqo.beginTime']").addClass("Wdate").click(function(){
			WdatePicker({el:this,dateFmt:'yyyy-MM-dd'});
		});
		$("input[name='oqo.endTime']").addClass("Wdate").click(function(){
			WdatePicker({el:this,dateFmt:'yyyy-MM-dd'});
		});
		
		//图表分析
		$(".btn_parse").click(function(){
     			window.open("/orderChart_showChart?oqo.chartType="+$(".select_chart_style").val(),"","width=1100px,height=500px");
		});
	});
</script>
<title>订货报表</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<s:form id="searchForm" namespace="/" action="orderChart_list" theme="simple">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							时间
							<s:date name="oqo.beginTime" format="yyyy-MM-dd" var="bt"/>
							<s:textfield name="oqo.beginTime" cssClass="ui_input_txt04" value="%{bt}"/>-
							<s:date name="oqo.endTime" format="yyyy-MM-dd" var="et"/>
							<s:textfield name="oqo.endTime" cssClass="ui_input_txt04" value="%{et}"/>
							货品
							<s:textfield name="oqo.keyword" cssClass="ui_input_txt04"/>
							供应商
							<s:select list="#suppliers" listValue="name" listKey="id" name="oqo.supplierId" headerKey="-1" headerValue="请选择" cssClass="ui_select02"/>
							品牌
							<s:select list="#brands" listValue="name" listKey="id" name="oqo.brandId" headerValue="请选择" headerKey="-1" cssClass="ui_select02"/>
							分组
							<s:select list="#types" name="oqo.groupType" listValue="groupName" cssClass="ui_select02"/>
							图表
							<s:select list="#{'line':'线形图','bar':'柱状图','pie':'饼状图'}" cssClass="ui_select02 select_chart_style"/>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_chartSerch"/>
							<input type="button" value="图表分析" class="ui_input_btn01 btn_parse"/>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th>分组类型</th>
							<th>订单数量</th>
							<th>订单金额</th>
						</tr>
						<tbody>
							<s:iterator value="#orderCharts">
								<tr>
									<td><s:property value="group"/></td>
									<td><s:property value="totalNumber"/></td>
									<td><s:property value="amount"/></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</s:form>
</body>
</html>
