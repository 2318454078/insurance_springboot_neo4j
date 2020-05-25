window.onload = function() {

    var input = document.getElementById('msg_input');//查找缓存
    document.getElementById('sendbtn').onclick = function() {
        sendMsg();
    }

    //enter键 发送
    document.onkeypress = function(event) {
        var e = event || window.event;
        var keycode = e.keyCode || e.which;
        console.log(e)
        if (keycode == 13) {//按下enter键
            sendMsg()
        }
    }

    function sendMsg() {
        var input = document.getElementById('msg_input');//查找缓存
        var ul = document.getElementById('content');

        var newLi = document.createElement('li');
        newLi.innerHTML = input.value;
        newLi.className = 'msgContent right';
        ul.appendChild(newLi);

        var div = document.createElement('div');
        div.style = 'clear:both';
        ul.appendChild(div);

        ajax({
            url : '/rest/appleyk/question/query?question='+ input.value,
            success : function(res) {
                console.log(res);
                var newLi = document.createElement('li');
                newLi.innerHTML = res;
                newLi.innerHTML.replace(" ","" );
                newLi.className = 'msgContent left';
                ul.appendChild(newLi);

                var div = document.createElement('div');
                div.style = 'clear:both';
                ul.appendChild(div);
                input.value = '';
                newLi.scrollIntoView();//将元素滚动到可见位置
            }
        })

        input.value = '';
        newLi.scrollIntoView();//将元素滚动到可见位置
    }

}

function ajax(obj) {
    var xhr = null;
    if (window.ActiveXObject) {
        xhr = new ActiveXObject('Microsoft.XMLHTTP')
    } else {
        xhr = new XMLHttpRequest();
    }

    //打开与服务器的连接
    if (obj.method) {
        xhr.open(obj.method, obj.url, true);
    } else {
        xhr.open('get', obj.url, true);
    }
    xhr.setRequestHeader("Content-Type",
        "application/x-www-form-urlencoded");
    xhr.setRequestHeader("Authorization",
        "APPCODE 3e9dfb924f464e9593a95f9d2bbf4348")

    xhr.onreadystatechange = function() {

        if (xhr.readyState == 4) {
            //数据接收完毕
            if (xhr.status == 200) {
                //console.log('请求成功',xhr.responseText)
                if (obj.success) {
                    obj.success(xhr.responseText)
                }

            } else {
                //console.log(xhr.status,'请求出错')
                if (obj.failure) {
                    obj.failure('请求失败')
                }
            }
        }
    }
    if (obj.method == undefined || obj.method.toLowerCase() == 'get') {
        xhr.send(null);
    } else {
        xhr.send(obj.params);

    }
}