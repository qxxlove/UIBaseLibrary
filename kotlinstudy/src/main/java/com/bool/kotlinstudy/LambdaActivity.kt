package com.bool.kotlinstudy

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.locks.Lock

/**
 * Lambda 表达式
 *
 *   语法：
 *         1. 无参数的情况 ：
val/var 变量名 = { 操作的代码 }

2. 有参数的情况
val/var 变量名 : (参数的类型，参数类型，...) -> 返回值类型 = {参数1，参数2，... -> 操作参数的代码 }

可等价于
// 此种写法：即表达式的返回值类型会根据操作的代码自推导出来。
val/var 变量名 = { 参数1 ： 类型，参数2 : 类型, ... -> 操作参数的代码 }

3. lambda表达式作为函数中的参数的时候，这里举一个例子：
fun test(a : Int, 参数名 : (参数1 ： 类型，参数2 : 类型, ... ) -> 表达式返回类型){
...
}

4. 所谓闭包，即是函数中包含函数，这里的函数我们可以包含(Lambda表达式，匿名函数，局部函数，对象表达式)。
5. 高阶函数即指：将函数用作一个函数的参数或者返回值的函数

 *
 */
class LambdaActivity : AppCompatActivity() {

    lateinit var btn: Button
    var sum: Int = 0

    val arr = arrayOf(1, 3, 5, 7, 9)
    val map = mapOf("key1" to "value1", "key2" to "value2", "key3" to "value3")

    var dataChanged = MutableLiveData<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda)
        initLambda()
        initHigherOrder()
        initHigherOrderTwo()
        initOther()
        initStutdy()
        dataChanged.observe(this, Observer { })

    }


    /**
     *  思考： 如果你想把“一块代码”赋给一个Java变量，应该怎么做呢？
     *
     */
    fun initStutdy() {
        var a = "哈哈"
        var study = study(a)
       
    }

    /**
     * 一个简单函数方法
     */
     fun study(name: String): Unit {
        println(name)
    }


    fun initOther() {
        test
        test()
        /**lambda表达式作为函数中的参数的时候*/
        /**常规写法*/
        test(10, sum(3, 5)) // 结果为：18
        /**lanbda*/
        test(10, { num1: Int, num2: Int -> num1 + num2 })  // 结果为：18   .

    }

    /**
     * 高阶函数
     */
    fun initHigherOrder() {
        // 过滤掉数组中元素小于5的元素，取其打印。这里的it就表示每一个元素。
        println(arr.filter { it < 5 }.component1())
        //使用Lambda表达式的时候，可以用下划线(_)表示未使用的参数，表示不处理这个参数。
        map.forEach { key, value ->
            println("$key \t $value")
        }
        // 不需要key的时候
        map.forEach { _, value ->
            println("$value")
        }
        //匿名函数作为接收者类型
        val iop = fun Int.(other: Int): Int = this + other
        println(2.iop(3))
        // 要用Lambda表达式作为接收者类型的前提是接收着类型可以从上下文中推断出来。
        html {
            // 带接收者的 lambda 由此开始
            body()   // 调用该接收者对象的一个方法
        }

        /**闭包*/
        val t = test(3)
        println(t)
        println(t)
        println(t)
        //引用外部变量，并改变外部变量的值
        arr.filter { it < 7 }.forEach { sum += it }    // 结果： 9
    }


    /**
     * 高阶函数
     *    sumBy ： 字符求和
     *
     */
    fun initHigherOrderTwo() {
        val testStr = "abc"
        val sum = testStr.sumBy { it.toInt() }
        println(sum)

        // 当我们需要执行一个代码块的时候就可以用到这个函数,并且这个代码块是独立的。
        // 即我可以在run()函数中写一些和项目无关的代码，因为它不会影响项目的正常运行。
        testRun1()
        //因为run函数执行了我传进去的lambda表达式并返回了执行的结果，
        // 所以当一个业务逻辑都需要执行同一段代码而根据不同的条件去判断得到不同结果的时候。可以用到run函数
        println("num = $num")     // 10
        // T.run 
        val str = "kotlin"
        str.run {
            println("length = ${this.length}")
            println("first = ${first()}")
            println("last = ${last()}")
        }
        //    with是正常的高阶函数，T.run()是扩展的高阶函数。
        //    with函数的返回值指定了receiver为接收者(看源码)。
        with(str) {
            println("length = ${this.length}")
            println("first = ${first()}")
            println("last = ${last()}")
        }
        // T.apply()函数 // T.also()函数    T.apply的作用除了实现能实现T.run函数的作用外，还可以后续的再对此操作
        // 使用： 为TextView ,Button 设置属性、 设置为Fragment设置数据传递
        btn.apply {
            //text = "kotlin"
            // textSize = 13f
        }.apply {
            // 这里可以继续去设置属性或一些TextView的其他一些操作
        }.apply {
            //setOnClickListener{ .... }
        }
        initAlsoApply()

        // takeIf
        val result = str.takeIf {
            it.startsWith("ko")
        }

        println("result = $result")      //result = kotlin
        // takeUnless 与 takeIf 相反
        val result1 = str.takeUnless {
            it.startsWith("ko")
        }
        println("result = $result1")       // result = null
        // repeat
        repeat(5) {
            println("我是重复的第${it + 1}次，我的索引为：$it")
        }
    }


    /**
     * let   also    apply
     */
    fun initAlsoApply() {
        "kotlin".let {
            println("原字符串：$it")         // kotlin
            it.reversed()
        }.let {
            println("反转字符串后的值：$it")     // niltok
            it.plus("-java")
        }.let {
            println("新的字符串：$it")          // niltok-java
        }

        "kotlin".also {
            println("原字符串：$it")     // kotlin
            it.reversed()
        }.also {
            println("反转字符串后的值：$it")     // kotlin
            it.plus("-java")
        }.also {
            println("新的字符串：$it")        // kotlin
        }

        "kotlin".apply {
            println("原字符串：$this")     // kotlin
            this.reversed()
        }.apply {
            println("反转字符串后的值：$this")     // kotlin
            this.plus("-java")
        }.apply {
            println("新的字符串：$this")        // kotlin
        }
    }

    /**
     * run 函数
     */
    private fun testRun1() {
        val str = "kotlin"

        run {
            val str = "java"   // 和上面的变量不会冲突
            println("str = $str")
        }

        println("str = $str")
    }

    /**
     * run 函数
     */
    val index = 3
    val num = run {
        when (index) {
            0 -> "kotlin"
            1 -> "java"
            2 -> "php"
            3 -> "javaScript"
            else -> "none"
        }
    }.length


    /**
     * Lambda表达式作为接收者类型
     */
    class HTML {
        fun body() {
            println("Lambda表达式作为接收者类型")
        }
    }

    fun html(init: HTML.() -> Unit): HTML {
        val html = HTML()  // 创建接收者对象
        html.init()        // 将该接收者对象传给该 lambda
        return html
    }


    fun initLambda() {
        // 这里举例一个Android中最常见的按钮点击事件的例子
        btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //Toast.makeText(LambdaActivity.this,"onClick",Toast.LENGTH_SHORT).show()
            }
        })

        btn.setOnClickListener { Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show() }
    }


    /** 无参数*/
    // 源代码
    fun test() {
        println("无参数")
    }

    // lambda代码
    val test = { println("无参数") }

    /**有参数*/
    // 源代码
    fun test(a: Int, b: Int): Int {
        return a + b
    }

    // lambda
    val test1: (Int, Int) -> Int =
            { a, b -> a + b }
    // 或者
    val test2 = { a: Int, b: Int -> a + b }

    /**有参数*/
    fun sum(num1: Int, num2: Int): Int {
        return num1 + num2
    }

    /**lambda表达式作为函数中的参数的时候*/
    //invoke()函数：表示为通过函数变量调用自身，因为上面例子中的变量b是一个匿名函数。
    fun test(a: Int, b: (num1: Int, num2: Int) -> Int): Int {
        return a + b.invoke(3, 5)
    }

    fun test(num1: Int, bool: (Int) -> Boolean): Int {
        return if (bool(num1)) {
            num1
        } else 0
    }


    /**
     * 闭包： 携带状态
     */
    fun test(b: Int): () -> Int {
        var a = 3
        return fun(): Int {
            a++
            return a + b
        }
    }


    /**
     * 高价函数： lock
     */
    fun <T> lock(lock: Lock, body: () -> T): T {
        lock.lock()
        try {
            return body()
        } finally {
            lock.unlock()
        }
    }


    /**
     * 自定义高阶函数
     */
    private fun resultByOpt(num1: Int, num2: Int, result: (Int, Int) -> Int): Int {
        return result(num1, num2)
    }

    private fun testDemo() {
        val result1 = resultByOpt(1, 2) { num1, num2 ->
            num1 + num2
        }

        val result2 = resultByOpt(3, 4) { num1, num2 ->
            num1 - num2
        }

        val result3 = resultByOpt(5, 6) { num1, num2 ->
            num1 * num2
        }

        val result4 = resultByOpt(6, 3) { num1, num2 ->
            num1 / num2
        }

        println("result1 = $result1")
        println("result2 = $result2")
        println("result3 = $result3")
        println("result4 = $result4")
    }

}
