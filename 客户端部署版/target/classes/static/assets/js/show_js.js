

var url1 = location.search;
console.log(url1);
var params = url1.substr(1);
window.onload = function() {
        var insurance_infor = [];
        var tbody = document.getElementById('insurance_tbody');
        var insurance_baoxianqijian;var insurance_id;var insurance_jiaofeifangshi;var insurance_kuanxianqi;
        var insurance_mianzetiaokuan;var insurance_name;var insurance_shouyiren;var insurance_toubaonianling;
        var insurance_youyuqi;var insurance_zerentiaokuan;var insurancecompanyid;var insurancetypeid;

        ajax({
            url : '/rest/appleyk/showbnrs?'+ params,
            success : function(res) {
                // console.log(res);
                var json = eval(res);

                insurance_infor=json;
                 // console.log(insurance_infor);
                $.each(insurance_infor, function (index, n) {
                    var newtr = document.createElement('tr');
                    newtr.id='insurance_tr_id'+index;
                    // insurance_id=insurance_infor[index].insurance_id;
                    insurance_name=insurance_infor[index].insurance_name;
                    insurance_baoxianqijian=insurance_infor[index].insurance_baoxianqijian;
                    insurance_jiaofeifangshi=insurance_infor[index].insurance_jiaofeifangshi;
                    insurance_kuanxianqi=insurance_infor[index].insurance_kuanxianqi;
                    insurance_mianzetiaokuan=insurance_infor[index].insurance_mianzetiaokuan;
                    insurance_shouyiren=insurance_infor[index].insurance_shouyiren;
                    insurance_toubaonianling=insurance_infor[index].insurance_toubaonianling;
                    insurance_youyuqi=insurance_infor[index].insurance_youyuqi;
                    insurance_zerentiaokuan=insurance_infor[index].insurance_zerentiaokuan;
                    // insurancecompanyid=insurance_infor[index].insurancecompanyid;
                    // insurancetypeid=insurance_infor[index].insurancetypeid;

                    var newtdname = document.createElement('td');
                    newtdname.innerHTML =insurance_name;
                    newtdname.setAttribute("title",insurance_name);
                    var newtdbaoxianqijian = document.createElement('td');
                    newtdbaoxianqijian.innerHTML =insurance_baoxianqijian.substring(0,16);
                    if(newtdbaoxianqijian.innerHTML.length>=16) newtdbaoxianqijian.innerHTML+='…';
                    newtdbaoxianqijian.setAttribute("title",insurance_baoxianqijian);
                    var newtdjiaofeifangshi = document.createElement('td');
                    newtdjiaofeifangshi.innerHTML =insurance_jiaofeifangshi.substring(0,16);
                    if(newtdjiaofeifangshi.innerHTML.length>=16) newtdjiaofeifangshi.innerHTML+='…';
                    newtdjiaofeifangshi.setAttribute("title",insurance_jiaofeifangshi);
                    var newtdkuanxianqi = document.createElement('td');
                    newtdkuanxianqi.innerHTML =insurance_kuanxianqi.substring(0,16);
                    if(newtdkuanxianqi.innerHTML.length>=16) newtdkuanxianqi.innerHTML+='…';
                    newtdkuanxianqi.setAttribute("title",insurance_kuanxianqi);
                    var newtdmianzetiaokuan = document.createElement('td');
                    newtdmianzetiaokuan.innerHTML =insurance_mianzetiaokuan.substring(0,16);
                    if(newtdmianzetiaokuan.innerHTML.length>=16) newtdmianzetiaokuan.innerHTML+='…';
                    newtdmianzetiaokuan.setAttribute("title",insurance_mianzetiaokuan);
                    var newtdshouyiren = document.createElement('td');
                    newtdshouyiren.innerHTML =insurance_shouyiren.substring(0,16);
                    if(newtdshouyiren.innerHTML.length>=16) newtdshouyiren.innerHTML+='…';
                    newtdshouyiren.setAttribute("title",insurance_shouyiren);
                    var newtdtoubaonianling = document.createElement('td');
                    newtdtoubaonianling.innerHTML =insurance_toubaonianling;
                    newtdtoubaonianling.setAttribute("title",insurance_toubaonianling);
                    var newtdyouyuqi = document.createElement('td');
                    newtdyouyuqi.innerHTML =insurance_youyuqi.substring(0,16);
                    if(newtdyouyuqi.innerHTML.length>=16) newtdyouyuqi.innerHTML+='…';
                    newtdyouyuqi.setAttribute("title",insurance_youyuqi);
                    var newtdzerentiaokuan = document.createElement('td');
                    newtdzerentiaokuan.innerHTML =insurance_zerentiaokuan.substring(0,16);
                    if(newtdzerentiaokuan.innerHTML.length>=16) newtdzerentiaokuan.innerHTML+='…';
                    newtdzerentiaokuan.setAttribute("title",insurance_zerentiaokuan);



                    // newtr.append(newtdid);
                    newtr.append(newtdname);
                    newtr.append(newtdbaoxianqijian);
                    newtr.append(newtdjiaofeifangshi);
                    newtr.append(newtdkuanxianqi);
                    newtr.append(newtdmianzetiaokuan);
                    newtr.append(newtdshouyiren);
                    newtr.append(newtdtoubaonianling);
                    newtr.append(newtdyouyuqi);
                    newtr.append(newtdzerentiaokuan);
                    // newtr.append(newtdcompanyid);
                    // newtr.append(newtdtypeid);

                    tbody.append(newtr);


                });

                // max count for one page
                var ONE_PAGE_COUNT = 10;
                // init page
                var currPage = 1;

                var newsLis = [];
                newsLis = insurance_infor;
                // total news count
                var count = newsLis.length;
                console.log(count);
                // total pages
                var totalPage = parseInt(count / ONE_PAGE_COUNT) + ((count % ONE_PAGE_COUNT) == 0? 0 : 1);

                console.log(totalPage);

// 传入显示的page参数，显示对应页面的新闻列表，隐藏其他列表
                function scanAllForShow(page) {
                    // page at least 1 or max totalPage
                    page = Math.max(1, Math.min(totalPage, page));
                    for (var i = 0;i < count;i++) {
                        if (parseInt(i / ONE_PAGE_COUNT) + 1 == page){
                            var trstyle = document.getElementById('insurance_tr_id'+i);
                            trstyle.style= "" ;
                        }
                        else{
                            var trstyle = document.getElementById('insurance_tr_id'+i);
                            trstyle.style= 'display: none' ;
                        }
                    }
                }




// 页面加载完成后调用此函数
                function init() {
                    newsLis = insurance_infor;
                    count = newsLis.length;
                    totalPage = parseInt(count / ONE_PAGE_COUNT + ((count % ONE_PAGE_COUNT) == 0 ? 0 : 1));
                    currPage = 1;
                    setUICount(count);
                    setUIPages(totalPage);
                    setUICurrPage(currPage);
                    scanAllForShow(currPage);
                    // 注册点击函数
                    document.getElementById('home').onclick=function (){
                        currPage = 1;
                        scanAllForShow(currPage);
                        setUICurrPage(currPage);
                    };
                    document.getElementById('prev').onclick=function (){
                        var next = currPage;
                        if (next <= 1)
                            return;

                        scanAllForShow(--currPage);

                        setUICurrPage(currPage);
                    };
                    document.getElementById('next').onclick=function (){
                        var last = currPage;
                        if (last == totalPage)
                            return;

                        scanAllForShow(++currPage);

                        setUICurrPage(currPage);
                    };
                    document.getElementById('last').onclick=function (){
                        currPage = totalPage;
                        scanAllForShow(currPage);
                        setUICurrPage(currPage);
                    };
                    document.getElementById('goTo').onclick=function (){
                        var target = document.getElementById('goToPage').value;
                        if (target == undefined)
                            target = currPage;
                        target = Math.max(1, Math.min(totalPage, target));
                        currPage = target;
                        scanAllForShow(target);
                        setUICurrPage(currPage);
                        document.getElementById('goToPage').value="";
                    };

                }
                init();
                console.log(currPage);

            }

        })


}
// function used to set news count
function setUICount(count) {
    if (count == undefined)
        count = 0;
    console.log(count);
    document.getElementById('cp_count').innerHTML = ""+count;
}

// function used to set total pages
function setUIPages(totalPage) {
    totalPage = Math.max(1, totalPage);
    document.getElementById('total-page').innerHTML = totalPage;
}

// update curr page
function setUICurrPage(currPage) {
    currPage = Math.max(1, currPage);
    document.getElementById('curr-page').innerHTML = currPage;
}
document.getElementById('btn').onclick = function(){
    var select_by_name = document.getElementById('inname').value;
    console.log(select_by_name);
     ajax({
    //     // url : '/rest/appleyk/showbnrs?'+ params,
          url : '/rest/appleyk/getInByName?name='+ select_by_name,
         success : function(res) {
              //console.log(res);
             var json = eval("(" + res + ")");
             //console.log(json);
                document.getElementById('insurance_tbody').style = 'display: none';
                document.getElementById('insurance_tbody2').style = '';
                var tbody2 = document.getElementById('insurance_tbody2');
                var newtr = document.createElement('tr');
            var newtdname = document.createElement('td');
            newtdname.innerHTML =select_by_name;
            // newtdname.setAttribute("title",insurance_name);
            var newtdbaoxianqijian = document.createElement('td');
            newtdbaoxianqijian.innerHTML =json.in_baoxianqijian;
            // newtdbaoxianqijian.setAttribute("title",insurance_baoxianqijian);
            var newtdjiaofeifangshi = document.createElement('td');
            newtdjiaofeifangshi.innerHTML =json.in_jiaofeifangshi;
            // newtdjiaofeifangshi.setAttribute("title",insurance_jiaofeifangshi);
            var newtdkuanxianqi = document.createElement('td');
            newtdkuanxianqi.innerHTML =json.in_kuanxianqi;
            // newtdkuanxianqi.setAttribute("title",insurance_kuanxianqi);
            var newtdmianzetiaokuan = document.createElement('td');
            newtdmianzetiaokuan.innerHTML =json.in_mianzetiaokuan;

            // newtdmianzetiaokuan.setAttribute("title",insurance_mianzetiaokuan);
            var newtdshouyiren = document.createElement('td');
            newtdshouyiren.innerHTML =json.in_shouyiren;
            // newtdshouyiren.setAttribute("title",insurance_shouyiren);
            var newtdtoubaonianling = document.createElement('td');
            newtdtoubaonianling.innerHTML =json.in_toubaonianling;
            // newtdtoubaonianling.setAttribute("title",insurance_toubaonianling);
            var newtdyouyuqi = document.createElement('td');
            newtdyouyuqi.innerHTML =json.in_youyuqi;
            // newtdyouyuqi.setAttribute("title",insurance_youyuqi);
            var newtdzerentiaokuan = document.createElement('td');
            newtdzerentiaokuan.innerHTML =json.in_zerentiaokuan;
            // newtdzerentiaokuan.setAttribute("title",insurance_zerentiaokuan);

                newtr.append(newtdname);
                newtr.append(newtdbaoxianqijian);
                newtr.append(newtdjiaofeifangshi);
                newtr.append(newtdkuanxianqi);
                newtr.append(newtdmianzetiaokuan);
                newtr.append(newtdshouyiren);
                newtr.append(newtdtoubaonianling);
                newtr.append(newtdyouyuqi);
                newtr.append(newtdzerentiaokuan);

                tbody2.append(newtr);
                document.getElementById('div_pages').style = 'display: none';
        // },
    //     error: function() {
    //         alert("失败")
         }
     });
};
document.getElementById('btn2').onclick = function(){
    document.getElementById('inname').value = null;
    document.getElementById('insurance_tbody').style = '';
    document.getElementById('insurance_tbody2').style = 'display: none';
    document.getElementById('div_pages').style = '';
};
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
    };
    if (obj.method == undefined || obj.method.toLowerCase() == 'get') {
        xhr.send(null);
    } else {
        xhr.send(obj.params);

    }
}