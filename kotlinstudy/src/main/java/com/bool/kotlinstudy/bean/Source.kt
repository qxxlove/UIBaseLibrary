package com.bool.kotlinstudy.bean

/**
 * 泛型接口
 *     类型协变
 *  in T:     来确保Source的成员函数只能消费T类型，而不能返回T类型
    out R：  来确保Source的成员函数只能返回R类型，而不能消费R类型
    实际上是定义了类型参数在该类或者接口的用途，是用来消费的还是用来返回的，对其做了相应的限定
       类型投射
       泛型函数

    泛型擦除的原理是什么？

 *@author TianMingming
 *@date 2020/1/7 16:52
 */
interface Source <in T, out R> {

    // in 函数，可以当做参数使用，消费，但是不能作为返回值
    fun mapT(t: T): Unit

    // out 函数，不能用来当参数，不能消费，但是可以作为返回值
    fun nextR(): R


    /**
     * eg ：
     *   from的泛型参数使用了协变注解out修饰，意味着该参数不能在该函数中消费，在该方法中 禁止 对该参数进行任何操作。

       对于fill函数中，dest的泛型参数使用了协变注解in修饰，
         Array<in String>与Java的 Array < ? super String> 相同,
        也就是说, 你可以使用CharSequence数组,或者 Object 数组作为 fill() 函数的参数、

      这种声明在Kotlin中称为类型投射(type projection)，
        类型投射的主要用于对参数做了相对因的限定，避免了对该参数类的不安全操作。

     */
    fun copy(from: Array<out String>, to: Array<Any>) {
        // ...
    }

    fun fill(dest: Array<in String>, value: String) {
        // ...
    }


    /**
     * 泛型函数
     * 类可以有类型参数。函数也可以有。类型参数要放在函数名称之前：
     */
    fun <T> singletonList(item: T): List<T> {
        // ……

        return  listOf(item)
    }

    fun <T> T.basicToString() : String {  // 扩展函数
        // ……
        return  ""
    }



}