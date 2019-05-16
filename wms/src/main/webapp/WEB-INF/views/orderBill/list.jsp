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
<script type="text/javascript" src="/js/jquery/plugins/my97/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>采购订单</title>
<style>
	.alt td{ background:black !important;}
</style>
<script type="text/javascript">
	$(function(){
		$(".btn_input").click(function(){
			window.location.href = $(this).data("url");
		});
		$("input[name='bq.beginTime']").addClass("Wdate").click(function(){
			WdatePicker({el:this,dateFmt:'yyyy-MM-dd'});
		});
		$("input[name='bq.endTime']").addClass("Wdate").click(function(){
			WdatePicker({el:this,dateFmt:'yyyy-MM-dd'});
		});
	})
	
</script>
</head>
<body>
	<s:actionerror/>
	<s:form id="searchForm" namespace="/" action="orderBill_list" theme="simple">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							业务时间
							<s:date name="bq.beginTime" format="yyyy-MM-dd" var="bt"/>
							<s:textfield name="bq.beginTime" cssClass="ui_input_txt02" value="%{bt}"/>-
							<s:date name="bq.endTime" format="yyyy-MM-dd" var="et"/>
							<s:textfield name="bq.endTime" cssClass="ui_input_txt02" value="%{bt}"/>
							供应商
							<s:select list="#suppliers" listValue="name" listKey="id" name="bq.supplierId" headerKey="-1" headerValue="请选择" cssClass="ui_select01"/>
							审批状态
							<s:select list="#{-1:'请选择',0:'未审核',1:'已审核'}" name="bq.status" cssClass="ui_select01"/>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-url="http://localhost:8080/orderBill_edit"/> 
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>订单编号</th>
							<th>订单时间</th>
							<th>审批状态</th>
							<th>总金额</th>
							<th>总数量</th>
							<th>审批时间</th>
							<th>制表时间</th>
							<th>供应商</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#pageQuery.queryResult">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" data-id="<s:property value="id"/>"/></td>
									<td><s:property value="sn"/></td>
									<td>
										<s:date name="vdate" format="yyyy-MM-dd"/>
									</td>
									<td>
										<s:if test="status==0">
											<span style="color:red">未审核</span>
										</s:if>
										<s:elseif test="status==1">
											<span style="color:green">已审核</span>
										</s:elseif>
									</td>
									<td><s:property value="totalAmount"/></td>
									<td><s:property value="totalNumber"/></td>
									<td>
										<s:date name="auditTime" format="yyyy-MM-dd"/>
									</td>
									<td>
										<s:date name="inputTime" format="yyyy-MM-dd"/>
									</td>
									<td><s:property value="supplier.name"/></td>
									<s:if test="status==0">
									<td>
										<s:a namespace="/" action="orderBill_audit"><s:param name="orderBill.id" value="id"/>审核</s:a>
										<s:a namespace="/" action="orderBill_edit"><s:param name="orderBill.id" value="id"/>编辑</s:a>
										<s:url namespace="/" action="orderBill_delete" var="urldata">
											<s:param name="orderBill.id" value="id"></s:param>
										</s:url>
										<a href="" class="btn_delete" data-url='<s:property value="#urldata"/>'>删除</a>
									</td>
									</s:if>
									<s:elseif test="status==1">
									<td>
										<s:a namespace="/" action="orderBill_view">
											<s:param name="orderBill.id" value="id"/>查看
										</s:a>
									</td>
									</s:elseif>
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
