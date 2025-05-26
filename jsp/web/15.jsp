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

<%--
    获取用户提交的数据
    http://localhost:8080/jsp/15.jsp?username=zhangsan
--%>
<%=request.getParameter("username")%>
<br>
<%--用EL表达式的方式--%>
${param.username}
<%-- getParameter和param只能获取第一个数据，也就是说如果传来的是复选框那么只能获取第一个。 --%>
<%=request.getParameter("hobby")%>
<%=request.getParameter("hobby")%>
${param.hobby}
${param.hobby}
<hr>

<%--
    getParameterValues和paramValues可以获取所有数据。
--%>
一维数组：<%=request.getParameterValues("hobby")%>、
一维数组：${paramValues.hobby}
<br>
<%=request.getParameterValues("hobby")[0]%>、
<%=request.getParameterValues("hobby")[1]%>
${paramValues.hobby[0]}、
${paramValues.hobby[1]}