package com.bjpowernode.template2;

/**
 * Description:
 *
 * @Author 夕川AU
 * @Create: 2025-05-07 14:11
 * @Version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        Person student = new Student();
        student.day();
        System.out.println("---------------------");
        Person teacher = new Teacher();
        teacher.day();
    }
}
