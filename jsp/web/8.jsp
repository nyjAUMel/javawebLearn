<%@page contentType="text/html;charset=utf-8" %>

<%-- 四个域都存了数据，并且key相同--%>
<%
 /* pageContext.setAttribute("data", "pageContext");
 request.setAttribute("data", "request"); */
 //session.setAttribute("data", "session");
 application.setAttribute("data", "application");
%>

<%-- EL表达式优先从小范围取数据 pageContext < request < session < application--%>
${data}
