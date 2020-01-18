package com.bool.kotlinstudy.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bool.kotlinstudy.R
import kotlinx.coroutines.*


/**
 * 协程
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
     * 执行结果为：Hello main -> World main -> End main，
     * 也就是说这个suspend函数仍然运行在主线程中，suspend并没有切换线程的作用。
     */
    fun initCoroutineTwo() {
        GlobalScope.launch(Dispatchers.Main) {
            println("Hello ${Thread.currentThread().name}")
            test()
            println("End ${Thread.currentThread().name}")
        }
    }

    /**挂起函数*/
    suspend fun test() {
        println("World ${Thread.currentThread().name}")
    }


    /**
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
     * 顺序编写异步代码有体现在什么地方呢？
     * 协程中的代码自动地切换到其他线程之后又自动地切换回了主线程！
     *
     * */
    fun initCoroutine() {
        /**   好处：“既按照顺序的方式编写代码，又可以让代码在不同的线程顺序执行”的“顺序编写异步代码的效果”。
        顺序编写保证了逻辑上的直观性，协程的自动线程切换又保证了代码的非阻塞性。
         */
        println("Start ${Thread.currentThread().name}")
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000L)
            println("Hello World ${Thread.currentThread().name}")
        }
        println("End ${Thread.currentThread().name}")
        //  结果： “Start main”->“End main”->“Hello World main”

        /**常用的Thread*/
        println("Start ${Thread.currentThread().name}")
        Thread {
            Thread.sleep(1000L)
            println("Hello World ${Thread.currentThread().name}")
        }.start()
        println("End ${Thread.currentThread().name}")
        // 结果： “Start main”->“End main”->“Hello World Thread-2”
    }
}
