<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tags-sys" tagdir="/WEB-INF/tags/sys" %>


<!DOCTYPE html>
<html>
<head>

    <title>电影院管理| 电影信息管理</title>
    <jsp:include page="../includes/header.jsp"></jsp:include>
    <link rel="stylesheet" href="/static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css"/>
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
                电影信息管理
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

                    <div class="box" style="margin-top: 15px">
                        <div class="box-header">
                            <h3 class="box-title">电影信息列表</h3>
                        </div>
                        <div class="box-body">
                            <a href="/movieInfo/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus"></i>新增</a>&nbsp;&nbsp;&nbsp;
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table class="table table-hover" id="treeTable">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>放映日期</th>
                                    <th>电影名称</th>
                                    <th>剧情介绍</th>
                                    <th>图片</th>
                                    <th>影票价格</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tbMovieInfos}" var="tbMovieInfo" varStatus="stauts">
                                    <tr id="${tbMovieInfo.id}">
                                        <td>${stauts.count}</td>
                                        <td>${tbMovieInfo.showTime}</td>
                                        <td>${tbMovieInfo.name}</td>
                                        <td style="overflow: hidden;text-overflow: ellipsis;width: 300px;white-space: nowrap;display: block">${tbMovieInfo.introduce}</td>
                                        <c:if test="${not empty tbMovieInfo.pic}">
                                            <td><a href="${tbMovieInfo.pic}">查看</a></td>
                                        </c:if>
                                        <c:if test="${empty tbMovieInfo.pic}">
                                            <td></td>
                                        </c:if>
                                        <td>${tbMovieInfo.price}</td>
                                        <td>
                                            <button onclick="App.deleteSingle('/movieInfo/deleteById',${tbMovieInfo.id})" type="button" class="btn btn-xs btn-danger"><i class="fa fa-delete"></i>删除</button>&nbsp;&nbsp;&nbsp;
                                        </td>
                                    </tr>
                                </c:forEach>
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
</body>
<script type="text/javascript">
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
</script>
</html>