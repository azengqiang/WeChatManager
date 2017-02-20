<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户信息</title>
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/jquery-3.1.1.min.js"></script>
    <link type="text/css" href="../../resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <link type="text/css" href="../../resources/bootstrap-table-develop/bootstrap-table.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/bootstrap-table-develop/bootstrap-table.js"></script>
    <script type="text/javascript" src="../../resources/bootstrap-table-develop/locale/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript">
        (function() {
            $('#table').bootstrapTable({
                url: '${pageContext.request.contextPath}/admin/userInfos',         //请求后台的URL（*）
                search: true,  //是否显示搜索框功能
                pagination: true,  //是否分页
                showRefresh: true, //是否显示刷新功能
                showToggle: true,
                showColumns: true,
                columns: [{
                    checkbox: true
                }, {
                    field: 'openid',
                    title: '微信号'
                }, {
                    field: 'nickname',
                    title: '昵称'
                }, {
                    field: 'sex',
                    title: '性别'
                },{
                    field: 'address',
                    title: '地址'
                },{
                    field: 'remark',
                    title: '备注'
                },{
                    field: 'groupid',
                    title: '分组'
                }
                ]
            });
        })

    </script>
</head>

<body>
<div class="panel-body" style="padding-bottom:0px;">
    <table id="table"></table>
</div>
</body>
</html>  