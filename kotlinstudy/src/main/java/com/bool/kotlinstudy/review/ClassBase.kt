package com.bool.kotlinstudy.review


/**
 * 基本类型
 */

class ClassBase {

    val one = 1 // Int
    val threeBillion = 3000000000 // Long
    val oneLong = 1L // Long
    val oneByte: Byte = 1

    var a = 2.13
    var b = 2.13f
    var c = 2.1333332323123f


    public fun intToLong(){
        one.toByte()
        one.toLong()
        one.toDouble()
        one.toFloat()

        threeBillion.toInt()
        threeBillion.toString()

        oneByte.toChar()
        oneByte.toFloat()
        oneByte.toDouble()
    }


    fun main() {
        fun printDouble(d: Double) { print(d) }
        val i = 1
        val d = 1.1
        val f = 1.1f
        printDouble(d)
       // printDouble(i) // 错误：类型不匹配
       // printDouble(f) // 错误：类型不匹配
    }





}