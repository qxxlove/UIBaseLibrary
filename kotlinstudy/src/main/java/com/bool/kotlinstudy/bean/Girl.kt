package com.bool.kotlinstudy.bean

/**
 * 主题构造函数
 *  Kotlin 中的一个类可以有一个主构造函数  以及一个或多个次构造函数。
 *      主构造函数是类头的一部分：它跟在类名后。
 * 下面典型的 构造函数，我们可以使用 constructor 关键字来形容，
 *      当然如果该类没有其他修饰符 可以省略 constructor 不写。
 *  如:
 *     如果构造函数有注解或可见性修饰符，这个 constructor 关键字是必需的，
 *          并且这些修饰符在它前面：
 *     public class Customer @Inject constructor(name: String) { }
 *         初始化的代码可以放到以 init 关键字作为前缀的初始化块中。
 *
 *@author TianMingming
 *@date 2020/1/7 13:46
 */
open class Girl constructor  (name : Int) {

    /**
     * init{...}中能使用构造函数中的参数
     */
    init {
        println(name)
        print("jasdhfjshdjahsjfdhjakfhjkf")
    }

    /**
     * 二级构造函数中的参数1(num)，是委托了主构造函数的参数num。
     * 当实例化类的时候只传1个参数的时候，只会执行init代码块中的代码。
     * 当传2个参数的时候，除了执行了init代码块中代码外，
     * 还执行了二级构造函数中的代码。
     */
    constructor(name : Int, num2: Int) : this(name = 1) {
        println(name + num2)
    }


    /**
     *
     * 二级需要主构造函数调用
     *   primary constructor call expected
     *    当类的主构造函数都存在默认值时的情况
     *    当实例化无参的构造函数时。使用了参数的默认值。
     */
    class Test constructor(num1: Int = 10 , num2: Int = 20){

        init {
            println("num1 = $num1\t num2 = $num2")
        }

        constructor(num1 : Int = 1, num2 : Int = 2, num3 : Int = 3) : this(num1,num2){
            println("num1 = $num1\t num2 = $num2 \t num3 = $num3")
        }
    }
}