var user;
var url = window.location.search;
var orderCode;
if(url.indexOf("=")){
    orderCode = url.substring(url.lastIndexOf("=")+1,url.length);
}


var receiver_ul = document.querySelector(".receiver-info ul");
function initAddress(addresses){
    if(addresses){
        var lis = document.querySelector(".receiver-info ul").childNodes;
        if(lis.length > 1){
            for(var j = lis.length - 2;j >= 0;j--){
                receiver_ul.removeChild(lis[j]);
            }
        }
        for(var i = 0;i < addresses.length;i++){
            var address = addresses[i];
            var li = document.createElement("li");
            var general = address.province.name+address.city?address.city.name:""+address.county?address.county.name:"";
            if(address.isDefault){
                li.className = "selected";
            }
            li.setAttribute("data-id",address.id);
            li.innerHTML = `<div class="address">
            <div class="front clearfix">
                <span class="receiver-name">${address.name}</span>
                <span class="receiver-phone">${address.phone}</span>
            </div>
            <div class="behind">
                <p class="detail-address">${general+address.detail}</p>
            </div>
            <div class="myicon">
                <i class="iconfont icon-icon_correct"></i>
            </div>
            </div>`;
            receiver_ul.insertBefore(li,document.querySelector(".receiver-info .last"));
        }
        var receiverDivs = receiver_ul.querySelectorAll(".address");
        for(var k = 0;k < receiverDivs.length;k++){
            receiverDivs[k].onclick = function(){
                for(var j = 0;j < receiverDivs.length;j++){
                    receiverDivs[j].parentNode.className = "";
                }
                this.parentNode.className = "selected";
            }
        }
    }
}
sendAjax("GET","/addresses/user",null,function(){
    if(arguments[0]){
        var addresses = JSON.parse(arguments[0]);
        initAddress(addresses);
    }
});

var back = document.querySelector(".back");
var addAddress = document.querySelector(".add-address-input");
document.querySelector(".receiver-info .last").onclick = function(){
    back.setAttribute("style","display:block;");
    addAddress.setAttribute("style","display:block;");
};

document.querySelector(".back .icon-cancel").onclick = function(){
    var inputs = document.querySelectorAll(".add-address-box input");
    for(var i = 0;i < inputs.length;i++){
        inputs[i].value = "";
    }
    var areaLis = document.querySelectorAll(".area>ul>li");
    for(var j = 0;j < areaLis.length;j++){
        areaLis[j].setAttribute("data-id","");
    }
    back.setAttribute("style","display:none;");
    addAddress.setAttribute("style","display:none;");
}

var tbody = document.querySelector(".order-info tbody");
var orderLast = document.querySelector(".order-last");
var box3 = document.querySelector(".box3");
if(orderCode){
    sendAjax("GET","/order/"+orderCode,null,function(){
        if(arguments[0]){
            var order = JSON.parse(arguments[0]);
            var totalPrice = 0;
            for(var i = 0;i < order.products.length;i++){
                let index = i;
                let orderProduct = order.products[i];
                let product = orderProduct.product;
                let tr = document.createElement("tr");
                let key = product.keyAttribute;
                let ka_id = key.id;
                let uri = product.color?"/img/color/first?ka_id="+ka_id+"&color_id="+product.color.id:"/img?ka_id="+ka_id;
                let keyValues = product.keyValues;
                let price = parseFloat(product.price).toFixed(2);
                let number = parseInt(orderProduct.buyNumber);
                let postPrice = parseFloat(order.postPrice);
                let attributes = "";
                for(var j = 0;j < keyValues.length;j++){
                    var keyValue = keyValues[j].keyValue;
                    attributes += keyValue.attributeValue.value+" ";
                }
                sendAjax("GET",uri,null,function(){
                    var img = JSON.parse(arguments[0]);
                    if(index == 0){
                        tr.innerHTML = `<td class="td1">
                            <img src="${img.url}" alt="">
                            <div>
                                <p class="p1">${key.name}</p>
                                <p class="p2">${attributes}</p>
                            </div>
                        </td>
                        <td>
                            <span>￥<span class="span1">${price}</span></span>
                        </td>
                        <td>
                            <span>${number}</span>
                        </td>
                        <td>
                            <span class="span2">￥<span class="span3">${(price * number).toFixed(2)}</span></span>
                        </td>   
                        <td class="td5" rowspan="${order.products.length}">
                            <span>快递配送：运费<span class="span1">￥<span class="span2">${postPrice.toFixed(2)}</span></span></span>
                        </td>`;
                    }else{
                        tr.innerHTML = `<td class="td1">
                        <img src="${img.url}" alt="">
                        <div>
                            <p class="p1">${key.name}</p>
                            <p class="p2">${attributes}</p>
                        </div>
                    </td>
                    <td>
                        <span>￥<span class="span1">${price}</span></span>
                    </td>
                    <td>
                        <span>${number}</span>
                    </td>
                    <td>
                        <span class="span2">￥<span class="span3">${(parseFloat(product.price) * number).toFixed(2)}</span></span>
                    </td>`;
                    }
                    tbody.insertBefore(tr,orderLast);
                    totalPrice += (parseFloat(product.price) * number); 
                    orderLast.querySelector(".span2").innerHTML = (postPrice+totalPrice).toFixed(2);
                    box3.querySelector(".div1 .box3-right>span").innerHTML = (postPrice + totalPrice).toFixed(2);
                    box3.querySelector(".div2 .box3-right>span").innerHTML = postPrice.toFixed(2);
                    box3.querySelector(".div3 .span1").innerHTML = (postPrice + totalPrice).toFixed(2);
                });
            }
        }
    });
}else{
    sendAjax("GET","/order",null,function(){
        if(arguments[0]){
            var order = JSON.parse(arguments[0]);
            var tr = document.createElement("tr");
            var orderProduct = order.products[0];
            var product = orderProduct.product;
            var key = product.keyAttribute;
            var ka_id = key.id;
            var uri = product.color?"/img/color/first?ka_id="+ka_id+"&color_id="+product.color.id:"/img?ka_id="+ka_id;
            var keyValues = product.keyValues;
            var price = parseFloat(product.price).toFixed(2);
            var number = parseInt(orderProduct.buyNumber);
            var postPrice = parseFloat(order.postPrice);
            var attributes = "";
            for(var i = 0;i < keyValues.length;i++){
                var keyValue = keyValues[i].keyValue;
                attributes += keyValue.attributeValue.value+" ";
            }
            sendAjax("GET",uri,null,function(){
                var img = JSON.parse(arguments[0]);
                tr.innerHTML = `<td class="td1">
                    <img src="${img.url}" alt="">
                    <div>
                        <p class="p1">${key.name}</p>
                        <p class="p2">${attributes}</p>
                    </div>
                </td>
                <td>
                    <span>￥<span class="span1">${price}</span></span>
                </td>
                <td>
                    <span>${number}</span>
                </td>
                <td>
                    <span class="span2">￥<span class="span3">${(price * number).toFixed(2)}</span></span>
                </td>   
                <td class="td5" rowspan="1">
                    <span>快递配送：运费<span class="span1">￥<span class="span2">${postPrice.toFixed(2)}</span></span></span>
                </td>`;
                tbody.insertBefore(tr,orderLast);
                orderLast.querySelector(".span2").innerHTML = (postPrice + price * number).toFixed(2);
                box3.querySelector(".div1 .box3-right>span").innerHTML = (postPrice + price * number).toFixed(2);
                box3.querySelector(".div2 .box3-right>span").innerHTML = postPrice.toFixed(2);
                box3.querySelector(".div3 .span1").innerHTML = (postPrice + price * number).toFixed(2);
            });
        }
    });
}


var address = document.querySelector(".back .add-address-input");
var province = address.querySelector(".area-province");
var city = address.querySelector(".area-city");
var county = address.querySelector(".area-county");
var provinceInput = province.querySelector("input");
var cityInput = city.querySelector("input");
var countyInput = county.querySelector("input");

var provinceList = province.querySelector(".area-list");
provinceInput.onclick = function(){
    provinceList.querySelector("ul").innerHTML = "";
    sendAjax("GET","/area/province",null,function(){
        if(arguments[0]){
            var provinces = JSON.parse(arguments[0]);
            for(var i = 0;i < provinces.length;i++){
                var p = provinces[i];
                var li = document.createElement("li");
                var div = document.createElement("div");
                li.appendChild(div);
                div.innerHTML = p.name;
                div.setAttribute("data-id",p.id);
                provinceList.querySelector("ul").appendChild(li);
                div.onclick = function(){
                    province.setAttribute("data-id",this.getAttribute("data-id"));
                    provinceInput.value = this.innerHTML;
                    provinceList.setAttribute("style","display:none;");
                }
            }
        }
    });
    cityInput.value = "";
    city.setAttribute("data-id",null);
    countyInput.value = "";
    county.setAttribute("data-id",null);
    provinceList.setAttribute("style","display:block;");
}


var cityList = city.querySelector(".area-list");
cityInput.onclick = function(){
    var province_id = province.getAttribute("data-id");
    if(province_id){
        cityList.querySelector("ul").innerHTML = "";
        sendAjax("GET","/area/city/"+province_id,null,function(){
            if(arguments[0]){
                var citys = JSON.parse(arguments[0]);
                for(var i = 0;i < citys.length;i++){
                    var c = citys[i];
                    var li = document.createElement("li");
                    var div = document.createElement("div");
                    li.appendChild(div);
                    div.innerHTML = c.name;
                    div.setAttribute("data-id",c.id);
                    cityList.querySelector("ul").appendChild(li);
                    div.onclick = function(){
                        city.setAttribute("data-id",this.getAttribute("data-id"));
                        cityInput.value = this.innerHTML;
                        cityList.setAttribute("style","display:none;");
                    }
                }
            }
        });
        countyInput.value = "";
        county.setAttribute("data-id",null);
        cityList.setAttribute("style","display:block;");
    }
};

var countyList = county.querySelector(".area-list");
countyInput.onclick = function(){
    var city_id = city.getAttribute("data-id");
    if(city_id){
        countyList.querySelector("ul").innerHTML = "";
        sendAjax("GET","/area/county/"+city_id,null,function(){
            if(arguments[0]){
                var countys = JSON.parse(arguments[0]);
                for(var i = 0;i < countys.length;i++){
                    var c = countys[i];
                    var li = document.createElement("li");
                    var div = document.createElement("div");
                    li.appendChild(div);
                    div.innerHTML = c.name;
                    div.setAttribute("data-id",c.id);
                    countyList.querySelector("ul").appendChild(li);
                    div.onclick = function(){
                        county.setAttribute("data-id",this.getAttribute("data-id"));
                        countyInput.value = this.innerHTML;
                        countyList.setAttribute("style","display:none;");
                    }
                }
            }
        });
        countyList.setAttribute("style","display:block;");
    }
};


var receiverName = address.querySelector("input[name='receiver-name']");
var receiverPhone = address.querySelector("input[name='receiver-phone']");
var detailAddress = address.querySelector("input[name='detail-address']");

receiverName.onclick = function(){
    receiverName.setAttribute("style","border:1px solid #e0e0e0");
}
receiverPhone.onclick = function(){
    receiverPhone.setAttribute("style","border:1px solid #e0e0e0");
}
detailAddress.onclick = function(){
    detailAddress.setAttribute("style","border:1px solid #e0e0e0");
}

var addressBox = address.querySelector(".add-address-box");
address.querySelector(".btn-sure").onclick = function(){
    var name = receiverName.value;
    var phone = receiverPhone.value;
    var province_id = province.getAttribute("data-id");
    var city_id = city.getAttribute("data-id");
    var county_id = county.getAttribute("data-id");
    var detail = detailAddress.value;
    if(name.length > 15 || name.length < 1){
        receiverName.setAttribute("style","border:1px solid #ff0000");
        return;
    }
    if(!checkPhone(phone)){
        receiverPhone.setAttribute("style","border:1px solid #ff0000");
        return;
    }
    if(!(province_id && city_id && county_id)){
        provinceInput.setAttribute("style","border:1px solid #ff0000");
        cityInput.setAttribute("style","border:1px solid #ff0000");
        county.setAttribute("style","border:1px solid #ff0000");
        return;
    }
    if(detail.length < 4 || detail.length > 50){
        detailAddress.setAttribute("style","border:1px solid #ff0000");        
        return;
    }
    var data = new FormData();
    data.append("name",name);
    data.append("phone",phone);
    data.append("detail",detail);
    data.append("province_id",province_id);
    data.append("city_id",city_id);
    data.append("county_id",county_id);
    sendAjax("POST","/address",data,function(){
        if(arguments[0]){
            var addresses = JSON.parse(arguments[0]);
            alert("保存成功");
            initAddress(addresses);
            document.querySelector(".back .icon-cancel").click();
        } 
    },false,function(){
        alert("保存失败");
    });
};

document.querySelector(".box3 .btn-pay").onclick = function(){
    sendAjax("PUT","/order/finish",orderCode,function(){
        alert("支付成功");
    });
};
