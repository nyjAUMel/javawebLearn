package com.bjpowernode.javaweb.jsp.bean;

/**
 * Description:
 *
 * @Author nyjAUMel
 * @Create: 2025-05-25 18:45
 */
public class Address {
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                '}';
    }
}
