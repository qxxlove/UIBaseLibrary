package com.bool.kotlinstudy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Kotlin 入门
 * https://www.jianshu.com/p/fa48ae888879
 */
class MainActivity : AppCompatActivity() {

   // public  static final boolean  = false ;
    /**常量  val <常量名> : <常量类型> = <初始值> */
     val sum = 4
     /**变量  var <变量名> : <变量类型> = <初始值>*/
     var strName : String = "3"
     var strAge = 4


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        strAge = 1
        strName = ""
      
    }


    /**
     * 基础方法的定义：
     *  fun  方法名  参数  返回值
     *  注意冒号
     */
    fun main (strName : String) : Int {
      return 0
    }


    /**
     * 有返回值
     */
    fun  max (a : Int ,b : Int) : Int{
       return if (a > b) a else b
    }

    /**
     * 无返回值
     */
    fun  min (a : Int ,b : Int) : Unit{
         if (a > b) a else b
    }

}
