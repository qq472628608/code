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
<script type="text/javascript">
	$(function(){
		$("input").prop("readonly",true);
	});
</script>
</head>
<body>
<s:form name="editForm" action="stockIncomeBill_saveOrUpdate" namespace="/" theme="simple" id="editForm">
	<s:hidden name="stockIncomeBill.id"/>
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">采购入库订单查看</span>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">订单编号</td>
					<td class="ui_text_lt">
						<s:textfield name="stockIncomeBill.sn" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">供应商</td>
					<td class="ui_text_lt">
						<s:textfield name="stockIncomeBill.supplier.name" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">仓库</td>
					<td class="ui_text_lt">
						<s:textfield name="stockIncomeBill.depot.name" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
                    <td class="ui_text_rt" width="140">明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
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
							<s:iterator value="stockIncomeBill.items">
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
                                </tr>
							</s:iterator>
							 <tr>
							 		<td></td>
                                    <td colspan="6" style="text-align: center">
                                        <span>到库总数量:<s:property value="stockIncomeBill.totalNumber"/></span>
                                        <span style="padding-left: 100px;">到库总金额:<s:property value="stockIncomeBill.totalAmount"/></span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
				
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input type="button" value="返回" style="color:black" class="ui_input_btn01" onclick="window.history.back();">
					</td>
				</tr>
			</table>
		</div>
	</div>
</s:form>
</body>
</html>