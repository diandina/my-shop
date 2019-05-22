<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html"><h1><img src="/static/images/logo.png" alt="" /></h1></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <div class="top-search">
                <form class="navbar-form navbar-right" action="/index/movie/search">
                    <input type="text" class="form-control" placeholder="搜索..." name="name" id="name">
                    <input type="submit" value=" ">
                </form>
            </div>
            <div class="header-top-right">
                <c:if test="${sessionScope.user.email==null}">
                    <div class="signin">
                        <a href="/index/huiyuan/form" type="button" class="btn btn-sm btn-default">会员注册</a>
                        <!-- pop-up-box -->
                        <script type="text/javascript" src="/static/js/modernizr.custom.min.js"></script>
                        <link href="/static/css/popuo-box.css" rel="stylesheet" type="text/css" media="all" />
                    </div>
                    <div class="signin">
                        <a href="/index/huiyuan/login/form" type="button" class="btn btn-sm btn-default">会员登录</a>
                    </div>
                </c:if>
                <c:if test="${user.email!=null}">
                    <div class="signin">
                        欢迎你：${user.username}&nbsp;&nbsp;&nbsp;
                        <a href="/index/huiyuan/logout" class="btn btn-sm btn-default">注销</a>
                    </div>
                </c:if>

                <div class="clearfix"> </div>
            </div>
        </div>
        <div class="clearfix"> </div>
    </div>
</nav>
<div class="col-sm-3 col-md-2 sidebar" style="position:absolute;height: 1200px">
    <div class="top-navigation">
        <div class="t-menu">MENU</div>
        <div class="t-img">
            <img src="/static/images/lines.png" alt="" />
        </div>
        <div class="clearfix"> </div>
    </div>
    <div class="drop-navigation drop-navigation">
        <ul class="nav nav-sidebar">
            <li class="active"><a href="/index" class="home-icon"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>主页</a></li>
            <li><a href="/index" class="menu1"><span class="glyphicon glyphicon-film" aria-hidden="true"></span>电影 类别</a></li>
            <li><a href="/index/movie/type?id=2" class="menu1"><span class="glyphicon glyphicon-blackboard" aria-hidden="true" style="width: 48px"></span>动作片</a></li>
            <li><a href="/index/movie/type?id=29" class="menu1"><span class="glyphicon glyphicon-blackboard" aria-hidden="true" style="width: 48px"></span>爱情片</a></li>
            <li><a href="/index/movie/type?id=36" class="menu1"><span class="glyphicon glyphicon-blackboard" aria-hidden="true" style="width: 48px"></span>枪战片</a></li>
            <li><a href="/index/movie/type?id=35" class="menu1"><span class="glyphicon glyphicon-blackboard" aria-hidden="true" style="width: 48px"></span>科幻片</a></li>
            <li><a href="/index/movie/type?id=37" class="menu1"><span class="glyphicon glyphicon-blackboard" aria-hidden="true" style="width: 48px"></span>喜剧片</a></li>
            <li><a href="/index/movie/type?id=38" class="menu1"><span class="glyphicon glyphicon-blackboard" aria-hidden="true" style="width: 48px"></span>悬疑片</a></li>
        </ul>
        <!-- script-for-menu -->
        <script>
            $( ".top-navigation" ).click(function() {
                $( ".drop-navigation" ).slideToggle( 300, function() {
                    // Animation complete.
                });
            });
        </script>
    </div>
</div>
<script type="text/javascript">
   /* //滚动插件
    $.ajax({
        url: '/index/catelog/selectAll',
        type: 'GET',
        dataType: 'json',
        success: function (result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            $("#movieType").html('');
            $.each(result,function(index,value){ //index是循环的序数
                $("#list").html($("#list").html() + "<a>" + value.name + "</a>"+"<br>");
            });
        },
        error: function (errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
        }
    });*/
</script>