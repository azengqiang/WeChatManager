<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/jquery-3.1.1.min.js"></script>
    <link type="text/css" href="../../resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <link type="text/css" href="../../resources/bootstrap-table-develop/bootstrap-table.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/bootstrap-table-develop/bootstrap-table.js"></script>
    <script type="text/javascript"
            src="../../resources/bootstrap-table-develop/locale/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript">
        function initTable() {
            $('#table').bootstrapTable('destroy');

            $("#table").bootstrapTable({
                method: "get",
                url: "<c:url value='/admin/lookMessage'/>",
                striped: true,
                pagination: true,
                pageSize: 5,
                pageNumber:1,
                pageList: [5, 10, 15],
                search: true,
                showColumns: true,
                showRefresh: true,
                showToggle:true,
                sidePagination: "server",
                height:600,
                clickToSelect: true,
                queryParamsType : "undefined",
                editabe:true,
                columns: [{
                            field:"msgid",
                            title:"序号",
                            align:"center",
                          /*  formatter: function (value, row, index) {
                                var pageSize =   $("#table").bootstrapTable("getPageSize");
                                var pageNumber =   $("#table").bootstrapTable("getPageNumber");
                                return pageSize * (pageNumber - 1) + index + 1;
                            }*/
                        }, {
                            field:"nickname",
                            title:"用户名",
                            align:"center"
                        }, {
                            field:"userContent",
                            title:"用户发送内容",
                            align:"center"
                        }, {
                            field:"robotContent",
                            title:"机器人回复内容",
                            align:"center"
                        }, {
                            field:"creationDate",
                            title:"创建时间",
                            align:"center"
                        }
                    ],
                queryParams: function queryParams(params) {
                    var param = {
                        pageNumber: params.pageNumber,
                        pageSize: params.pageSize
                    };
                    return param;
                },
                onLoadSuccess: function(){


                },
                onLoadError: function(){
                }
            });
        }

        $(document).ready(function () {
            initTable();
            $("#search").bind("click", initTable);
        });
    </script>
    <style>
        table {table-layout: fixed;word-break: break-all;word-wrap: break-word}
    </style>
</head>

<body>
<div class="panel-body" style="padding-bottom:0px;">
    <table id="table" data-toggle="table">
        <thead>
        <tr>
            <th data-field="msgid">消息id</th>
            <th data-field="nickname">用户名</th>
            <th data-field="userContent">用户发送内容</th>
            <th data-field="robotContent">机器人回复内容</th>
            <th data-field="creationDate">创建时间</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>  