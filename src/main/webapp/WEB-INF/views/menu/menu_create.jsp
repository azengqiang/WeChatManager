<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>菜单管理</title>
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/jquery-3.1.1.min.js"></script>
    <link type="text/css" href="../../resources/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<form class="form-horizontal" role="form" action="menuSubmit"  method="post" style="margin-top: 20px;width: 50%">
    <div class="form-group" >
        <label for="firstMenu" class="col-sm-2 control-label">父级菜单</label>
        <div class="col-sm-10">
            <select class="form-control" id="firstMenu" name="firstMenu">
                <option></option>
            <c:forEach items="${firstMenus}" var="firstMenu">
                <option>${firstMenu.name}</option>
            </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="menuName" class="col-sm-2 control-label">菜单名称</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="menuName"
                   placeholder="请输入菜单名称" name="menuName">
        </div>
        <span class="help-block col-sm-5" for="menuName" style="color: red;">${hint_menuName}</span>
    </div>
    <div class="form-group" >
        <label for="menuType" class="col-sm-2 control-label">菜单类型</label>
        <div class="col-sm-10">
            <select class="form-control" id="menuType" name="menuType">
                <option>click</option>
                <option>view</option>
            </select>
        </div>
    </div>
    <div class="form-group" >
        <label for="menuValue" class="col-sm-2 control-label">键值或者url地址</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="menuValue"
                   placeholder="请输入菜单键值或url地址" name="menuValue">
        </div>
        <span class="help-block col-sm-5" for="menuValue" style="color: red;">${hint_menuValue}</span>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">提交</button>
        </div>
    </div>
</form>
<form class="form-horizontal"  action="menuCreate" method="post" style="margin-top: 20px;width: 70%">
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">生成微信菜单</button>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <span class="help-block col-sm-5"  style="color: red;">${hint_menuInfo}</span>
        </div>
    </div>
</form>
</body>
</html>