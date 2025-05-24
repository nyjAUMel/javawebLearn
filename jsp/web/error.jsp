<%--
  Created by IntelliJ IDEA.
  User: 91776
  Date: 2025/5/24
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--当你设置了 isErrorPage="true" 后，JSP 页面中就可以使用内置对象 exception，
它代表在其他页面发生异常时传递过来的 Throwable 对象。
--%>
<%@page isErrorPage="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>出错页面</h1>
<%--
   跳到错误页面后根本看不到错误信息，为了解决这个问题：
        可以在错误页面可以启动jsp九大内置对象之一：exception--%>
<%
    // 打印出异常信息，输出到后台
    exception.printStackTrace();
%>
</body>
</html>
