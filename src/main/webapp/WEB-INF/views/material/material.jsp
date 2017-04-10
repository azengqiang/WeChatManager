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
          <li class="active">素材管理</li>
      </ol>
      <ul id="myTab" class="nav nav-tabs">
          <li class="active">
            <a href="#home" data-toggle="tab">
              图文消息
            </a>
          </li>
          <li><a href="#pic" data-toggle="tab">图片</a></li>
          <li><a href="#allCount" data-toggle="tab">素材总数</a></li>
          <li><a href="#list" data-toggle="tab">素材列表</a></li>
      </ul>
      <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="home">
                <form method="post" action="fileUploadLocal" enctype="multipart/form-data">
                    <div>
                      <input type="file" multiple="multiple" name="file" accept="image/bmp,image/png,image/jpeg,image/jpg,image/gif">
                      <input type="submit" value="上传" name="submit" />

                    </div>
              </form>
          </div>
          <div class="tab-pane fade" id="pic">
              <form method="post" action="fileUploadLocal" enctype="multipart/form-data">
                  <div>
                      <input type="file" multiple="multiple" name="file" accept="image/bmp,image/png,image/jpeg,image/jpg,image/gif">
                      <input type="submit" value="上传" name="submit" />

                  </div>
              </form>
          </div>
          <div class="tab-pane fade" id="allCount">
              <input type="button" value="获取素材总数" name="submit2" onclick="getMaterialCount()" />
              <br><span>音频：${materialCount.voice_count}</span>
              <br><span>视频：${materialCount.video_count}</span>
              <br><span>图片：${materialCount.image_count}</span>
              <br><span>图文：${materialCount.news_count}</span>
          </div>
          <div class="tab-pane fade" id="list">
              <div style="padding: 50px 50px 10px;width:50%">
                  <form class="bs-example bs-example-form" role="form" method="post" action="getMaterials">
                      <div class="input-group">
                          <span class="input-group-addon">类型</span>
                          <input required type="text" name="type" id="type"class="form-control" placeholder="请输入类型">
                      </div>
                      <br>
                      <div class="input-group">
                          <span class="input-group-addon">起始</span>
                          <input required type="text" name="offset" id="offset" class="form-control" placeholder="请输入起始位置">
                      </div>
                      <br>
                      <div class="input-group">
                          <span class="input-group-addon">数量</span>
                          <input required type="text" name="count" id="count" class="form-control" placeholder="请输入素材数量">
                      </div>
                      <button type="submit" class="btn btn-primary btn-lg btn-block" style="margin-top: 10px;">提交</button>
                  </form>
              </div>
          </div>
      </div>
</div>
<script type="text/javascript">
    var getMaterialCount =  function (){
        $.ajax({
            type: "POST",
            url: "${base.contextPath}/admin/getMaterialCount",
            dataType: "json",
            contentType: 'application/json',
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