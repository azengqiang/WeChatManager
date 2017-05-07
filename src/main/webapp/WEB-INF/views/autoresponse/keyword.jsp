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
            var url = "<c:url value='/admin/ruleInfo'/>";
            $("#table").bootstrapTable({
                method: "get",
                url: url,
                striped: true,
                pagination: true,
                pageSize: 10,
                pageNumber: 1,
                pageList: [5, 10, 15],
                search: true,
                sidePagination: "server",
                height: 600,
                queryParamsType: "undefined",
                editabe: true,
                checkboxHeader: true,
                columns: [{
                    field: 'state',
                    checkbox: true
                }, {
                    field: 'autoResponseMsgId',
                    title: "序号",
                    align: "center",
                    width: "50px"
                }, {
                    field: 'ruleName',
                    title: "规则",
                    width: "120px"
                }, {
                    field: 'keywordMsg',
                    title: "关键词",
                    align: "center",
                    width: "250px"
                }, {
                    field: 'responseMsg',
                    title: "回复",
                    align: "center"
                },{
                    field: 'creationDate',
                    title: "创建时间",
                    align: "center",
                    width: "150px",
                },{
                    title: "操作",
                    align: "center",
                    width: "120px",
                    formatter: function (value, row, index) {
                        var operate = '<button onclick="updateValidate('+"'"+row.autoResponseMsgId+"'"+","+"'"+row.ruleName+
                                "'"+","+"'"+row.keywordMsg+"'"+","+"'"+row.responseMsg+"'"+')">修改规则</button>';
                        return operate;
                    }
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
        <button class="btn btn-primary btn-md" data-toggle="modal" data-target="#addRule">新建规则</button>
        <button class="btn btn-primary btn-md" onclick="deleteValidate()" id="delete">删除规则</button>
    </div>
    <!--删除规则开始-->
    <div class="modal fade" id="deleteRule">
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
                    <a class="btn btn-success" data-dismiss="modal" onclick="deleteRule()">确定</a>
                </div>
            </div>
        </div>
    </div>
    <!--删除规则结束-->
    <div id="myTabContent" class="tab-content" style="margin-right: 10px">
        <div class="tab-pane fade in active" id="home">
            <div>
                <table id="table" data-toggle="table"/>
            </div>
        </div>
    </div>
    <!--添加规则开始-->
    <div class="modal fade" id="addRule" tabindex="-1" role="dialog" aria-labelledby="addRuleLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="addRuleLabel">新建规则</h4>
                </div>
                <div class="modal-body">
                    <div class="input-group">
                        <span class="input-group-addon">规则名称</span>
                        <input required type="text " id="ruleName" name="ruleName" class="form-control"
                               placeholder="请输入规则名称，限制60个字"/>
                    </div>
                    <br>

                    <div class="input-group">
                        <span class="input-group-addon">关键字词</span>
                        <input required type="text " id="keyword" name="keyword" class="form-control"
                               placeholder="请输入关键字词，以，隔开"/>
                    </div>
                    <br>

                    <div class="input-group">
                        <span class="input-group-addon">回复內容</span>
                        <textarea required type="text " id="responseMsg" name="responseMsg" class="form-control"
                                  placeholder="请输入回复内容,限制600字" rows="6" maxlength="600"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="addRule()">提交</button>
                </div>
            </div>
        </div>
    </div>
    <!--添加规则结束-->
    <!--更新规则开始-->
    <div class="modal fade" id="updateRule" tabindex="-1" role="dialog" aria-labelledby="updateRuleLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="updateRuleLabel">修改规则</h4>
                </div>
                <div class="modal-body">
                    <div class="input-group">
                        <span class="input-group-addon">规则名称</span>
                        <input type="text " id="autoResponseMsgId" name="autoResponseMsgId" style="display: none">
                        <input required type="text " id="ruleName1" name="ruleName" class="form-control"
                               placeholder="请输入规则名称，限制60个字"/>
                    </div>
                    <br>

                    <div class="input-group">
                        <span class="input-group-addon">关键字词</span>
                        <input required type="text " id="keyword1" name="keyword" class="form-control"
                               placeholder="请输入关键字词，以，隔开"/>
                    </div>
                    <br>

                    <div class="input-group">
                        <span class="input-group-addon">回复內容</span>
                        <textarea required type="text " id="responseMsg1" name="responseMsg" class="form-control"
                                  placeholder="请输入回复内容,限制600字" rows="6" maxlength="600"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="updateRule()">提交</button>
                </div>
            </div>
        </div>
    </div>
    <!--更新规则结束-->
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
</div>
<script type="text/javascript">
    var deleteValidate = function () {
        var select = $('#table').bootstrapTable('getSelections');
        if (select.length == 0) {
            $("#hintMessage").val("请至少选择一个规则");
            $("#hintDialog").modal();
        } else {
            $("#deleteRule").modal();
        }
    }
    var deleteRule = function () {
        var select = $('#table').bootstrapTable('getSelections');
        //js转换String日期为Date类型
        for(var i=0;i<select.length;i++){
            var str = select[i].creationDate;
            var val = Date.parse(str);
            var newDate = new Date(val);
            select[i].creationDate = newDate;
        }
        var deleteRuleData = JSON.stringify(select);
        debugger;
        $.ajax({
            type: "post",
            url: "${base.contextPath}/admin/deleteKeyResponse",
            data: deleteRuleData,
            contentType: "application/json",
            success: function (data) {
                window.location.reload();
            }
        });
    }
    var addRule = function () {
        var rule = {};
        rule.ruleName = $("#ruleName").val();
        rule.keywordMsg = $("#keyword").val();
        rule.responseMsg = $("#responseMsg").val();
        var addRuleData = JSON.stringify(rule);
        $.ajax({
            type: "post",
            url: "${base.contextPath}/admin/addKeywordResponse",
            data: addRuleData,
            contentType: "application/json",
            success: function (data) {
                window.location.reload();
            }
        });
    }
    var updateRule = function(){
        var rule = {};
        rule.autoResponseMsgId =  $("#autoResponseMsgId").val();
        rule.ruleName = $("#ruleName1").val();
        rule.keywordMsg = $("#keyword1").val();
        rule.responseMsg = $("#responseMsg1").val();
        var updateRuleData = JSON.stringify(rule);

        $.ajax({
            type: "post",
            url: "${base.contextPath}/admin/updateKeyResponse",
            data: updateRuleData,
            contentType: "application/json",
            success: function (data) {
                window.location.reload();
            }
        });
    }
    var updateValidate =function(autoResponseMsgId,ruleName,keywordMsg,responseMsg){
        $("#autoResponseMsgId").val(autoResponseMsgId);
        $("#ruleName1").val(ruleName);
        $("#keyword1").val(keywordMsg);
        $("#responseMsg1").val(responseMsg);
        $("#updateRule").modal();
   ;
    }
</script>
</body>
</html>