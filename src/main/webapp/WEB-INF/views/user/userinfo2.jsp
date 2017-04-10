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
    <script type="text/javascript"
            src="../../resources/bootstrap-table-develop/locale/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript">
        function initTable() {
            //先销毁表格
            $('#table').bootstrapTable('destroy');
            //初始化表格,动态从服务器加载数据
            $("#table").bootstrapTable({
                method: "get",  //使用get请求到服务器获取数据
                url: "<c:url value='/admin/userInfo'/>", //获取数据的Servlet地址
                striped: true,  //表格显示条纹
                pagination: true, //启动分页
                pageSize: 5,  //每页显示的记录数
                pageNumber:1, //当前第几页
                pageList: [5, 10, 15],  //记录数可选列表
                search: true,  //是否启用查询
                showColumns: true,  //显示下拉框勾选要显示的列
                showRefresh: true,  //显示刷新按钮
                showToggle:true,//显示card还是table
                sidePagination: "server", //表示服务端请求
                clickToSelect: true,
                //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                //设置为limit可以获取limit, offset, search, sort, order
                queryParamsType : "undefined",
                queryParams: function queryParams(params) {   //设置查询参数
                    var param = {
                        pageNumber: params.pageNumber,
                        pageSize: params.pageSize
                    };
                    return param;
                },
                onLoadSuccess: function(){  //加载成功时执行


                },
                onLoadError: function(){  //加载失败时执行

                }
            });
        }

        $(document).ready(function () {
            //调用函数，初始化表格
            initTable();
            //当点击查询按钮的时候执行
            $("#search").bind("click", initTable);
        });
    </script>
    <style>
        table {table-layout: fixed;word-break: break-all;word-wrap: break-word}
    </style>
</head>

<body>
<div class="panel-body" style="padding-bottom:0px;">
    <table id="table" data-toggle="table" >
        <thead>
        <tr>
            <th data-field="userid">id</th>
            <th data-field="openid">微信号</th>
            <th data-field="nickname">昵称</th>
            <th data-field="sex">性别</th>
            <th data-field="address">地址</th>
            <th data-field="subscribe">关注状态</th>
            <th data-field="subscribe_time">关注时间</th>
            <th data-field="lang">语言</th>
            <%--<th data-field="headimgurl">头像</th>--%>
            <th data-field="remark">备注</th>
            <th data-field="unionid">平台id</th>
            <th data-field="groupid">分组</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>  