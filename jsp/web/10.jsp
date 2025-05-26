<%@ page import="com.bjpowernode.javaweb.jsp.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: 91776
  Date: 2025/5/26
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  User user = new User();
  user.setUsername("张三");

  // 存入域当中
  request.setAttribute("userObj", user);

  request.setAttribute("a.b.c", "丁真");
%>

${userObj.username}
<%-- 或者这种写法(注意要有双引号)。这种情况可以解决key有特殊字符，比如说key是a.b.c--%>
${userObj["username"]}
${a.b.c}
上面是空白
${["a.b.c"]}