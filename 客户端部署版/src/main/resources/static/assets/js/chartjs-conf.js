// import Chart from 'Chart.js'


var defaults1 = {
    scaleOverlay : false,
    scaleOverride : false,
    scaleSteps : null,
    scaleStepWidth : null,
    scaleStartValue : null,
    scaleLineColor : "rgba(0,0,0,.1)",
    scaleLineWidth : 1,
    scaleShowLabels : true,
    scaleLabel : "<%=value%>",
    scaleFontFamily : "'Arial'",
    scaleFontSize : 12,
    scaleFontStyle : "normal",
    scaleFontColor : "#666",
    scaleShowGridLines : true,
    scaleGridLineColor : "rgba(0,0,0,.05)",
    scaleGridLineWidth : 1,
    bezierCurve : true,
    pointDot : true,
    pointDotRadius : 4,
    pointDotStrokeWidth : 2,
    datasetStroke : true,
    datasetStrokeWidth : 2,
    datasetFill : true,
    animation : true,
    animationSteps : 60,
    animationEasing : "easeOutQuart",
    onAnimationComplete : null
};
var defaults2 = {
    scaleOverlay : false,
    scaleOverride : false,
    scaleSteps : null,
    scaleStepWidth : null,
    scaleStartValue : null,
    scaleLineColor : "rgba(0,0,0,.1)",
    scaleLineWidth : 1,
    scaleShowLabels : true,
    scaleLabel : "<%=value%>",
    scaleFontFamily : "'Arial'",
    scaleFontSize : 12,
    scaleFontStyle : "normal",
    scaleFontColor : "#666",
    scaleShowGridLines : true,
    scaleGridLineColor : "rgba(0,0,0,.05)",
    scaleGridLineWidth : 1,
    barShowStroke : true,
    barStrokeWidth : 2,
    barValueSpacing : 5,
    barDatasetSpacing : 1,
    animation : true,
    animationSteps : 60,
    animationEasing : "easeOutQuart",
    onAnimationComplete : null
};
var defaults3 = {
    scaleOverlay : false,
    scaleOverride : false,
    scaleSteps : null,
    scaleStepWidth : null,
    scaleStartValue : null,
    scaleShowLine : true,
    scaleLineColor : "rgba(0,0,0,.1)",
    scaleLineWidth : 1,
    scaleShowLabels : false,
    scaleLabel : "<%=value%>",
    scaleFontFamily : "'Arial'",
    scaleFontSize : 12,
    scaleFontStyle : "normal",
    scaleFontColor : "#666",
    scaleShowLabelBackdrop : true,
    scaleBackdropColor : "rgba(255,255,255,0.75)",
    scaleBackdropPaddingY : 2,
    scaleBackdropPaddingX : 2,
    angleShowLineOut : true,
    angleLineColor : "rgba(0,0,0,.1)",
    angleLineWidth : 1,
    pointLabelFontFamily : "'Arial'",
    pointLabelFontStyle : "normal",
    pointLabelFontSize : 12,
    pointLabelFontColor : "#666",
    pointDot : true,
    pointDotRadius : 3,
    pointDotStrokeWidth : 1,
    datasetStroke : true,
    datasetStrokeWidth : 2,
    datasetFill : true,
    animation : true,
    animationSteps : 60,
    animationEasing : "easeOutQuart",
    onAnimationComplete : null
};



window.onload = function () {
    var bardata = new Array();
    var barlabel = new Array();
    var radardata = new Array();
    var radarlabel = new Array();
     var map = {26: "试试", 48: "emm", 90: "等等", 60: "hh", 103: "略略", 78: "看看", 56: "嘻嘻"};
    var url1 = location.search;
    console.log(url1);
    var params = url1.substr(1);


    ajax({
        url : '/rest/appleyk/insurance/getComInNumByAge?'+params,
        success : function(res) {
            console.log(res);
            var json = eval("(" + res + ")");
            var j=0;
            $.each(json, function (key, value) {
                barlabel[j] = key;
                bardata[j] = value;
                j++;

            });

            var barChartData = {
                labels: barlabel,
                datasets: [
                    {
                        fillColor: "rgba(151,187,205,0.5)",
                        strokeColor: "rgba(151,187,205,1)",
                        data: bardata

                    }
                ]

            };
            var bar1 = document.getElementById("bar").getContext("2d");
            window.myBar = new Chart(bar1).Bar(barChartData,defaults2);


        }
    });

    ajax({
        url : '/rest/appleyk/insurance/getComInsurNumByTypes?'+params,
        success : function(res) {
            console.log(res);
            var jsonr = eval("(" + res + ")");
            var k=0;
            $.each(jsonr, function (key, value) {
                radarlabel[k]= key;
                radardata[k] = value;
                k++;
            });


            var radarChartData = {
               labels :radarlabel,
                // labels : ["医疗保险","人寿保险","教育保险","交通保险","养老保险","意外保险","重症保险"],
                datasets : [
                    {
                        fillColor : "rgba(151,187,205,0.5)",
                        strokeColor : "rgba(151,187,205,1)",
                        pointColor : "rgba(151,187,205,1)",
                        pointStrokeColor : "#fff",
                        data :radardata
                        // data : [68,78,70,99,96,74,100]
                    }
                ]

            };


            var radar1 = document.getElementById("radar").getContext("2d");
            window.myRadar = new Chart(radar1).Radar(radarChartData,defaults3);



        }
    });

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