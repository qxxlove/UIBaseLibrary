package com.bool.kotlinstudy.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bool.kotlinstudy.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


/**
 * 协程
 *   1. 协程实战
 *   https://mp.weixin.qq.com/s/0lgYPvwL1B6ohvKoBHC0oQ
 *   2.协程 Kotlin Coroutine 初探
 *    https://www.jianshu.com/p/0aaf300ac0fe
 *    协程第二篇（简易版）
 *    https://www.jianshu.com/p/af6604b64709
 *
 *
 */
class CoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        initCoroutine()
        initCoroutineTwo()

    }


    /**
     * 注意： Dispatchers.Main
     * 执行结果为：Hello main -> World main -> End main，
     * 也就是说这个suspend函数仍然运行在主线程中，suspend并没有切换线程的作用。
     */
    fun initCoroutineTwo() {
        GlobalScope.launch(Dispatchers.Main) {
            println("Hello ${Thread.currentThread().name}")
            test("123123")
            println("End ${Thread.currentThread().name}")
        }
    }

    /**
     * 挂起函数 (suspend 一个挂起函数的标识)
     * */
    suspend fun test() {
        println("World ${Thread.currentThread().name}")
    }


    /**
     *  注意： Dispatchers.IO
     * 执行的结果为：Hello main -> World DefaultDispatcher-worker-1 -> End main，
     * 这说明我们的suspend函数的确运行在不同的线程之中了。 就是一个挂起函数的标识
     * 就是说实际是上withContext方法进行的线程切换的工作
     * 线程切换其实还有其他方法，但是withContext是最常用的一个，其他还需进一步了解
     */
    suspend fun test(a:String){
        withContext(Dispatchers.IO){
            println("World ${Thread.currentThread().name}")
        }
    }


    /**
     *  1. 顺编写异步代码有体序现在什么地方呢？
     *  2. 协程中的代码自动地切换到其他线程之后又自动地切换回了主线程！
     *
     * */
    fun initCoroutine() {
        /**   好处：
         * “既按照顺序的方式编写代码，又可以让代码在不同的线程顺序执行”的
         *   “顺序编写异步代码的效果”。
             ①顺序编写保证了逻辑上的直观性，
             ②协程的自动线程切换又保证了代码的非阻塞性。
         */
        println("Start ${Thread.currentThread().name}")
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000L)
            println("Hello World ${Thread.currentThread().name}")
        }
        println("End ${Thread.currentThread().name}")
        //  验证结果： “Start main”->“End main”->“Hello World main”

        /**常用的Thread*/
        println("Start ${Thread.currentThread().name}")
        Thread {
            Thread.sleep(1000L)
            println("Hello World ${Thread.currentThread().name}")
        }.start()
        println("End ${Thread.currentThread().name}")
        // 结果： “Start main”->“End main”->“Hello World Thread-2”
    }


    /**
     * 某 KtExtension.kt 文件
     * 默认主线程的协程
     * 注意有两个异常捕获：
     * 1. 捕获整个协程的异常；
     * 2.捕获协程代码块执行的异常。为了保证程序的稳定，两个都必须要有。
     */
    fun launch(block: suspend (CoroutineScope) -> Unit,
               error: ((e: Exception) -> Unit)? = null,
               context: CoroutineContext = Dispatchers.Main): Job {
        return GlobalScope.launch(context + CoroutineExceptionHandler { _, e ->
            Log.e("==>coroutineException", e.message)    //1
        }) {
            try {
                block(this)
            } catch (e: Exception) {        //2
                Log.e("==>coroutineError", e.message)
                if (error != null) {
                    error(e)
                }
            }
        }
    }


   /* launch这个方法会返回一个Job，
   这个Job就相当于RxJava的Disposable，可以调用其方法进行中断协程：
   val job = launch({
        ... ...
    })
    //中断协程
    job.cancel()*/
}
