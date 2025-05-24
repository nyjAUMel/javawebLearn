<%@ page import="com.bjpowernode.oa.bean.Dept" %>
<%@page contentType="text/html;charset=UTF-8" %>

<%-- 从request作用域中获取部门对象 --%>
<%Dept dept = (Dept) request.getAttribute("dept");%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>部门详情</title>

</head>

<body>
<h1>部门详情</h1>
<hr/>
部门编号：<%=dept.getDeptNo()%><br>
部门名称：<%=dept.getDeptName()%><br>
部门位置：<%=dept.getLoc()%><br>

<input type="button" value="返回" onclick="history.back()">
</body>
</html>