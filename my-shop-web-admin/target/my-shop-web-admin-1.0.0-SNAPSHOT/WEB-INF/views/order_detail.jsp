<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

    <title>电影院售票| 订单详情</title>
    <jsp:include page="../includes/header.jsp"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="box-body">
    <table class="table" id="dataTable">
        <tbody>
            <tr>
                <td>电影名称:</td>
                <td>${tbMovieInfo.name}</td>
            </tr>
            <tr>
                <td>放映日期:</td>
                <td>${tbMovieInfo.showTime}</td>
            </tr>
            <tr>
                <td>订票数量:</td>
                <td>${tbOrderItem.num}</td>
            </tr>
            <tr>
                <td>单价：</td>
                <td>${tbOrderItem.price}</td>
            </tr>
            <tr>
                <td>总价：</td>
                <td>${tbOrderItem.totalFee}</td>
            </tr>
            <tr>
                <td>消费用户:</td>
                <td>${tbUser.username}</td>
            </tr>
        </tbody>
    </table>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
</body>
</html>
