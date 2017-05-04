<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>素材管理</title>
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/jquery-3.1.1.min.js"></script>
    <link type="text/css" href="../../resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <link type="text/css" href="../../resources/bootstrap-table-develop/bootstrap-table.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/bootstrap-table-develop/bootstrap-table.js"></script>
    <script type="text/javascript"
            src="../../resources/bootstrap-table-develop/locale/bootstrap-table-zh-CN.js"></script>
</head>
<body>
<div style="margin-left: 20px;">

    <ol class="breadcrumb">
        <li><a href="#">主菜单</a></li>
        <li><a href="#">素材管理</a></li>
        <li class="active">素材列表</li>
    </ol>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#home" data-toggle="tab">
                素材列表
            </a>
        </li>
        <li>
            <a href="#total" data-toggle="tab">素材总数</a>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade" id="total">
            <div class="col-sm-2" style="margin-top: 10px;">
                <input onclick="getMaterialCount()" id="materialCount" name="count" type="button"
                       class="btn btn-primary" value="获取素材总数"/>
                <br><br><input class=".text-success" id="voice" value="音频总数量：暂无数据"
                               style="border-style:none;background: #FFF" disabled/>
                <br><br><input class=".text-success" id="video" value="视频总数量：暂无数据"
                               style="border-style:none;background: #FFF" disabled/>
                <br><br><input class=".text-success" id="image" value="图片总数量：暂无数据"
                               style="border-style:none;background: #FFF" disabled/>
                <br><br><input class=".text-success" id="news" value="图文总数量：暂无数据"
                               style="border-style:none;background: #FFF" disabled/>
            </div>
        </div>
        <div class="tab-pane fade in active" id="home">
            <div class="form-group">
                <div class="col-sm-12">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <div class="input-group">
                                <span class="input-group-addon">类型</span>
                                <select class="form-control" id="type" name="type">
                                    <option value="image">图片</option>
                                    <option value="news">图文</option>
                                    <option value="video">视频</option>
                                    <option value="voice">音频</option>
                                </select>
                            </div>
                            <br>

                            <div class="input-group">
                                <span class="input-group-addon">起始</span>
                                <input required type="number" id="offset" name="offset" id="offset" class="form-control"
                                       placeholder="请输入起始位置">
                            </div>
                            <br>

                            <div class="input-group">
                                <span class="input-group-addon">数量</span>
                                <input required type="number" id="count" name="count" id="count" class="form-control"
                                       placeholder="请输入素材数量">
                            </div>
                            <button onclick="getMaterials()" type="button" class="btn btn-primary btn-lg btn-block"
                                    style="margin-top:10px;height: 40px">查看素材列表
                            </button>
                        </div>
                        <div class="col-sm-9">
                            <table id="picList" style="display: none">
                            </table>
                            <table id="newsList" style="display: none">
                            </table>
                            <%--<table class="table table-striped"  style="display:none;" id="newsList">
                                <caption>图文素材列表</caption>
                                <thead>
                                <tr>
                                    <th width="12%">标题</th>
                                    <th width="12%">封面</th>
                                    <th width="15%">显示封面</th>
                                    <th width="12%">作者</th>
                                    <th width="12%">摘要</th>
                                    <th width="12%">内容</th>
                                    <th width="12%">URL</th>
                                    <th width="20%">原文地址</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${newsList}" var="news">
                                    <tr style="height: 50px">
                                        <td>${item.title}</td>
                                        <td>${item.thumb_media_id}</td>
                                        <td>${item.show_cover_pic}</td>
                                        <td>${item.author}</td>
                                        <td>${item.digest}</td>
                                        <td>${item.content}</td>
                                        <td>${item.url}</td>
                                        <td>${item.content_source_url}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <table class="table table-striped"  style="display:none;" id="picList">
                                <caption> 图片素材列表</caption>

                                <thead>
                                <tr>
                                    <th width="40%">媒体ID</th>
                                    <th width="40%">文件名称</th>
                                    <th width="40%">图片URL</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${otherList}" var="other">
                                    <tr style="height: 50px">
                                        <td>${other.media_id}</td>
                                        <td>${other.name}</td>
                                        <td>${other.url}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>--%>
                        </div>
                    </div>
                </div>
            </div>
            <%--  <div>
                  <div id="newsDiv" style="display: none">
                      图文素材
                     <table id="newsList"/>
                  </div>
              </div>
              <div>
                  <div id="picDiv" style="display: none">
                      图片素材
                     <table id="picList"/>
                  </div>
              </div>--%>
        </div>
    </div>
</div>
<script type="text/javascript">
    var getMaterials = function () {
        var param = {};
        param.type = $("#type").val();
        param.offset = $("#offset").val();
        param.count = $("#count").val();
        debugger;
        if (param.type == 'image') {
            document.getElementById("newsList").style.display = "none";
            document.getElementById("picList").style.display = "block";
            initPicList(param.type, param.offset, param.count);
        } else if (param.type == 'news') {
            document.getElementById("picList").style.display = "none";
            document.getElementById("newsList").style.display = "block";
            initNewsList(param.type, param.offset, param.count);
        }
    }
    $(document).ready(function () {
        // initNewsList();
        // initPicList();
    });
    function initNewsList(type, offset, count) {
        $('#newsList').bootstrapTable('destroy');
        var url = "<c:url value='/admin/getMaterials?type="+type+"&offset="+offset+"&count="+count+"'/>";
        debugger
        $("#newsList").bootstrapTable({
            method: "get",
            url: url,
            striped: true,
            pagination: true,
            pageSize: 5,
            pageNumber: 1,
            pageList: [5, 10, 15, 20],
            sidePagination: "client",
            height: 600,
            clickToSelect: true,
            queryParamsType: "undefined",
            editabe: true,
            columns: [{
                field: 'id',
                title: "序号",
                width: "40px"
            }, {
                title: "图文标题",
                width: "120px",
                formatter: function (value, row, index) {
                    var items = row.content.news_item;
                    return items[0].title;
                }
            }, {
                title: "封面图片",
                width: "80px",
                formatter: function (value, row, index) {
                    var items = row.content.news_item;
                    return items[0].thumb_media_id;
                }
            }, {
                title: "图文作者",
                width: "80px",
                formatter: function (value, row, index) {
                    var items = row.content.news_item;
                    return items[0].author;
                }
            }, {
                title: "图文摘要",
                width: "80px",
                formatter: function (value, row, index) {
                    var items = row.content.news_item;
                    return items[0].digest;
                }
            }, {
                title: "显示封面",
                width: "80px",
                formatter: function (value, row, index) {
                    var items = row.content.news_item;
                    var show = (items[0].show_cover_pic == 1) ? '是' : '否';
                    var operate = '<span>' + show + '</span>';
                    return operate;
                }
            }, {
                title: "图文内容",
                width: "160px",
                formatter: function (value, row, index) {
                    var items = row.content.news_item;
                    return items[0].content;
                }
            }, {
                title: "原文地址",
                width: "80px",
                formatter: function (value, row, index) {
                    var items = row.content.news_item;
                    return items[0].content_source_url;
                }
            }, {
                title: "媒体id",
                width: "80px",
                formatter: function (value, row, index) {
                    return row.media_id;
                }
            }, {
                title: "创建时间",
                width: "80px",
                formatter: function (value, row, index) {
                    return row.update_time;
                }
            }],
            queryParams: function queryParams(params) {
                var param = {
                    pageNumber: params.pageNumber,
                    pageSize: params.pageSize,
                    searchText: params.searchText,
                };
                return param;
            },
        });
        $('#picList').bootstrapTable('destroy');
    }
    function initPicList(type, offset, count) {
        $('#picList').bootstrapTable('destroy');
        var url = "<c:url value='/admin/getMaterials?type="+type+"&offset="+offset+"&count="+count+"'/>";
        debugger
        $("#picList").bootstrapTable({
            method: "get",
            url: url,
            striped: true,
            pagination: true,
            pageSize: 5,
            pageNumber: 1,
            pageList: [5, 10, 15, 20],
            sidePagination: "client",
            height: 600,
            clickToSelect: true,
            queryParamsType: "undefined",
            editabe: true,
            columns: [{
                field: 'id',
                title: "序号",
                width: "40px",
            }, {
                title: "媒体ID",
                width: "120px",
                formatter: function (value, row, index) {
                    return row.media_id;
                }
            }, {
                title: "图片名称",
                width: "80px",
                formatter: function (value, row, index) {
                    return row.name;
                }
            }, {
                title: "图片路径",
                width: "80px",
                formatter: function (value, row, index) {
                    return row.url;
                }
            }, {
                title: "创建时间",
                width: "80px",
                formatter: function (value, row, index) {
                    return row.update_time;
                }
            }],
            queryParams: function queryParams(params) {
                var param = {
                    pageNumber: params.pageNumber,
                    pageSize: params.pageSize,
                    searchText: params.searchText
                };
                return param;
            }
        });
        $('#newsList').bootstrapTable('destroy');
    }

    var getMaterialCount = function () {
        $.ajax({
            type: "post",
            url: "${base.contextPath}/admin/getMaterialCount",
            dataType: "json",
            success: function (data) {
                debugger;
                if (data.voice_count != null) {
                    $("#voice").val("音频总数量：" + data.voice_count);
                    $("#video").val("视频总数量：" + data.video_count);
                    $("#image").val("图片总数量：" + data.image_count);
                    $("#news").val("图文总数量：" + data.news_count);
                } else {
                    alert("获取数据失败")
                }
            }
        });
    }
</script>
</body>
</html>