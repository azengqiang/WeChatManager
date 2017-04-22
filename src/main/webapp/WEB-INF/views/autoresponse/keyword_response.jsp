<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/jquery-3.1.1.min.js"></script>
    <link type="text/css" href="../../resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <link type="text/css" href="../../resources/bootstrap-table-develop/bootstrap-table.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/bootstrap-table-develop/bootstrap-table.js"></script>
    <script type="text/javascript"
            src="../../resources/bootstrap-table-develop/locale/bootstrap-table-zh-CN.js"></script>
    <script src="../../resources/bootstrap-tree/js/bootstrap-treeview.js"></script>

    <script type="text/javascript">
        function initTable() {
            $('#table').bootstrapTable('destroy');
            /*  var groupName = $("#groupTitle").val();
             var groupid = -1;
             var nowGroupsJson =
            ${groupsJson};
             var groups = eval(nowGroupsJson);
             for (var i = 0; i < groups.length; i++) {
             if (groupName == groups[i].name) {
             groupid = groups[i].id;
             break;
             }
             }*/
            var url = "<c:url value='/admin/groupUserInfo?groupid=-1'/>";
            $("#table").bootstrapTable({
                method: "get",
                url: url,
                striped: true,
                pagination: true,
                pageSize: 3,
                pageNumber: 1,
                pageList: [3, 5, 10],
                search: true,
                sidePagination: "server",
                height: 600,
                queryParamsType: "undefined",
                editabe: true,
                columns: [{
                    field: 'state',
                    checkbox: true,
                    formatter: stateFormatter
                }, {
                    field: 'userid',
                    title: "序号",
                    align: "center",
                    width: "80px",
                    /*  formatter: function (value, row, index) {
                     var pageSize =   $("#table").bootstrapTable("getPageSize");
                     var pageNumber =   $("#table").bootstrapTable("getPageNumber");
                     return pageSize * (pageNumber - 1) + index + 1;
                     }*/
                }, {
                    title: "规则",
                    width: "250px",
                    formatter: function (value, row, index) {
                        var info = '<div><div style="float:left;" ><img class="img-rounded" alt="140x140" style="width: 50px; height: 50px;" ' + 'src="' + row.headimgurl + '"></img></div>'
                                + '<div style="float:left;margin-left: 30px;"><span>昵称: ' + row.nickname + '</span><br>' + '<span>地址: ' + row.address + '</span><br>' + '<span>备注: ' + row.remark + '</span></div>'
                                + '<div style="float:left;margin-left: 30px;"><span>性别: ' + row.sex + '</span><br>' + '<span>关注时间: ' + row.subscribe_time + '</span></div></div>';
                        return info;
                    }

                }, {
                    title: "关键词",
                    align: "center",
                    width: "250px",
                    formatter: function (value, row, index) {
                        var operate = '<a onclick="updateRemark(' + "'" + row.remark + "'" + ",'" + row.openid + "'"
                                + ')">修改备注</a><a style="margin-left: 20px;" '
                                + 'onclick="moveUser(' + "'" + row.openid + "'" + ",'" + row.groupName + "'" + ')">移动分组</a>';
                        return operate;
                    }
                }, {
                    title: "回复",
                    align: "center",
                }
                ],
                queryParams: function queryParams(params) {
                    var param = {
                        pageNumber: params.pageNumber,
                        pageSize: params.pageSize,
                        searchText: params.searchText,
                    };
                    return param;
                },
                onLoadSuccess: function () {


                },
                onLoadError: function () {
                }
            });
        }
        function stateFormatter(value, row, index) {
            if (row.id === 2) {
                return {
                    disabled: true,
                    checked: true
                };
            }
            if (row.id === 0) {
                return {
                    disabled: true,
                    checked: true
                }
            }
            return value;
        }
        $(document).ready(function () {
            initTable();
            $("#search").bind("click", initTable);
        });
    </script>
    <style>
        table {
            table-layout: fixed;
            word-break: break-all;
            word-wrap: break-word
        }
    </style>
</head>
<body>
<div style="margin-left: 20px;">

    <ol class="breadcrumb">
        <li><a href="#">主菜单</a></li>
        <li><a href="#">自动回复</a></li>
        <li class="active">关键词回复设置</li>
    </ol>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#home" data-toggle="tab">
                关键词回复设置
            </a>
        </li>
    </ul>
    <div style="margin-top: 20px;">
        <button class="btn btn-primary btn-md" data-toggle="modal" data-target="#addGroup">新建规则</button>
        <button class="btn btn-primary btn-md" onclick="deleteValidate()">删除规则</button>
    </div>

    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="home">
            <form method="post" action="fileUploadLocal" enctype="multipart/form-data">
                <div>
                    <table id="table" data-toggle="table"/>
                </div>
            </form>
        </div>
    </div>
    <form class="form-horizontal" method="POST" action="createGroup">
        <div class="modal fade" id="addGroup" tabindex="-1" role="dialog" aria-labelledby="addGroupLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="addGroupLabel">新建规则</h4>
                    </div>
                    <div class="modal-body">
                        <div class="input-group">
                            <span class="input-group-addon">规则名称</span>
                            <input required type="text " name="ruleName" class="form-control"
                                   placeholder="请输入规则名称，限制60个字"/>
                        </div>
                        <br>

                        <div class="input-group">
                            <span class="input-group-addon">关键字词</span>
                            <input required type="text " name="keyword" class="form-control"
                                   placeholder="请输入关键字词，以，隔开"/>
                        </div>
                        <br>

                        <div class="input-group">
                            <span class="input-group-addon">回复內容</span>
                            <textarea required type="text " name="responseMsg" class="form-control"
                                      placeholder="请输入回复内容,限制600字" rows="6"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary">提交</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">

</script>
</body>
</html>