document.querySelector(".fa-sort-desc").onclick=function(){
    var shows = document.querySelectorAll(".show");
    var notshows = document.querySelectorAll(".notshow");
    if(shows.length){
        for(var i = 0;i < shows.length;i++){
            shows[i].className = "notshow";
        }
    }else{
        shows.className="notshow";
    }
    if(notshows.length){
        for(var i = 0;i < notshows.length;i++){
            notshows[i].className = "show";
        }
    }else{
        notshows.className = "show";
    }
}
var region = document.querySelector("input[name='region']");
document.querySelector(".regionlist .list").onclick = function(event){
    var target = event.target;
    if(target.className != ""){
        return;
    }
    if(target.tagName.toLowerCase() === "div"){
        region.value = target.children[0].innerHTML;
        clearRegion();
        target.parentNode.className = "selected";
    }
    if(target.tagName.toLowerCase() === "span"){
        region.value = target.innerHTML;
        clearRegion();
        target.parentNode.parentNode.className = "selected";
    }
    document.querySelector(".fa-sort-desc").click();
}
function clearRegion(){
    var lis = document.querySelectorAll(".list li");
    for(var i = 0;i < lis.length;i++){
        lis[i].className = "";
    }
}


var errorSpan = document.querySelector(".message .span3");

var btn_identifyCode = document.querySelector(".btn_identifyCode");
if(btn_identifyCode){
    btn_identifyCode.onclick=function(){
        var usernameInput = document.querySelector("input[name='username']");
        if(checkMail(usernameInput.value)){
            time(this);
            var data = new FormData();
            data.append("username",usernameInput.value);
            sendAjax("POST","/api/identifyCode",data);
        }else{
            errorSpan.innerHTML = "请输入正确的邮箱";
        }
    }
}

document.querySelector("input[name='username']").onfocus = function(){
    errorSpan.innerHTML = "";
}

document.querySelector("input[name='password']").onfocus = function(){
    var usernameInput = document.querySelector("input[name='username']");
    if(!checkMail(usernameInput.value)){
        errorSpan.innerHTML = "请输入正确的邮箱";
    }else{
        errorSpan.innerHTML = "";
        document.querySelector("input[name='repassword']").removeAttribute("disabled");
    }
}

document.querySelector("input[name='repassword']").onfocus = function(){
    var passwordInput = document.querySelector("input[name='password']");
    if(!checkPassword(passwordInput.value)){
        errorSpan.innerHTML = "请输入正确的密码";
        this.setAttribute("disabled","true");
    }
}
document.querySelector("input[name=repassword]").onkeyup = function(){
    var passwordInput = document.querySelector("input[name='password']");
    if(!passwordInput.value || passwordInput.value != this.value){
        errorSpan.innerHTML = "密码输入不一致";
    }else{
        errorSpan.innerHTML = "";
    }
}

document.querySelector(".btn_submit").onclick = function(){
    var username = document.querySelector("input[name='username']").value;
    var identifyCode = document.querySelector("input[name='identifyCode']").value;
    var password = document.querySelector("input[name='password']").value;
    var repassword = document.querySelector("input[name='repassword']").value;
    if(!checkMail(username)){
        errorSpan.innerHTML = "请输入正确的邮箱";
        return;
    }
    if(identifyCode.length != 6){
        errorSpan.innerHTML = "验证码错误";
        return;
    }
    if(checkPassword){

    }
    if(!password || !repassword || password != repassword){
        errorSpan.innerHTML = "密码输入不一致";
        return;
    }
    var data = new FormData();
    data.append("username",username);
    data.append("identifyCode",identifyCode);
    data.append("password",password);
    sendAjax("POST","/user",data,function(){
        window.location = "/html/index.html";
    },false,function(){
        if(arguments[0]){
            errorSpan.innerHTML = arguments[0];
        }
    });
};

document.querySelector(".main").onkeyup = function(event){
    var code = event.keyCode;
    if(code == 13){
        document.querySelector(".btn_submit").click();
    }
}

