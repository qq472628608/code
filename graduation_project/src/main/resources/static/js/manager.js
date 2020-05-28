var tag = "Product";
var bars = document.querySelectorAll(".bar");
for(var i = 0;i < bars.length;i++){
    bars[i].onclick=function(){
        for(var i = 0;i < bars.length;i++){
            bars[i].className = "bar";
        }
        this.className = "bar selected";
    }
}

var details = document.querySelectorAll(".detail li");
function detailClick(index){    
    if(details.length){
        for(var i = 0;i < details.length;i++){
            details[i].className = "";
        }
        details[index].className = "selected";
    }else{
        details.className = "selected";
    }
}


sendAjax("GET","/user",null,function(){
    if(arguments[0]){
        var user = JSON.parse(arguments[0]);
        document.querySelector(".right .role span").innerHTML = user.role.name+"："+user.name;
    }
});

var left = document.querySelector(".left");
var right = document.querySelector(".right");
var toolbarContainer = document.querySelector(".toolbar-container");
var selectAll = document.querySelector(".btn-selectAll");
var clear = document.querySelector(".btn-clear");
var searchBtn = document.querySelector(".btn-search");
var batchDelete = document.querySelector(".btn-batchDelete");
var listContainer = document.querySelector(".list-container");
var productTag = left.querySelector(".fa-bars");
productTag.onclick = function(){
    tag = "Product";
    initProduct();
};
left.querySelector(".fa-user-circle-o").onclick = function(){
    tag = "User";
    initUser();
};
left.querySelector(".fa-signal").onclick = function(){
    tag = "Kind";
    initKind();
};
left.querySelector(".fa-sticky-note-o").onclick = function(){
    tag = "Order";
    initOrder();
};
left.querySelector(".fa-sign-out").onclick = function(){
    sendAjax("DELETE","/api/user");
};

productTag.click();

selectAll.onclick = function(){
    var is = listContainer.querySelectorAll(".checkbox i");
    var isHasNotChecked = false;
    for(var i = 0;i < is.length;i++){
        if(is[i].className === "iconfont icon-checkbox"){
            isHasNotChecked = true;
            break;
        }
    }
    if(isHasNotChecked){
        for(var m = 0;m < is.length;m++){
            is[m].className = "iconfont icon-checkbox1";
        }  
    }else{
        for(var m = 0;m < is.length;m++){
            is[m].className = "iconfont icon-checkbox";
        } 
    }
}

function clearInput(){
    var inputs = toolbarContainer.querySelectorAll("input");
    for(var i = 0;i < inputs.length;i++){
        inputs[i].value = "";
    }
}


var page = document.querySelector(".page");
var pageInput = page.querySelector("input[name='page']");
var currentSpan = page.querySelector(".current");
currentSpan.innerHTML = 1;
pageInput.value = 1;

function mySearch(current){
    switch(tag){
        case "Product":searchProduct(current);break;
        case "User":searchUser(current);break;
        case "Kind":searchKind(current);break;
        case "Order":searchOrder(current);break;
    }
}
page.querySelector(".btn-prev").onclick = function(){
    var current = parseInt(currentSpan.innerHTML);
    pageInput.value = pageInput.value - 1 <= 0?1:pageInput.value - 1;
    current = current - 1 <= 0?1:current - 1;
    mySearch(current);
};
page.querySelector(".btn-next").onclick = function(){
    var current = parseInt(currentSpan.innerHTML);
    var totalPage = parseInt(page.querySelector(".totalpage").innerHTML);
    pageInput.value = pageInput.value + 1 >= totalPage?totalPage:pageInput.value + 1;
    current = current + 1 >= totalPage?totalPage:current + 1;
    mySearch(current);
};
page.querySelector(".btn-go").onclick = function(){
    var current = pageInput.value;
    var totalPage = parseInt(page.querySelector(".totalpage").innerHTML);
    if(/[0-9]+/.test(current) && current > 0 && current <= totalPage){
        mySearch(current);
    }
};
pageInput.onkeyup = function(event){
    var code = event.keyCode;
    if(code == 13){
        page.querySelector(".btn-go").click();
    }
}


var roles = [];
sendAjax("GET","/roles",null,function(){
    if(arguments[0]){
        var rs = JSON.parse(arguments[0]);
        for(var i = 0;i < rs.length;i++){
            roles.push(rs[i]);
        }
    }
})

function setUserTable(users){
    var table = document.querySelector(".list-container table");
    table.innerHTML = "";
    var title = document.createElement("tr");
    title.className = "list-title";
    title.innerHTML = `<td></td><td>用户名</td><td>昵称</td><td>头像</td><td>联系号码</td><td>用户级别</td><td>性别</td><td>操作</td>`;
    table.appendChild(title);
    for(var i = 0;i < users.length;i++){
        var user = users[i];
        var role = user.role;
        var tr = document.createElement("tr");
        tr.setAttribute("data-id",user.id);
        tr.innerHTML = `<td class="checkbox"><i class="iconfont icon-checkbox"></i></td><td>${user.username}</td><td class="td-name">${user.name}</td><td><img src="${user.icon}" alt=""></td><td>${user.phone}</td>
        <td class="level level${role.sn}"><span>${role.name}</span><ul data-lock=0 class="pullDown tdPullDown"></ul><div class="trangle"></div></td><td class=${user.sex?"boy":"girl"}><i class="${user.sex?"iconfont icon-zhenhuichongtubiaozhizuo-kuozhan-1":"iconfont icon-zhenhuichongtubiaozhizuo-kuozhan-"}"></i></td>
        <td>
            <button class="btn-delete"><span>删除</span></button>
        </td>`;
        tr.querySelector(".checkbox i").onclick = function(){
            if(this.className === "iconfont icon-checkbox"){
                this.className = "iconfont icon-checkbox1";
            }else{
                this.className = "iconfont icon-checkbox";
            }
        };
        tr.querySelector(".trangle").onclick = function(){
            var tr = this.parentNode.parentNode;
            var pullDown = tr.querySelector(".tdPullDown");
            var lock = pullDown.getAttribute("data-lock");
            if(lock == 1){
                pullDown.setAttribute("style","display:none;");
                pullDown.setAttribute("data-lock",0);
                return;
            }
            pullDown.innerHTML = "";
            var span = tr.querySelector(".level span")
            for(var j = 0;j < roles.length;j++){
                var role = roles[j];
                var li = document.createElement("li");
                li.innerHTML = role.name;
                pullDown.appendChild(li);
                li.onclick = function(){
                    span.innerHTML = this.innerHTML;
                    pullDown.setAttribute("style","display:none;");
                    pullDowns[m].setAttribute("data-lock",0);
                }
            }
            var pullDowns = document.querySelectorAll(".tdPullDown");
            for(var m = 0;m < pullDowns.length;m++){
                pullDowns[m].setAttribute("style","display:none;");
                pullDowns[m].setAttribute("data-lock",0);
            }
            pullDown.setAttribute("style","display:block;");
            pullDown.setAttribute("data-lock",1);
        };
        tr.querySelector(".btn-delete").onclick = function(){
            var id = this.parentNode.parentNode.getAttribute("data-id");
            sendAjax("DELETE","/user/"+id,null,function(){
                searchUser(parseInt(currentSpan.innerHTML));
            });
        };
        table.appendChild(tr);
    }
}

function searchUser(current){
    var usernameInput = toolbarContainer.querySelector("input[name='username']");
    var phoneInput = toolbarContainer.querySelector("input[name='phone']");
    var roleInput = toolbarContainer.querySelector("input[name='role']");
    var sexs = toolbarContainer.querySelectorAll("input[name='sex']");
    var sex = 3;
    for(var i = 0;i < sexs.length;i++){
        if(sexs[i].checked){
            sex = sexs[i].parentNode.querySelector("span").innerHTML == "男"?1:0;
            break;
        }
    }
    var data = new FormData();
    data.append("username",usernameInput.value);
    data.append("phone",phoneInput.value);
    data.append("roleName",roleInput.value);
    data.append("sex",sex);
    data.append("current",current);
    sendAjax("PUT","/users",data,function(){
        if(arguments[0]){
            var pageResult = JSON.parse(arguments[0]);
            page.querySelector(".totalcount").innerHTML = pageResult.count;  
            page.querySelector(".totalpage").innerHTML = pageResult.totalPage;
            page.querySelector(".current").innerHTML = pageResult.current;
            pageInput.value = pageResult.current;
            setUserTable(pageResult.result);
        }
    });
}

function initUser(){
    toolbarContainer.innerHTML = `<li>
        <input type="text" placeholder="用户名" name="username">
    </li>
    <li>
        <input type="text" placeholder="手机号" name="phone">
    </li>
    <li>
        <div class="selectlist">
            <lable class="selectLable"><input type="text" name="role" placeholder="角色" disabled=true></lable>
            <ul data-lock=0 class="pullDown">
            </ul>
        </div>
    </li>
    <li>
        <label for="">
            <input type="radio" name="sex"><span>男</span>
        </label>
        <label for="">
            <input type="radio" name="sex"><span>女</span>
        </label>
    </li>`;
    var btnBox = document.querySelector(".toolbar .btn-box");
    var dom = btnBox.querySelector(".btn-add");
    if(dom){
       btnBox.removeChild(dom); 
    }
    var selectLable = toolbarContainer.querySelector(".selectLable");
    var roleInput = toolbarContainer.querySelector("input[name='role']");
    selectLable.onclick = function(){
        var pullDown = toolbarContainer.querySelector(".pullDown");
        if(pullDown.getAttribute("data-lock") == 1){
            pullDown.setAttribute("style","display:none;");
            pullDown.setAttribute("data-lock",0);
            return;
        }
        pullDown.innerHTML = "";
        for(var i = 0;i < roles.length;i++){
            var role = roles[i];
            var li = document.createElement("li");
            li.innerHTML = role.name;
            pullDown.appendChild(li);
            li.onclick = function(){
                roleInput.value = this.innerHTML;
                pullDown.setAttribute("style","display:none;");
                pullDown.setAttribute("data-lock",0);
            }
        }
        pullDown.setAttribute("style","display:block;");
        pullDown.setAttribute("data-lock",1);
    };
    searchBtn.onclick = function(){
        searchUser(1);
    }
    clear.onclick = function(){
        clearInput();
        var sexs = toolbarContainer.querySelectorAll("input[name='sex']");
        for(var j = 0;j < sexs.length;j++){
            sexs[j].checked = false;
        }
    }
    searchUser(1);
}


var editProduct = document.querySelector(".addOrUpdate-product");
editProduct.querySelector(".product-title .icon-cancel").onclick = function(){
    document.querySelector(".spu .spu-submit").setAttribute("data-id",-1);
    showProductEdit(false);
};
var saSelect = editProduct.querySelector("select[name='saleAttribute']");
var productDialog = document.querySelector(".product-dialog");
var productBack = productDialog.querySelector(".back");


function initProductTable(products){
    if(products){
        var productTable = document.querySelector(".product table");
        productTable.innerHTML = "";
        var first = document.createElement("tr");
        first.className = "first";
        first.innerHTML = "<td>SPU</td><td>价格</td><td>库存</td><td>上架状态</td><td>颜色</td><td>删除</td>";
        productTable.appendChild(first);
        for(var i= 0;i < products.length;i++){
            var product = products[i];
            var tr = document.createElement("tr");
            tr.setAttribute("data-id",product.id);
            tr.innerHTML = `<td>${product.name}</td><td>${product.price}</td><td>${product.number}</td>
            <td class="state">${product.state == 1?"上架":"下架"}</td><td>${product.color.name}</td><td><i class="iconfont icon-cancel"></i></td>`;
            tr.querySelector(".icon-cancel").onclick = function(){
                var tr = this.parentNode.parentNode;
                var id = tr.getAttribute("data-id");
                sendAjax("DELETE","/product/"+id,null,function(){
                    tr.querySelector(".state").innerHTML = "下架";
                });
            };
            productTable.appendChild(tr);
        }
    }
}

function showProductEdit(isShow,product){
    if(isShow){
        var kindSelect = editProduct.querySelector("select[name='kind']");
        sendAjax("GET","/kinds",null,function(){
            if(arguments[0]){
                kindSelect.innerHTML = "";
                var kinds = JSON.parse(arguments[0]);
                for(var i = 0;i < kinds.length;i++){
                    var kind = kinds[i];
                    var option = document.createElement("option");
                    option.value = kind.id;
                    option.innerHTML = kind.cn_name;
                    kindSelect.appendChild(option);
                }
                getSaleAttribute(kindSelect.value);
            }
        });
        if(product){
            var keyId = product.keyAttribute.id;
            document.querySelector(".spu .spu-submit").setAttribute("data-id",keyId);
            document.querySelector(".spu").setAttribute("data-id",keyId);
            sendAjax("GET","/products/"+keyId,null,function(){
                if(arguments[0]){
                    var products = JSON.parse(arguments[0]);
                    initProductTable(products);
                }
            });
            kind_id = product.keyAttribute.kind.id;
            getSaleAttribute(kind_id);
        }
        kindSelect.onchange = function(){
            var kind_id = editProduct.querySelector("select[name='kind']").value;
            getSaleAttribute(kind_id);
        }
        saSelect.onchange = setAttributeValue;
        var colorSelect = editProduct.querySelector("select[name='color']");
        sendAjax("GET","/colors",null,function(){
            if(arguments[0]){
                colorSelect.innerHTML = "";
                var colors = JSON.parse(arguments[0]);
                for(var i = 0;i < colors.length;i++){
                    var color = colors[i];
                    var option = document.createElement("option");
                    option.value = color.id;
                    option.innerHTML = color.name;
                    colorSelect.appendChild(option);
                }
            }
        });
        if(product){
            editProduct.querySelector("input[name='name']").value = product.keyAttribute.name;
            editProduct.querySelector("input[name='basePrice']").value = product.keyAttribute.basePrice;
            editProduct.querySelector(".introduction").innerHTML = product.keyAttribute.introduction;
            editProduct.querySelector("input[name='simpleIntroduction']").value = product.keyAttribute.simpleIntroduction;
            var kind_id = product.keyAttribute.kind.id;
            var options = kindSelect.querySelectorAll("option");
            for(var m = 0;m < options.length;m++){
                var option = options[m];
                if(kind_id == option.value){
                    option.selected = true;
                }
            }
            editProduct.querySelector("input[name='price']").value = product.price;
            editProduct.querySelector("input[name='number']").value = product.number;
            var color_id = product.color?product.color.name:"";
            if(color_id){
                var options = colorSelect.querySelectorAll("option");
                for(var n = 0;n < options.length;n++){
                    var option = options[n];
                    if(color_id == option.value){
                        option.selected = true;
                    }
                }
            }else{
                var option = document.createElement("option");
                option.innerHTML = "";
                colorSelect.appendChild(option);
                option.selected = true;
            }
            var stateInputs = editProduct.querySelectorAll("input[name='state']");
            if(product.state == 1){
                stateInputs[0].click();
            }else{
                stateInputs[1].click();
            }
            var saList = editProduct.querySelector(".sa-list");
            var keyValues = product.keyValues;
            for(var j = 0;j < keyValues.length;j++){
                var keyValueProduct = keyValues[j];
                var av = keyValueProduct.keyValue.attributeValue;
                var value = av.value;
                var saName = av.saleAttribute.name;
                var li = document.createElement("li");
                li.setAttribute("data-id",keyValueProduct.id);
                var i = document.createElement("i");
                li.innerHTML = saName+"："+value;
                li.appendChild(i);
                saList.appendChild(li);
                i.onclick = function(){
                    var id = this.parentNode.getAttribute("data-id");
                    sendAjax("DELETE","/kvp/"+id,null,function(){
                        this.parentNode.parentNode.removeChild(this.parentNode);
                    });
                }
            }
            var keyId = product.keyAttribute.id;
            editProduct.querySelector(".spu .icon-Increase").onclick = function(){
                sendAjax("GET","/img/state?ka_id="+keyId+"&state=13",null,function(){
                    if(arguments[0]){
                        var imgs = JSON.parse(arguments[0]);
                        showImgBox(true,imgs);
                    }
                });
                productBack.setAttribute("style","display:block;");
            };
            editProduct.querySelector(".sku .icon-Increase").onclick = function(){
                var colorId = editProduct.querySelector("input[name='color']").getAttribute("data-id");
                if(colorId){
                    sendAjax("GET","/img/color?ka_id="+keyId+"&color_id="+colorId,null,function(){
                        if(arguments[0]){
                            var imgs = JSON.parse(arguments[0]);
                            showImgBox(true,imgs);
                        }
                    });
                }else{
                    sendAjax("GET","/img/state?ka_id="+keyId+"&state=12",null,function(){
                        if(arguments[0]){
                            var imgs = JSON.parse(arguments[0]);
                            showImgBox(true,imgs);
                        }
                    });
                }
            };
        }else{
            editProduct.querySelector(".spu .icon-Increase").onclick = function(){
                showImgBox(true);
            }
        }
        editProduct.setAttribute("style","display:block;");
    }else{
        var inputs = editProduct.querySelectorAll("input");
        for(var i = 0;i < inputs.length;i++){
            if(inputs[i].type == "text"){
                inputs[i].value = "";
            }else{
                inputs[i].checked = false;
            }
        }
        editProduct.setAttribute("style","display:none;");
    }
}


function getSaleAttribute(kind_id){
    if(kind_id){
        sendAjax("GET","/saleAttributes/"+kind_id,null,function(){
            if(arguments[0]){
                saSelect.innerHTML = "";
                var saleAttributes = JSON.parse(arguments[0]);
                for(var i = 0;i < saleAttributes.length;i++){
                    var sa = saleAttributes[i];
                    var option = document.createElement("option");
                    option.value = sa.id;
                    option.innerHTML = sa.name;
                    saSelect.appendChild(option);
                }
                setAttributeValue();
            }
        });
    }
}


function setAttributeValue(){
    var avSelect = editProduct.querySelector("select[name='attributeValue']");
    var saSelect = editProduct.querySelector("select[name='saleAttribute']");
    var sa_id = saSelect.value;
    sendAjax("GET","/attributeValues/"+sa_id,null,function(){
        if(arguments[0]){
            avSelect.innerHTML = "";
            var attributeValues = JSON.parse(arguments[0]);
            for(var i = 0;i < attributeValues.length;i++){
                var attributeValue = attributeValues[i];
                var option = document.createElement("option");
                option.innerHTML = attributeValue.value;
                option.value = attributeValue.id;
                avSelect.appendChild(option);
            }
        }
    });
}

function setImgBox(imgs){
    var imgBox = productDialog.querySelector(".product-dialog-box ul");
    for(var i = 0;i < imgs.length;i++){
        var li = document.createElement("li");
        var img = document.createElement("img");
        img.src = imgs[i].url;
        li.appendChild(img);
        imgBox.appendChild(li);
    }
}

function showImgBox(isShow,imgs){
    if(isShow){
        if(imgs){
            setImgBox(imgs);
        }
        productBack.setAttribute("style","display:block;");
    }else{
        productBack.setAttribute("style","display:none;");
    }
}

function addSa(){
    editProduct.querySelector(".addSa-a").setAttribute("style","display:none;");
    editProduct.querySelector(".addSa").setAttribute("style","display:block;");
}

productDialog.querySelector(".product-dialog-title .icon-cancel").onclick = function(){
    showImgBox(false);
};

productDialog.querySelector(".btn-cancel").onclick = function(){
    showImgBox(false);
};


function initProduct(){
    toolbarContainer.innerHTML = `<li>
    <input type="text" placeholder="商品名称" name="name">
 </li>
 <li>
    <input type="text" placeholder="价格" name="min"> - 
    <input type="text" placeholder="价格" name="max">
 </li>
 <li>
     <input type="radio" name="state"><span>上架</span>
     <input type="radio" name="state"><span>下架</span>
 </li>`;
    var dom = document.querySelector(".toolbar .btn-box .btn-add");
    var btnAdd = document.createElement("button");
    if(!dom){
        btnAdd.innerHTML = "增加";
        btnAdd.className = "btn-add";
        document.querySelector(".toolbar .btn-box").appendChild(btnAdd);
    }
    searchBtn.onclick = function(){
        searchProduct(1);
    }
    clear.onclick = function(){
        clearInput();
    }
    btnAdd.onclick = function(){
        showProductEdit(true);
    };
    searchProduct(1);
}

function searchProduct(current){
    var name = toolbarContainer.querySelector("input[name='name']").value;
    var min = toolbarContainer.querySelector("input[name='min']").value;
    var max = toolbarContainer.querySelector("input[name='max']").value;
    var states = toolbarContainer.querySelectorAll("input[name='state']");
    var state = 1;
    if(states[1].checked){
        state = 0;
    }
    var data = new FormData();
    data.append("name",name);
    data.append("min",min);
    data.append("max",max);
    data.append("state",state);
    data.append("current",current);
    sendAjax("PUT","/products",data,function(){
        if(arguments[0]){
            var pageResult = JSON.parse(arguments[0]);
            page.querySelector(".totalcount").innerHTML = pageResult.count;  
            page.querySelector(".totalpage").innerHTML = pageResult.totalPage;
            page.querySelector(".current").innerHTML = pageResult.current;
            pageInput.value = pageResult.current;
            setProductTable(pageResult.result);
        }
    });
}

function setProductTable(products){
    var table = document.querySelector(".list-container table");
    table.innerHTML = "";
    var title = document.createElement("tr");
    title.className = "list-title";
    title.innerHTML = `<td></td><td>名称</td><td>价格</td><td>库存</td><td>创建时间</td><td>上架状态</td><td>颜色</td><td>操作</td>`;
    table.appendChild(title);
    for(var i = 0;i < products.length;i++){
        var product = products[i];
        var tr = document.createElement("tr");
        tr.setAttribute("data-id",product.id);
        tr.innerHTML = `<td class="checkbox"><i class="iconfont icon-checkbox"></i></td><td>${product.name}</td><td>${product.price}</td>
        <td class="td-name">${product.number}</td><td>${product.createTime}</td><td>${product.state == 1?"上架":"下架"}</td>
        <td>${product.color?product.color.name:""}</td>
        <td>
            <button class="btn-delete"><span>删除</span></button>
        </td>`;
        tr.querySelector(".checkbox i").onclick = function(){
            if(this.className === "iconfont icon-checkbox"){
                this.className = "iconfont icon-checkbox1";
            }else{
                this.className = "iconfont icon-checkbox";
            }
        };
        tr.querySelector(".btn-delete").onclick = function(){
            var id = this.parentNode.parentNode.getAttribute("data-id");
            sendAjax("DELETE","/product/"+id,null,function(){
            	searchProduct(1);
            });
        };
        table.appendChild(tr);
    }
}


function setKindTable(kinds){
    var table = document.querySelector(".list-container table");
    table.innerHTML = "";
    var title = document.createElement("tr");
    title.className = "list-title";
    title.innerHTML = `<td></td><td>名称</td><td>标志</td><td>操作</td>`;
    table.appendChild(title);
    for(var i = 0;i < kinds.length;i++){
        var kind = kinds[i];
        var tr = document.createElement("tr");
        tr.setAttribute("data-id",kind.id);
        tr.innerHTML = `<td class="checkbox"><i class="iconfont icon-checkbox"></i></td><td>${kind.cn_name}</td><td class="td-name">${kind.name}</td>
        <td>
            <button class="btn-update"><span>更新</span></button>
        </td>`;
        tr.querySelector(".checkbox i").onclick = function(){
            if(this.className === "iconfont icon-checkbox"){
                this.className = "iconfont icon-checkbox1";
            }else{
                this.className = "iconfont icon-checkbox";
            }
        };
        tr.querySelector(".btn-update").onclick = function(){
            var id = this.parentNode.parentNode.getAttribute("data-id");
            sendAjax("GET","/kind/"+id,null,function(){
                if(arguments[0]){
                    var kind = JSON.parse(arguments[0]);
                    document.querySelector(".addOrUpdate-kind .btn-sure").setAttribute("data-id",kind.id);
                    document.querySelector(".addOrUpdate-kind input[name='cnName']").value = kind.cn_name;
                    document.querySelector(".addOrUpdate-kind input[name='name']").value = kind.name;
                    showKindEdit(true);
                }
            });
        };
        table.appendChild(tr);
    }
}

function searchKind(current){
    var kindName = toolbarContainer.querySelector("input[name='kind']").value;
    var data = new FormData();
    data.append("name",kindName);
    data.append("current",current);
    sendAjax("PUT","/kinds",data,function(){
        if(arguments[0]){
            var pageResult = JSON.parse(arguments[0]);
            page.querySelector(".totalcount").innerHTML = pageResult.count;  
            page.querySelector(".totalpage").innerHTML = pageResult.totalPage;
            page.querySelector(".current").innerHTML = pageResult.current;
            pageInput.value = pageResult.current;
            setKindTable(pageResult.result);
        }
    });
}

function showKindEdit(isShow){
    var back = document.querySelector(".addOrUpdate-kind .back");
    var input = document.querySelector(".addOrUpdate-kind .add-address-input");
    if(isShow){
        back.setAttribute("style","display:block;");
        input.setAttribute("style","display:block;");
    }else{
        back.setAttribute("style","display:none;");
        input.setAttribute("style","display:none;");
        input.querySelector("input[name='cnName']").value = "";
        input.querySelector("input[name='name']").value = "";
        back.querySelector(".btn-sure").setAttribute("data-id","");
    }
}

document.querySelector(".addOrUpdate-kind .icon-cancel").onclick = function(){
    showKindEdit(false);
};

document.querySelector(".addOrUpdate-kind .btn-sure").onclick = function(){
    var cn_name = document.querySelector(".addOrUpdate-kind input[name='cnName']").value;
    var name = document.querySelector(".addOrUpdate-kind input[name='name']").value;
    if(cn_name.length > 20){
        cn_name.setAttribute("style","border:1px solid #e0e0e0");
        return;
    }
    if(name.length > 20){
        name.setAttribute("style","border:1px solid #e0e0e0");
        return;
    }
    var id = this.getAttribute("data-id");
    var data = new FormData();
    data.append(name);
    data.append(cn_name);
    if(id){
        data.append(id);
        sendAjax("PUT","/kind",data,function(){
            searchKind(parseInt(currentSpan.innerHTML));
            showKindEdit(false);
        });
    }else{
        sendAjax("POST","/kind",data,function(){
            searchKind(parseInt(currentSpan.innerHTML));
            showKindEdit(false);
        });
    }
};

document.querySelector(".addOrUpdate-kind .btn-cancel").onclick = function(){
    showKindEdit(false);
};

function initKind(){
    toolbarContainer.innerHTML = `
        <li>
            <input type="text" placeholder="商品种类" name="kind">
        </li>
    `;
    var dom = document.querySelector(".toolbar .btn-box .btn-add");
    var btnAdd = document.createElement("button");
    if(!dom){
        btnAdd.innerHTML = "增加";
        btnAdd.className = "btn-add";
        document.querySelector(".toolbar .btn-box").appendChild(btnAdd);
    }
    searchBtn.onclick = function(){
        searchKind(1);
    }
    clear.onclick = function(){
        clearInput();
    }
    document.querySelector(".toolbar .btn-box .btn-add").onclick = function(){
        showKindEdit(true);
    };
    searchKind(1);
}


function setOrderTable(orders){
    var table = document.querySelector(".list-container table");
    table.innerHTML = "";
    var title = document.createElement("tr");
    title.className = "list-title";
    title.innerHTML = `<td></td><td>下单人用户名</td><td>订单号</td><td>收货人昵称</td><td>收货人手机号</td><td>创建时间</td><td>订单状态</td>`;
    table.appendChild(title);
    for(var i = 0;i < orders.length;i++){
        var order = orders[i];
        var tr = document.createElement("tr");
        tr.innerHTML = `<td class="checkbox"><i class="iconfont icon-checkbox"></i></td><td>${order.user.name}</td><td class="td-name">${order.orderCode}</td>
        <td>${order.address.name}</td><td>${order.address.phone}</td><td>${order.createTime}</td><td>${order.orderState}</td>`;
        tr.querySelector(".checkbox i").onclick = function(){
            if(this.className === "iconfont icon-checkbox"){
                this.className = "iconfont icon-checkbox1";
            }else{
                this.className = "iconfont icon-checkbox";
            }
        };
        table.appendChild(tr);
    }
}


function searchOrder(current){
    var orderCode = toolbarContainer.querySelector("input[name='orderCode']").value;
    var username = toolbarContainer.querySelector("input[name='username']").value;
    var receiverPhone = toolbarContainer.querySelector("input[name='username']").value;
    var orderState = toolbarContainer.querySelector("input[name='orderState']").getAttribute("data-id");
    var data = new FormData();
    data.append("orderCode",orderCode);
    data.append("username",username);
    data.append("phone",receiverPhone);
    data.append("orderState",orderState);
    data.append("current",current);
    sendAjax("PUT","/orders",data,function(){
        if(arguments[0]){
            var pageResult = JSON.parse(arguments[0]);
            page.querySelector(".totalcount").innerHTML = pageResult.count;  
            page.querySelector(".totalpage").innerHTML = pageResult.totalPage;
            page.querySelector(".current").innerHTML = pageResult.current;
            pageInput.value = pageResult.current;
            setOrderTable(pageResult.result);
        }
    });
}


function initOrder(){
    toolbarContainer.innerHTML = `<li>
        <input type="text" placeholder="订单号" name="orderCode">
    </li>
    <li>
        <input type="text" placeholder="用户名" name="username">
    </li>
    <li>
        <input type="text" placeholder="接收人手机号" name="receiverPhone">
    </li>
    <li>
        <div class="selectlist">
            <lable class="selectLable"><input data-id = "" type="text" name="orderState" placeholder="订单状态" disabled=true></lable>
            <ul data-lock=0 class="pullDown">
                <li data-id = 1>未付款</li>
                <li data-id = 2>待发货</li>
                <li data-id = 3>已发货</li>
                <li data-id = 4>其他</li>
            </ul>
        </div>
    </li>
    `;
    var btnBox = document.querySelector(".toolbar .btn-box");
    var dom = btnBox.querySelector(".btn-add");
    if(dom){
       btnBox.removeChild(dom); 
    }
    searchBtn.onclick = function(){
        searchOrder(1);
    }
    var orderStateInput = toolbarContainer.querySelector("input[name='orderState']");
    clear.onclick = function(){
        clearInput();
        orderStateInput.setAttribute("data-id","");
    }
    toolbarContainer.querySelector(".selectLable").onclick = function(){
        var pullDown = toolbarContainer.querySelector(".pullDown");
        if(pullDown.getAttribute("data-lock") == 1){
            pullDown.setAttribute("style","display:none;");
            pullDown.setAttribute("data-lock",0);
        }else{
            pullDown.setAttribute("style","display:block;");
            pullDown.setAttribute("data-lock",1);
        }
    };
    toolbarContainer.querySelector(".pullDown").onclick = function(event){
        var target = event.target;
        if("li" === target.tagName.toLowerCase()){
            orderStateInput.innerHTML = target.innerHTML;
            orderStateInput.setAttribute("data-id",target.getAttribute("data-id"));
            toolbarContainer.querySelector(".pullDown").setAttribute("style","display:none;");
        }
    }
    searchOrder(1);
}


var spuFileInput = document.querySelector(".spu input[name='spu-file']");
var spuImgBox = productDialog.querySelector(".product-dialog-box ul");
var spuFiles = [];
document.querySelector(".product-dialog-box .first").onclick = function(){
    spuFileInput.click();
    spuFileInput.onchange = function(){
        var file = this.files[0];
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(){
            spuFiles.push(file);
            var li = document.createElement("li");
            var img = document.createElement("img");
            img.src = this.result;
            li.appendChild(img);
            spuImgBox.appendChild(li);
        }
    }
};


function addColor(){
    document.querySelector(".sku .addColor").setAttribute("style","display:block");
}


document.querySelector(".spu .spu-submit").onclick = function(){
    var keyId = this.getAttribute("data-id");
    var data = new FormData();
    var spu = document.querySelector(".spu");
    var name = spu.querySelector("input[name='name']").value;
    var kind_id = spu.querySelector("select[name='kind']").value;
    var basePrice  = spu.querySelector("input[name='basePrice']").value;
    var simpleIntroduction = spu.querySelector("input[name='simpleIntroduction']").value;
    var introduction = spu.querySelector(".introduction").innerHTML;
    data.append("name",name);
    data.append("introduction",introduction);
    data.append("basePrice",basePrice);
    data.append("simpleIntroduction",simpleIntroduction);
    data.append("kind_id",kind_id);
    data.append("id",keyId);
    sendAjax("POST","/key",data,function(){
        if(keyId < 0){
            keyId = arguments[0];
            document.querySelector(".spu .spu-submit").setAttribute("data-id",keyId);
        }
        if(spuFiles.length > 0){
            var data2 = new FormData();
            for(var i = 0;i < spuFiles.length;i++){
                data2.append("files",spuFiles[i]);
            }
            sendAjax("POST","/api/files",data2,function(){
                var urls = JSON.parse(arguments[0]);
                var data3 = new FormData();
                data3.append("state",13);
                data3.append("key_id",keyId);
                data3.append("urls",urls);
                sendAjax("POST","/imgs",data3,function(){
                    alert("操作成功");
                });
            });
        }
    });
         
};


var sku = document.querySelector(".sku");

document.querySelector(".add-color").onclick = function(){
    var keyId = document.querySelector(".spu .spu-submit").getAttribute("data-id");
    var name = sku.querySelector("input[name='addColor']").value;
    if(name && keyId){
        var data = new FormData();
        data.append("name",name);
        sendAjax("POST","/color",data,function(){
            var id = arguments[0];
            var colorSelect = editProduct.querySelector("select[name='color']");  
            var op = document.createElement("option");
            op.value = id;
            op.innerHTML = name;
            colorSelect.appendChild(op);
            op.selected = true;
            var keyColor = {
                ka_id:keyId,
                color:{
                    id:id
                }
            }
            sendAjax("POST","/keyColor",keyColor,function(){
                alert("添加成功");
            },true);
        });
    }
};

var keyTag = "";
document.querySelector(".add-sku").onclick = function(){
    var saSelect = sku.querySelector("select[name='saleAttribute']");
    var avSelect = sku.querySelector("select[name='attributeValue']");
    var keyId = document.querySelector(".spu .spu-submit").getAttribute("data-id");
    var sa_id = saSelect.value;
    var av_id = avSelect.value;
    var saName = saSelect.querySelector("option[value='"+sa_id+"']").innerHTML;
    var avName = avSelect.querySelector("option[value='"+av_id+"']").innerHTML;
    var data = new FormData();
    data.append("ka_id",keyId);
    data.append("av_id",av_id);
    sendAjax("POST","/keyValue",data);
    var saList = sku.querySelector(".sa-list");
    sendAjax("GET","/keyValue?ka_id="+keyId+"&av_id="+av_id,null,function(){
        if(arguments[0]){
            if(keyTag){
                keyTag = keyTag+","+arguments[0];
            }else{
                keyTag = ""+arguments[0];
            }
            var li = document.createElement("li");
            var i = document.createElement("i");
            li.innerHTML = saName+"："+avName;
            li.appendChild(i);
            saList.appendChild(li);
        }
    });
};


var skuFiles = [];
var skuFilesNum = 0;
var skuImgBox = sku.querySelector(".sku-imgbox");
sku.querySelector(".skuImg .icon-Increase").onclick = function(){
    if(skuFiles.length > 3){
        alert("添加图片不能超过四张");
        return;
    }
    var skuFileInput = sku.querySelector("input[name='sku-file']");
    skuFileInput.click();
    skuFileInput.onchange = function(){
        var file = this.files[0];
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(){
            skuFiles.push(file);
            var li = document.createElement("li");
            var img = document.createElement("img");
            img.src = this.result;
            li.appendChild(img);
            skuImgBox.appendChild(li);
        }
    }
};

var skuSmallFiles = [];
var skuSmallFilesNum = 0;
var skuImgBox = sku.querySelector(".sku-imgbox");
sku.querySelector(".skuSmallImg .icon-Increase").onclick = function(){
    if(skuSmallFiles.length > 3){
        alert("添加图片不能超过四张");
        return;
    }
    var skuSmallFileInput = sku.querySelector("input[name='sku-smallFile']");
    skuSmallFileInput.click();
    skuSmallFileInput.onchange = function(){
        var file = this.files[0];
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(){
            skuSmallFiles.push(file);
            var li = document.createElement("li");
            var img = document.createElement("img");
            img.src = this.result;
            li.appendChild(img);
            skuImgBox.appendChild(li);
        }
    }
};

document.querySelector(".sku .sku-submit").onclick = function(){
    var keyId = document.querySelector(".spu .spu-submit").getAttribute("data-id");
    if(keyId < 0){
        alert("请指定spu!");
        return;
    }
    var price = sku.querySelector("input[name='price']").value;
    var number = sku.querySelector("input[name='number']").value;
    var color_id = sku.querySelector("select[name='color']").value;
    var stateInputs = editProduct.querySelectorAll("input[name='state']");
    var state = 0;
    if(stateInputs[0].checked){
        state = 1;
    }else{
        state = 0;
    }
    var product = {
        price:price,
        number:number,
        state:state,
        keyAttribute:{
            id:keyId
        },
        color:{
            id:color_id
        },
        keyTag:keyTag
    }
    sendAjax("POST","/product",product,function(){
        if(arguments[0]){
            if(keyTag){
                var kvs = keyTag.split(",");
                var data3 = new FormData();
                data3.append("kvs",kvs);
                data3.append("product_id",arguments[0]);
                sendAjax("POST","/kvp",data3);
            }
            if(skuFiles.length > 0){
                var data = new FormData();
                for(var i = 0;i < skuFiles.length;i++){
                    data.append("files",skuFiles[i]);
                }
                sendAjax("POST","/api/files",data,function(){
                    var urls = JSON.parse(arguments[0]);
                    var data2 = new FormData();
                    data2.append("state",12);
                    data2.append("key_id",keyId);
                    data2.append("color_id",color_id);
                    data2.append("urls",urls);
                    sendAjax("POST","/imgs",data2);
                });
            }
            if(skuSmallFiles.length > 0){
                var data4 = new FormData();
                for(var j = 0;j < skuSmallFiles.length;j++){
                    data4.append("files",skuSmallFiles[j]);
                }
                sendAjax("POST","/api/files",data4,function(){
                    var urls = JSON.parse(arguments[0]);
                    var data5 = new FormData();
                    data5.append("state",15);
                    data5.append("key_id",keyId);
                    data5.append("urls",urls);
                    sendAjax("POST","/imgs",data5);
                });
            }
            sendAjax("GET","/products/"+keyId,null,function(){
                if(arguments[0]){
                    var products = JSON.parse(arguments[0]);
                    initProductTable(products);
                }
            });
            keyTag = "";
            alert("添加成功");
        }
    },true);
};


document.querySelector(".product-dialog-btn .btn-sure").onclick = function(){
    showImgBox(false);
};


sku.querySelector(".clear-skuimg").onclick = function(){
    skuFilesNum = 0;
    skuFiles = [];
    skuSmallFiles = [];
    sku.querySelector(".sku-imgbox").innerHTML = "";
}

sku.querySelector(".clear-sku").onclick = function(){
    sku.querySelector(".sa-list").innerHTML = "";
    keyTag = "";
};
