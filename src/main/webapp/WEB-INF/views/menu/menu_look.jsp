<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/jquery-3.1.1.min.js"></script>
    <link type="text/css" href="../../resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <link type="text/css" href="../../resources/bootstrap-table-develop/bootstrap-table.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/bootstrap-table-develop/bootstrap-table.js"></script>
    <script type="text/javascript"
            src="../../resources/bootstrap-table-develop/locale/bootstrap-table-zh-CN.js"></script>
    <style>
        table {
            table-layout: fixed;
            word-break: break-all;
            word-wrap: break-word;

        }
        table, table td, table th {
            border: 1px solid #000000;
            border-collapse: collapse;
        }
        table tr td {
            overflow:hidden;
            white-space:nowrap;
            text-overflow:ellipsis;
            -o-text-overflow:ellipsis;
            -moz-text-overflow: ellipsis;
            -webkit-text-overflow: ellipsis;
        }
    </style>
</head>
<body>
<button class="btn btn-primary btn-md" data-toggle="modal" data-target="#deleteMenu"  style="margin-top: 10px;">删除自定义菜单</button>
<!-- 信息删除确认 -->
<div class="modal fade" id="deleteMenu">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p>您确认要删除吗？</p>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="url"/>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <a class="btn btn-success" data-dismiss="modal" onclick="deleteMenu()">确定</a>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- 信息删除确认结束 -->
<table class="table table-striped">
    <caption>当前菜单信息</caption>
    <thead>
    <tr>
        <th>菜单名称</th>
        <th>菜单类型</th>
        <th>属性值</th>
        <th>上级菜单名称</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${menuDetails}" var="menuDetail">
        <tr style="height: 50px">
            <td width="15%">${menuDetail.name}</td>
            <td width="15%">${menuDetail.type}</td>
            <td width="15%">${menuDetail.value}</td>
            <td width="15%">${menuDetail.superiorName}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script type="text/javascript">
   var deleteMenu = function(){
       debugger;
       $.ajax({
           type: "post",
           url: "${base.contextPath}/admin/deleteMenu",
           contentType: "application/json",
           success: function (data) {
              // window.location.reload();
           }
       });
    }
</script>
</body>
</html>  