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
            <div><label style="margin-left: 70px;font-size: 30px">电影详细信息</label></div>
            <div class="box-body table-responsive">
                <div style="margin-left: 30px;font-size: 15px">
                    <img src="${tbMovieInfo.pic}" style="margin-top: 20px;width: 250px;">
                    <div style="float: right;margin-right: 400px">
                        <label>放映日期:${tbMovieInfo.showTime}</label><br>
                        <label>电影名称:${tbMovieInfo.name}</label><br>
                        <label>剧情介绍:</label>
                        <div style="width: 300px">${tbMovieInfo.introduce}</div>
                        <label>影票价格:${tbMovieInfo.price}</label><br>
                        <label>座位号:</label><input type="text" id="seat" readonly="readonly" style="border: none" value="${seatNum}"> <a href="/qiantai/movie/seat?id=${tbMovieInfo.id}">选座</a><br>
                        <label>数&nbsp;&nbsp;&nbsp;量:</label><input type="text" value="${counter}" id="num" style="border: none"> <a onclick="check()">购票</a>
                    </div>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
<div class="clearfix"> </div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
</body>
<script type="text/javascript">
    function check(){
        var num=document.getElementById("num").value;
        var seatNum=document.getElementById("seat").value;
        if(${user.email==null}){
            alert("请先登录");
        }else if(num==""){
            alert("请先选座");
        }
        else{
            window.location.href="/qiantai/movie/mychart?id=${tbMovieInfo.id}&num="+num+"&seatNum="+seatNum;
        }
    }
</script>
</html>
