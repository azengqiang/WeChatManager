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
    <script type="text/javascript">
        function initTable() {
            $('#table').bootstrapTable('destroy');
            var url = "<c:url value='/admin/newsInfo'/>";
            $("#table").bootstrapTable({
                method: "get",
                url: url,
                striped: true,
                pagination: true,
                pageSize: 5,
                pageNumber: 1,
                pageList: [5, 10,15,20],
                search: true,
                showColumns: true,  //显示下拉框勾选要显示的列
                showRefresh: true,  //显示刷新按钮
                showToggle:true,//显示card还是table
                sidePagination: "server",
                height: 600,
                clickToSelect:true,
                searchText:'',
                queryParamsType: "undefined",
                editabe: true,
                columns: [{
                    field: 'state',
                    checkbox: true,
                }, {
                    field: 'id',
                    title: "序号",
                    width: "40px",
                }, {
                    field: 'title',
                    title: "图文标题",
                    width: "120px",
                }, {
                    field: 'thumb_media_id',
                    title: "封面图片",
                    width: "80px",
                },{
                    field: 'author',
                    title: "图文作者",
                    width: "80px",
                },{
                    field: 'digest',
                    title: "图文摘要",
                    width: "80px",
                },{
                    field: 'show_cover_pic',
                    title: "显示封面",
                    width: "80px",
                    formatter: function (value, row, index) {
                        var show = (row.show_cover_pic==1)?'是':'否';
                        var operate = '<span>'+show+'</span>';
                        return operate;
                    }
                },{
                    field: 'content',
                    title: "图文内容",
                    width: "160px",
                },{
                    field: 'content_source_url',
                    title: "原文地址",
                    width: "80px",
                },{
                    field: 'newsMediaId',
                    title: "媒体id",
                    width: "80px",
                },{
                    field: 'creationDate',
                    title: "创建时间",
                    width: "80px",
                },/*{
                    title: "操作",
                    align: "center",
                    width: "100px",
                    formatter: function (value, row, index) {
                        var operate = '<a onclick="updateNews()">修改</a>';
                        return operate;
                    }
                },*/
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
        .news-content {
            margin-top: 5px;
        }
    </style>
</head>
<body>
<div style="margin-left: 20px;">

    <ol class="breadcrumb">
        <li><a href="#">主菜单</a></li>
        <li><a href="#">素材管理</a></li>
        <li class="active">图文管理</li>
    </ol>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#home" data-toggle="tab">
                图文消息
            </a>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="home">
            <div style="margin-top: 5px;">
                <button class="btn btn-primary btn-md" data-toggle="modal" data-target="#addNews">添加图文素材</button>
                <button class="btn btn-primary btn-md" onclick="deleteValidate()">删除图文素材</button>
            </div>
            <div>
                <table id="table" data-toggle="table"/>
            </div>
        </div>
    </div>
    <!-- 新建分组模态框 -->
    <div class="modal fade" id="addNews" tabindex="-1" role="dialog" aria-labelledby="addNewsLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="addNewsLabel">添加图文素材</h4>
                </div>
                <div class="modal-body">
                    <div class="input-group">
                        <span class="input-group-addon">标题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        <input required type="text " id="title" name="title" class="form-control"
                               placeholder="请输入图文标题">
                    </div>
                    <div class="input-group news-content">
                        <span class="input-group-addon">作者&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        <input required type="text " id="author" name="author" class="form-control"
                               placeholder="请输入图文作者">
                    </div>
                    <div class="input-group news-content">
                        <span class="input-group-addon">摘要&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        <input required type="text " id="digest" name="digest" class="form-control"
                               placeholder="请输入图文摘要">
                    </div>
                    <div class="input-group news-content">
                        <span class="input-group-addon">封面图片</span>
                        <select class="form-control" id="thumb_media_id" name="thumb_media_id">
                            <c:forEach items="${pictures}" var="picture">
                                <option value="${picture.mediaId}">${picture.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="input-group news-content">
                        <span class="input-group-addon">显示封面</span>
                        <select class="form-control" id="show_cover_pic" name="show_cover_pic">
                            <option value="1">显示</option>
                            <option value="0">不显示</option>
                        </select>
                    </div>
                    <div class="input-group news-content">
                        <span class="input-group-addon">原文地址</span>
                        <input required type="text " id="content_source_url" name="content_source_url"
                               class="form-control"
                               placeholder="请输入图文原文地址">
                    </div>
                    <div class="input-group news-content">
                        <span class="input-group-addon">图文内容</span>
                        <textarea required type="text " id="content" name="content" class="form-control"
                                  placeholder="请输入图文具体内容" rows="7" cols="120" maxlength="10000"></textarea>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary" onclick="addNews()">提交</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 信息删除确认 -->
    <div class="modal fade" id="deleteNews">
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
                    <a class="btn btn-success" data-dismiss="modal" onclick="deleteNews()">确定</a>
                </div>
            </div>
        </div>
    </div>
    <!-- 信息删除确认结束 -->
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

    var updateNews = function (){

    }

    var addNews = function () {
        var newsList=[];
        var news = {};
        news.title = $("#title").val();
        news.thumb_media_id = $("#thumb_media_id").val();
        news.author = $("#author").val();
        news.digest = $("#digest").val();
        news.show_cover_pic = $("#show_cover_pic").val();
        news.content = $("#content").val();
        news.content_source_url = $("#content_source_url").val();
        newsList.push(news);
        var newsData = JSON.stringify(newsList);
        debugger;
        $.ajax({
            type: "post",
            url: "${base.contextPath}/admin/addNews",
            data: newsData,
            contentType: "application/json",
            success: function (data) {
                debugger;
            }
        });
    }
    var deleteValidate = function () {
       var deleteNews =  $('#table').bootstrapTable('getSelections');
        if(null==deleteNews || 0==deleteNews.length){
            $("#hintMessage").val("请选择您要删除的图片素材");
            $("#hintDialog").modal();
        }else{
            $("#deleteNews").modal();
        }
    }
    var deleteNews = function (){
        var deleteNewsList=[];
        var deleteNews =  $('#table').bootstrapTable('getSelections');
        for(var i=0;i<deleteNews.length;i++){
            var news={};
            news.newsMediaId =   deleteNews[i].newsMediaId;
            deleteNewsList.push(news);
        }
        var deleteNewsData = JSON.stringify(deleteNewsList);
        debugger;
        $.ajax({
            type: "post",
            url: "${base.contextPath}/admin/deleteNews",
            data: deleteNewsData,
            contentType: "application/json",
            success: function (data) {
                debugger;
                window.location.reload();
            }
        });
    }

</script>
</body>
</html>