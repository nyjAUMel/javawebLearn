<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.bjpowernode.javaweb.jsp.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: 91776
  Date: 2025/5/26
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 存 Map 集合--%>
<%
    Map<String, String> map = new HashMap<>();
    map.put("name", "张三");
    map.put("password", "123456");
    map.put("age", "18");
    request.setAttribute("map", map);

    // 存对象
    HashMap<String, User> stringUserHashMap = new HashMap<>();
    User user = new User();
    user.setUsername("丁程鑫");
    user.setAge(66);
    stringUserHashMap.put("user", user);
    request.setAttribute("stringUserHashMap", stringUserHashMap);
%>

<%-- 取出map集合中的普通字符串--%>
${map.name}
${map["password"]}

<hr>

<%-- 取出map集合中的对象的属性--%>
${stringUserHashMap.user.username}
${stringUserHashMap.user["age"]}
