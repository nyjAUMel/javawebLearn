
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%-- 控制整个页面忽略EL表达式--%>
<%@ page isELIgnored="true"%>
<%
    request.setAttribute("username", "丁真");
%>
<%--EL表达式被设置为忽略时，就会把它整体当成是一个普通的字符串--%>
${username}

<br>

<%--上面这种做法会忽略整个JSP页面的EL表达式，如果只想忽略某个可以加反斜杠--%>
\${username}