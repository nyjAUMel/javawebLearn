package com.bjpowernode.javaweb.bean;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

import java.util.Objects;

/*
* 普通的类，但实现了HttpSessionBindingListener接口
*
* HttpSessionBindingListener与HttpSessionAttributeListener不同，前者只有实现了该接口的类被添加到session
* 中时才会被监听。后者则是监听所有添加到session域中的属性，包括普通属性，对象属性，数组属性等。
* */
public class User1 implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("User1.valueBound");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("User1.valueUnbound");
    }

    private String usercode;
    private String username;
    private String password;

    public User1() {
    }

    public User1(String usercode, String username, String password) {
        this.usercode = usercode;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User1{" +
                "usercode='" + usercode + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User1 user1 = (User1) o;
        return Objects.equals(usercode, user1.usercode) && Objects.equals(username, user1.username) && Objects.equals(password, user1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usercode, username, password);
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
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
