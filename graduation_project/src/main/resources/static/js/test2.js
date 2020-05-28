var tds = document.querySelectorAll("td");
var div1 = document.querySelector(".div1 span");
var div2 = document.querySelector(".div2 span");
var currentNum = "";
var arr = [];
var calText = [];
var result = [];
for(var i = 0; i < tds.length; i++){
    tds[i].onclick=function(){
        var text = div2.innerHTML;
        var tag = this.innerHTML;
        if("C" == tag){
            arr = [];
            calText = [];
            result = [];
            div1.innerHTML = "";
            text = "";
        }else if("=" == tag){
            console.log(arr);
            calText.push(parseFloat(currentNum));
            for(var i = arr.length - 1;i >= 0;i--){
                calText.push(arr[i]);
            }
            console.log(calText);
            cal(calText);
            console.log(result);
            if(result[0] || result[0] == 0){
                div1.innerHTML = result[0];
            }
            arr = [];
            calText = [];
            result = [];
            currentNum = "";
        }else{
            change(tag);
            text += tag;
        }
        div2.innerHTML = text;
    }
}

function change(tag){
    if(parseFloat(tag) || tag == 0){
        currentNum += tag;
    }else{
        calText.push(parseFloat(currentNum));
        currentNum = "";
        if(arr.length > 0){
            check(arr,tag);
        }else if(calText.length > 0 && arr.length == 0){
            arr.push(tag);
        }else{
            return;
        }
    }
}

function cal(calText){
    for(var i = 0 - 1; i < calText.length; i++){
        var current = calText[i];
        if(parseFloat(current) || current == 0){
            result.push(parseFloat(current));
        }else{
            var num1 = result.pop();
            var num2 = result.pop();
            if(current == "."){
                num = num2 + "." +num1;
                result.push(parseFloat(num));
            }else if(current == "*"){
                result.push(parseFloat(num2 * num1));
            }else if(current == "/"){
                result.push(parseFloat(num2 / num1));
            }else if(current == "+"){
                result.push(parseFloat(num2 + num1));
            }else if(current == "-"){
                result.push(parseFloat(num2 - num1));
            }
        }
    }
}

function check(arr,tag){
    if(arr.length > 0){
        if("." == tag){
            if(arr[arr.length - 1] == "."){
                calText.push(arr.pop());
                check(arr,tag);
            }else{
                arr.push(tag);
            }
        }else if("/" == tag || "*" == tag){
            var top = arr[arr.length - 1];
            if(top == "/" || top == "*" || top == "."){
                calText.push(arr.pop());
                check(arr,tag);
            }else{
                arr.push(tag);
                console.log(arr);
            }
        }else if("+" == tag || "-" == tag){
            var top = arr[arr.length - 1];
            if(arr.length > 1){
                calText.push(arr.pop());
                check(arr,tag);
            }else{
                calText.push(arr.pop());
                arr.push(tag);
            }
        }
    }
}

document.querySelector("table").onmousedown = function(event){
    event.target.setAttribute("style","box-shadow: 1px 1px 1px 1px red;");
};
document.querySelector("table").onmouseup = function(event){
    event.target.setAttribute("style","box-shadow:none;");
};
