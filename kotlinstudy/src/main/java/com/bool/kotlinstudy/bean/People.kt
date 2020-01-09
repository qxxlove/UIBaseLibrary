package com.bool.kotlinstudy.bean

/**
 * 常量生明
 *  1.在顶层声明
    2.在object修饰的类中声明，在kotlin中称为对象声明，它相当于Java中一种形式的单例类
    3.在伴生对象中声明
 *@author TianMingming
 *@date 2019/12/23 19:19
 */
const val indexx = 0  //     ==> public static final int
open class People {

    /**伴生对象*/
    companion object {
        const val NUM_C = "伴生对象中声明"
    }
}