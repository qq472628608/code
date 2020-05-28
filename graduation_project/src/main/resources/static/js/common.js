/**
 * while mouse enter header show the info of tag
 */
var link = document.querySelector(".link");
if(link){
    link.onmouseover = function(event){
        var target = event.target;
        var logoLine = document.querySelector(".header .logo line");
        logoLine.setAttribute("style","stroke:rgb(0,190,255,1);stroke-width:12");
        if("a-kind" === target.className){
            var links = document.querySelectorAll(".link .a-kind");
            for(var i = 0;i < links.length;i++){
                links[i].parentNode.querySelector(".detail").setAttribute("style","display:none");
            }
            target.parentNode.querySelector(".detail").setAttribute("style","display:block");
            document.querySelector(".link .user").onmouseleave();
            document.querySelector(".header").className = "header header-spread";
            var header_spread = document.querySelector(".header-spread");
            if(header_spread){
                header_spread.onmouseleave=function(){
                    this.className = "header";
                    var details = this.querySelectorAll(".detail");
                    for(var i = 0;i < details.length;i++){
                        details[i].setAttribute("style","display:none");
                    }
                }
            }
        }
    }
}

var link_user = document.querySelector(".link .user");
if(link_user){
    var user_ops_box = document.querySelector(".user-ops-box");
    link_user.onmouseenter = function(){
        var header_spread = document.querySelector(".header-spread");
        if(header_spread){
            document.querySelector(".header-spread").onmouseleave();
        }
        user_ops_box.setAttribute("style","display:block;");
    }
    link_user.onmouseleave = function(){
        user_ops_box.setAttribute("style","display:none;");
    }
}


/**
 * change buyNumber event
 */
var btn_numbers = document.querySelectorAll(".select-number");
if(btn_numbers){
    for(var i = 0;i < btn_numbers.length;i++){
        btn_numbers[i].onclick = function(event){
            var number_input = this.querySelector("input[name='number']");
            var dom = event.target;
            if(dom.className.indexOf("incr") > -1){
                number_input.value++;
            }
            if(dom.className.indexOf("desc") > -1){
                if(number_input.value <= 1){
                    return;
                }else{
                    number_input.value--;
                }
            }
        }
    }
}

var link_ul = document.querySelector(".header-link-ul");
var link_last = document.querySelector(".link-last");
if(link_ul){
    sendAjax("GET","/kinds",null,function(){
        if(arguments[0]){
            var kinds = JSON.parse(arguments[0]);
            for(var i = 0;i < kinds.length;i++){
                let kind = kinds[i];
                var kind_li = document.createElement("li");
                kind_li.className = "header-li"+(i+1);  
                kind_li.setAttribute("data-id",kind.id);
                var a = document.createElement("a");
                var div = document.createElement("div");
                let ul = document.createElement("ul");
                div.className = "detail";
                div.appendChild(ul);
                a.href = "/html/list.html?kind="+kind.id;
                a.innerHTML = kind.cn_name;
                a.className = "a-kind";
                kind_li.appendChild(a);
                kind_li.appendChild(div);
                link_ul.insertBefore(kind_li,link_last);
                sendAjax("GET","/key/kind?kind_id="+kind.id+"&begin=0&end=8",null,function(){
                    if(arguments[0]){
                        var keys = JSON.parse(arguments[0]);
                        for(var j = 0;j < keys.length;j++){
                            var pli = document.createElement("li");
                            pli.innerHTML = `<a href="/html/detail.html?id=${keys[j].id}">
                            <img src="${keys[j].img.url}" alt="">
                            <div class="detail-name">
                                ${keys[j].name}
                            </div>
                            <div class="detail-price">
                                ￥${keys[j].basePrice}
                            </div>
                        </a>`;
                        ul.appendChild(pli);
                    }
                    }
                });
            }
        }
    });
}

var header = document.querySelector(".header");
sendAjax("GET","/user",null,function(){
    if(arguments[0]){
        var user = JSON.parse(arguments[0]);
        document.querySelector(".user-title-tag").setAttribute("style",'background: url("'+user.icon+'") center no-repeat;background-size:100%;');
        if(header){
            sendAjax("GET","/car/number",null,function(){
                if(arguments[0]){
                    var totalNumber = arguments[0];
                    header.querySelector(".shoppingcar em").innerHTML = totalNumber;
                }
            });
        }
    }
});


function showDialog(){
    document.querySelector(".back").setAttribute("style","display:block;");
    document.querySelector(".dialog-general").setAttribute("style","display:block;");
}
function closeDialog(){
    document.querySelector(".back").setAttribute("style","display:none;");
    document.querySelector(".dialog-general").setAttribute("style","display:none;");
}


function exit(){
    sendAjax("DELETE","/api/user",null,function(){
        window.location.reload();
    });
}

function search(){
    var name = document.querySelector(".header input[name='search']").value;
    window.location = "/html/search.html?name="+name;
}

if(header){
    header.querySelector("input[name='search']").onkeyup = function(event){
        var code = event.keyCode;
        if(code == 13){
            header.querySelector(".search a").click();
        }
    }
}

