package com.bool.kotlinstudy.bean

/**
 * 密封类（枚举）  ：用来表示受限的类继承结构。
 *   声明一个密封类，需要在类名前面添加 sealed 修饰符。
 *     虽然密封类也可以有子类，但是所有子类都必须在与密封类自身相同的文件中声明。
 *   相对于密封类的子类 必须要在一个文件中，扩展密封类子类的类（间接继承者）可以放在任何位置，而无需在同一个文件中。
 *    1.一个密封类是自身抽象的，它不能直接实例化 ，但是可以有抽象（abstract）成员。
      2.密封类不允许有非-private 构造函数（其构造函数默认为 private）。
      3. 枚举类的中的每一个枚举常量都只能存在一个实例。而密封类的子类可以存在多个实例。

 *@author TianMingming
 *@date 2020/1/7 15:08
 */
sealed  class UserBean {

    data class Const(val number: Double) : UserBean()
    data class Sum(val e1: UserBean, val e2: UserBean) : UserBean()
    object NotANumber : UserBean()   // 单例模式
}

/**外部生名*/
//object NotANumber : UserBean()   // 单例模式