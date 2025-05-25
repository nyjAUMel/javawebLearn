package com.bjpowernode.javaweb.jsp.bean;

/**
 * Description:
 *
 * @Author nyjAUMel
 * @Create: 2025-05-25 18:17
 */
public class User {
    // 标准属性
    private String username;
    private String password;
    private int age;
    private Address address;

    // 连续大写字母属性
    private String URL = "http://example.com";

    // 布尔类型属性
    private boolean active = true;

    // 特殊方法名属性（无对应字段）
    private String email = "test@example.com";

    // 标准 getter/setter
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // 连续大写字母 getter
    public String getURL() {
        return URL;
    }

    // 布尔类型 getter
    public boolean isActive() {
        return active;
    }

    // 特殊方法名 getter（属性名映射为 emailAddress）
    public String getEmailAddress() {
        return email;
    }

    // 无字段只有方法的属性（属性名映射为 DJ）
    public String getDJ() {
        return "Hello DJ!";
    }

    // 对象
    public Address getAddress() {
        return address;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", URL='" + URL + '\'' +
                ", active=" + active +
                ", email='" + email + '\'' +
                '}';
    }
}
