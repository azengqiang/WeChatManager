<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户基本信息</title>
    <link type="text/css" href="../../resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
${user}

<style>
    table {
        table-layout: fixed;
        word-break: break-all;
        word-wrap: break-word;
    }

</style>
<table class="table table-striped">
    <caption> 用户信息列表</caption>

    <thead>
    <tr>
        <th width="15%">openid</th>
        <th width="10%">昵称</th>
        <th width="10%">性别</th>
        <th width="10%">地址</th>
        <th width="10%">头像</th>
        <th width="10%">备注</th>
        <th width="10%">分组</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userInfos}" var="user">
        <tr style="height: 50px">
            <td>${user.openid}</td>
            <td>${user.nickname}</td>
            <td>${user.sex}</td>
            <td>${user.address}</td>
            <td width="70px" height="70px"><img src="${user.headimgurl}" width="70px" height="70px"/>
            </td>
            <td>${user.remark}</td>
            <td>${user.groupid}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>  