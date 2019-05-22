<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/30 0030
  Time: 下午 2:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="My Play Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- bootstrap -->
    <link href="/static/css/bootstrap.min.css" rel='stylesheet' type='text/css' media="all" />
    <!-- //bootstrap -->
    <link href="/static/css/dashboard.css" rel="stylesheet">
    <!-- Custom Theme files -->
    <link href="/static/css/style.css" rel='stylesheet' type='text/css' media="all" />
    <script src="/static/js/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="/static/assets/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <!--start-smoth-scrolling-->
    <link rel="stylesheet" href="/static/assets/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <style>
        *{
            padding:0;
            margin:0;
        }
        .container{
            width:800px;
            height:380px;
            overflow: hidden;
            position:relative;
            margin:0 auto;
            margin-left: 3px;
        }
        .list{
            width:4000px;
            height:380px;
            position:absolute;

        }
        .list>img{
            float:left;
            width:800px;
            height:380px;
        }
        .pointer{
            position:absolute;
            width:100px;
            bottom:20px;
            left:370px;
        }
        .pointer>span{
            cursor:pointer;
            display:inline-block;
            width:10px;
            height:10px;
            background: #7b7d80;
            border-radius:50%;
            border:1px solid #fff;
        }
        .pointer .on{
            background: #28a4c9;
        }
        .arrow{
            position:absolute;
            text-decoration:none;
            width:40px;
            height:40px;
            background: #727d8f;
            color:#fff;
            font-weight: bold;
            line-height:40px;
            text-align:center;
            top:180px;
            display:none;
        }
        .arrow:hover{
            background: #0f0f0f;
        }
        .left{
            left:0;
        }
        .right{
            right:0;
        }
        .container:hover .arrow{
            display:block;
        }
    </style>

</head>
<body>
<jsp:include page="/WEB-INF/views/site/navAndLeft.jsp"></jsp:include>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="height: 800px">
    <div class="main-grids" style="height: 800px">
        <div class="top-grids">
            <jsp:include page="/WEB-INF/views/site/head.jsp"></jsp:include>
            <div class="container">
                <div class="list" style="left:0px;" id="list">
                </div>
                <div class="pointer">
                    <span index="1" class="on"></span>
                    <span index="2"></span>
                    <span index="3"></span>
                    <span index="4"></span>
                    <span index="5"></span>
                </div>
                <a href="#" rel="external nofollow" rel="external nofollow" class="arrow left"><</a>
                <a href="#" rel="external nofollow" rel="external nofollow" class="arrow right">></a>
            </div>

            <div class="box-body table-responsive" id="tableInfo" style="margin-top: 10px">
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="/static/js/unslider.min.js"></script>
<script>
    var imgCount = 5;
    var index = 1;
    var intervalId;
    var buttonSpan = $('.pointer')[0].children;//htmlCollection 集合
    //自动轮播功能 使用定时器
    autoNextPage();
    function autoNextPage(){
        intervalId = setInterval(function(){
            nextPage(true);
        },5000);
    }
    //当鼠标移入 停止轮播
    $('.container').mouseover(function(){
        console.log('hah');
        clearInterval(intervalId);
    });
    // 当鼠标移出，开始轮播
    $('.container').mouseout(function(){
        autoNextPage();
    });
    //点击下一页 上一页的功能
    $('.left').click(function(){
        nextPage(false);
    });
    $('.right').click(function(){
        nextPage(true);
    });
    //小圆点的相应功能 事件委托
    clickButtons();
    function clickButtons(){
        var length = buttonSpan.length;
        for(var i=0;i<length;i++){
            buttonSpan[i].onclick = function(){
                $(buttonSpan[index-1]).removeClass('on');
                if($(this).attr('index')==1){
                    index = 5;
                }else{
                    index = $(this).attr('index')-1;
                }
                nextPage(true);

            };
        }
    }
    function nextPage(next){
        var targetLeft = 0;
        //当前的圆点去掉on样式
        $(buttonSpan[index-1]).removeClass('on');
        if(next){//往后走
            if(index == 5){//到最后一张，直接跳到第一张
                targetLeft = 0;
                index = 1;
            }else{
                index++;
                targetLeft = -800*(index-1);
            }

        }else{//往前走
            if(index == 1){//在第一张，直接跳到第五张
                index = 5;
                targetLeft = -800*(imgCount-1);
            }else{
                index--;
                targetLeft = -800*(index-1);
            }

        }
        $('.list').animate({left:targetLeft+'px'});
        //更新后的圆点加上样式
        $(buttonSpan[index-1]).addClass('on');


    }

    $.ajax({    //使用JQuery内置的Ajax方法
        type: "get",        //post请求方式
        async: false,        //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "/index/movie/selectAll",    //请求发送到ShowInfoIndexServlet处
        dataType: "json",        //返回数据形式为json
        success: function (result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result != null && result.length > 0) {
                //动态生成表格
                var tableInfos = document.getElementById('tableInfo');
                var code = '<TABLE style="width:900px;height:350px;" border="0">';
                var row = result.length % 4 == 0 ? result.length / 4 : Math.floor(result.length / 4) + 1;
                for (var i = 0; i < row; i++) {
                    code += '<TR>';
                    for (var j = 5; j < 9; j++) {
                        if (4 * i + j < result.length) {
                            code += '<TD>' +
                                '<div class="col-md-12 resent-grid recommended-grid slider-top-grids" style="width: 270px;">\n' +
                                '<div class="resent-grid-img recommended-grid-img">\n' +
                                '<a href="/qiantai/movie/detail?id=' + result[4 * i + j].id + '"><img src="' + result[4 * i + j].pic + '" alt="" /></a>\n' +
                                '                            </div>\n' +
                                '                            <div class="resent-grid-info recommended-grid-info">\n' +
                                '                                <h3><a href="/qiantai/movie/detail?id=' + result[4 * i + j].id + '" class="title title-info">'
                                + result[4 * i + j].name + '</a></h3>\n' +
                                '                                <p class="glyphicon glyphicon-time" style="color: dodgerblue;">'
                                + result[4 * i + j].showTime + '</p>\n' +
                                '                            </div>\n' +
                                '                        </div>' + '</TD>';
                        }
                    }
                    code += '</TR>';
                }
                tableInfos.innerHTML = code + '</TABLE>';

                var list = document.getElementById('list');
                for (var i = 0; i < 5; i++) {
                    var img=document.createElement("img");
                    img.setAttribute("id",result[i].id);
                    img.src=result[i].pic;
                    (function(i){
                        //删除操作
                        img.onclick=function(){
                            window.location.href="/qiantai/movie/detail?id="+result[i].id;
                        }
                    })(i);
                    list.append(img);
                }
            } else {

                alert("无数据！");
            }
        }
    });

</script>
</body>
</html>
