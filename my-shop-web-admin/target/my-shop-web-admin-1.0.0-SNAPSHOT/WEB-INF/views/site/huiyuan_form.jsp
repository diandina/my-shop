<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/30 0030
  Time: 下午 2:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    <!--start-smoth-scrolling-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<jsp:include page="/WEB-INF/views/site/navAndLeft.jsp"></jsp:include>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class="main-grids">
        <div class="top-grids">

            <jsp:include page="/WEB-INF/views/site/head.jsp"></jsp:include>
            <div class="clearfix"> </div>
        </div>
    </div>
    <div class="recommended" style="height: 500px">
        <div class="content-wrapper" style="margin-left: 50px">
            <!-- Main content -->
            <section class="content">
                <div class="row">
                    <div class="col-xs-12">
                        <c:if test="${baseResult!=null}">
                            <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                    ${baseResult.message}
                            </div>
                        </c:if>
                        <!-- Horizontal Form -->
                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h3 class="box-title"> ${tbUser.id==null?"注册":"编辑"}用户</h3>
                            </div>
                            <!-- /.box-header -->
                            <!-- form start -->
                            <form:form id="inputForm" class="form-horizontal" action="/index/huiyuan/save" method="post" modelAttribute="tbUser">
                                <form:hidden path="id"/>
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="email" class="col-sm-2 control-label" style="font-size: 15px">邮箱</label>

                                        <div class="col-sm-5">
                                            <form:input path="email" class="form-control required email" placeholder="请输入邮箱"></form:input>
                                        </div>
                                    </div>
                                    <c:if test="${tbUser.id==null}">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label" style="font-size: 15px">密码</label>
                                            <div class="col-sm-5">
                                                <input type="password" id="password" name="password" class="form-control required email" placeholder="请输入密码" style="width: 258px">
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="form-group">
                                        <label for="username" class="col-sm-2 control-label" style="font-size: 15px">姓名</label>

                                        <div class="col-sm-5">
                                            <form:input path="username" class="form-control required"  placeholder="请输入姓名"></form:input>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="phone" class="col-sm-2 control-label" style="font-size: 15px">手机号</label>

                                        <div class="col-sm-5">
                                            <form:input path="phone" class="form-control required mobile" placeholder="请输入手机号"></form:input>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.box-body -->
                                <div class="box-footer">
                                    <button type="button" class="btn btn-default" style="margin-left: 120px" onclick="history.go(-1)">返回</button>
                                    <button type="submit" class="btn btn-info pull-right" style="margin-right: 630px">提交</button>
                                </div>
                                <!-- /.box-footer -->
                            </form:form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>

</div>
<div class="clearfix"> </div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
</body>
</html>
