package com.bool.uibaselibrary.bean;

/**
 * 车辆列表adapter
 *
 * @author TianMingming
 * @date 2019/12/27 15:29
 */
public class Student {

    private  String name ;
    private  int  age ;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
}
