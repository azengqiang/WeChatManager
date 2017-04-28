<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>素材管理</title>
  <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/jquery-3.1.1.min.js"></script>
  <link type="text/css" href="../../resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
  <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">
        img{
            cursor: pointer;
            transition: all 0.6s;
            margin: 20px;
        }
        img:hover{
            transform: scale(1.5);
        }
    </style>
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
             图片
            </a>
          </li>
      </ul>
    <!-- 信息删除确认 -->
    <div class="modal fade" id="deletePic">
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
                    <a class="btn btn-success" data-dismiss="modal" onclick="deletePic()">确定</a>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->
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
      <div id="myTabContent" class="tab-content"  style="margin-top: 20px;margin-left: 20px">
          <div class="tab-pane fade in active" id="home">
              <form method="post" action="fileUploadLocal" enctype="multipart/form-data">
                  <div>
                      <input type="file" multiple="multiple" name="file" accept="image/bmp,image/png,image/jpeg,image/jpg,image/gif">
                      <br>
                      <input type="submit" value="上传图片" name="submit" />
                  </div>
              </form>
              <button class="btn btn-primary btn-md" onclick="deleteValidate()" style="margin-top: 10px;">删除图片</button>
          </div>
          <div style="margin-top: 20px;">
              <c:forEach items="${pictures}" var="picture" varStatus="status" >
                  <div class="form-group" style="float: left;background: azure">
                      <div class="form-group">
                      <img src="${basePath}${picture.path}" style="height: 180px;width: 180px;"
                               class="img-responsive img-thumbnail" alt="Cinque Terre">
                      </div>
                      <div class="form-group" style="margin-left: 60px;">
                          <span >${picture.name}</span>
                          <div class="checkbox">
                              <label>
                                  <input type="checkbox" name="check" id="${status.index}" value="${picture.mediaId}">勾选
                              </label>
                          </div>
                      </div>
                  </div>
              </c:forEach>
          </div>
      </div>
</div>

<script type="text/javascript">
    var picChoose=[];
    $(function(){
        var s = $("input[name='check']");
        s.each(function(i) {;
            $(this).click(function(){
                if(this.checked==true){
                    picChoose.push(this.value);

                }else{
                    picChoose.pop(this.value);
                }
            });
        });
    })
    var deleteValidate= function(){
        if(0==picChoose.length){
            $("#hintMessage").val("请选择您要删除的图片");
            $("#hintDialog").modal();
        }else{
            $("#deletePic").modal();
        }
    }
    var deletePic = function(){
        var picChooseData = new Array();
        for(var i=0;i<picChoose.length;i++){
            var pic ={};
            pic.mediaId =  picChoose[i];
            picChooseData.push(pic);
        }

        $.ajax({
            type: "post",
            url: "${base.contextPath}/admin/deleteMaterial",
            data: JSON.stringify(picChooseData),
            contentType: "application/json",
            success: function (data) {
                window.location.reload();
            }
        });

    }


    var getMaterialCount =  function (){
        $.ajax({
            type: "POST",
            url: "${base.contextPath}/admin/getMaterialCount",
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                if (data["success"] == true) {

                } else {

                }
            }
        });
    }
</script>
</body>
</html>