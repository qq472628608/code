jQuery.ajaxSettings.traditional = true;
/** table鼠标悬停换色* */
$(function() {
	// 如果鼠标移到行上时，执行函数
	$(".table tr").mouseover(function() {
		$(this).css({background : "#CDDAEB"});
		$(this).children('td').each(function(index, ele){
			$(ele).css({color: "#1D1E21"});
		});
	}).mouseout(function() {
		$(this).css({background : "#FFF"});
		$(this).children('td').each(function(index, ele){
			$(ele).css({color: "#909090"});
		});
	});
	
/*	//全选
	$("#all").click(function(){
		$(".acb").prop("checked",this.checked);
	});
	//批量删除
	$(".btn_bathDelete").click(function(){
		var ids = new Array();
		var url = $(this).data("url");
		$.each($(".acb:checked"),function(index,item){
			ids[index] = $(item).data("id");
		});
		if(ids.length == 0){
			$.dialog({
				titile:"提示",
				content:"请选择要删除的对象",
				ok:function(){}
			});
			return;
		}else{
			$.dialog({
				title:"提示",
				content:"你确认要删除数据吗",
				ok:function(){
					$.post(url,{ids,ids},function(){
						window.location.reload();
					});
				},
				cancel:true
			});
		}
	});*/
	
	$(".btn_delete").click(function(){
		var url = $(this).data("url");
/*		$.dialog({
			titile:"提示",
			content:"你确认要删除数据吗",
			ok:function(){*/
				$.post(url,function(){
					window.location.reload();
				});
/*			},
			cancel:true
		});*/
	});
	
	//全选
	$("#all").click(function(){
		$(".acb").prop("checked",this.checked);
	});
	//批量删除
	$(".btn_bathDelete").click(function(){
		var ids = new Array();
		var url = $(this).data("url");
		$.each($(".acb:checked"),function(index,item){
			ids[index] = $(item).data("id");
		});
		if(ids.length == 0){
			/*$.dialog({
				titile:"提示",
				content:"请选择要删除的对象",
				ok:function(){}
			});*/
			alert("请选择要删除的对象");
			return;
		}else{
			$.post(url,{ids,ids},function(){
				window.location.reload();
			});
		}
	});

});
