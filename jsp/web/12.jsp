<%@page import="com.bjpowernode.javaweb.jsp.bean.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>
<%--
  Created by IntelliJ IDEA.
  User: 91776
  Date: 2025/5/26
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // 存放一个字符串数组
    String [] usernames = {"张三","李四","王五","赵六","田七"};
    request.setAttribute("usernames", usernames);

    // 存放一个对象数组
    User [] users = new User[2];
    User user1 = new User();
    user1.setUsername("王源");
    User user2 = new User();
    user2.setUsername("丁真");

    users[0] = user1;
    users[1] = user2;
    request.setAttribute("users", users);

    // List集合
    ArrayList<String> list = new ArrayList<>();
    list.add("刘耀文");
    list.add("马嘉祺");
    list.add("丁程鑫");
    request.setAttribute("list", list);

    // Set集合
    Set<String> set = new HashSet<>();
    set.add("宋亚轩");
    set.add("贺峻霖");
    set.add("严浩翔");
    request.setAttribute("set", set);
%>

<%-- 从数组中取字符串数据 --%>
${usernames}
<br>
${usernames[0]}
<br>
${usernames[1]}
<br>
<%--越界显示的是空--%>
${usernames[100]}

<hr>

<%-- 从数组中取对象的属性--%>
${users[0].username}
<br>
${users[1]["username"]}

<hr>

<%-- 从list集合中取字符串数据--%>
${list} <%-- 调用list的toString()方法--%>
${list[0]}

<%-- 从set集合中取字符串数据--%>
${set}