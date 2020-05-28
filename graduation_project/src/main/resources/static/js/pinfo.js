var info = document.querySelector(".info");
var order = document.querySelector(".order");
var collection = document.querySelector(".collection");
var address = document.querySelector(".address");
var dialog = document.querySelector(".dialog");
var orderDetail = document.querySelector(".orderDetail");

show("info");

function showCommonDialog(text){
    dialog.setAttribute("style","display:block;");
    dialog.querySelector(".dialog-text").innerHTML = text;
    setTimeout(function(){
        dialog.setAttribute("style","display:none;");
    },3000);
}

function close(){
    info.setAttribute("style","display:none;");
    order.setAttribute("style","display:none;");
    collection.setAttribute("style","display:none;");
    address.setAttribute("style","display:none;");
    orderDetail.setAttribute("style","display:none;");
}


function initInfo(user){
    info.querySelector(".head-img-div img").src = user.icon;
    info.querySelector(".username").innerHTML = user.username;
    info.querySelector("input[name='phone']").value = user.phone;
    info.querySelector("input[name='name']").value = user.name;
    var sex = user.sex;
    var boy = info.querySelector("input[value='1']");
    var girl = info.querySelector("input[value='2']");
    sex == 1?boy.checked = true:girl.checked = true;
    info.querySelector("input[name='p-note']").value = user.note;
}

var collection_ul = document.querySelector(".collection-box ul");
function initCollection(collects){
    collection_ul.innerHTML = "";
    for(var i = 0;i < collects.length;i++){
        let li = document.createElement("li");
        let collect = collects[i];
        let keyAttribute = collect.product.keyAttribute;
        let a = document.createElement("a");
        a.href = "/html/detail.html?id="+keyAttribute.id;
        let div1 = document.createElement("div");
        div1.className = "collection-product";
        let div2 = document.createElement("div");
        div2.className = "collection-product-name";
        div2.innerHTML = keyAttribute.name;
        let span = document.createElement("span");
        span.className = "collection-delete";
        let iconFont = document.createElement("i");
        iconFont.className = "iconfont icon-delete";
        let img = document.createElement("img");
        img.src = keyAttribute.img.url;
        li.appendChild(div1);
        div1.appendChild(span);
        span.appendChild(iconFont);
        div1.appendChild(a);
        a.appendChild(img);
        div1.appendChild(div2);
        iconFont.onclick = function(){
            sendAjax("DELETE","/collect/"+collect.id,null,function(){
                if(arguments[0]){
                    var collects = JSON.parse(arguments[0]);
                    showCommonDialog("已移出收藏");
                    initCollection(collects);
                }
            });
        }
        collection_ul.appendChild(li);
    }
}

var addressList = address.querySelector(".exist-address-box-body");
var spans = address.querySelectorAll(".span1 span");
function initAddress(addresses){
    addressList.innerHTML = "";
    spans[0].innerHTML = addresses.length;
    spans[1].innerHTML = 10 - addresses.length;
    for(var i = 0;i < addresses.length;i++){
        var address = addresses[i];
        var ul = document.createElement("ul");
        ul.innerHTML = `<li class="li1">
            ${address.name}
        </li>
        <li class="li2">
            ${address.province.name+address.city.name+address.county.name+address.detail}
        </li>
        <li class="li3">
            ${address.phone}
        </li>
        <li class="li4">    
            <div class="li4-dd">
                <a href="javascript:updateAddress(${address.id});">修改</a>
                <a href="javascript:deleteAddress(${address.id});" class="delete">删除</a>
            </div>
            <a href="javascript:setDefault(${address.id})" class="default">设为默认</a>
        </li>`; 
        if(address.isDefault === 1){
            ul.setAttribute("style","border: 1px solid #00c3f5;");
            var a = ul.querySelector(".default");
            a.setAttribute("style","visibility:visible");
            a.innerHTML = "默认地址";
            a.href = "javascript:;";
        }
        addressList.appendChild(ul);
    }
}

var imgs = [];
sendAjax("GET","/imgs",null,function(){
    if(arguments[0]){
        var images = JSON.parse(arguments[0]);
        for(var i = 0;i < images.length;i++){
            imgs.push(images[i]);
        }
    }
});

function getImg(key,color){
    if(color){
        for(var i = 0;i < imgs.length;i++){
            var img = imgs[i];
            if(img.color){
                if(key == img.key_attribute_id && color == img.color.id){
                    return img;
                }
            }
        }
    }else{
        for(var i = 0;i < imgs.length;i++){
            var img = imgs[i];
            if(key == img.key_attribute_id){
                return img;
            }
        }
    }
}



var orderBody = order.querySelector(".order-body>ul");
function initOrder(orders){
    orderBody.innerHTML = "";
    for(var i = 0;i < orders.length;i++){
        let order = orders[i];
        let orderProducts = order.products;
        let table = document.createElement("table");
        table.innerHTML = `<thead>
            <tr class="table-title">
                <td class="td1">
                    <span class="span1">下单时间：</span><span class="order-time">${order.createTime}</span>
                    <span class="span2">订单号：</span><span class="order-code">${order.orderCode}</span>
                    <i class="iconfont icon-service"></i>&nbsp;<span class="span3">联系客服</span>
                </td>
                <td class="td2"></td>
                <td class="td3"></td><td class="td4"></td>
            </tr>
        </thead>`
        let tbody = document.createElement("tbody");
        table.appendChild(tbody);
        var totalPrice = parseFloat(order.postPrice);
        for(var j = 0;j < orderProducts.length;j++){
            let orderProduct = orderProducts[j];
            let product = orderProduct.product;
            let key = product.keyAttribute;
            let ka_id = key.id;
            let keyValues = product.keyValues;
            let price = parseFloat(product.price).toFixed(2);
            let number = parseInt(orderProduct.buyNumber);
            totalPrice += parseFloat(product.price)*number;
            let attributes = "";
            for(var k = 0;k < keyValues.length;k++){
                var keyValue = keyValues[k].keyValue;
                attributes += keyValue.attributeValue.value+" ";
            }
            let generalInfo = key.name+"&nbsp;"+attributes+"&nbsp;"+(product.color?(product.color.name+"&nbsp;"):"")+
            "￥"+price+" x "+number;
            let img = product.color?getImg(ka_id,product.color.id):getImg(ka_id);
            let tr = document.createElement("tr");
            if(j == 0){
                tr.className = "first-tr";
                tr.innerHTML = `<td  class="td1 clearfix" colspan="2">
                <img src="${img.url}" alt="">
                <span>${generalInfo}</span>
            </td>
            <td rowspan="${orderProducts.length}">
                <span class="order-price"></span>
            </td>
            <td rowspan="${orderProducts.length}">
                <span class="order-state">${order.state}</span>
            </td>
            <td class="order-operation" rowspan="${orderProducts.length}">
                <ul>
                    <li>
                        <a href="/html/pay.html?sn=${order.orderCode}">立即付款</a>
                    </li>
                    <li>
                        <a href="javascript:cancelOrder(${order.id});">取消订单</a>
                    </li>
                    <li>
                        <a href="javascript:showOrderDetail('${order.orderCode}');">查看详情</a>
                    </li>
                </ul>
            </td>`;
            }else{
                tr.innerHTML = `<td class="td1 clearfix" colspan="2">
                <img src="${img.url}" alt="">
                <span>${generalInfo}</span>
            </td>`;
            }
            tbody.appendChild(tr);
            if(j == orderProducts.length - 1){
                tbody.querySelector(".first-tr .order-price").innerHTML = totalPrice.toFixed(2);
            }
        }
        orderBody.appendChild(table);
    }
}


function show(name){
    close();
    if("info" === name){
        info.setAttribute("style","display:block;");
        sendAjax("GET","/user",null,function(){
            if(arguments[0]){
                var user = JSON.parse(arguments[0]);
                initInfo(user);
            }
        });
    }
    if("order" === name){
        order.setAttribute("style","display:block;");
        sendAjax("GET","/orders/user",null,function(){
            if(arguments[0]){
                var orders = JSON.parse(arguments[0]);
                initOrder(orders);
            }
        });
    }
    if("collection" === name){
        collection.setAttribute("style","display:block;");
        sendAjax("GET","/collects",null,function(){
            if(arguments[0]){
                var collects = JSON.parse(arguments[0]);
                initCollection(collects);
            }
        });
    }
    if("address" === name){
        address.setAttribute("style","display:block;");
        sendAjax("GET","/addresses/user",null,function(){
            if(arguments[0]){
                var addresses = JSON.parse(arguments[0]);
                initAddress(addresses);
            }
        });
    }
}

var file;
var fileInput = info.querySelector("input[name='file']");
info.querySelector(".btn-change-head").onclick = function(){
    fileInput.click();
    fileInput.onchange = function(){
        file = this.files[0];
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(){
            info.querySelector(".head-img-div img").src = this.result;
        }
    }
};

info.querySelector(".btn-change-detail").onclick = function(){
    var boxs = info.querySelectorAll(".info-box input");
    for(var i = 0;i < boxs.length;i++){
        boxs[i].removeAttribute("disabled"); 
    }
};

info.querySelector(".changePs a").onclick = function(){
    this.setAttribute("style","display:none;");
    document.querySelector(".info .change-password").setAttribute("style","display:block;");
};

info.querySelector(".info-btn-submit").onclick = function(){
    var name = info.querySelector("input[name='name']").value;
    var boy = info.querySelector("input[value='1']");
    var sex = boy.checked?1:2;
    var note = info.querySelector("input[name='p-note']").value;
    if(file){
        var data = new FormData();
        data.append("file",file);
        sendAjax("POST","/api/file",data,function(){
            if(arguments[0]){
                var iconName = arguments[0];
                var user = {
                    name:name,
                    sex:sex,
                    note:note,
                    icon:iconName
                };
                sendAjax("PUT","/user",user,function(){
                    if(arguments[0]){
                        var user = JSON.parse(arguments[0]);
                        initInfo(user);
                        document.querySelector(".user-title-tag").setAttribute("style",'background: url("'+user.icon+'") center no-repeat;background-size:100%;');
                        showCommonDialog("保存成功");
                        var boxs = info.querySelectorAll(".info-box input");
                        for(var i = 0;i < boxs.length;i++){
                            boxs[i].disabled = "true"; 
                        }
                    }
                },true,function(){
                    showCommonDialog("保存失败"+arguments[0]);
                });
            }
        });
    }else{
        var user = {
            name:name,
            sex:sex,
            note:note
        }
        sendAjax("PUT","/user",user,function(){
            if(arguments[0]){
                var user = JSON.parse(arguments[0]);
                initInfo(user);
                showCommonDialog("保存成功");
                var boxs = info.querySelectorAll(".info-box input");
                for(var i = 0;i < boxs.length;i++){
                    boxs[i].disabled = "true"; 
                }
            }
        },true,function(){
            if(arguments[0]){
                showCommonDialog(arguments[0]);
            }
        });
    }
};

dialog.querySelector(".icon-cancel").onclick = function(){
    dialog.setAttribute("style","display:none;");
};

document.querySelector(".info-btn-changePs").onclick = function(){
    var newPassword = info.querySelector("input[name='password']").value;
    if(!checkPassword(newPassword)){
        showCommonDialog("密码格式不正确");
    }else{
        var data = new FormData();
        data.append("newPassword",newPassword);
        sendAjax("PUT","/user/password",data,function(){
            showCommonDialog("更新成功");
            info.querySelector(".changePs a").setAttribute("style","display:inline-block;");
            document.querySelector(".info .change-password").setAttribute("style","display:none;");
        },false,function(){
            showCommonDialog("更新失败"+arguments[0]);
        });
    }
};

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
    city.setAttribute("data-id","");
    countyInput.value = "";
    county.setAttribute("data-id","");
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
        county.setAttribute("data-id","");
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

document.querySelector(".box").onclick = function(event){
    var target = event.target;
    if("area-list" === target.className){
        this.setAttribute("style","display:block;");
        return;
    }
    if("area-input-province" === target.className){
        return;
    }
    if("area-input-city" === target.className){
        return;
    }
    if("area-input-county" === target.className){
        return;
    }
    provinceList.setAttribute("style","display:none;");
    cityList.setAttribute("style","display:none;");
    countyList.setAttribute("style","display:none;");
}

var receiverName = address.querySelector("input[name='receiver-name']");
var receiverPhone = address.querySelector("input[name='receiver-phone']");
var detailAddress = address.querySelector("input[name='detail-address']");
var isDefaultInput = address.querySelector("input[name='isDefault']");

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
address.querySelector(".btn-save-address").onclick = function(){
    var name = receiverName.value;
    var phone = receiverPhone.value;
    var province_id = province.getAttribute("data-id");
    var city_id = city.getAttribute("data-id");
    var county_id = county.getAttribute("data-id");
    var detail = detailAddress.value;
    var isDefault = isDefaultInput.checked?1:0;
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
    data.append("isDefault",isDefault);
    data.append("province_id",province_id);
    data.append("city_id",city_id);
    data.append("county_id",county_id);
    if(addressBox.getAttribute("data-id")){
        var id = addressBox.getAttribute("data-id");
        data.append("id",id);
        sendAjax("PUT","/address",data,function(){
            if(arguments[0]){
                var addresses = JSON.parse(arguments[0]);
                showCommonDialog("修改成功");
                initAddress(addresses);
            }
        },false,function(){
            showCommonDialog("修改失败");
        });
    }else{
        sendAjax("POST","/address",data,function(){
            if(arguments[0]){
                var addresses = JSON.parse(arguments[0]);
                showCommonDialog("保存成功");
                initAddress(addresses);
            } 
        },false,function(){
            showCommonDialog("保存失败");
        });
    }
};


function updateAddress(id){
    if(id){
        sendAjax("GET","/address/"+id,null,function(){
            if(arguments[0]){
                var address = JSON.parse(arguments[0]);
                receiverName.value = address.name;
                receiverPhone.value = address.phone;
                detailAddress.value = address.detail;
                if(address.isDefault){
                    isDefaultInput.checked = true;
                }
                provinceInput.value = address.province.name;
                province.setAttribute("data-id",address.province.id);
                cityInput.value = address.city.name;
                city.setAttribute("data-id",address.city.id);
                countyInput.value = address.county.name;
                county.setAttribute("data-id",address.county.id);
                addressBox.setAttribute("data-id",address.id);
            }
        });
    }
}

function deleteAddress(id){
    if(id){
        sendAjax("DELETE","/address/"+id,null,function(){
            if(arguments[0]){
                var addresses = JSON.parse(arguments[0]);
                initAddress(addresses);
                showCommonDialog("删除成功");
            }
        },false,function(){
            showCommonDialog("删除失败");
        });
    }
}

function setDefault(id){
    if(id){
        sendAjax("PUT","/address/"+id,null,function(){
            if(arguments[0]){
                var addresses = JSON.parse(arguments[0]);
                initAddress(addresses);
            }
        });
    }
}

function cancelOrder(id){
    if(id){
        sendAjax("DELETE","/order/"+id,null,function(){
            if(arguments[0]){
                var orders = JSON.parse(arguments[0]);
                initOrder(orders);
            }
        });
    }
}


var orderDetailBody = orderDetail.querySelector("tbody");
var orderDetailLastTr = orderDetail.querySelector(".order-last");
var orderDetailCircles = orderDetail.querySelectorAll(".progress .circle");
var stateP1 = orderDetail.querySelector(".state .p1");
var stateP2 = orderDetail.querySelector(".state .p2");
var covering = orderDetail.querySelector(".progress-covering");

function changeCircle(state){
    switch(state){
        case "待付款":
            orderDetailCircles[0].setAttribute("style","background: #00cbf9;");
            covering.setAttribute("style","width:0%;");
            stateP1.innerHTML = "订单状态：等待买家付款（请尽快付款）";
            stateP2.innerHTML = "24小时后订单会自动取消";
            break;
        case "待发货":
            for(var i = 0;i < 2;i++){
                orderDetailCircles[i].setAttribute("style","background: #00cbf9;");
            }
            covering.setAttribute("style","width:34%;");
            stateP1.innerHTML = "订单状态：等待卖家发货";
            stateP2.innerHTML = "请及时注意物流消息";
            break;
        case "已发货":
            for(var i = 0;i < 3;i++){
                orderDetailCircles[i].setAttribute("style","background: #00cbf9;");
            }
            covering.setAttribute("style","width:67%;");
            stateP1.innerHTML = "订单状态：卖家已经发货";
            stateP2.innerHTML = "请及时注意物流消息";
            break;
        case "其他":
            for(var i = 0;i < 4;i++){
                orderDetailCircles[i].setAttribute("style","background: #00cbf9;");
            }
            covering.setAttribute("style","width:100%;");
            stateP1.innerHTML = "订单状态：订单已经完成";
            stateP2.innerHTML = "评论赢好礼";
            break;
    }
}
function showOrderDetail(orderCode){
    if(orderCode){
        close();
        orderDetail.setAttribute("style","display:block;");
        sendAjax("GET","/order/"+orderCode,null,function(){
            if(arguments[0]){
                var order = JSON.parse(arguments[0]);
                var orderProducts = order.products;
                orderDetail.querySelector(".order-time").innerHTML = order.createTime;
                var state = order.state;
                orderDetail.querySelector(".order-code").innerHTML = order.orderCode;
                changeCircle(state);
                var postPrice = order.postPrice;
                for(var i = 0;i < orderProducts.length;i++){
                    let orderProduct = orderProducts[i];
                    let product = orderProduct.product;
                    let key = product.keyAttribute;
                    let keyValues = product.keyValues;
                    let price = product.price;
                    let number = orderProduct.buyNumber;
                    let attributes = "";
                    for(var j = 0;j < keyValues.length;j++){
                        var keyValue = keyValues[j].keyValue;
                        attributes += keyValue.attributeValue.value+" ";
                    }
                    let tr = document.createElement("tr");
                    let img = product.color?getImg(key.id,product.color.id):getImg(key.id);
                    tr.innerHTML = `<tr>
                    <td class="td1">
                        <img src="${img.url}" alt="">
                        <div>
                            <p class="p1">${key.name}</p>
                            <p class="p2">${attributes}</p>
                        </div>
                    </td>
                    <td>
                        <span>￥<span class="span1">${price.toFixed(2)}</span></span>
                    </td>
                    <td>
                        <span>${number}</span>
                    </td>
                    <td>
                        <span class="span2">￥<span class="span3">${(price*number).toFixed(2)}</span></span>
                    </td>   
                    <td class="td5">
                        ${state == "其他"?'<a href="javascript:comment('+key.id+');">发表评论</a>':"--"}
                    </td>
                </tr>`;
                orderDetailBody.insertBefore(tr,orderDetailLastTr);
                }
            }
        });
        sendAjax("GET","/order/totalPrice?orderCode="+orderCode,null,function(){
            if(arguments[0]){
                var priceVo = JSON.parse(arguments[0]);
                orderDetail.querySelector(".order-total .div1 .box3-right span").innerHTML = parseFloat(priceVo.productsPrice).toFixed(2);
                orderDetail.querySelector(".order-total .div2 .box3-right span").innerHTML = parseFloat(priceVo.postPrice).toFixed(2);
                orderDetail.querySelector(".order-total .div3 .span1").innerHTML = parseFloat(priceVo.totalPrice).toFixed(2);
            }
        });
    }
}


function comment(keyId){
    opsCommentEdit(true);
    orderDetail.querySelector(".edit-comment button").setAttribute("data-key",keyId);
}

orderDetail.querySelector(".back .icon-cancel").onclick = function(){
    opsCommentEdit(false);
};

var commentImgInput = orderDetail.querySelector(".edit-comment input[name='file']");
var commentImgBox = orderDetail.querySelector(".edit-comment .edit-comment-img");
var files = [];
var imgCount = 0;
orderDetail.querySelector(".edit-comment .icon-Increase").onclick = function(){
    if(imgCount >= 7){
        showCommonDialog("最多只能插入七张图片");
        return;
    }
    commentImgInput.click();
    commentImgInput.onchange = function(){
        var file = this.files[0];
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(){
            var li = document.createElement("li");
            var img = document.createElement("img");
            img.src = this.result;
            li.appendChild(img);
            files.push(file);
            commentImgBox.appendChild(li);
            imgCount++;
        }
    }
};

var stars = orderDetail.querySelectorAll(".edit-comment .icon-star");
for(var i = 0;i < stars.length;i++){
    var star = stars[i];
    star.setAttribute("data-index",i);
}
for(var i = 0;i < stars.length;i++){
    stars[i].onclick = function(){
        var starList = this.parentNode;
        var numberSpan = this.parentNode.parentNode.querySelector(".number");
        var thisIndex = this.getAttribute("data-index");
        var subStars = starList.querySelectorAll(".icon-star");
        var firstIndex = subStars[0].getAttribute("data-index");
        for(var k = 0;k < subStars.length;k++){
            subStars[k].setAttribute("style","color: #bbbbbb;");
        }
        for(var j = 0;j < subStars.length;j++){
            var subStar = subStars[j];
            if(parseInt(thisIndex,10) >= parseInt(subStar.getAttribute("data-index"),10)){
                numberSpan.innerHTML = parseInt(thisIndex,10) - parseInt(firstIndex,10) + 1;
                subStar.setAttribute("style","color: #ffb643;");
            }
        }
    }
}


orderDetail.querySelector(".edit-comment button").onclick = function(){
    var keyId = this.getAttribute("data-key");
    if(files.length > 0){
        var data = new FormData();
        for(var i = 0;i < files.length;i++){
            data.append("files",files[i]);
        }
        sendAjax("POST","/api/files",data,function(){
            if(arguments[0]){
                var names = JSON.parse(arguments[0]);
                var data2 = new FormData();
                var numbers = orderDetail.querySelectorAll(".back .all-comment-right .number");
                var text = orderDetail.querySelector(".back .edit-text-area").innerHTML;
                var text1 = text.replace(/</g,"&lt;").replace(/>/g,"&gt;");
                data2.append("general",numbers[0].innerHTML);
                data2.append("fit",numbers[1].innerHTML);
                data2.append("sellerService",numbers[2].innerHTML);
                data2.append("sellerSpeed",numbers[3].innerHTML);
                data2.append("ka_id",keyId);
                data2.append("urls",names);
                data2.append("text",text1);
                sendAjax("POST","/comment",data2,function(){
                    if(arguments[0]){
                        opsCommentEdit(false);
                        showCommonDialog("评论成功");
                    }
                });
            }
        });
    }else{
        var data = new FormData();
        var numbers = orderDetail.querySelectorAll(".back .all-comment-right .number");
        var text = orderDetail.querySelector(".back .edit-text-area").innerHTML;
        var text1 = text.replace(/</g,"&lt;").replace(/>/g,"&gt;");
        data.append("general",numbers[0]);
        data.append("fit",numbers[1]);
        data.append("sellerService",numbers[2]);
        data.append("sellerSpeed",numbers[3]);
        data.append("ka_id",keyId);
        data.append("text",text1);
        sendAjax("POST","/comment",data2,function(){
            if(arguments[0]){
                opsCommentEdit(false);
                showCommonDialog("评论成功");
            }
        });
    }
};

function opsCommentEdit(isopen){
    if(isopen){
        orderDetail.querySelector(".back").setAttribute("style","display:block;");
        orderDetail.querySelector(".edit-comment").setAttribute("style","display:block;");
        imgCount = 0;
    }else{
        var stars = orderDetail.querySelectorAll(".back .star i");
        for(var i = 0;i < stars.length;i++){
            stars[i].setAttribute("style","color: #ffb643;");
        }
        orderDetail.querySelector(".edit-comment .edit-text-area").innerHTML = "";
        orderDetail.querySelector(".edit-comment .edit-comment-img").innerHTML = "";
        orderDetail.querySelector(".back").setAttribute("style","display:none;");
        orderDetail.querySelector(".edit-comment").setAttribute("style","display:none;");
        imgCount = 0;
    }
}


var orderTitles = order.querySelectorAll(".order-title li");
for(let i = 0;i < orderTitles.length;i++){
    let title = orderTitles[i];
    if(i === 0){
        title.onclick = function(){
            for(var j = 0;j < orderTitles.length;j++){
                if(j == orderTitles.length - 1){
                    orderTitles[j].className = "last";
                }else{
                    orderTitles[j].className = "";
                }
            }
            this.className += " selected";
            sendAjax("GET","/orders/user",null,function(){
                if(arguments[0]){
                    var orders = JSON.parse(arguments[0]);
                    initOrder(orders);
                }
            });
        }
    }else{
        title.onclick = function(){
            for(var j = 0;j < orderTitles.length;j++){
                if(j == orderTitles.length - 1){
                    orderTitles[j].className = "last";
                }else{
                    orderTitles[j].className = "";
                }
            }
            this.className += " selected";
            sendAjax("GET","/orders/state/"+i,null,function(){
                if(arguments[0]){
                    var orders = JSON.parse(arguments[0]);
                    initOrder(orders);
                }
            });
        }
    }
}
