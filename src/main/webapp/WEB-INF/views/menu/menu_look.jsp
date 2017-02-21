<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<html>
<head>
	<title>hello</title>
	<script type="text/javascript">
		var param = {};
		param["user"] = userViewModel.model;
		param["roles"] = userViewModel.role.get("userRole");
		$.ajax({
			type: "post",
			url: "${base.contextPath}/hr/employee/create_user",
			data: kendo.stringify(param),
			dataType: "json",
			contentType: 'application/json',
			success: function (data) {
				if (data["success"] == true) {
					kendo.ui.showInfoDialog({
						message: $l('创建成功')
					});
					$("#createUserWin").data("kendoWindow").close();
				} else {
					kendo.ui.showInfoDialog({
						message: $l('创建失败')
					});
					$("#createUserWin").data("kendoWindow").close();
				}
			}
		});
	</script>
</head>

<body>
查看
</body>
</html>  