<%@page contentType="text/html;charset=utf-8" %>

<%-- 四个域都存了数据，并且key相同--%>
<%
    pageContext.setAttribute("data", "pageContext");
    request.setAttribute("data", "request");
    session.setAttribute("data", "session");
    application.setAttribute("data", "application");
%>

<%-- EL表达式默认从小范围取数据 pageContext < request < session < application--%>
${data}

<hr>

<%-- 在EL表达式可以指定范围来读取数据--%>
<%--EL 表达式的四个隐式范围对象是 pageScope, requestScope, sessionScope, applicationScope，用于访问不同作用域中的数据。--%>
${applicationScope.data}
${sessionScope.data}
${requestScope.data}
${pageScope.data}