function newSwiper(){
    return new Swiper(".swiper-container",{
        spaceBetween: 30,
        centeredSlides: true,
        autoplay: {
            delay: 5000,
            disableOnInteraction: false,
        },
        pagination: {
            el: ".swiper-pagination",
            clickable: true,
        },
        on:{
            slideChange:function(){
                var header = document.querySelector(".header-container");
                var logoLine = header.querySelector(".logo line");
                if(this.activeIndex == 6 || this.activeIndex == 1 || this.activeIndex == 2
                     || this.activeIndex == 4){
                    header.className = "header-container header-change";
                    logoLine.setAttribute("style","stroke:rgb(0,190,255,1);stroke-width:12");
                }else{
                    header.className = "header-container";
                    logoLine.setAttribute("style","stroke:rgb(255,255,255,1);stroke-width:12");
                }
            }
        }
    });
}

sendAjax("GET","/img/key/8",null,function(){
    if(arguments[0]){
        var imgs = JSON.parse(arguments[0]);
        var wrapper = document.querySelector(".swiper-wrapper");
        wrapper.innerHTML = "";
        for(var i = 0;i < imgs.length;i++){
            var swiper = document.createElement("div");
            swiper.className = "swiper-slide";
            var swiper_img = document.createElement("img");
            swiper_img.src = imgs[i].url;
            swiper.appendChild(swiper_img);
            wrapper.appendChild(swiper);
        }
        swiper = newSwiper();
    }
});

sendAjax("GET","/img/key/10",null,function(){
    if(arguments[0]){
        var maxs = document.querySelectorAll(".max-img");
        img = JSON.parse(arguments[0]);
        for(var i = 0;i < maxs.length;i++){
            maxs[i].src = img[i].url;
        }
    }
});

sendAjax("GET","/key/kind?kind_id=1&begin=0&end=2",null,function(){
    if(arguments[0]){
        var as = document.querySelectorAll(".box-container1 a");
        var keys = JSON.parse(arguments[0]);
        as[1].href = "/html/detail.html?id="+keys[0].id;
        as[2].href = "/html/detail.html?id="+keys[0].id;
        as[1].querySelector("img").src = keys[0].img.url;
        as[1].querySelector(".title1").innerHTML = keys[0].name;
        as[2].querySelector("img").src = keys[1].img.url;
        as[2].querySelector(".title1").innerHTML = keys[1].name;
    }
});

var two_img = document.querySelector(".two-img");
sendAjax("GET","/key/kind?kind_id=1&begin=0&end=2",null,function(){
    if(arguments[0]){
        var keys = JSON.parse(arguments[0]);
        for(var i = 0;i < keys.length;i++){
            var li = document.createElement("li");
            li.innerHTML = `<a href="/html/detail.html?id=${keys[i].id}">
            <div class="product">
                <span class="tag">新品</span>
                <div class="name">${keys[i].name}</div>
                <div class="introduction">${keys[i].simpleIntroduction}</div>
                <div class="price">￥<span class="price-number">${keys[i].basePrice}</span></div>
                <img src="${keys[i].img.url}" alt="">
            </div>
        </a>`;  
        two_img.appendChild(li);
        }
    }
});

var table1 = document.querySelector(".box-container2 table");
sendAjax("GET","/key/kind?kind_id=1&begin=2&end=8",null,function(){
    if(arguments[0]){
        var keys = JSON.parse(arguments[0]);
        var tr = document.createElement("tr");
        for(var i = 0;i < keys.length;i++){
            if(i > 0 && i % 4 == 0){
                table1.appendChild(tr);
                tr = document.createElement("tr");
            }
            var td = document.createElement("td");
            td.innerHTML = `<td>
            <a href="/html/detail.html?id=${keys[i].id}">
                <div class="product">
                    <span class="tag">领券</span>
                    <img src="${keys[i].img.url}" alt="">
                    <div class="name">${keys[i].name}</div>
                    <div class="introduction">${keys[i].simpleIntroduction}</div>
                    <div class="price">￥<span class="price-number">${keys[i].basePrice}</span></div>
                </div>
            </a>
        </td>`;
        tr.appendChild(td);
        }
        table1.appendChild(tr);
    }
});

var products2 = document.querySelectorAll(".box-container3 .product");
sendAjax("GET","/key/kind?kind_id=2&begin=0&end=12",null,function(){
    if(arguments[0]){
        var keys = JSON.parse(arguments[0]);
        for(var i = 0;i < products2.length;i++){
            products2[i].parentNode.href="/html/detail.html?id="+keys[i].id;
            products2[i].querySelector("img").src = keys[i].img.url;
            products2[i].querySelector(".name").innerHTML = keys[i].name;
            products2[i].querySelector(".introduction").innerHTML = keys[i].simpleIntroduction;
            products2[i].querySelector(".price-number").innerHTML = keys[i].basePrice;
        }
    }
});
var products3 = document.querySelectorAll(".box-container4 .product");
sendAjax("GET","/key/kind?kind_id=3&begin=0&end=12",null,function(){
    if(arguments[0]){
        var keys = JSON.parse(arguments[0]);
        for(var i = 0;i < products3.length;i++){
            products3[i].parentNode.href="/html/detail.html?id="+keys[i].id;
            products3[i].querySelector("img").src = keys[i].img.url;
            products3[i].querySelector(".name").innerHTML = keys[i].name;
            products3[i].querySelector(".introduction").innerHTML = keys[i].simpleIntroduction;
            products3[i].querySelector(".price-number").innerHTML = keys[i].basePrice;
        }
    }
});
var products4 = document.querySelectorAll(".box-container5 .product");
sendAjax("GET","/key/kind?kind_id=4&begin=0&end=12",null,function(){
    if(arguments[0]){
        var keys = JSON.parse(arguments[0]);
        for(var i = 0;i < products4.length;i++){
            products4[i].parentNode.href="/html/detail.html?id="+keys[i].id;
            products4[i].querySelector("img").src = keys[i].img.url;
            products4[i].querySelector(".name").innerHTML = keys[i].name;
            products4[i].querySelector(".introduction").innerHTML = keys[i].simpleIntroduction;
            products4[i].querySelector(".price-number").innerHTML = keys[i].basePrice;
        }
    }
});
