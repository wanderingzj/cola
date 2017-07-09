package com.wanderingzj.model;

/**
 * @author wangzhongjiezhongjie
 * @since 2017/6/28
 */
public class Friend {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Friend() {
    }

    public Friend(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getDescription() {
        return "This is " + name + ", His age is " + age;
    }
}
