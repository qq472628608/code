/**
 * method of send ajax request
 */
function sendAjax(method,url,data,callback,useJson,fail){
    var ajax = new XMLHttpRequest();
    ajax.open(method,url,true);
    ajax.onreadystatechange=function(){
    	if(ajax.readyState == 4){
            if(parseInt(ajax.status/100) == 2 || parseInt(ajax.status/100) == 3){
                if(callback){
                    callback.call(this,ajax.responseText);
                }
            }else{
                fail.call(this,ajax.responseText);
            }
    	}
    }
    if(data){
        if(useJson){
            ajax.setRequestHeader("Content-Type","application/json;charset=utf-8");
            ajax.send(JSON.stringify(data));
        }else{
            ajax.send(data);
        }
    }else{
        ajax.send();
    }
}


/**
 * check the text is it mail
 * @param {*} str 
 */
function checkMail(str){
    var mail = /^[0-9a-zA-Z_.-]+[@][0-9a-zA-Z_.-]+([.][a-zA-Z]+){1,2}$/;
    return mail.test(str);
}

function checkPassword(str){
    var password = /^[a-zA-Z]{1}([a-zA-Z0-9]){7,17}$/;
    return password.test(str);
}

function checkPhone(str){
    var phone = /^1[3|4|5|8][0-9]\d{8}$/;
    return phone.test(str);
}

/**
 * change the button state of identifyCode
 */
var tag = 60;
function time(button){
    if(tag == 0){
        button.removeAttribute("disabled");
        button.innerHTML = "发送验证码";
        tag = 60;
    }else{
        button.setAttribute("disabled",true);
        button.innerHTML = "已发送("+tag+")s";
        tag--;
        setTimeout(function(){
            time(button);
        },1000);
    }
}

function getOnlyTag(){
    var date = new Date();
    return ""+date.getFullYear()+date.getMonth()+date.getDay()+date.getHours()
    +date.getMinutes()+date.getSeconds()+date.getUTCMilliseconds()+ parseInt(Math.random()*10000);
}

