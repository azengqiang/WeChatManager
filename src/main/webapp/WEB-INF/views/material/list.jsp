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
          <li><a href="#pic" data-toggle="tab">图片</a></li>
          <li><a href="#allCount" data-toggle="tab">素材总数</a></li>
          <li><a href="#list" data-toggle="tab">素材列表</a></li>
      </ul>
      <div id="myTabContent" class="tab-content" style="padding: 20px 20px 10px;">
            <div class="tab-pane fade in active" id="home">
                <div class="form-group">
                    <div class="col-sm-3" style="width: 200px;">
                        <input onclick="getMaterialCount()" id="materialCount" name="count"  type="button" class="btn btn-primary" value="获取素材总数"/>
                        <br><br><span class=".text-success">音频数量：${materialCount.voice_count}</span>
                        <br><br><span class=".text-success">视频数量：${materialCount.video_count}</span>
                        <br><br><span class=".text-success">图片数量：${materialCount.image_count}</span>
                        <br><br><span class=".text-success">图文数量：${materialCount.news_count}</span>
                    </div>
                    <div class="col-sm-9">
                        <div style="width: 300px;">
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
                                <input required type="number" id="offset" name="offset" id="offset" class="form-control" placeholder="请输入起始位置">
                            </div>
                            <br>
                            <div class="input-group">
                                <span class="input-group-addon">数量</span>
                                <input required type="number" id="count" name="count" id="count" class="form-control" placeholder="请输入素材数量">
                            </div>
                            <button onclick="getMaterials()" type="button" class="btn btn-primary btn-lg btn-block" style="margin-top:10px;height: 40px">提交</button>

                            <span>${materials}</span>
                        </div>
                    </div>
                </div>
          </div>
      </div>
</div>
<script type="text/javascript">
    var getMaterialCount =  function (){
        $.ajax({
            type: "post",
            url: "${base.contextPath}/admin/getMaterialCount",
            dataType: "json",
            success: function (data) {
                if (data["success"] == true) {

                } else {

                }
            }
        });
    }
    var getMaterials =  function (){
        var param = {};
        param.type =  $("#type").val();
        param.offset =  $("#offset").val();
        param.count =  $("#count").val();
        debugger;
        $.ajax({
            type: "post",
            url: "${base.contextPath}/admin/getMaterials",
            data: JSON.stringify(param),
            contentType: "application/json",
            success: function (data) {
                debugger;
                if (data["success"] == true) {

                } else {

                }
            }
        });
    }
</script>
</body>
</html>