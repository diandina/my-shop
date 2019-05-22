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
</head>
<body>
<jsp:include page="/WEB-INF/views/site/navAndLeft.jsp"></jsp:include>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="height: 800px">
    <div class="main-grids" style="height: 800px">
        <div class="top-grids">
            <jsp:include page="/WEB-INF/views/site/head.jsp"></jsp:include>
            <div class="box-body table-responsive" id="tableInfo">
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
<script type="text/javascript">
    var url= window.location.href;
    var lastDigits = url.substring(url.lastIndexOf('?') + 6);
    $.ajax({    //使用JQuery内置的Ajax方法
        type: "get",        //post请求方式
        async: false,        //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "/index/movie/selectAllByName?name="+lastDigits,    //请求发送到ShowInfoIndexServlet处
        dataType: "json",        //返回数据形式为json
        success: function (result) {
            $("#name").val(decodeURI(lastDigits));
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result != null && result.length > 0) {
                //动态生成表格
                var tableInfos = document.getElementById('tableInfo');
                var code = '<TABLE style="width:900px;height:350px;" border="0">';
                var row = result.length % 4 == 0 ? result.length / 4 : Math.floor(result.length / 4) + 1;
                for (var i = 0; i < row; i++) {
                    code += '<TR>';
                    for (var j = 0; j < 4; j++) {
                        if(4*i+j<result.length){
                            code += '<TD>' +
                                '<div class="col-md-12 resent-grid recommended-grid slider-top-grids" style="width: 270px;">\n' +
                                '<div class="resent-grid-img recommended-grid-img">\n' +
                                '<a href="single.html"><img src="' + result[4 * i + j].pic + '" alt="" /></a>\n' +
                                '                            </div>\n' +
                                '                            <div class="resent-grid-info recommended-grid-info">\n' +
                                '                                <h3><a href="/qiantai/movie/detail?id='+result[4 * i + j].id +'" class="title title-info">'
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
            } else {

                alert("无数据！");

            }
        }
    });

</script>
</body>
</html>
