$(function(){
	$("#selectAll").click(function(){
		$(".all option").appendTo($(".selected"));
	});
	$("#deselectAll").click(function(){
		$(".selected option").appendTo($(".all"));
	});
	$("#select").click(function(){
		$(".all option:selected").appendTo($(".selected"));
	});
	$("#deselect").click(function(){
		$(".selected option:selected").appendTo($(".all"));
	});
	
	var	selecteds = $.map( $(".selected option"),function(item,index){
			return item.value;
	});
	$.each($(".all option"),function(index,item){
		if($.inArray(item.value,selecteds) >=0 ){
			$(item).remove();
		}
	});
	$("#editForm").submit(function(){
		$(".selected option").prop("selected",true);
	});
});


$(function(){
	$("#mselectAll").click(function(){
		$(".all_menus option").appendTo($(".selected_menus"));
	});
	$("#mdeselectAll").click(function(){
		$(".selected_menus option").appendTo($(".all_menus"));
	});
	$("#mselect").click(function(){
		$(".all_menus option:selected").appendTo($(".selected_menus"));
	});
	$("#mdeselect").click(function(){
		$(".selected_menus option:selected").appendTo($(".all_menus"));
	});
	
	var	selecteds = $.map( $(".selected_menus option"),function(item,index){
		return item.value;
	});
	$.each($(".all_menus option"),function(index,item){
		if($.inArray(item.value,selecteds) >=0 ){
			$(item).remove();
		}
	});
	$("#editForm").submit(function(){
		$(".selected_menus option").prop("selected",true);
	});
});