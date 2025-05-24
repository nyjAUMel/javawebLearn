<%@ page contentType="text/html;charset=UTF-8" %>

<%-- 在<%! %>里写的代码会自动翻译到service方法外 --%>
<%! %>
<%-- 所以这个会报错 --%>
<%--<%!--%>
<%--    System.out.println("Hello JSP");--%>
<%--%>--%>

<%
    System.out.println("Hello JSP");
%>

<%!
    // 成员变量
    private String name = "zhangsan";

    // 静态代码块
    static {
        System.out.println("静态代码块");
    }

    // 方法
    public void method() {
        System.out.println("丁真方法");
    }
%>


<%--
 在<%%>里写的代码块因为会被翻译到service方法里，所以会有先后顺序要求。比如要先声明变量再使用
 在<%!%>里写的代码会被翻译到类当中
 <%%>和<%!%>没有可比性，<%!%>即使写在<%%>后面也会翻译在它前面。
 所以先写的<%%>可以调用后面声明的代码<%!%>
 --%>
<%
    System.out.println(f);
%>
<%!
    public static String f = "f";
%>