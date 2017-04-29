<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta name="keywords" content="scclui框架">
    <meta name="description" content="scclui为轻量级的网站后台管理系统模版。">
    <title>首页</title>

    <link rel="stylesheet" href="${base.contextPath}/resources/common/layui/css/layui.css">
    <link rel="stylesheet" href="${base.contextPath}/resources/common/css/sccl.css">
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/jquery-3.1.1.min.js"></script>
    <link type="text/css" href="../../resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>

<body class="login-bg">
<div class="login-box">
    <header>
        <h1>SWPU911管理系统</h1>
    </header>
    <div class="login-main">
        <input name="__RequestVerificationToken" type="hidden" value="">

        <div class="layui-form-item">
            <label class="login-icon">
                <i class="layui-icon"></i>
            </label>
            <input type="text" id="userName" name="userName"
                   placeholder="这里输入登录名" class="layui-input">
        </div>
        <div class="layui-form-item">
            <label class="login-icon">
                <i class="layui-icon"></i>
            </label>
            <input type="password" id="password" name="password"
                   placeholder="这里输入密码" class="layui-input">
        </div>
        <div class="layui-form-item">
            <div class="pull-left login-remember">
                <label>记住帐号？</label>

                <input type="checkbox" name="rememberMe" value="true" lay-skin="switch" title="记住帐号">

                <div class="layui-unselect layui-form-switch"><i></i></div>
            </div>
            <div class="pull-right">
                <button class="layui-btn layui-btn-primary" onclick="doLogin(this.form)">
                    <i class="layui-icon"></i> 登录
                </button>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <footer>
        <p>neijaing © neijiangyizhong.cn</p>
    </footer>
</div>
<script type="text/html" id="code-temp">
    <div class="login-code-box">
        <input type="text" class="layui-input" id="code"/>
        <img id="valiCode" src="/maqnage/validatecode?v=636150612041789540" alt="验证码"/>
    </div>
</script>
<script src="${base.contextPath}/resources/common/layui/layui.js"></script>
<script>
    var doLogin = function (loginform) {
        var userName = $("#userName").val()
        var password = $("#password").val();
        if (userName == null || userName == undefined || userName === '') {
            alert('请输入用户名');
            return;
        }
        if (password == null || password === undefined || password === '') {
            alert('请输入密码');
            return;
        }
        var user = {}
        user.userName = userName;
        user.password = password;
        var userData = JSON.stringify(user);
        $.ajax({
            type: "post",
            url: "${base.contextPath}/admin/doLogin",
            data: userData,
            contentType: "application/json",
            success: function (data) {
                debugger;
                if (data == "false") {
                    alert("登录失败，请检查您输入的用户名和密码是否正确");
                } else {
                    window.location.href = "index";
                }
            }
        });
    }
</script>
</body>
</html>
