package com.bool.kotlinstudy.bean

/**
 * 泛型,When 的使用
 *@author TianMingming
 *@date 2019/12/31 13:55
 */
enum class Colors (val  r: Int, var g : Int, val  b: Int) {
    RED(0,224,3),
    BLUE(0,224,3),
    GREEN(0,224,3) ;

    fun rgb (){
        println(r+g+b)
    }
}