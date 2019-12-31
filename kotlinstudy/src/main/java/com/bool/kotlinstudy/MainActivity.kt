package com.bool.kotlinstudy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bool.kotlinstudy.bean.Colors
import com.bool.kotlinstudy.bean.Student
import com.bool.kotlinstudy.bean.indexx

/**
 * Kotlin 入门
 * https://www.jianshu.com/p/fa48ae888879
 *
 *   1. 常量
 *   2. 变量
 *   3. 非空
 *   4. 方法，类， 对象
 *   5. if ,for，
 *   6. 集合，数组
 *   7. kotlin 中的顶层函数也就是我们所说的工具类
 *      Java中调用Kotlin中定义顶层函数，一般是顶层文件名+"Kt"后缀作为静态函数的类名调用相应函数
       通过Kotlin中的@file: JvmName("自定义生成类名")注解就可以自动生成对应Java调用类名
 *      顶层属性即常量 类似于 psfs
 *
 *      扩展函数，属性
 *       
 *   5. 枚举，when     现在不清楚
 *
 *
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


    // set
    val set = hashSetOf(1, 2, 3)
    // list
    val arr = arrayListOf(2, 3, 4, 5)
    // hashmap
    val map = hashMapOf(1 to "A", 2 to "B")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVar()
        initStudent()
        initNull()
        initEnum()

    }


    fun getColor(color: Colors) {
        var  a  = 1
        when (a){
            
        }
    }

    private fun initEnum() {
        indexx
    }


    /**
     * if 语句
     */
    fun getMaxNumber(a: Int, b: Int) {
        if (a > b) a else b
    }

    fun getTotalNumber(a: Int, b: Int) = a + b

    /**
     * for 语句
     *
     * reversed() : 反序。即和初始化的顺序反过来。
    sorted() : 自然升序。
    sortedBy{} : 根据条件升序，即把不满足条件的放在前面，满足条件的放在后面
    sortedDescending() : 自然降序。
    sortedByDescending{} : 根据条件降序。和sortedBy{}相反
     */
    fun oneMethond(args: Array<String>) {
        for (arg in args) {
            print(arg)
        }

        //  检测某个数字是否在指定区间外
        var x: Int = 1
        var y: Int = 4
        if (x in 1..y - 1) { // x是否在 1到y-1的范围
            print("OK")
        }
        for (i in 1..100) {
        }  //  1到100范围
        for (i in 1 until 100) {
        } // 半开范围，不包括100，相当于[1,100)
        for (x in 2..10 step 2) {
        } // 每次加2，内容为 x , x+2 ,x+4 ...
        for (x in 10 downTo 1) {
        } // 倒序


        val langs = listOf("C", "C++", "Java", "Python", "JavaScript")
        // startsWith 给定的字串和自己定义的字串相比较，看是否是前缀相同
        langs.filter { it.startsWith("C") }
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach { println(it) }
    }


    /**
     * 初始化变量
     */
    private fun initVar() {
        strAge = 1
        strName = ""
        println(strAge)
        println("姓名：" + strName)
    }

    /**
     * 初始化实体类
     */
    private fun initStudent() {
        stndent = Student()
        println(stndent.age)
        println(stndent.sum)
        println(stndent.isBoy)
        stndent.counter = 3
        println(stndent.counter)


    }

    /**
     * 非空语句
     */
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


    /**
     * is 语句
     */
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


    /**
     * 可边长
     * public void setData(Object... objects)。
     */
    fun vars(vararg args: Int) {
        for (arg in args) {
            print(arg)
        }
    }

    /**匿名函数*/
    val sumLambda: (Int, Int, Int) -> Int = { a, b, c -> a + b + c }

    /**基本 常见的方法定义*/
    fun sum(a: Int, b: Int, c: Int): Int {
        return a + b + c
    }

}
