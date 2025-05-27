<%--
  Created by IntelliJ IDEA.
  User: 91776
  Date: 2025/5/27
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
    EL表达式中的运算符
        1. 算术运算符
            + - * / %
        2. 比较运算符
            > < >= <= == != eq
        3. 逻辑运算符
            && || ! and or not
        4. 条件运算符
            ? :
        5. 取值运算
            [] 和 .
        6. empty运算符
--%>
${10 + 20}<br>
<%--EL表达式中 加号之能做运算，不能做拼接。所以这里会转成数字--%>
${10 + "20"} <%-- 输出 30 --%>
<br>
\${10 + "abc"} <%-- 报错NumberFormatException --%>
\${"def" + "abc"}

<hr>

<%-- 关系运算符--%>
${10 > 20}<br>
${10 < 20}<br>
${"abc" == "abc"}<br>

<%
    Object o = new Object();
    request.setAttribute("o1", o);
    request.setAttribute("o2", o);
%>
${o1 == o2}
${requestScope.o1 == requestScope.o2}

<br>
<%--
!!!!!! EL表达式中的 == 其实调用了equals方法
--%>
<%
    String abc = new String("abc");
    String cde = new String("abc");
    request.setAttribute("abc", abc);
    request.setAttribute("cde", cde);
%>
${abc == cde}<br>
<%-- eq 和 == 一样调用的都是equals方法--%>
${abc eq cde}<br>
<%-- != 也会调用对象的equals方法--%>
${abc != cde}<br>
${!(abc eq cde)}<br>
${not(abc eq cde)}<br>

<hr>

<%--条件运算符--%>
${empty param.username ? "用户名是空的" : "欢迎"}<br>

<hr>

<%-- empty 运算符--%>
<%-- 判断是否为空,空返回true反之返回true--%>
1${empty "abc"}<br>
2${empty ""}<br>
3${empty null}<br>
4${empty requestScope.abc}<br>
5${!empty param.username}<br>
<%--不传password时为true--%>
6${param.password == null}<br>
7${empty param.password == null}<br>
<%-- false--%>
8${empty true}<br>
<%-- false--%>
9${empty false}<br>