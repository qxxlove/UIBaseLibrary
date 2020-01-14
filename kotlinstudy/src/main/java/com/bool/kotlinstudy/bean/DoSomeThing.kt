package com.bool.kotlinstudy.bean

/**
 * 接口
 *     1. 在接口中申明属性。接口中的属性要么是抽象的，要么提供访问器的实现。
 *        接口属性不可以有后备字段。而且访问器不可以引用它们。
 *        作为抽象：即重写属性的时候是在实现类的 类参数 中。这也是用代码提示去重写的实现方法
 *        作为访问器： 即手动方式去实现重写，并提供get()方法
 *     2. 存在冲突时
 *        eg: Demo4实现了Demo4InterfaceOne和Demo4InterfaceTwo两个接口，
 *            而两个接口中都存在两个相同方法名的方法。因此编译器不知道应该选哪个，
 *            故而我们用super<接口名>.方法名来区分。
 *@author TianMingming
 *@date 2020/1/14 13:37
 */
interface DoSomeThing {

    /**
     * 注意： val num3: Int = 3  这种方式不提供，为直接报错的
     * */
    val num1: Int
        get() = 3
    
    val num2 : Int


    /**
     * 定义一个无参数无返回值的方法
     */
    fun fun1()

    /**
     * 定义一个有参数的方法
     */
    fun fun2(num: Int)

    /**
     * 定义一个有参数有返回值的方法
     */
    fun fun3(num: Int) : Int

    // 下面的两个方法是有结构体， 故可以不重写
    /**
     * 定义一个无参数有返回值的方法
     */
    fun fun4() : String{
        return "fun4"
    }

    /**
     * 定义一个无结构体函数，大括号是可以省略的
     */
    fun fun5(){
        // 如果函数中不存在表达式，大括号可以省略。
        // 如fun1一样
    }
}