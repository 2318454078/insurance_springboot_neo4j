// var data1 = [];
//
// window.onload = function () {
//     var url1 = location.search;
//     console.log(url1);
//     var params = url1.substr(1);
//     var map;
//     var i = 0;
//     ajax({
//         url : '/rest/appleyk/insurance/getComInNumByAge?'+params,
//         success : function(res) {
//             console.log(res);
//             map = {26: "试试", 48: "emm", 90: "等等", 60: "hh", 103: "略略", 78: "看看", 56: "嘻嘻"};
//             for (var key in map) {
//                 console.log(key);
//                 data1[i] = key;
//                 i++;
//             }
//             console.log(data1);
//
//         }
//     })
//
//
// }
//
// function ajax(obj) {
//     var xhr = null;
//     if (window.ActiveXObject) {
//         xhr = new ActiveXObject('Microsoft.XMLHTTP')
//     } else {
//         xhr = new XMLHttpRequest();
//     }
//
//     //打开与服务器的连接
//     if (obj.method) {
//         xhr.open(obj.method, obj.url, true);
//     } else {
//         xhr.open('get', obj.url, true);
//     }
//     xhr.setRequestHeader("Content-Type",
//         "application/x-www-form-urlencoded");
//     xhr.setRequestHeader("Authorization",
//         "APPCODE 3e9dfb924f464e9593a95f9d2bbf4348")
//
//     xhr.onreadystatechange = function() {
//
//         if (xhr.readyState == 4) {
//             //数据接收完毕
//             if (xhr.status == 200) {
//                 //console.log('请求成功',xhr.responseText)
//                 if (obj.success) {
//                     obj.success(xhr.responseText)
//                 }
//
//             } else {
//                 //console.log(xhr.status,'请求出错')
//                 if (obj.failure) {
//                     obj.failure('请求失败')
//                 }
//             }
//         }
//     }
//     if (obj.method == undefined || obj.method.toLowerCase() == 'get') {
//         xhr.send(null);
//     } else {
//         xhr.send(obj.params);
//
//     }
// }