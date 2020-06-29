package com.bool.kotlinstudy.bean

/**
 * 枚举
 *   1. 枚举类中的每一个枚举常量都是一个对象，并且他们之间用逗号分隔。
 *   2. 因为每一个枚举都是枚举类的实例，所以他们可以是初始化过的。
 *
     枚举常量的匿名类：
      要实现枚举常量的匿名类，则必须提供一个抽象方法（必须重写的方法）。
         且该方法定义在枚举类内部。而且必须在枚举变量的后面。
      枚举变量之间使用逗号（,）分割开。但是最后一个枚举变量必须使用分号结束。
          不然定义不了抽象方法。
      在上面已经说过，每一个枚举常量就是一个对象。

 *
 *@author TianMingming
 *@date 2019/12/31 13:55
 */
enum class Colors (var argb : Int) {

    RED(0xFF0000){
        override fun print() {
            println("我是枚举常量 RED ")
        }
    },
    WHITE(0xFFFFFF){
        override fun print() {
            println("我是枚举常量 WHITE ")
        }
    },
    BLACK(0x000000){
        override fun print() {
            println("我是枚举常量 BLACK ")
        }
    },
    GREEN(0x00FF00){
        override fun print() {
            println("我是枚举常量 GREEN ")
        }
    };


    /**抽象方法*/
    abstract fun print()

}