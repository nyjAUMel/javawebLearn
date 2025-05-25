<%@page contentType="text/html;UTF-8" %>

<%--
EL 表达式（Expression Language）的作用是：简化 JSP 页面中 Java 对象和数据的访问，替代繁琐的 <%= %> 写法，使页面更简洁、易维护。
 它主要解决了 在 JSP 中写 Java 脚本代码（如 request.getAttribute()、session.getAttribute()）过于复杂、影响页面结构 的问题。

 总结：
    EL 表达式用于在 JSP 页面中更简单地获取和显示 Java 对象数据，提升开发效率与页面可读性。

    EL只负责取和打印
--%>
<%
    request.setAttribute("name", "zhangsan");
%>

<%--从作用域当取去出来，并打印到浏览器上。使用java代码怎么办呢？--%>
<%=request.getAttribute("name")%>
<%--使用EL表达式实现   (两者等价)--%>
${name}