var url = window.location.search;
var key_id = url.substring(url.lastIndexOf("=")+1,url.length);

var box = document.querySelector(".box");
var links = document.querySelectorAll(".link .a-kind");
for(var i = 0;i < links.length;i++){
    links[i].onmouseenter=function(){
        for(var i = 0;i < links.length;i++){
            links[i].parentNode.querySelector(".detail").setAttribute("style","display:none");
        }
        this.parentNode.querySelector(".detail").setAttribute("style","display:block");
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
var introductions = document.querySelectorAll(".all-introduction-title span");
for(var i = 0;i < introductions.length;i++){
    introductions[i].onclick = function(){
        introductions[0].className = "all-introduction-detail";
        introductions[1].className = "all-introduction-comment";
        if(this.className == "all-introduction-detail"){
            this.className = "all-introduction-detail active";
            document.querySelector(".introduction-comment").setAttribute("style","display:none;");
            document.querySelector(".introduction-detail").setAttribute("style","display:block;");
        }
        if(this.className == "all-introduction-comment"){
            this.className = "all-introduction-comment active";
            document.querySelector(".introduction-detail").setAttribute("style","display:none;");
            document.querySelector(".introduction-comment").setAttribute("style","display:block;");
        }
    }
}


var finish = 0;
var right = document.querySelector(".right");
var products = [];
sendAjax("GET","/products/"+key_id,null,function(){
    if(arguments[0]){
        var ps = JSON.parse(arguments[0]);
        products = ps;
        finish++;
        var p = ps[0];
        right.querySelector(".title").innerHTML = p.name;
        right.querySelector(".introduction").innerHTML = p.introduction;
        right.querySelector(".money-number").innerHTML = parseFloat(p.price).toFixed(2);
        box.setAttribute("data-id",p.id)
    }
});

var option = document.querySelector(".option-box");
var images = [];
sendAjax("GET","/img/"+key_id,null,function(){
    if(arguments[0]){
        var imgs = JSON.parse(arguments[0]);
        images = imgs;
        if(existColor(imgs)){
            var obj = {};
            var div = document.createElement("div");
            var span = document.createElement("span");
            span.className = "detail-small-title";
            span.innerHTML = "颜色分类";
            var ul = document.createElement("ul");
            ul.className = "color";
            div.appendChild(span);
            div.appendChild(ul);
            option.appendChild(div);
        }
        for(var i = 0;i < imgs.length;i++){
            var color = imgs[i].color;
            if(color){
                colorName = imgs[i].color.name;
                if(!obj[colorName]){
                    var li = document.createElement("li");
                    var img = document.createElement("img");
                    var span1 = document.createElement("span");
                    img.src = imgs[i].url;
                    span1.innerHTML = colorName;
                    li.appendChild(img);
                    li.appendChild(span1);
                    li.setAttribute("data-id",color.id);
                    ul.appendChild(li);
                    obj[colorName] = 1;
                }
            }
            if(imgs[i].state == 13){
                var detail_img = document.querySelector(".introduction-detail");
                var img = document.createElement("img");
                img.src = imgs[i].url;
                detail_img.appendChild(img);
            }
        }
        finish++;
    }
});

sendAjax("GET","/keyValue/"+key_id,null,function(){
    if(arguments[0]){
        var keyValues = JSON.parse(arguments[0]);
        var obj = {};
        var ul;
        for(var i = 0;i < keyValues.length;i++){
            saName = keyValues[i].attributeValue.saleAttribute.name;
            saId = keyValues[i].attributeValue.saleAttribute.id;
            if(!obj[saName]){
                var div = document.createElement("div");
                option.appendChild(div);
                obj[saName] = 1;
                var span = document.createElement("span");
                ul = document.createElement("ul");
                ul.setAttribute("data-index",saId);
                ul.className = keyValues[i].attributeValue.saleAttribute.className+" attr";
                span.className = "detail-small-title";
                if(saName.length <= 2){
                    span.className = "detail-small-title short";
                }
                span.innerHTML = saName;
                div.appendChild(span);
                div.appendChild(ul);
            }
            var li = document.createElement("li");
            if("version" === keyValues[i].attributeValue.saleAttribute.className){
                let a = document.createElement("a");
                var name = encodeURIComponent(keyValues[i].attributeValue.value);
                sendAjax("GET","/key/name?name="+name,null,function(){
                    if(arguments[0]){
                        var key = JSON.parse(arguments[0]);
                        a.href="/html/detail.html/?id="+key.id;
                    }
                });
                a.innerHTML = keyValues[i].attributeValue.value;
                li.appendChild(a);
            }else{
                li.innerHTML = keyValues[i].attributeValue.value;
            }
            li.setAttribute("data-id",keyValues[i].id);
            document.querySelector("ul[data-index='"+saId+"']").appendChild(li);
        }
        finish++;
    }
});

document.querySelector(".option-box").onclick = function(event){
    var target = event.target;
    if("li" == target.tagName.toLowerCase()){
        clearOptionSelected(target.parentNode);
        target.className = "selected";
        getProduct();
    }
    if("li" == target.parentNode.tagName.toLowerCase()){
        clearOptionSelected(target.parentNode.parentNode);
        target.parentNode.className = "selected";
        getProduct();
    }
};

function clearOptionSelected(target){
    var lis = target.querySelectorAll("li");
    for(var i = 0;i < lis.length;i++){
        lis[i].className = "";
    }
}


var left = document.querySelector(".left");
function getProduct(){
    var uls = document.querySelectorAll(".option-box ul");
    var color_id;
    var keyTag = "";
    for(var i = 0;i < uls.length;i++){
        if("version" === uls[i].className){
            continue;
        }
        if("color" === uls[i].className){
            var lis = uls[i].querySelectorAll("li");
            for(var j = 0;j < lis.length;j++){
                if("selected" === lis[j].className){
                    color_id = lis[j].getAttribute("data-id");
                }
            }
        }else{
            var lis = uls[i].querySelectorAll("li");
            for(var j = 0;j < lis.length;j++){
                if("selected" === lis[j].className){
                    keyTag += lis[j].getAttribute("data-id")+",";
                }
            }
        }
    }
    if(keyTag){
        keyTag = keyTag.substring(0,keyTag.length - 1);
    }
    for(var m = 0;m < products.length;m++){
        var p = products[m];
        var pKeyTag = p.keyTag?p.keyTag:"";
        if(p.color){
            console.log(color_id);
            console.log(keyTag);
            console.log(p.color.id);
            console.log(pKeyTag);
            if(color_id == p.color.id && keyTag == pKeyTag){
                setBaseAttribute(p);
                setImg(color_id);
                box.setAttribute("data-id",p.id);
                return p;
            }
        }else{
            if(keyTag == pKeyTag){
                setBaseAttribute(p);
                box.setAttribute("data-id",p.id);
                return p;
            }  
        }
    }
}

function setBaseAttribute(p){
    right.querySelector(".title").innerHTML = p.name;
    right.querySelector(".introduction").innerHTML = p.introduction;
    right.querySelector(".money-number").innerHTML = parseFloat(p.price).toFixed(2);
    if(p.number <= 0){
        var btn_buy = document.querySelector(".btn-buy");
        btn_buy.innerHTML = "售罄";
        btn_buy.className = "btn-buy btn-notEnough";
    }
}

function existColor(imgs){
    if(imgs){
        for(var i = 0;i < imgs.length;i++){
            if(imgs[i].color){
                return true;
            }
        }
        return false;
    }
}

var timer = setInterval(function(){
    if(finish == 3){
        var product = products[0];
        var color_id;
        if(product.color){
            color_id = product.color.id;
            var color_lis = right.querySelectorAll(".color li");
            for(var i = 0;i < color_lis.length;i++){
                var li = color_lis[i];
                if(color_id == li.getAttribute("data-id")){
                    li.className = "selected";
                }
            }
            setImg(color_id);
        }else{
            setImg();
        }
        if(product.keyTag){
            var attrs = product.keyTag.split(",");
            var attr_lis = right.querySelectorAll(".attr li");
            for(var j = 0;j < attr_lis.length;j++){
                var attr_li = attr_lis[j];
                for(var h = 0;h < attrs.length;h++){
                    var attr = attrs[h];
                    if(attr_li.getAttribute("data-id") == attr){
                        attr_li.className = "selected";
                    }
                }
            }
        }
        window.clearInterval(timer);
    }
},200);


function setImg(color_id){
    var index = 0;
    var smallIndex = 0;
    var big_img = left.querySelector(".big-img");
    var small_img = left.querySelector(".small-img");
    big_img.innerHTML = "";
    small_img.innerHTML = "";
    for(var s = 0;s < images.length;s++){
        var img = images[s];
        if(img.state == 15 && smallIndex < 4){
            var new_img = document.createElement("img");
            new_img.src = img.url;
            var li = document.createElement("li");
            new_img.className = "small";
            li.appendChild(new_img);
            small_img.appendChild(li);
            smallIndex++;
        }
        if(smallIndex >= 4){
            break;
        }
    }
    if(color_id){
        for(var k = 0;k < images.length;k++){
            var img = images[k];
            if(img.color){
                if(img.color.id == color_id){
                    var new_img = document.createElement("img");
                    new_img.src = img.url;
                    if(index == 0){
                        new_img.className = "big";
                        big_img.appendChild(new_img);
                    }else{
                        var li = document.createElement("li");
                        new_img.className = "small";
                        li.appendChild(new_img);
                        small_img.appendChild(li);
                    }
                    index++;
                }
            }
        }
    }else{
        for(var i = 0;i < images.length;i++){
            var img = images[i];
            if(img.state == 12){
                var new_img = document.createElement("img");
                new_img.src = img.url;
                if(index == 0){
                    new_img.className = "big";
                    big_img.appendChild(new_img);
                }else{
                    var li = document.createElement("li");
                    new_img.className = "small";
                    li.appendChild(new_img);
                    small_img.appendChild(li);
                }
                index++;
            }
        }
    }
}
document.querySelector(".small-img").onclick = function(event){
    var target = event.target;
    if("img" === target.tagName.toLowerCase()){
        var url = target.src;
        document.querySelector(".big-img img").src = url;
    }
}

document.querySelector(".btn-buy").onclick = function(){
    if(this.className === "btn-buy btn-notEnough"){
        return;
    }
    var product_id = box.getAttribute("data-id");
    var number = document.querySelector("input[name='number']").value;
    var ops = [];
    var op = {
        product_id:product_id,
        number:number
    };
    ops.push(op);
    sendAjax("POST","/order",ops,function(){
        if(arguments[0]){
            window.location = "../html/pay.html?sn="+arguments[0];
        }
    },true);
};

var dialog = document.querySelector(".dialog-car");
document.querySelector(".btn-addcar").onclick = function(){
    var buyBtn = document.querySelector(".btn-buy");
    if(buyBtn.className === "btn-buy btn-notEnough"){
        return;
    }
    var product_id = box.getAttribute("data-id");
    var number = document.querySelector("input[name='number']").value;
    var dto = {
        product_id:product_id,
        number:number
    };
    sendAjax("POST","/car",dto,function(){
        dialog.setAttribute("style","display:block;");
        var carProductNumber = parseInt(document.querySelector(".header .shoppingcar em").innerHTML);
        document.querySelector(".header .shoppingcar em").innerHTML = ++carProductNumber;
        setTimeout(function(){
            dialog.setAttribute("style","display:none;");
        },3000);
    },true);
}

dialog.querySelector(".icon-cancel").onclick = function(){
    dialog.setAttribute("style","display:none;");
}

var commonDialog = document.querySelector(".dialog");
function showCommonDialog(text){
    commonDialog.setAttribute("style","display:block;");
    commonDialog.querySelector(".dialog-text").innerHTML = text;
    setTimeout(function(){
        commonDialog.setAttribute("style","display:none;");
    },3000);
}

document.querySelector(".collect").onclick = function(){
    var product_id = box.getAttribute("data-id");
    var data = new FormData();
    data.append("product_id",product_id);
    sendAjax("POST","/collect",data,function(){
        showCommonDialog(添加成功);
    },false,function(){
        if(arguments[0]){
            showCommonDialog(arguments[0]);
        }
    });
}

var comment = document.querySelector(".introduction-comment");

function setStar(stars,netNum){
    var number = parseFloat(netNum).toFixed(1);
    var num = Math.floor(number);
    for(var i = 0;i < stars.length;i++){
        if(i < num){
            stars[i].setAttribute("style","color: #ffb643;");
        }
        if(i == num){
            if((number - num) > 0){
                stars[i].className = "iconfont icon-star-half";
                stars[i].setAttribute("style","color: #ffb643;");
            }
        }
    }
}

function getStar(){
    sendAjax("GET","/comments/star/"+key_id,null,function(){
        if(arguments[0]){
            var starVo = JSON.parse(arguments[0]);
            comment.querySelector(".number0").innerHTML = starVo.count;
            comment.querySelector(".all-comment-score").innerHTML = parseFloat(starVo.total).toFixed(1);
            var allStars = comment.querySelectorAll(".all-comment-star i");
            setStar(allStars,starVo.total);
            comment.querySelector(".number1").innerHTML = parseFloat(starVo.general).toFixed(1);
            var number1Stars = comment.querySelectorAll(".number1-star i");
            setStar(number1Stars,starVo.general);
            comment.querySelector(".number2").innerHTML = parseFloat(starVo.fit).toFixed(1);
            var number2Stars = comment.querySelectorAll(".number2-star i");
            setStar(number2Stars,starVo.fit);
            comment.querySelector(".number3").innerHTML = parseFloat(starVo.sellerService).toFixed(1);
            var number3Stars = comment.querySelectorAll(".number3-star i");
            setStar(number3Stars,starVo.sellerService);
            comment.querySelector(".number4").innerHTML = parseFloat(starVo.sellerSpeed).toFixed(1);
            var number4Stars = comment.querySelectorAll(".number4-star i");
            setStar(number4Stars,starVo.sellerSpeed);
        }
    });
}
var current = 1;
var commnetList = document.querySelector(".show-comment>ul");
function getComment(current){
    sendAjax("GET","/comments?ka_id="+key_id+"&current="+current,null,function(){
        if(arguments[0]){
            var comments = JSON.parse(arguments[0]);
            for(var i = 0;i < comments.length;i++){
                var comment = comments[i];
                var imgs = comment.imgs;
                var li = document.createElement("li");
                li.innerHTML = `<div>
                <ul class="user clearfix">
                    <li class="user-header-img">
                        <img src="${comment.icon}" alt="">
                    </li>
                    <li class="user-name">
                        ${comment.userName}
                    </li>
                    <li class="star">
                        <span>
                            <i class="iconfont icon-star"></i>
                            <i class="iconfont icon-star"></i>
                            <i class="iconfont icon-star"></i>
                            <i class="iconfont icon-star"></i>
                            <i class="iconfont icon-star"></i>
                        </span>
                    </li>
                </ul>
                <div class="text">
                    <div class="comment-text">
                        ${comment.text}
                    </div>
                    <ul class="comment-img">
                    </ul>
                    <div class="comment-time">${comment.time}</div>
                </div>
            </div>`;
                var stars = li.querySelectorAll(".star i");
                setStar(stars,comment.star);
                var commentImg = li.querySelector(".comment-img");
                for(var j = 0;j < imgs.length;j++){
                    var url = imgs[j].url;
                    var imgLi = document.createElement("li");
                    var img = document.createElement("img");
                    img.src = url;
                    imgLi.appendChild(img);
                    commentImg.appendChild(imgLi);
                }
                commnetList.appendChild(li);
            }
        }
    });
    current++;
}
getStar();
getComment(1);

comment.querySelector(".btn-comment-more").onclick = function(){
    getStar();
    getComment(current);
};

