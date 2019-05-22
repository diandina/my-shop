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
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="height: 800px;position: absolute;">
    <div class="main-grids" style="height: 800px">
        <div class="top-grids">
            <jsp:include page="/WEB-INF/views/site/head.jsp"></jsp:include>
            <div><label style="margin-left: 170px;font-size: 30px">我的订单</label></div>
            <div class="box-body table-responsive">
                <div style="margin-left: 30px;font-size: 15px;border: 1px">
                    <c:forEach var="tbOrder" items="${tbOrders}"  varStatus="status">
                        <div style="border:2px solid rgba(249,212,255,0.24);width:400px;padding-left: 60px">
                            <label>订单编号:</label><input type="text" readonly="readonly" value="${tbOrder.orderNum}" style="border: none"><br>
                            <label style="margin-left: 15px">总金额:</label><input type="text" readonly="readonly" value="${tbOrder.totalFee}" style="border: none"><br>
                            <label>下单日期:</label><input type="text" readonly="readonly" value="${tbOrder.time}" style="border: none"><br>
                            <label>付款方式:</label><input type="text" readonly="readonly" value="${tbOrder.payWay}" style="border: none"><br>
                            <c:if test="${tbOrder.status==false || empty tbOrder.status}">
                                <label>订单状态:</label><input type="text" readonly="readonly" value="未受理" style="color:red;border:none;">
                            </c:if>
                            <c:if test="${tbOrder.status==true}">
                                <label>订单状态:</label><input type="text" readonly="readonly" value="已受理" style="border: none">
                            </c:if>
                            <button onclick="see('/qiantai/order/orderListDetail?id=${tbOrder.id}')">订单明细</button>
                            <br>
                        </div><br>
                    </c:forEach>
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
<script>
    function see(url) {
        $.ajax({
            url:url,
            type:"get",
            dataType:"html",
            success:function(data){
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    }
</script>
</body>
</html>
