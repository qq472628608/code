//批量删除
$(function(){
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
