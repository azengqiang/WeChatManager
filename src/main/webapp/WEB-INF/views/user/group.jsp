<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            var groupName = $("#groupTitle").val();
            var groupid = -1;
            var nowGroupsJson = ${groupsJson};
            var groups = eval(nowGroupsJson);
            for (var i = 0; i < groups.length; i++) {
                if (groupName == groups[i].name) {
                    groupid = groups[i].id;
                    break;
                }
            }
            var url = "<c:url value='/admin/groupUserInfo?groupid="+groupid+"'/>";
            $("#table").bootstrapTable({
                method: "get",
                url: url,
                striped: true,
                pagination: true,
                pageSize: 5,
                pageNumber: 1,
                pageList: [5, 10,15,20],
                search: true,
                showRefresh: true,  //显示刷新按钮
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
                    title: "用户信息",
                    formatter: function (value, row, index) {
                        var subscribe = (row.subscribe=='0')?'未关注':'已关注';
                        var info = '<div><div style="float:left;" ><img class="img-rounded" alt="140x140" style="width: 50px; height: 50px;" ' + 'src="' + row.headimgurl + '"></img></div>'
                                + '<div style="float:left;margin-left: 30px;"><span>昵称: ' + row.nickname + '</span><br>' + '<span>地址: ' + row.address + '</span><br>' + '<span>备注: ' + row.remark + '</span></div>'
                                + '<div style="float:left;margin-left: 60px;"><span>性别: ' + row.sex + '</span><br>' + '<span>关注时间: ' + row.subscribe_time + '</span><br>' + '<span>关注状态: ' + subscribe + '</span></div></div>';
                        return info;
                    }

                }, {
                    title: "操作",
                    align: "center",
                    width: "180px",
                    formatter: function (value, row, index) {
                        var operate = '<a onclick="updateRemark(' + "'" + row.remark + "'" + ",'" + row.openid + "'"
                                + ')">修改备注</a><a style="margin-left: 20px;" '
                                + 'onclick="moveUser(' + "'" + row.openid + "'" + ",'" + row.groupName + "'" + ')">移动分组</a>';
                        return operate;
                    }
                },
                ],
                queryParams: function queryParams(params) {
                    var param = {
                        pageNumber: params.pageNumber,
                        pageSize: params.pageSize,
                        searchText:params.searchText,
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

<div id="page-content" style="width:96%;margin-top: 5px;">
    <div style="margin-bottom: 5px">
        <div>
            <button class="btn btn-primary btn-md" data-toggle="modal" data-target="#addGroup">新建用户分组</button>
            <button class="btn btn-primary btn-md" onclick="deleteValidate()">删除用户分组</button>
            <button class="btn btn-primary btn-md" onclick="updateValidate()">修改分组名</button>
        </div>
        <!--提示框开始 -->
        <div class="modal fade" id="hintDialog" tabindex="-1" role="dialog" aria-labelledby="hintLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="hintLabel">警告</h4>
                    </div>
                    <div class="modal-body">
                        <input type="text " name="hintMessage" id="hintMessage" class="form-control" readonly
                               style="background-color:transparent;outline:none;border:none;">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal -->
        </div>
        <!--提示框结束 -->
        <!-- 新建分组模态框 -->
        <form class="form-horizontal" method="POST" action="createGroup">
            <div class="modal fade" id="addGroup" tabindex="-1" role="dialog" aria-labelledby="addGroupLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="addGroupLabel">新建分组</h4>
                        </div>
                        <div class="modal-body">
                            <div class="input-group">
                                <span class="input-group-addon">分组名称</span>
                                <input required type="text " name="groupName" class="form-control"
                                       placeholder="请输入分组名称">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="submit" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal -->
            </div>
        </form>
        <!-- 新建分组模态框结束-->
        <!-- 信息删除确认 -->
        <div class="modal fade" id="deleteGroup">
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
                        <a class="btn btn-success" data-dismiss="modal" onclick="deleteGroup()">确定</a>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
        <!-- 信息删除确认结束 -->
        <!-- 信息修改确认 -->
        <form class="form-horizontal" method="POST" action="updateGroup">
            <div class="modal fade" id="updateGroup">
                <div class="modal-dialog">
                    <div class="modal-content message_align">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="updateGroupLabel">新建分组</h4>
                        </div>
                        <div class="modal-body">
                            <div class="input-group">
                                <span class="input-group-addon">原分组名称</span>
                                <input type="text " name="originGroupName" id="originGroupName" class="form-control"
                                       readonly>
                            </div>
                            <br>

                            <div class="input-group">
                                <span class="input-group-addon">新分组名称</span>
                                <input required type="text " name="newGroupName" id="newGroupName" class="form-control">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="submit" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
        </form>
        <!-- 信息修改确认结束 -->
        <!-- 修改用户备注名开始 -->
        <form class="form-horizontal" method="POST" action="updateRemark">
            <div class="modal fade" id="updateRemark">
                <div class="modal-dialog">
                    <div class="modal-content message_align">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="updateRemarkLabel">修改备注名</h4>
                        </div>
                        <div class="modal-body">
                            <div class="input-group">
                                <span class="input-group-addon">原备注名称</span>
                                <input type="text " name="remarkOpenId" id="remarkOpenId" style="display: none">
                                <input type="text " name="originRemark" id="originRemark" class="form-control" readonly>
                            </div>
                            <br>

                            <div class="input-group">
                                <span class="input-group-addon">新备注名称</span>
                                <input required type="text " name="newRemark" id="newRemark" class="form-control">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="submit" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
        </form>
        <!-- 修改用户备注名结束 -->
        <!-- 修改用户分组开始 -->
        <form class="form-horizontal" method="POST" action="moveUser">
            <div class="modal fade" id="moveUser">
                <div class="modal-dialog">
                    <div class="modal-content message_align">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="moveUserLabel">移动分组</h4>
                        </div>
                        <div class="modal-body">
                            <div class="input-group">
                                <span class="input-group-addon">原分组</span>
                                <input type="text " name="groupOpenId" id="groupOpenId" style="display: none">
                                <input type="text " name="originUserGroupName" id="originUserGroupName"
                                       class="form-control" readonly>
                            </div>
                            <br>

                            <div class="input-group">
                                <span class="input-group-addon">新分组</span>
                                <select class="form-control" id="newGroupId" name="newGroupId">
                                    <c:forEach items="${groups}" var="group">
                                        <option value="${group.id}">${group.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="submit" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
        </form>
        <!-- 修改用户分组结束 -->
    </div>

    <form id="form" class="form-horizontal" method="post" enctype="application/json;charset=UTF-8">
        <div class="row" style="margin-left: 5px;">

            <div class="form-group">
                <div class="col-sm-3" id="tree" style="width: 270px;">

                </div>
                <div class="col-sm-9">
                    <div>
                        <input type="text " id="groupTitle" name="groupTitle" value="全部分组" class="form-control"
                               style="text-align:center;background-color:transparent;outline:none;border:none;"
                               readonly>
                    </div>
                    <br>
                    <div>
                        <table id="table" data-toggle="table"/>
                    </div>

                </div>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
    $('#tree').treeview({
        data: ${groupTreeJson},
        showCheckbox: true,
        showTags: true,
        color: "#000000",
        backColor: "#FFFFFF",
        href: "#node-1",
        selectable: true,
        state: {
            checked: true,
            disabled: true,
            expanded: true,
            selected: true,
        },
        onNodeChecked: function (event, data) {
            if ("全部分组" == data.text) {
                $('#tree').treeview('checkAll', {silent: true});
            }
        },
        onNodeUnchecked: function (event, data) {
            if ("全部分组" == data.text) {
                $('#tree').treeview('uncheckAll', {silent: true});
            }
        },
        onNodeSelected: function (event, data) {
            var nodeId;
            var selectNodeIds = $('#tree').treeview('getSelected', nodeId);
            $("#groupTitle").val(selectNodeIds[0].text);
            initTable();
        },
    });

    var deleteNodes = new Array();
    var deleteValidate = function () {
        var nodeId;
        var deleteNodeIds = $('#tree').treeview('getChecked', nodeId);
        if (deleteNodeIds == null || deleteNodeIds.length == 0) {
            $("#hintMessage").val("请至少选择一个分组");
            $("#hintDialog").modal();
        } else {
            deleteNodes.splice(0, deleteNodes.length);
            for (var i = 0; i < deleteNodeIds.length; i++) {
                if ("全部分组" == deleteNodeIds[i].text) {
                    continue;
                }
                if ("未分组" == deleteNodeIds[i].text || "黑名单" == deleteNodeIds[i].text || "星标组" == deleteNodeIds[i].text) {
                    $("#hintMessage").val("包含微信默认分组，默认分组不可删除");
                    $("#hintDialog").modal();
                    deleteNodes = [];
                    return;
                }
                deleteNodes.push(deleteNodeIds[i].text);
            }
            $("#deleteGroup").modal();
        }
    }

    var deleteGroup = function () {
        debugger;
        var group = new Array();
        for (var i = 0; i < deleteNodes.length; i++) {
            var p = {};
            p.name = deleteNodes[i];
            group.push(p);
        }
        var deleteGroupData = JSON.stringify(group);
        $.ajax({
            type: "post",
            url: "${base.contextPath}/admin/deleteGroup",
            data: deleteGroupData,
            contentType: "application/json",
            success: function (data) {
                window.location.reload();
            }
        });
    }

    var updateValidate = function () {
        var nodeId;
        var deleteNodeIds = $('#tree').treeview('getChecked', nodeId);
        if (deleteNodeIds != null && 1 == deleteNodeIds.length) {
            if ("未分组" == deleteNodeIds[0].text || "黑名单" == deleteNodeIds[0].text || "星标组" == deleteNodeIds[0].text) {
                $("#hintMessage").val("微信默认分组 不可修改名称");
                $("#hintDialog").modal();
            } else {
                $("#originGroupName").val(deleteNodeIds[0].text);
                $("#updateGroup").modal();
            }
        } else {
            $("#hintMessage").val("请选择一个分组");
            $("#hintDialog").modal();
        }
    }

    var updateRemark = function (remark, openId) {
        $("#originRemark").val(remark);
        $("#remarkOpenId").val(openId);
        $("#updateRemark").modal();
    }

    var moveUser = function (openId, groupName) {
        $("#originUserGroupName").val(groupName);
        $("#groupOpenId").val(openId);
        $("#moveUser").modal();
    }
</script>
</body>
</html>
