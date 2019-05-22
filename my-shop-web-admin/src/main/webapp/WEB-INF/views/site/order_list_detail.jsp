<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

    <title>我的影院| 订单详情</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">

<div class="box-body">
    <table id="dataTable">
        <thead>
        <tr>
            <th style="width: 100px">电影名称</th>
            <th>放映日期</th>
            <th style="width: 80px;text-align: center">订票数量</th>
            <th style="width: 60px">单价</th>
            <th style="width: 60px">总价</th>
            <th>座位号</th>
        </tr>
        </thead>
        <tbody>
                <c:forEach items="${tbOrderItems}" var="t" varStatus="s">
                    <tr>
                    <td>${t.movieName}</td>
                    <td>${t.movieTime}</td>
                    <td style="text-align: center">${t.num}</td>
                    <td>${t.price}</td>
                    <td>${t.totalFee}</td>
                    <td>${map.get(s.index)}</td></tr>
                </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
