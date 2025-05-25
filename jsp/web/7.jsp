<%@ page import="com.bjpowernode.javaweb.jsp.bean.User" %>
<%@ page import="com.bjpowernode.javaweb.jsp.bean.Address" %><%--
  Created by IntelliJ IDEA.
  User: niuyujie
  Date: 2025/5/25
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    Address address = new Address();
    address.setCity("北京");

    User user = new User();
    user.setUsername("张三");
    user.setPassword("123456");
    user.setAge(18);
    user.setAddress(address);


    // 存储user对象到request作用域中
    request.setAttribute("userObj", user);
%>

<%-- 使用 EL 表达式从reqeust域中取出对象并输出--%>
<%--
 在 JSP 的 EL 表达式 ${userObj} 中，当直接输出一个对象时，EL 表达式会默认调用该对象的 toString() 方法来生成字符串输出。
    EL 表达式的默认行为
        EL 表达式 ${userObj} 会尝试从作用域（如 request、session、application）中获取 userObj 对象。
        如果 userObj 是一个非基本类型的 Java 对象（如 User 类实例），EL 表达式会自动调用该对象的 toString() 方法来输出内容。
        如果 User 类没有重写 toString() 方法，则会调用 Object 类的默认 toString()（返回类似 com.bjpowernode.javaweb.jsp.bean.User@1a2b3c4d 的哈希值）。
 --%>
${userObj}
<br>
<%-- 这个调用的还是getter()方法，只不过去了get首字母小写 --%>
${userObj.username}
<br>
${userObj.password}
<br>
${userObj.age}
<br>
<%--User类里并没有这个字段，只有一个get方法。可以调用成功，说明和属性关系不大，只是调用了get方法--%>
${userObj.DJ}


<%-- 标准属性 --%>
用户名：${userObj.username} （调用 getUsername()）<br>
密码：${userObj.password} （调用 getPassword()）<br>
年龄：${userObj.age} （调用 getAge()）<br>

<%-- 连续大写字母属性 --%>
URL：${userObj.URL} （调用 getURL()）<br>

<%-- 布尔类型属性 --%>
激活状态：${userObj.active} （调用 isActive()）<br>

<%-- 特殊方法名属性 --%>
邮箱：${userObj.emailAddress} （调用 getEmailAddress()）<br>

<%-- 无字段只有方法的属性 --%>
DJ 属性：${userObj.DJ} （调用 getDJ()）<br>

<%-- 直接输出对象（调用 toString()）--%>
对象字符串：${userObj} <br>


<%-- 输出对象属性 --%>
${userObj.address.city}