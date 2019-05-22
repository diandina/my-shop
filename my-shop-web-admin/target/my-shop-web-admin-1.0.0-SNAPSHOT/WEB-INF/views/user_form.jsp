<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>

    <title>我的商城| 用户管理</title>
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
                            <h3 class="box-title"> ${tbUser.id==null?"新增":"编辑"}用户</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" class="form-horizontal" action="/user/save" method="post" modelAttribute="tbUser">
                            <form:hidden path="id"/>
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">邮箱</label>

                                    <div class="col-sm-5">
                                        <form:input path="email" class="form-control required email" placeholder="请输入邮箱"></form:input>
                                    </div>
                                </div>
                                <c:if test="${tbUser.id==null}">
                                    <div class="form-group">
                                        <label for="password" class="col-sm-2 control-label">密码</label>

                                        <div class="col-sm-5">
                                            <form:password path="password" class="form-control required"  placeholder="请输入密码"></form:password>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="form-group">
                                    <label for="username" class="col-sm-2 control-label">姓名</label>

                                    <div class="col-sm-5">
                                        <form:input path="username" class="form-control required"  placeholder="请输入姓名"></form:input>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="phone" class="col-sm-2 control-label">手机号</label>

                                    <div class="col-sm-5">
                                        <form:input path="phone" class="form-control required mobile" placeholder="请输入手机号"></form:input>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">管理员</label>
                                    <div class="col-sm-5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="hidden" id="admin">
                                        <c:if test="${tbUser.admin==true}">
                                            <input type="radio" name="admin" id="is" value="1" style="width: 20px;height: 20px" checked="checked">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="radio" name="admin" id="isNot" value="0" style="width: 20px;height: 20px">否
                                        </c:if>
                                        <c:if test="${tbUser.admin==false}">
                                            <input type="radio" name="admin" id="is" value="1" style="width: 20px;height: 20px">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="radio" name="admin" id="isNot" value="0" style="width: 20px;height: 20px" checked="checked">否
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="javascrtpt:window.location.href='http://localhost:8080/user/list'">返回</button>
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
<script>
    /*$(function(){
        Validate.init();
    });*/
</script>
</body>
</html>
