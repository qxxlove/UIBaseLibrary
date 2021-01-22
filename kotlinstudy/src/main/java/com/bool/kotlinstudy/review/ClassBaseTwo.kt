package com.bool.kotlinstudy.review

class ClassBaseTwo {

    val a: Int = 100
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a

    val b: Int = 10000
    val boxedB: Int? = b
    val anotherBoxedB: Int? = b


    /**
     * 为什么会产生不一样的结果？
     *
     */
    public fun show (){
        println(boxedA === anotherBoxedA) // true
        println(boxedB === anotherBoxedB) // false
    }

}