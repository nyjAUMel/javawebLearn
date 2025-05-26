<%--
  Created by IntelliJ IDEA.
  User: 91776
  Date: 2025/5/26
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    request.setAttribute("username", "丁真");
%>

<%--写错了情况下，EL表达式什么都不输出--%>
<%=request.getAttribute("name")%>   <%-- null --%>
EL表达式：${name} <%-- 浏览器对null进行了处理，将null变成了空白 --%>