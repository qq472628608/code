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


document.querySelector(".btn_submit").onkeyup = function(event){
    var code = event.keyCode;
    if(code == 13){
        document.querySelector(".btn_submit").click();
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
    if(!password || !repassword || password != repassword){
        errorSpan.innerHTML = "密码输入不一致";
        return;
    }
    var data = new FormData();
    data.append("username",username);
    data.append("identifyCode",identifyCode);
    data.append("newPassword",password);
    sendAjax("PUT","/user/password",data,function(){
        window.location = "/html/index.html";
    },false,function(){
        if(arguments[0]){
            errorSpan.innerHTML = arguments[0];
        }
    });
};

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