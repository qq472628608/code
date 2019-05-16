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
<script type="text/javascript" src="/js/jquery/plugins/my97/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<script type="text/javascript">
	var domThis;
	function setThis(dom){
		 domThis = dom;
	}
	function clearTr(tr){
		tr.find("[tag=name]").val("");
		tr.find("[tag=brand]").html("");
		tr.find("[tag=costPrice]").val("");
		tr.find("[tag=number]").val("");
		tr.find("[tag=amount]").html("");
		tr.find("[tag=remark]").val("");
	}
	function getJson(json){
		var tr = $(domThis).parents("tr:first");
		tr.find("input[tag=name]").val(json.name);
		tr.find("input[tag=pid]").val(json.id);
		tr.find("span[tag=brand]").html(json.brand.name);
		tr.find("input[tag=costPrice]").val(json.costPrice);
	}
	$(function(){ 
		$("#edit_table_body").on("click",".searchproduct",function(){
			window.open("/product_showProduct","","width=1000px,height=600px");
			setThis(this);
		}).on("blur","input[tag=costPrice],input[tag=number]",function(){
			var tr = $(this).parents("tr:first");
			var costPrice = tr.find("[tag=costPrice]").val();
			var number = tr.find("[tag=number]").val();
			tr.find("[tag=amount]").html(Number(costPrice*number).toFixed(2));
		}).on("click",".removeItem",function(){
			var tr = $(this).parents("tr:first");
			if($("#edit_table_body tr").length>1){
				tr.remove();
			}else{
				clearTr(tr);
			}
		});
		
		$(".appendRow").click(function(){
			var copy = $("#edit_table_body tr:first").clone();
			clearTr(copy);
			copy.appendTo($("#edit_table_body"));
		});
		
		$(".btn_submit").click(function(){
			$.each($("#edit_table_body tr"),function(index,item){
				var tr = $(item);
				tr.find("[tag=pid]").prop("name","orderBill.items["+index+"].product.id");
				tr.find("[tag=costPrice]").prop("name","orderBill.items["+index+"].costPrice");
				tr.find("[tag=number]").prop("name","orderBill.items["+index+"].number");
				tr.find("[tag=remark]").prop("name","orderBill.items["+index+"].remark");
			});
			$("#editForm").submit();
		});
		$("input[name='orderBill.vdate']").addClass("Wdate").click(function(){
			WdatePicker({el:this,dateFmt:'yyyy-MM-dd'});
		});
	});
</script>
</head>
<body>
<s:form name="editForm" action="orderBill_saveOrUpdate" namespace="/" theme="simple" id="editForm">
	<s:hidden name="orderBill.id"/>
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">采购订单编辑</span>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">订单编号</td>
					<td class="ui_text_lt">
						<s:textfield name="orderBill.sn" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">业务时间</td>
					<td class="ui_text_lt">
						<s:date name="orderBill.vdate" format="yyyy-MM-dd" var="vt"/>
						<s:textfield name="orderBill.vdate" cssClass="ui_input_txt02" value="%{vt}"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">供应商</td>
					<td class="ui_text_lt">
						<s:select list="#suppliers" name="orderBill.supplier.id" listKey="id" listValue="name" cssClass="ui_select01"/>
					</td>
				</tr>
				<tr>
                    <td class="ui_text_rt" width="140">明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <s:if test="orderBill.id==null">
                                <tr>
                                    <td></td>
                                    <td>
                                        <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt04" tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <s:hidden name="orderBill.items.product.id" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"></span></td>
                                    <td><s:textfield tag="costPrice" name="orderBill.items.costPrice"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><s:textfield tag="number" name="orderBill.items.number"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><span tag="amount"></span></td>
                                    <td><s:textfield tag="remark" name="orderBill.items.remark"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
							</s:if>
							<s:iterator value="orderBill.items">
								<tr>
                                    <td></td>
                                    <td>
                                        <s:textfield disabled="true" readonly="true" name="product.name" cssClass="ui_input_txt04" tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <s:hidden name="product.id" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"><s:property value="product.brand.name"/></span></td>
                                    <td><s:textfield tag="costPrice" name="costPrice"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><s:textfield tag="number" name="number"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><span tag="amount"><s:property value="amount"/></span></td>
                                    <td><s:textfield tag="remark" name="remark"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
							</s:iterator>
                            </tbody>
                        </table>
                    </td>
                </tr>
				
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input type="button" value="确定保存" class="ui_input_btn01 btn_submit"/>
						&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</s:form>
</body>
</html>