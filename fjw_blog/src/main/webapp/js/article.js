var url = window.location.search;
var id = url.substring(url.lastIndexOf("=")+1,url.length);
if(!id){
    window.location.href = "404.html";
}
function sendAjax(method,url,data,success){
	var ajax = new XMLHttpRequest();
    ajax.open(method,url,true);
    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ajax.onreadystatechange=function(){
    	if(ajax.readyState == 4&&ajax.status ==200){
    		if(success){
    			success.call(this,ajax.responseText);
    		}
    	}else if(ajax.readyState == 4&&ajax.status != 200){
    		window.location.href="404.html";
    	}
    }
    if(data){
        ajax.send(data);
    }else{
        ajax.send();
    }
}
function dataAjax(url,data,success){
	var ajax = new XMLHttpRequest();
    ajax.open("post",url,true);
    ajax.onreadystatechange=function(){
        if(ajax.readyState == 4&&ajax.status ==200){
            if(success){
                success.call(this,ajax.responseText);
            }
        }
    }
    ajax.send(data);
}

document.querySelector(".link_logo_container img").onclick=function(){
    window.location.href="index.html";
};
function link(target){
	sendAjax("get","/currentUserId",null,function(){
	    if(arguments[0]){
	    	window.location.href=target+"?id="+arguments[0];
	    }else{
	    	alert("请登录");
	        window.location.href="index.html";
	    }
	});
}
    sendAjax("get","/getArticle?id="+id,null,function(){
        if(arguments[0]){
            var value = JSON.parse(arguments[0]);
            document.querySelector(".article_title").innerHTML = value.title;
            var str = value.text;
            var str1 = str.replace(/&lt;/g,"<");
            var text = str1.replace(/&gt;/g,">");
            var textDom = document.querySelector(".text");
            textDom.innerHTML = text;
            var codes = textDom.querySelectorAll("code");
            for(var i=0;i<codes.length;i++){
            	var html = codes[i].innerHTML;
            	html = html.replace(/\<\/?p\>/gim,"");
            	html = html.replace(/</g,"&lt;");
            	html = html.replace(/>/g,"&gt;"); 
            	html = html.replace(/&lt;br&gt;/g,"<br>");
            	codes[i].innerHTML = html;
            }
            document.querySelector(".tag_a1 i").className = value.tag1;
            Prism.highlightAll();
        }
    });
sendAjax("get","/currentUserId",null,function(){
    if(arguments[0] == 1){
        var li = document.createElement("li");
        li.className = "tag_li";
        li.innerHTML='<a class="tag_a3" href="javascript:updateArticle();">'+
        '修改</a>';
        document.querySelector(".tag_ul").appendChild(li);
    }
});
function updateArticle(){
	window.location.href="/updateArticle?articleid="+id;
}
var tag_a1 = document.querySelector(".tag_a1");
tag_a1.onclick=function(){
    var dom = this;
    sendAjax("get","/currentUserId",null,function(){
        if(arguments[0]){
            var name = dom.querySelector("i").className;
            var data = "userid="+arguments[0]+"&articleid="+id;
            if(name == "fa fa-tags"){
                sendAjax("post","/collect",data,function(){
                dom.querySelector("i").className = "fa fa-star";
            });
            }else{
                sendAjax("post","/removeCollection",data,function(){
                dom.querySelector("i").className = "fa fa-tags";
                });
            }
        }else{
            alert("请登录");
            window.location.href = "index.html";
        }
    });
}
var tag_a2 = document.querySelectorAll(".tag_a2");
if(tag_a2 != null){
    tag_a2.onclick=function(){
        var dom = this;
        var fa = this.querySelector("i");
        if(fa.className == "fa fa-heart-o"){
            var data = "isAdd=true&articleid="+id;
            sendAjax("post","/love",data,function(){
                if(arguments[0]){
                    dom.querySelector("span").innerHTML = arguments[0];
                    dom.querySelector("i").className = "fa fa-heart";
                }
            });
        }else{
            var data = "isAdd=false&articleid="+id;
            sendAjax("post","/love",data,function(){
                if(arguments[0]){
                    dom.querySelector("span").innerHTML = arguments[0];
                    dom.querySelector("i").className = "fa fa-heart-o";
                }
            });
        }
    }
}
var comments = document.querySelector(".recent_comments");
function createComment(json){
    var value = JSON.parse(json);
    if(value.length > 0){
        for(var i in value){
            var comment = document.createElement("div");
            var text = value[i].text;
            if(text.length>18){
                text = text.substring(0,18)+"......";
            }
            comment.className = "comment";
            comment.innerHTML = 
        '<input type="hidden" name="id" value='+value[i].id+'>'+
        '<div class="comment_head">'+
        '<a class="a_visitor" href="pinfo.html?id='+value[i].user.id+'"><img class="head" src="'+value[i].user.image+'"></a>'+		
        '</div>'+
        '<div class="comment_content">'+
            '<a class="a_comment" href="article.html?id='+value[i].article.id+'">'+
                '<span class="content">'+text+'</span>'+
            '</a>'+
                '<p class="content_time">'+value[i].time+'</p>'+
        '</div>';
            comments.appendChild(comment);
        }
    }
}
sendAjax("get","/getFourComment",null,function(){
    if(arguments[0]){
        createComment(arguments[0]);
    }
});
function find(kindSn){
  window.location.href = "home.html?kindSn="+kindSn;
}

function listComment(json){
    var value = JSON.parse(json);
    var list = document.querySelector(".list_ul");
    if(value.length > 0){
        for(var i in value){
            if(!value[i].parent){
                var li = document.createElement("li");
                li.innerHTML = '<div class="put_comment">'+
                '<input type="hidden" name="id" value="'+value[i].id+'">'+
                '<ul>'+
                    '<li>'+
                        '<a href="pinfo.html?id='+value[i].user.id+'">'+
                            '<img src="'+value[i].user.image+'">'+
                        '</a>'+
                    '</li>'+
                    '<div class="put_body">'+
                        '<div class="put_user">'+
                            '<i class="fa fa-user-o"></i>&nbsp;Posted by：'+
                            '<a class="a_put_user" href="pinfo.html?id='+value[i].user.id+'">'+value[i].user.name+'</a>'+
                        '</div>'+
                        '<div class="put_content">'+
                            value[i].text
                        +'</div>'+
                        '<div class="put_time">'+ 
                            value[i].time 
                        +'</div>'+
                        '<div class="reply_btn">'+
                            '<a href="javascript:void(0);">&nbsp;&nbsp;&nbsp;回复</a>'+
                        '</div>'+
                    '</div>'+
                '</ul>'+
                '<div class="edit_reply">'+
				    '<div contenteditable="true" class="edit_content">'+							
				    '</div>'+
				    '<div class="edit_button">'+
				        '<button>提交&nbsp;<i class="fa fa-arrow-right"></i></button>'+
				    '</div>'+
			    '</div>'+
            '</div>';
            list.appendChild(li);
            }else{
                var li = document.querySelector(".list_ul .put_comment input[value='"+value[i].parent.id+"']").parentNode.parentNode;
                var div = document.createElement("div");
                div.className = "reply_comment";
                div.innerHTML = '<ul>'+
                '<input type="hidden" name="id" value="'+value[i].id+'">'+
                '<li>'+
                    '<a href="pinfo.html?id='+value[i].user.id+'">'+
                        '<img src="'+value[i].user.image+'">'+
                    '</a>'+
                '</li>'+
                '<div class="put_body">'+
                    '<div class="put_user">'+
                        '<i class="fa fa-user-o"></i>&nbsp;Posted by'+
                        '<a class="a_put_user" href="pinfo.html?id='+value[i].user.id+'">'+value[i].user.name+'</a>'+
                    '</div>'+
                    '<div class="put_content">'+
                        value[i].text
                    +'</div>'+
                    '<div class="put_time"> '+
                        value[i].time
                    +'</div>'+
                '</div>'+
            '</ul>';
            li.appendChild(div);
            }
        }
    }
}
function currentComment(){
    sendAjax("get","/listComment?articleid="+id,null,function(){
        if(arguments[0]){
            listComment(arguments[0]);
            var reply_btn = document.querySelectorAll(".reply_btn");
            for(var i in reply_btn){
                reply_btn[i].onclick=function(){
                var edit = this.parentNode.parentNode.parentNode.querySelector(".edit_reply");
                edit.setAttribute("style","display:block;");
                }   
            }
            var edit_btn = document.querySelectorAll(".edit_button button");
for(var i in edit_btn){
    edit_btn[i].onclick=function(){
        var dom = this;
        var name = this.parentNode.parentNode.className;
        var str = this.parentNode.parentNode.querySelector(".edit_content").innerText;
        var str1 = str.replace(/</g,"&lt;");
        var text = str1.replace(/>/g,"&gt;");
        var data = new FormData();
        data.append("text",text);
        data.append("articleid",id);
        if(name == "edit"){
            dataAjax("/saveComment",data,function(){
                document.querySelector(".edit_content").innerText="";
                document.querySelector(".list_ul").innerHTML="";
                currentComment();
            });
        }else{
            var commentId = dom.parentNode.parentNode.parentNode.querySelector("input").value;
            data.append("parentid",commentId);
            dataAjax("/saveComment",data,function(){
                var edit = dom.parentNode.parentNode;
                edit.setAttribute("style","display:none;");
                document.querySelector(".edit_content").innerText="";
                document.querySelector(".list_ul").innerHTML="";
                currentComment();
            });
        }
    }
}
        }
    });  
}
currentComment();
