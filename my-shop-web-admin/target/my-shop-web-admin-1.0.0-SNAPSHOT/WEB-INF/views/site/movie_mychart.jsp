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
            <div><label style="margin-left: 400px;font-size: 30px">我的购物车</label></div>
            <div class="box-body table-responsive">
                <div style="margin-left: 30px;font-size: 15px">
                    <table id="table" style="margin-left: 120px;margin-top: 20px">
                        <thead>
                        <tr style="background-color: lightblue">
                            <th style="width: 200px;padding-left: 40px">电影名称</th>
                            <th style="width: 160px">放映日期</th>
                            <th style="width: 100px">影票价格</th>
                            <th style="width: 100px">订票数量</th>
                            <th style="width: 100px">总金额</th>
                            <th style="width: 100px">操作</th>
                        </tr></thead>
                        <tbody id="tbMain"></tbody>
                    </table>
                    <div style="margin-left: 350px;margin-top: 60px">
                        <a href="/qiantai/movie/delAllMychart">清空</a>
                        <a href="/index">继续</a>
                        <a href="/qiantai/movie/jiezhang">结账</a>
                    </div>
                </div>
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
</body>
<script type="text/javascript">
    $(function () {
        var tbody = document.getElementById('tbMain');
        getDataRow();
        function getDataRow(){
            var name = new Array();
            var showTime = new Array();
            var price = new Array();
            var num = new Array();
            var ids = new Array();
            var totalFee = new Array();
            <c:forEach items="${tbMovieInfoList}" var="t">
            name.push("${t.name}"); //js中可以使用此标签，将EL表达式中的值push到数组中
            ids.push("${t.id}"); //js中可以使用此标签，将EL表达式中的值push到数组中
            showTime.push("${t.showTime}"); //js中可以使用此标签，将EL表达式中的值push到数组中
            price.push("${t.price}"); //js中可以使用此标签，将EL表达式中的值push到数组中
            </c:forEach>

            <c:forEach items="${numList}" var="t">
            num.push("${t}"); //js中可以使用此标签，将EL表达式中的值push到数组中
            </c:forEach>

            <c:forEach items="${totalList}" var="t">
            totalFee.push("${t}"); //js中可以使用此标签，将EL表达式中的值push到数组中
            </c:forEach>

            for(var i=0;i<${tbMovieInfoList.size()};i++){
                var row = document.createElement('tr'); //创建行

                var cell1 = document.createElement('td'); //创建第一列id
                cell1.innerHTML = name[i]; //填充数据
                row.appendChild(cell1); //加入行  ，下面类似

                var cell2 = document.createElement('td');//创建第二列name
                cell2.innerHTML =showTime[i];
                row.appendChild(cell2);

                var cell3 = document.createElement('td');//创建第三列job
                cell3.innerHTML = price[i];
                row.appendChild(cell3);

                var cell4 = document.createElement('td');//创建第四列job
                cell4.innerHTML = num[i];
                row.appendChild(cell4);

                var cell5 = document.createElement('td');//创建第五列job
                cell5.innerHTML = totalFee[i];
                row.appendChild(cell5);

                //到这里，json中的数据已经添加到表格中，下面为每行末尾添加删除按钮

                var cell6 = document.createElement('td');//创建第六列，操作列
                row.appendChild(cell6);
                var btnDel = document.createElement('input'); //创建一个input控件
                btnDel.setAttribute('type','button'); //type="button"
                btnDel.setAttribute('value','删除');

                (function(i){
                    //删除操作
                    btnDel.onclick=function(){
                        if(confirm("确定删除这一行嘛？")){
                            //找到按钮所在行的节点，然后删掉这一行
                            // this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);
                            window.location.href="/qiantai/movie/delMychart?id="+ids[i];
                            //btnDel - td - tr - tbody - 删除(tr)
                            //刷新网页还原。实际操作中，还要删除数据库中数据，实现真正删除
                        }
                    }
                })(i);
                cell6.appendChild(btnDel);  //把删除按钮加入td，别忘了
                tbody.appendChild(row);
            }
        }
    });
    function check(){
        var num=document.getElementById("num").value;
        if(${user.email==null}){
            alert("请先登录");
        }else{
            window.location.href="qiantai/movie/mychart?id=${tbMovieInfo.id}&num="+num;
        }
    }
</script>
</html>
