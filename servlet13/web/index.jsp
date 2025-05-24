<%--
  Created by IntelliJ IDEA.
  User: 91776
  Date: 2025/5/22
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/cookie/generate">服务器生成cookie，然后将cookie响应给浏览器，浏览器接收cookie，将cookie放到客户端上</a>
<br>
<a href="<%=request.getContextPath()%>/cookie/receive">另一个Servlet获取Cookie(因为Cookie可能有多个，所以返回的是数组)</a>
</body>
</html>
