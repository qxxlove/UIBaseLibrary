package com.bool.kotlinstudy.bean

/**
 * 数据类
 *  以下是系统自带的方法
 *  equals()/hashCode() 对；
    toString() 格式是 "User(name=John, age=42)"；
    componentN() 函数 按声明顺序对应于所有属性；
    copy() 函数
  注意:
     在 JVM 中，如果生成的类需要含有一个无参的构造函数，则所有的属性必须指定默认值。
     data class User(val name: String = "", val age: Int = 0)
  数据类的属性修改用copy 函数

 *@author TianMingming
 *@date 2020/1/7 14:08
 */
data class CarData (var name: String, var age: Int) {




}