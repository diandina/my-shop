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
            <div class="box-body table-responsive">
                <div style="border:2px solid rgba(249,212,255,0.24);width:400px;padding-left: 60px">
                <div style="margin-left: 30px;font-size: 15px">
                    <br>
                    <br>
                    <label style="color: red">恭喜您，订单提交成功！</label><br>
                    <label>订单编号:</label><input type="text" readonly="readonly" value="${tbOrder.orderNum}" style="border: none"><br><br>
                    <label style="margin-left: 15px">总金额:</label><input type="text" readonly="readonly" value="${tbOrder.totalFee}" style="border: none"><br><br>
                    <label>下单日期:</label><input type="text" readonly="readonly" value="${tbOrder.time}" style="border: none"><br><br>
                    <label>付款方式:</label><input type="text" readonly="readonly" value="${tbOrder.payWay}" style="border: none"><br><br>
                </div></div>
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
</script>
</html>
