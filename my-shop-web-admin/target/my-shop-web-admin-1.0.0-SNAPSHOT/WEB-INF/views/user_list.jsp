<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tags-sys" tagdir="/WEB-INF/tags/sys" %>


<!DOCTYPE html>
<html>
<head>

    <title>电影院售票系统| 用户管理</title>
    <jsp:include page="../includes/header.jsp"></jsp:include>
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
                用户管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <!-- /.row -->
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult!=null}">
                        <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <div class="box box-info box-info-search" style="display: none">
                        <div class="box-header with-border">
                            <h3 class="box-title">高级搜索</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="row form-horizontal">
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="username" class="col-sm-3 control-label"
                                               style="height: 10px">姓名</label>
                                        <div class="col-sm-9">
                                            <input id="username" class="form-control" placeholder="姓名">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="email" class="col-sm-3 control-label">邮箱</label>

                                        <div class="col-sm-9">
                                            <input id="email" class="form-control" placeholder="邮箱">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="phone" class="col-sm-3 control-label">手机</label>

                                        <div class="col-sm-9">
                                            <input id="phone" class="form-control" placeholder="手机">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            <button type="submit" class="btn btn-info pull-right" onclick="search()">搜索</button>
                        </div>
                        <!-- /.box-footer -->
                    </div>
                    <div class="box" style="margin-top: 15px">
                        <div class="box-header">
                            <h3 class="box-title">用户列表</h3>
                        </div>
                        <div class="box-body">
                            <a href="/user/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus"></i>新增
                            </a>&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-sm btn-default"
                                    onclick="App.deleteMulti('/user/delete')"><i class="fa fa-trash-o"></i>批量删除
                            </button>&nbsp;&nbsp;&nbsp;
                            <button type="button"
                                    onclick="$('.box-info-search').css('display')=='none'?$('.box-info-search').show('fast'):$('.box-info-search').hide('fast')"
                                    class="btn btn-sm btn-primary"><i class="fa fa-search"></i>搜索
                            </button>&nbsp;&nbsp;&nbsp;
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table class="table table-hover" id="dataTable">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal icheck_master"></th>
                                    <th>序号</th>
                                    <th>用户名</th>
                                    <th>手机号</th>
                                    <th>邮箱</th>
                                    <th>更新时间</th>
                                    <th>管理员</th>
                                    <th style="padding-left: 80px">操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="../includes/copyright.jsp"/>
</div>

<jsp:include page="../includes/footer.jsp"></jsp:include>
<%--自定义模态框--%>
<tags-sys:modal/>
<script>
    var _dataTable;
    $(function () {
        var _columns;
        _columns = [
            {
                "data": function (row, type, val, meta) {
                    return '<input type="checkbox" class="minimal" id="' + row.id + '">';
                }
            },
            {
                "data": function (row, type, val, meta) {
                    var startIndex = meta.settings._iDisplayStart;
                    return meta.row + startIndex + 1;
                }
            },
            {"data": "username"},
            {"data": "phone"},
            {"data": "email"},
            {
                "data": function (row, type, val, meta) {
                    return timestampToTime(row.updated);
                }
            },
            {
                "data": function (row, type, val, meta) {
                    if (row.admin) {
                        return '<label style="color: red">是</label>';
                    } else {
                        return "否";
                    }
                }
            },
            {
                "data": function (row, type, val, meta) {
                    var detailUrl = "/user/detail?id=" + row.id;
                    var deleteUrl = "/user/delete?id=" + row.id;
                    return '<button onclick="App.showDetail(\'' + detailUrl + '\')" type="button" class="btn btn-default btn-xs"><i class="fa fa-search">查看</i></button>&nbsp;&nbsp;&nbsp;' +
                        '<a href="/user/form?id=' + row.id + '" type="button" class="btn btn-info btn-xs"><i class="fa fa-edit"></i>编辑</a>&nbsp;&nbsp;&nbsp;' +
                        '<button type="button" class="btn btn-danger btn-xs" onclick="App.deleteSingle(\'' + deleteUrl + '\',\'' + row.id + '\')"><i class="fa fa-trash-o"></i>删除</button>';
                }
            }
        ];
        _dataTable = App.initDataTables("/user/page", _columns);
    });

    /*将时间戳变成日期格式*/
    function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        var Y = date.getFullYear() + '-';
        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var D = date.getDate() + ' ';
        var h = date.getHours() + ':';
        var m = date.getMinutes() + ':';
        var s = date.getSeconds();
        return Y + M + D + h + m + s;
    }

    function search() {
        var username = $("#username").val();
        var phone = $("#phone").val();
        var email = $("#email").val();
        var param = {
            "username": username, "phone": phone, "email": email
        };
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }

</script>
</body>
</html>