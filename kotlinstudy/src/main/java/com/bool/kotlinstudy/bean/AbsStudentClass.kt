package com.bool.kotlinstudy.bean

/**
 * 抽象类
 * 抽象类，不能创建实例 ，和 java 的都比较相似
 *@author TianMingming
 *@date 2020/1/7 13:35
 */
abstract class AbsStudentClass {

    // 自身的属性
    val TAG = this.javaClass.simpleName
    // 抽象属性
    abstract var name : String
    // 抽象方法
    abstract fun init()

    // 抽象方法 默认就是open（此处省略） 不能实例，必须继承实现
    abstract fun  ab1()
    //抽象类中的 方法 不是默认open的，所以需要标明
    open fun ab2(){}

    fun ab3(){}

    // 自身的函数
    fun test() : Unit{
        // exp
    }

}