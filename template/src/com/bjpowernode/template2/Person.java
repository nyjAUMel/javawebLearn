package com.bjpowernode.template2;

/**
 * Description:
 *      Teacher和Student类，都是继承Person类
 *      1. Person就是模板设计模式当中的模板类
 *      2. day()方法就是模板方法设计模式当中的模板方法
 * @Author 夕川AU
 * @Create: 2025-05-07 11:31
 * @Version: 1.0
 */
public abstract class Person {

    // 模板方法
    // 添加了final关键字，表示该方法不能被重写，这样核心算法就得到了保护
    // 模板方法定义核心的算法骨架，具体的实现步骤可以延迟到子类中实现
    // 核心算法一方面是得到了保护，另一方面就是算法得到了重复使用
    // 另外代码也得到了复用，因为算法中某些步骤是固定的，这些固定的代码不会随着子类的变化而变化，这一部分代码可以写到模板类当中
    public final void day() {
        study();
        eat();
        doSport();
        sleep();
        play();
    }
    public abstract void study();

    public void eat() {
        System.out.println("吃东西");
    }

    public void doSport() {
        System.out.println("运动");
    }

    public void sleep() {
        System.out.println("睡觉");
    }

    public void play() {
        System.out.println("打游戏");
    }

}
