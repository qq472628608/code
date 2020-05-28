var url  = window.location.search;
var id = url.substring(url.lastIndexOf("=")+1,url.length);

function newSwiper(){
    return new Swiper(".swiper-container",{
        spaceBetween: 30,
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
        }
    });
}

var box = document.querySelector(".list-box>ul");
function init(keys){
    box.innerHTML = "";
    for(var i = 0;i < keys.length;i++){
        let key = keys[i];
        let li = document.createElement("li");
        box.appendChild(li);
        let keyId = key.id;
        li.innerHTML = `<a href="/html/detail.html?id=${keyId}">
        <div class="product">
            <span class="tag">领券</span>
            <img src="${key.img.url}" alt="">
            <ul class="colorlist">
            </ul>
            <div class="baseInfo">
                <div class="name">${key.name}</div>
                <div class="introduction">${key.introduction}</div>
                <div class="price">￥${key.basePrice}</div>
            </div>
        </div>
    </a>`;
        // if(key.img.color){
        //     let currentColorId = key.img.color.id;
        //     sendAjax("GET","/keyColor/"+keyId,null,function(){
        //         if(arguments[0]){
        //             var colors = JSON.parse(arguments[0]);
        //             var colorlist = li.querySelector(".colorlist");
        //             for(var j = 0;j < colors.length;j++){
        //                 var color = colors[j].color;
        //                 var colorLi = document.createElement("li");
        //                 colorlist.appendChild(colorLi);
        //                 if(currentColorId == color.id){
        //                     colorLi.className = "selected";
        //                 } 
        //                 var colorImg = document.createElement("img");
        //                 colorImg.src = color.url;
        //                 colorLi.appendChild(colorImg);
        //             }
        //         }
        //     });
        // }
    }
}

sendAjax("GET","/key/kind?kind_id="+id+"&begin=0&end=15",null,function(){
    if(arguments[0]){
        var keys = JSON.parse(arguments[0]);
        init(keys);
    }
});

var wrapper = document.querySelector(".swiper-wrapper");
sendAjax("GET","/keys?number=8",null,function(){
    if(arguments[0]){
        var keys = JSON.parse(arguments[0]);
        var times = keys.length % 4 == 0?keys.length / 4:keys.length/4 + 1;
        for(var i = 0;i < times;i++){
            var swiper = document.createElement("div");
            swiper.className = "swiper-slide";
            var ul = document.createElement("ul");
            swiper.appendChild(ul);
            for(var j = 0;j < 4;j++){
                var index = (4*i)+j; 
                var key = keys[index];
                var li = document.createElement("li");
                ul.appendChild(li);
                li.innerHTML = `<a href="/html/detail.html?id=${key.id}">
                <img src="${key.img.url}" alt="">
                <div class="name">${key.name}</div>
                <div class="price">￥${key.basePrice}</div>
            </a>`;
            ul.appendChild(li);
            }
            wrapper.appendChild(swiper);
            swiper = newSwiper();
        }
    }
});

document.querySelector(".sequence-default").onclick = function(){
    sendAjax("GET","/key/kind?kind_id="+id+"&begin=0&end=15",null,function(){
        if(arguments[0]){
            var keys = JSON.parse(arguments[0]);
            init(keys);
        }
    });
}

document.querySelector(".sequence-newest").onclick=function(){
    sendAjax("GET","/key/kind/desc?kind_id="+id+"&begin=0&end=15",null,function(){
        if(arguments[0]){
            var keys = JSON.parse(arguments[0]);
            init(keys);
        }
    });
}

var priceAsc = document.querySelector(".price-asc");
var priceDesc = document.querySelector(".price-desc");
priceAsc.onclick = function(){
    this.setAttribute("style","border-bottom-color:#00beff;");
    priceDesc.setAttribute("style","border-top-color:#inherit;");
    sendAjax("GET","/keys/price?kind_id="+id+"&number=15&tag=asc",null,function(){
        if(arguments[0]){
            var keys = JSON.parse(arguments[0]);
            init(keys);
        }
    });
}

priceDesc.onclick = function(){
    this.setAttribute("style","border-top-color:#00beff");
    priceAsc.setAttribute("style","border-bottom-color:#inherit;");
    sendAjax("GET","/keys/price?kind_id="+id+"&number=15&tag=desc",null,function(){
        if(arguments[0]){
            var keys = JSON.parse(arguments[0]);
            init(keys);
        }
    });
}

