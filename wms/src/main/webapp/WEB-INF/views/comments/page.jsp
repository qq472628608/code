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
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$(".btn_page").click(function(){
			$("input[name='bq.currentPage']").val($(this).data("page")||$("input[name='bq.currentPage']").val());
			$("#searchForm").submit();
		});
		$(":input[name='bq.pageSize']").change(function(){
			$("#searchForm").submit();
		});
	});
</script>
</head>
<body>
		<div class="ui_tb_h30">
					<div class="ui_flt" style="height: 35px; line-height: 35px;">
						共有
						<span class="ui_txt_bold04">
							<s:property value="#pageQuery.totalCount"/>
						</span>
						条记录，当前第
						<span class="ui_txt_bold04">
							<s:property value="#pageQuery.currentPage"/>
						</span>页
					</div>
					<div class="ui_frt">
						<input type="button" value="首页" class="ui_input_btn01 btn_page" data-page="1"/>
						<input type="button" value="上一页" class="ui_input_btn01 btn_page" data-page="<s:property value="#pageQuery.prePage"/>"/>
						<input type="button" value="下一页" class="ui_input_btn01 btn_page" data-page="<s:property value="#pageQuery.nextPage"/>"/>
						<input type="button" value="尾页" class="ui_input_btn01 btn_page" data-page="<s:property value="#pageQuery.totalPage"/>"/>
						
						<s:select list="{7,10,15,30}" name="bq.pageSize"/>
						转到第<s:textfield name="bq.currentPage" cssClass="ui_input_txt01"/>页
							 <input type="button" class="ui_input_btn01 btn_page" value="跳转"/>
					</div>
				</div>
</body>
</html>