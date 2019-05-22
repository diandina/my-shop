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
            <div><label style="margin-left: 400px;font-size: 30px">留言</label></div>
            <div class="box-body table-responsive">
                <div style="margin-left: 30px;font-size: 15px;border: 1px">
                    <br>
                    <br>
                    <div style="border:2px solid rgba(249,212,255,0.24);padding-left: 60px">
                        <table width="900px">
                            <tr>
                                <th style="width: 60px">序号</th>
                                <th style="width: 60px">留言内容</th>
                                <th style="width: 60px">电影名</th>
                                <th style="width: 60px">留言时间</th>
                                <th style="width: 60px;padding-left: 60px">操作</th>
                            </tr>
                            <c:forEach var="tbMessage" items="${tbMessages}" varStatus="status">
                                <tr>
                                    <td style="width: 60px">${status.index+1}</td>
                                    <td style="width: 60px">${tbMessage.content}</td>
                                    <td style="width: 60px">${tbMessage.movieName}</td>
                                    <td style="width: 60px">${tbMessage.time}</td>
                                    <td style="width: 60px;padding-left: 60px"><a
                                            href="/qiantai/movie/message/messageForm?id=${tbMessage.id}" onclick="return check(this)">回复</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div style="margin-left: 375px;margin-top: 40px;"><a href="/qiantai/movie/message/messageFormAdd">我要留言</a></div>
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
<script>
    function toVaild() {
        var val = document.getElementById("payway").value;
        if (val == "0") {
            alert("请选择付款方式，之后进行提交");
            return false;
        }
        else {
            return true;
        }
    }

    function check(t) {
        if (${user.email==null}) {
            var flag=confirm("请先登录");
            if(flag){
                t.href="/qiantai/movie/message";
            }
            return false;
        } else {
            return true;
        }
    }
</script>
</html>
