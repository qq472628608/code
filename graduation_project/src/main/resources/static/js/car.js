var boxBody = document.querySelector(".box-body");
var boxTotal = document.querySelector(".box-total");
function init(){
    boxBody.innerHTML = "";
    sendAjax("GET","/car",null,function(){
        if(arguments[0]){
            var car = JSON.parse(arguments[0]);
            var carProducts = car.carProducts;
            var totalPrice = 0;
            var totalNumber = 0;
            for(var i = 0;i < carProducts.length;i++){
                let carProduct = carProducts[i];
                let product = carProduct.product;
                let keyValues = product.keyValues;
                let ka_id = product.keyAttribute.id;
                let uri = product.color?"/img/color/first?ka_id="+ka_id+"&color_id="+product.color.id:"/img?ka_id="+ka_id;
                let ul = document.createElement("ul");
                ul.setAttribute("data-id",product.id);
                let attributes = "";
                let price = parseFloat(product.price).toFixed(2);
                let number = parseInt(carProduct.buyNumber);
                totalPrice += (price*number);
                totalNumber += number;
                for(var j = 0;j < keyValues.length;j++){
                    var keyValue = keyValues[j].keyValue;
                    attributes += keyValue.attributeValue.value+" ";
                }
                sendAjax("GET",uri,null,function(){
                    if(arguments[0]){
                        var img = JSON.parse(arguments[0]);
                        ul.innerHTML=` <li class="li1">
                        <div class="icon myicon-selected">
                            <i class="iconfont icon-icon_correct"></i>
                        </div>
                    </li>
                    <li class="li2">
                        <a href="/html/detail.html?id=${product.keyAttribute.id}">
                            <img src="${img.url}" alt="">
                            <div class="name">
                                <p class="p1">${product.keyAttribute.name}</p>
                                <p class="p2">${attributes}</p>
                            </div>
                        </a>
                    </li>
                    <li class="li3">
                        <span>￥<span class="singlePrice">${price}</span></span>
                    </li>
                    <li class="li4">
                        <div class="select-number">
                            <button class="btn-number desc">-</button>
                            <input type="text" name="number" value="${number}" readonly="true">
                            <button class="btn-number incr">+</button>
                        </div>
                    </li>
                    <li class="li5">
                        <span>￥<span class="small-total">${(price * number).toFixed(2)}</span></span>
                    </li>
                    <li class="li6">
                        <span class="hidden">--</span>
                        <span><i class="iconfont icon-cancel"></i></span>
                    </li>`;
                        boxBody.appendChild(ul);
                        boxTotal.querySelector(".left .span2").innerHTML = carProducts.length;
                        boxTotal.querySelector(".left .span3").innerHTML = carProducts.length;
                        boxTotal.querySelector(".right .span3").innerHTML = totalPrice.toFixed(2);
                        document.querySelector(".header .shoppingcar em").innerHTML = totalNumber;
                    }
                });
            }
        }
    });
}

init();

document.querySelector(".box").onclick=function(event){
    var target = event.target;
    if("iconfont icon-icon_correct" === target.className || target.className.indexOf("myicon") > -1){
        var icons = document.querySelectorAll(".icon");
        if("i" === target.tagName.toLowerCase()){
            target = target.parentNode;
        }
        if(target.className.indexOf("icon-all") > -1){
            if(target.className.indexOf("myicon-selected") > -1){
                for(var i = 0;i < icons.length;i++){
                    if(icons[i].className.indexOf("icon-all") > -1){
                        icons[i].className = "icon icon-all myicon";
                    }else{
                        icons[i].className = "icon myicon";
                    }
                }
            }else{
                for(var i = 0;i < icons.length;i++){
                    if(icons[i].className.indexOf("icon-all") > -1){
                        icons[i].className = "icon icon-all myicon-selected";
                    }else{
                        icons[i].className = "icon myicon-selected";
                    }
                }
            }
        }else{
            changeIcon(target);
        }
        var selectedNumber = 0;
        var totalPrice = 0;
        var boxIcons = document.querySelectorAll(".box-body .icon");
        for(var i =0;i < boxIcons.length;i++){
            var icon = boxIcons[i];
            if("icon myicon-selected" === icon.className){
                selectedNumber++;
                totalPrice += parseFloat(icon.parentNode.parentNode.querySelector(".small-total").innerHTML);
            }
        }
        document.querySelector(".box-total .left .span3").innerHTML = selectedNumber;
        document.querySelector(".box-total .right .span3").innerHTML = totalPrice.toFixed(2);
    }
}


function changeIcon(icon){
    if(icon.className.indexOf("icon-all") > -1){
        if(icon.className.indexOf("myicon-selected") > -1){
            icon.className = "icon icon-all myicon";
        }else{
            icon.className = "icon icon-all myicon-selected";
        }
    }else{
        if(icon.className.indexOf("myicon-selected") > -1){
            icon.className = "icon myicon";
        }else{
            icon.className = "icon myicon-selected";
        }
    }
}


document.querySelector(".box-title .li6 a").onclick = function(){
    var hiddens = document.querySelectorAll(".box-body .hidden");
    var shows = document.querySelectorAll(".box-body .icon-cancel");
    if(this.className === "off"){
        this.className = "";
        for(var i = 0;i < hiddens.length;i++){
            hiddens[i].setAttribute("style","display:none");
        }
        for(var i = 0;i < shows.length;i++){
            shows[i].setAttribute("style","display:inline");
        }
    }else{
        this.className = "off";
        for(var i = 0;i < hiddens.length;i++){
            hiddens[i].setAttribute("style","display:inline");
        }
        for(var i = 0;i < shows.length;i++){
            shows[i].setAttribute("style","display:none");
        }
    }
};


boxBody.onclick=function(event){
    var target = event.target;
    if(target.className.indexOf("btn-number") > -1){
        var ul = target.parentNode.parentNode.parentNode;
        var number_input = ul.querySelector("input[name='number']");
        if(target.className.indexOf("incr") > -1){
            number_input.value++;
        }
        if(target.className.indexOf("desc") > -1){
            if(number_input.value <= 1){
                return;
            }else{
                number_input.value--;
            }
        }
        var singlePrice = parseFloat(ul.querySelector(".singlePrice").innerHTML);
        ul.querySelector(".small-total").innerHTML = (number_input.value * singlePrice).toFixed(2);
        var smallTotals = boxBody.querySelectorAll(".small-total");
        var totalPrice = 0;
        for(var i = 0;i < smallTotals.length;i++){
            var smallTotal = parseFloat(smallTotals[i].innerHTML);
            totalPrice += smallTotal;
        }
        document.querySelector(".box-total .right .span3").innerHTML = totalPrice.toFixed(2);
        var product_id = ul.getAttribute("data-id");
        var number = number_input.value;
        var dto = {
            product_id:product_id,
            number:number
        }
        sendAjax("PUT","/car",dto,null,true);
    }
    if(target.className === "iconfont icon-cancel"){
        showDialog();
        document.querySelector(".dialog-general .btn-sure").onclick = function(){
            var ul = target.parentNode.parentNode.parentNode;
            var product_id = ul.getAttribute("data-id");
            sendAjax("DELETE","/car/"+product_id,null,function(){
                init();
                //boxBody.removeChild(ul);
            });
            closeDialog();
        };
        document.querySelector(".dialog-general .btn-cancel").onclick = function(){
            closeDialog();
        }
    }
};

document.querySelector(".box-total .btn-calculate").onclick = function(){
    var uls = boxBody.querySelectorAll("ul");
    var ops = [];
    var ids = [];
    for(var i = 0;i < uls.length;i++){
        var ul = uls[i];
        var li1_div = ul.querySelector(".li1>div");
        if(li1_div.className === "icon myicon-selected"){
            var product_id = ul.getAttribute("data-id"); 
            var number = ul.querySelector("input[name='number']").value;
            var op = {
                product_id:product_id,
                number:number
            }
            ids.push(product_id);
            ops.push(op);
        }
    }
    sendAjax("POST","/order",ops,function(){
        if(arguments[0]){
            var data = new FormData();
            var orderCode = arguments[0];
            data.append("ids",ids);
            sendAjax("DELETE","/car",data,function(){
                window.location = "/html/pay.html?code="+orderCode;
            });
        }
    },true);
};
