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
        <li><a href="#">自动回复</a></li>
        <li class="active">关注回复设置</li>
    </ol>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#home" data-toggle="tab">
                关注回复设置
            </a>
        </li>
    </ul>
    <div class="modal fade" id="deleteSubscribeResponse">
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
                    <a class="btn btn-success" data-dismiss="modal" onclick="deleteSubscribeResponseMsg()">确定</a>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <div id="myTabContent" class="tab-content" style="margin-top: 20px;">
        <form class="form-horizontal" method="POST" action="setSubscribeResponse">
            <div class="tab-pane fade in active" id="home">
                  <textarea id="subscribeResponseMsg" name="subscribeResponseMsg"
                            maxlength="600" autofocus rows="15" cols="120" wrap="soft" placeholder="请输入用户关注公众号后的回复语"
                            onkeydown="checkMaxInput(this,600)" onkeyup="checkMaxInput(this,600)"
                            onfocus="checkMaxInput(this,600)"
                            onblur="checkMaxInput(this,600)">${subscribeResponseMsg.responseMsg}</textarea>
            </div>
            <div style="margin-top: 20px;">
                <button type="submit" class="btn btn-primary">保存设置</button>
                <button type="button" class="btn btn-default"data-toggle="modal" data-target="#deleteSubscribeResponse" id="delete">删除设置
                </button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    var responseMsg = $("#subscribeResponseMsg").val();
    if(responseMsg==null || responseMsg ==undefined || responseMsg.trim()=="") {
        $("#delete").attr("disabled", true);
    }
    var deleteSubscribeResponseMsg = function () {
        var p = {}
        p.responseMsg = $("#subscribeResponseMsg").val();
        var deleteSubscribeResponseMsgData = JSON.stringify(p);
        debugger;
        $.ajax({
            type: "post",
            url: "${base.contextPath}/admin/deleteSubscribeResponse",
            data: deleteSubscribeResponseMsgData,
            contentType: "application/json",
            success: function (data) {
                window.location.reload();
            }
        });
    }


    //多行文本输入框剩余字数计算
    function checkMaxInput(obj, maxLen) {
        if (obj == null || obj == undefined || obj == "") {
            return;
        }
        if (maxLen == null || maxLen == undefined || maxLen == "") {
            maxLen = 600;
        }

        var strResult;
        var $obj = $(obj);
        var newid = $obj.attr("id") + 'msg';

        if (obj.value.length > maxLen) { //如果输入的字数超过了限制
            obj.value = obj.value.substring(0, maxLen); //就去掉多余的字
            strResult = '<span id="' + newid + '" class=\'Max_msg\' ><br/>还可以输入(' + (maxLen - obj.value.length) + ')字</span>'; //计算并显示剩余字数
        }
        else {
            strResult = '<span id="' + newid + '" class=\'Max_msg\' ><br/>还可以输入(' + (maxLen - obj.value.length) + ')字</span>'; //计算并显示剩余字数
        }

        var $msg = $("#" + newid);
        if ($msg.length == 0) {
            $obj.after(strResult);
        }
        else {
            $msg.html(strResult);
        }
    }

    //清空剩除字数提醒信息
    /* function resetMaxmsg() {
     $("span.Max_msg").remove();
     }*/
</script>
</body>
</html>