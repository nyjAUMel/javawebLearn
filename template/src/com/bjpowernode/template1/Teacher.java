package com.bjpowernode.template1;

/**
 * Description:
 *
 * @Author 夕川AU
 * @Create: 2025-05-07 11:26
 * @Version: 1.0
 */
public class Teacher {

    /**
     * 老师的一天
     */
    public void day() {
        study();
        eat();
        doSport();
        sleep();
        play();
        doHomeWork();
    }

    public void study() {
        System.out.println("老师教学生");
    }

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

    public void doHomeWork() {
        System.out.println("做作业");
    }
}
