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
    <link href="http://code.jquery.com/ui/1.10.4/themes/ui-darkness/jquery-ui.css" />
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
            <div>
                <div class="box-header with-border">
                    <h3 class="box-title" style="margin-left: 300px"> 新建留言</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form class="form-horizontal" action="/qiantai/movie/message/add" method="post">
                    <div class="box-body">
                        <div class="form-group">
                            <label class="col-sm-2 control-label" style="font-size: large">电影名称</label>
                            <select style="width: 180px;margin-left: 20px;margin-top: 11px" name="movieName" id="movieName">
                                <option>请选择</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" style="font-size: large">留言内容</label>
                            <div class="col-sm-5">
                                <textarea cols="15" rows="7" name="content" id="content" class="form-control required name"></textarea>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button class="btn btn-default" onclick="javascrtpt:history.go(-1);" style="margin-left: 183px">返回</button>
                        <button type="submit" class="btn btn-info pull-right" style="margin-right: 460px">提交</button>
                    </div>
                    <!-- /.box-footer -->
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<jsp:include page="/WEB-INF/includes/footer.jsp"></jsp:include>
<script src="/static/assets/plugins/dropzone/min/dropzone.min.js"></script>
<script src="/static/assets/plugins/js/jquery-ui.min.js"></script>
</body>
<script>
    $.ajax({
            type:"get",
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            url:"/index/movie/selectAll",
            success:function(result){
                $.each(result,function(index,value){
                    $("#movieName").append("<option value='"+value.id+"'>"+value.name+"</option>");
                })
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
            },
            async:false             //false表示同步
        }

    );
</script>
</html>
