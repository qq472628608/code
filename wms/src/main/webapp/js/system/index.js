//加载当前日期
function loadDate(){
	var time = new Date();
	var myYear = time.getFullYear();
	var myMonth = time.getMonth() + 1;
	var myDay = time.getDate();
	if (myMonth < 10) {
		myMonth = "0" + myMonth;
	}
	document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."	+ myDay;
}

/**
 * 隐藏或者显示侧边栏
 * 
 **/
function switchSysBar(flag){
	var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
	if( flag==true ){	// flag==true
		left_menu_cnt.show(500, 'linear');
		side.css({width:'280px'});
		$('#top_nav').css({width:'77%', left:'304px'});
    	$('#main').css({left:'280px'});
	}else{
        if ( left_menu_cnt.is(":visible") ) {
			left_menu_cnt.hide(10, 'linear');
			side.css({width:'60px'});
        	$('#top_nav').css({width:'100%', left:'60px', 'padding-left':'28px'});
        	$('#main').css({left:'60px'});
        	$("#show_hide_btn").find('img').attr('src', 'images/common/nav_show.png');
        } else {
			left_menu_cnt.show(500, 'linear');
			side.css({width:'280px'});
			$('#top_nav').css({width:'77%', left:'304px', 'padding-left':'0px'});
        	$('#main').css({left:'280px'});
        	$("#show_hide_btn").find('img').attr('src', 'images/common/nav_hide.png');
        }
	}
}

$(function(){
	loadDate();
	
	// 显示隐藏侧边栏
	$("#show_hide_btn").click(function() {
        switchSysBar();
    });
});

$(function(){
	$("#dleft_tab1 li a").click(function(){
		$("#rightMain").attr("src",$(this).data("url"));
		$("#here_area").html("当前位置：业务&nbsp;>&nbsp;"+$(this).text());
	});
});
//=================================================================================
var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		callback:{
			onClick:onClick
		}
	};

function onClick(event, treeId, treeNode){
	if(treeNode.action){
		$("#rightMain").prop("src",treeNode.action);
	}
	if(treeNode.getParentNode()){
		$("#here_area").html("当前位置："+treeNode.getParentNode().name+"&nbsp;>&nbsp;"+treeNode.name);
	}
}

function filter(treeId, parentNode, childNodes) {
	if (!childNodes) return null;
	for (var i=0, l=childNodes.length; i<l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}

	var zNodes = {
			business:[
				{id:1,pId:0,name:"业务模块",open:true},
				{id:11,pId:1,name:"品牌管理",open:true,action:"brand_list"},
				{id:12,pId:1,name:"供应商管理",open:true,action:"supplier_list"},
				{id:13,pId:1,name:"商品管理",open:true,action:"product_list"},
				{id:14,pId:1,name:"仓库管理",open:true,action:"depot_list"},
				{id:15,pId:1,name:"客户管理",open:true,action:"client_list"},
				{id:16,pId:1,name:"采购订单管理",open:true,action:"orderBill_list"},
				{id:17,pId:1,name:"采购入库管理",open:true,action:"stockIncomeBill_list"},
				{id:18,pId:1,name:"销售出库管理",open:true,action:"stockOutcomeBill_list"}
			],
			systemManage:[
				{id:1,pId:0,name:"系统模块",open:true},
				{id:11,pId:1,name:"部门管理",open:true,action:"department_list"},
				{id:12,pId:1,name:"员工管理",open:true,action:"employee_list"},
				{id:13,pId:1,name:"权限管理",open:true,action:"permission_list"},
				{id:14,pId:1,name:"角色管理",open:true,action:"role_list"},
				{id:15,pId:1,name:"系统菜单管理",open:true,action:"systemMenu_list"}
			],
			charts:[
				{id:1,pId:0,name:"报表模块",open:true},
				{id:11,pId:1,name:"即时库存报表",open:true,action:"productStock_list"},
				{id:11,pId:1,name:"订货报表",open:true,action:"orderChart_list"},
				{id:11,pId:1,name:"销售报表",open:true,action:"saleChart_list"}
			]
	};
	function createTree(sn){
		$.fn.zTree.init($("#dleft_tab1"), setting, zNodes[sn]);
	}
//===================================================================================
$(function(){
	createTree("business");
	$("#TabPage2 li").click(function(){
		//window.location.href="http://localhost:8080/main.action";
		createTree($(this).data("rootmenu"));
		$.each($("#TabPage2 li"),function(index,item){
			$(this).removeClass("selected");
			$(this).children().prop("src","images/common/"+(index+1)+".jpg");
		});
		var index = $(this).index()+1;
		$(this).addClass("selected");
		$(this).children().prop("src","images/common/"+index+"_hover.jpg");
		$("#nav_module img").prop("src","images/common/module_"+index+".png");
	});
});