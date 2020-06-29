package com.bool.kotlinstudy.bean

/**
 * List集合如何定义
 *
 *  get,set属性以及可以自定义
 */
class Student : People() {

    val age: String = "8888"
    var sum: Int = 3
    // 0 女  1 男
    var sex: Int = 0
    val isBoy: Boolean
        get() {
            return if (sex == 1) {
                true
            } else {
                false
            }
        }

    //初始化值会直接写入备用字段
    var counter = 0
        get() = field   // field可以理解为自己本身
        set(value) {
            if (value >= 0)
                field = value
        }

    // 这种情况并不需要备用字段，所有不会生成备用字段
    val isEmpty : Boolean = false
    //  get() = this.size == 0


    /**
     * 测试扩展函数
     *  当： 一个类定义有一个成员函数与一个扩展函数，
     *      而这两个函数又有相同的接收者类型、相同的名字并且参数都没有 或者参数类型都一样
     *      说白了，就是两个一模一样，只是定义方式不同，此时 成员函数 优先级高于 扩展函数
     *      当然，只要两者有任何不同，就不存在优先级之说了
     *
     */
    fun foo() {
        println("member")
    }

    fun Student.foo() {
        println("extension")
    }

    fun Student.foo(c: Int) {
        println("extension")
    }


    /**
     * 重载函数
     */
    @JvmOverloads
    fun <T> joinString(
            collection: Collection<T> = listOf(),
            separator: String = ",",
            prefix: String = "",
            postfix: String = ""
    ): String {
        return collection.joinToString(separator, prefix, postfix)
    }

    
    /**
     *  调用的地方
     */
    fun main(args: Array<String>) {
        //函数使用命名参数可以提高代码可读性
        println(joinString(collection = listOf(1, 2, 3, 4), separator = "%", prefix = "<", postfix = ">"))
        println(joinString(collection = listOf(1, 2, 3, 4), separator = "%", prefix = "<", postfix = ">"))
        println(joinString(collection = listOf(1, 2, 3, 4), prefix = "<", postfix = ">"))
        println(joinString(collection = listOf(1, 2, 3, 4), separator = "!", prefix = "<"))
        println(joinString(collection = listOf(1, 2, 3, 4), separator = "!", postfix = ">"))
        println(joinString(collection = listOf(1, 2, 3, 4), separator = "!"))
        println(joinString(collection = listOf(1, 2, 3, 4), prefix = "<"))
        println(joinString(collection = listOf(1, 2, 3, 4), postfix = ">"))
        println(joinString(collection = listOf(1, 2, 3, 4)))
    }



    
    /**
     * inner 关键字是重点
     *  没有添加 inner 的情况下，称之为 嵌套类，CButton 中是无法获取到外部类的引用的。
      如果添加上 inner 后，则算是内部类，可以获取到外部类的引用。
     */
    inner class CButton {
        var b: Int = sum
    }

}