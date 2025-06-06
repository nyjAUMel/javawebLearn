package oa.bean;


import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        // 用户登陆了
        // User类型的对象向session中存放了
        // 获取ServletContext对象
        ServletContext application = event.getSession().getServletContext();
        // 获取在线人数
        Object onlineCount = application.getAttribute("onlineCount");
        if (onlineCount == null) {
            application.setAttribute("onlineCount", 1);
        } else {
            application.setAttribute("onlineCount", (Integer) onlineCount + 1);
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        // 用户退出了
        // User类型的对象向session中移除了
        ServletContext application = event.getSession().getServletContext();
        Object onlineCount = application.getAttribute("onlineCount");
        application.setAttribute("onlineCount", (Integer) onlineCount - 1);
    }

    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
