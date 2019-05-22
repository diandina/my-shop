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
    <link href="/static/css/seat.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/site/navAndLeft.jsp"></jsp:include>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="height: 800px">
    <div class="main-grids" style="height: 800px">
        <div class="top-grids">
            <jsp:include page="/WEB-INF/views/site/head.jsp"></jsp:include>
            <div class="demo">
                <div id="seat-map">
                    <div class="front">屏幕</div>
                </div>
                <div class="booking-details">
                    <label style="color: darkgray;font-size: 15px">影片：<span>${tbMovieInfo.name}</span></label>
                    <label style="color: darkgray;font-size: 15px">时间：<span>${tbMovieInfo.showTime}</span></label>
                    <form action="/qiantai/movie/todetail">
                        <input type="hidden" name="id" value="${tbMovieInfo.id}">
                        <input type="hidden" name="seatNum" id="seatNum">
                        <p>座位：</p>
                        <ul id="selected-seats" name="selected-seats"></ul>
                        <label style="color: darkgray;font-size: 15px">票数：</label><input id="counter" name="counter" style="border: none;color: darkgray;font-size: 14px" value="0"/>
                        <p>总计：<b>￥<label id="total">0</label></b></p>
                        <button class="checkout-button" type="submit">确定购买</button>
                    </form>
                    <%--<p>票数：<label id="counter">0</label></p>--%>

                    <div id="legend"></div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script src="/static/js/jquery.seat-charts.js" type="text/javascript"></script>
<script type="text/javascript">
    function check(){
        var num=document.getElementById("num").value;
        if(${user.email==null}){
            alert("请先登录");
        }else{
            window.location.href="/qiantai/movie/mychart?id=${tbMovieInfo.id}&num="+num;
        }
    }
    var price = ${tbMovieInfo.price}; //票价
    $(document).ready(function() {
        var $cart = $('#selected-seats'), //座位区
            $counter = $('#counter'), //票数
            $total = $('#total'); //总计金额
        var array=new Array();
        var sc = $('#seat-map').seatCharts({
            map: [  //座位图
                'aaaaaaaaaa',
                'aaaaaaaaaa',
                '__________',
                'aaaaaaaa__',
                'aaaaaaaaaa',
                'aaaaaaaaaa',
                'aaaaaaaaaa',
                'aaaaaaaaaa',
                'aaaaaaaaaa',
                'aa__aa__aa'
            ],
            legend : { //定义图例
                node : $('#legend'),
                items : [
                    [ 'a', 'available',   '可选座' ],
                    [ 'a', 'unavailable', '已售出']
                ]
            },
            click: function () { //点击事件
                if (this.status() == 'available') { //可选座
                    $('<li>'+(this.settings.row+1)+'排'+this.settings.label+'座</li>')
                        .attr('id', 'cart-item-'+this.settings.id)
                        .data('seatId', this.settings.id)
                        .appendTo($cart);
                    $counter.val(sc.find('selected').length+1);
                    $total.text(recalculateTotal(sc)+price);
                    array.push(this.settings.id);
                    $('#seatNum').val(array);
                    return 'selected';
                } else if (this.status() == 'selected') { //已选中
                    //更新数量
                    $counter.val(sc.find('selected').length-1);
                    //更新总计
                    $total.text(recalculateTotal(sc)-price);

                    //删除已预订座位
                    $('#cart-item-'+this.settings.id).remove();
                    array=remove(array,this.settings.id);
                    $('#seatNum').val(array);
                    //可选座
                    return 'available';
                } else if (this.status() == 'unavailable') { //已售出
                    return 'unavailable';
                } else {
                    return this.style();
                }
            }
        });
        //已售出的座位
        <c:forEach items="${mapString}" var="t">
        sc.get('${t}').status('unavailable');
        </c:forEach>
    });

    //移除数组中特定元素
    function remove(arr, item) {
        var result=[];
        for(var i=0; i<arr.length; i++){
            if(arr[i]!=item){
                result.push(arr[i]);
            }
        }
        return result;
    }

    //计算总金额
    function recalculateTotal(sc) {
        var total = 0;
        sc.find('selected').each(function () {
            total += price;
        });

        return total;
    }
</script>
</html>
