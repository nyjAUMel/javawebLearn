<%--
  Created by IntelliJ IDEA.
  User: 91776
  Date: 2025/5/26
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  JSP中EL表达式的隐含对象
    1. pageContext
    2. param
    3. paramValues
    4. initParam
    5. 其它
--%>
应用根路径：${pageContext.request.contextPath}

<hr>

<%-- 获取用户提交的数据--%>
<%=request.getParameter("username")%>
<br>
<%--用EL表达式的方式--%>
${param.username}