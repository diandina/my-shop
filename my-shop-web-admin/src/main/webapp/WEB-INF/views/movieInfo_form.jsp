<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tags-sys" tagdir="/WEB-INF/tags/sys" %>

<!DOCTYPE html>
<html>
<head>

    <title>电影院管理系统| 信息管理</title>
    <jsp:include page="../includes/header.jsp"></jsp:include>
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/dropzone.css"/>
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/basic.css"/>
    <link rel="stylesheet" href="/static/assets/plugins/js/jquery-ui.min.css"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/left.jsp"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                信息管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>

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
                            <h3 class="box-title"> ${tbMovieInfo.id==null?"新增":"编辑"}内容</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" class="form-horizontal" action="/movieInfo/save" method="post" modelAttribute="tbMovieInfo">
                            <form:hidden path="id"/>
                            <div class="box-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">上映日期</label>
                                    <div class="col-sm-5">
                                        <form:input path="showTime" type="text" value="${showTime}" class="form-control required "></form:input>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="catelogId" class="col-sm-2 control-label">电影类别</label>
                                    <div class="col-sm-5">
                                        <select id="catelogId" name="catelogId" class="form-control required name">
                                            <option value=''>请选择</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-2 control-label">电影名称</label>

                                    <div class="col-sm-5">
                                        <form:input path="name" class="form-control required name"></form:input>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">剧情介绍</label>

                                    <div class="col-sm-5">
                                        <textarea id="introduce" cols="70" rows="5" name="introduce"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pic" class="col-sm-2 control-label">图片</label>
                                    <div class="col-sm-5">
                                        <form:input path="pic" class="form-control required" placeholder="图片"></form:input>
                                        <div id="dropz" class="dropzone"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="price" class="col-sm-2 control-label">影片价格</label>

                                    <div class="col-sm-5">
                                        <form:input path="price" class="form-control required name"></form:input>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button class="btn btn-default" onclick="javascrtpt:history.go(-1);">返回</button>
                                <button type="submit" class="btn btn-info pull-right">提交</button>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../includes/copyright.jsp"/>
</div>

<jsp:include page="../includes/footer.jsp"></jsp:include>
<script src="/static/assets/plugins/dropzone/min/dropzone.min.js"></script>
<%--<script src="/static/assets/plugins/js/jquery-3.1.1.min.js"></script>--%>
<script src="/static/assets/plugins/js/jquery-ui.min.js"></script>

<script>
    $(function () {
        $.ajax({
            url:'/movie/catelog/listData',
            type:'get',
            success:function(data) {
                $.each(data, function(i, obj) {
                    var option = $('<option />');
                    option.val(obj.id);
                    option.text(obj.name);
                    $('#catelogId').append(option);
                });
            }
        })
    });
    $('#showTime').datepicker({
        minDate: new Date(),
        closeText: '关闭',
        prevText: '&#x3c;上月',
        nextText: '下月&#x3e;',
        currentText: '今天',
        monthNames: ['一月','二月','三月','四月','五月','六月',
            '七月','八月','九月','十月','十一月','十二月'],
        monthNamesShort: ['一','二','三','四','五','六',
            '七','八','九','十','十一','十二'],
        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
        dayNamesMin: ['日','一','二','三','四','五','六'],
        weekHeader: '周',
        dateFormat: 'yy-mm-dd',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: true,
        yearSuffix: '年',
        orientation:'bottom'
    });
    /**
     * 初始化Dropzone
     */
    App.initDropzone({
        id:"#dropz",
        url:"/upload",
        init:function () {
            this.on("success",function (file,data) {
                $("#pic").val(data.fileName);
            });
        }
    });
</script>
</body>
</html>
