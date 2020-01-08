package com.bool.kotlinstudy.bean

import com.bool.kotlinstudy.R

/**
 * 主题构造函数
 * Kotlin 中的一个类可以有一个主构造函数   以及 一个或多个次构造函数。 主构造函数是类头的一部分：它跟在类名后。
 * 下面典型的 构造函数，我们可以使用 constructor 关键字来形容，当然如果该类没有其他修饰符 可以省略 constructor 不写。
 *  如:
 *     如果构造函数有注解或可见性修饰符，这个 constructor 关键字是必需的，并且这些修饰符在它前面：
 *     public class Customer @Inject constructor(name: String) { }
 *  初始化的代码可以放到以 init 关键字作为前缀的初始化块中。
 *@author TianMingming
 *@date 2020/1/7 13:46
 */
class Girl constructor  (name : String) {

    init {
        print("jasdhfjshdjahsjfdhjakfhjkf")
    }


}