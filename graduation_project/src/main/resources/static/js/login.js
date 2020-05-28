var span1 = document.querySelector(".span1");
var span2 = document.querySelector(".span2");

span1.onclick=function(){
    this.className = "span1 selected";
    span2.className = "span2";
    document.querySelector(".psLogin").setAttribute("style","display:block");
    document.querySelector(".identifyLogin").setAttribute("style","display:none");
    document.querySelector(".btn_submit").className = "btn_submit submit-default";
};
span2.onclick = function(){
    this.className = "span2 selected";
    span1.className = "span1";
    document.querySelector(".psLogin").setAttribute("style","display:none");
    document.querySelector(".identifyLogin").setAttribute("style","display:block");
    document.querySelector(".btn_submit").className = "btn_submit submit-identify";
}

var errorSpan = document.querySelector(".error-message");
/**
 * identifyCode click event
 */
var btn_identifyCode = document.querySelector(".btn_identifyCode");
if(btn_identifyCode){
    btn_identifyCode.onclick=function(){
        var usernameInput = document.querySelector("input[name='email']");
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
/**
 * while mouse click username input clear the error message
 */
document.querySelector("input[name='email']").onfocus = function(){
    errorSpan.innerHTML = "";
}
/**
 * while mouse click password input clear the error message
 */
document.querySelector("input[name='username']").onfocus = function(){
    errorSpan.innerHTML = "";
}

/**
 * while edit password checking username
 */
document.querySelector("input[name='password']").onfocus = function(){
    var usernameInput = document.querySelector("input[name='username']");
    if(!checkMail(usernameInput.value)){
        errorSpan.innerHTML = "请输入正确的邮箱";
    }else{
        errorSpan.innerHTML = "";
    }
}
/**
 * submit button click event 
 */
document.querySelector(".btn_submit").onclick = function(){
    if(this.className == "btn_submit submit-default"){
        var username = document.querySelector("input[name='username']").value;
        var password = document.querySelector("input[name='password']").value;
        if(!checkMail(username)){
            errorSpan.innerHTML = "请输入正确的邮箱";
            return;
        }
        if(!password){
            errorSpan.innerHTML = "请输入密码";
            return;
        }
        var data = new FormData();
        data.append("username",username);
        data.append("password",password);
        sendAjax("POST","/api/user",data,function(){
            if(arguments[0]){
                errorSpan.innerHTML = arguments[0];
            }else{
                window.location = document.referrer;
            }
        });
    }else if(this.className == "btn_submit submit-identify"){
        var email = document.querySelector("input[name='email']").value;
        var identifyCode = document.querySelector("input[name='identifyCode']").value;
        if(!checkMail(email)){
            errorSpan.innerHTML = "请输入正确的邮箱";
            return;
        }
        if(!identifyCode || identifyCode.length != 6){
            errorSpan.innerHTML = "验证码错误";
            return;
        }
        var data = new FormData();
        data.append("username",email);
        data.append("identifyCode",identifyCode);
        sendAjax("POST","/api/user/identifyCode",data,function(){
            window.location = document.referrer;
        },false,function(){
            if(arguments[0]){
                errorSpan.innerHTML = arguments[0];
            }
        });
    }
}

document.querySelector(".box-container").onkeyup = function(event){
    var code = event.keyCode;
    if(code == 13){
        document.querySelector(".btn_submit").click();
    }
}

var user;
sendAjax("GET","/user",null,function(){
    if(arguments[0]){
        user = JSON.parse(arguments[0]);
        window.location = "/html/index.html";
    }
});
