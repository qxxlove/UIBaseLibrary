package com.bool.kotlinstudy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bool.kotlinstudy.bean.Student

/**
 * Kotlin 入门
 * https://www.jianshu.com/p/fa48ae888879
 */
class MainActivity : AppCompatActivity() {

    // public  static final boolean  = false ;
    /**常量  val <常量名> : <常量类型> = <初始值> */
    val sum = 4
    /**变量  var <变量名> : <变量类型> = <初始值>*/
    var strName: String = "3"
    var strAge = 4
    /**延迟初始化话*/
    lateinit var stndent: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVar()
        initStudent()
        initNull()

    }

    private fun initVar() {
        strAge = 1
        strName = ""
        println(strAge)
        println("姓名：" + strName)
    }

    private fun initStudent() {
        stndent = Student()
        println(stndent.age)
        println(stndent.sum)
        println(stndent.isBoy)
        stndent.counter = 3
        println(stndent.counter)


    }

    private fun initNull() {
        //类型后面加 ? 表示可为空
        var age: String? = null
        println(age)     // null
        //字段后面加 "!!" ，如果为null则抛出空指针异常
       // val ages = age!!.toInt()
       // println(ages)
        //字段后面加 ”？“  如果为null不做处理返回 null
        val ages1 = age?.toInt()
        println(ages1)    //  null
        //使用 ”?:“  表示age为null返回-1
        val ages2 = age?.toInt() ?: -1
        println(ages2)       // -1 

        println(getStringLengthOne(""))
       println(getStringLengthTwo(""))


    }


    /**
     * 基础方法的定义：
     *  fun  方法名  参数  返回值
     *  注意冒号
     */
    fun main(strName: String): Int {
        return 0
    }


    /**
     * 有返回值
     */
    fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    /**
     * 无返回值
     */
    fun min(a: Int, b: Int): Unit {
        if (a > b) a else b
    }

    fun getStringLengthOne(obj: Any): Int? {
        if (obj is String) {
            // `obj`在这个分支中自动转换为`String`类型
            return obj.length
        }
        // `obj`仍然是`Any`类型
        return null
    }

    fun getStringLengthTwo(obj: Any): Int? {
        // `obj`在这个分支中自动转换为`String`类型
        if (obj !is String) return null
        return obj.length
    }

}
