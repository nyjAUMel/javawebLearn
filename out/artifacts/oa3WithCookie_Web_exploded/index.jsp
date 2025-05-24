<%@page contentType="text/html;charset=utf-8" %>
<%-- 访问jsp时不生成session对象--%>
<%@page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>欢迎使用OA系统</title>
</head>

<body>
<%-- 前端发送请求路径的时候，如果请求路径是绝对路径，要以 / 开头，加项目名 --%>
<%-- 项目名oa写死了，这显然不合适 --%>
<%-- <a href="/oa3/dept/list">查看部门列表</a> --%>

<%-- <a href="<%=request.getContextPath()%>/dept/list">查看部门列表</a> --%>

<h1>Login Page</h1>
<hr>
<%
    // 获取Cookie
    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        response.sendRedirect(request.getContextPath()+"/dept/list");
    }
%>
<form action="<%=request.getContextPath()%>/user/login" method="post">
    username: <input type="text" name="username"><br>
    password: <input type="password" name="password"><br>
    <input type="checkbox" name="remember" value="1">十天免登录<br>
    <input type="submit" value="login">
</form>
</body>

</html>