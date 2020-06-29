package com.bool.kotlinstudy.grammar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bool.kotlinstudy.R


/**
 * Object 的用法
 *  ①对象声明 ，一般用来实现单例
 *  ②伴生对象 ，类似 Java 的 static 关键字，也可以用于工厂方法模式
 *  ③对象表达式 ，一般用来代替 Java 的匿名内部类
 *
 *  object 的语义是这样的： 定义一个类并创建一个实例 。
 *  补充：
 *     首先 类加载阶段 可以分为加载、验证、准备、解析、初始化、使用、卸载 七个步骤 。
 *     static 代码块就是在 初始化 阶段执行的。
 *     那么，哪些场景会触发类的初始化呢？有如下几种场景：
        1.通过 new 实例化对象
        2.读写一个类的静态字段
        3.调用一个类的静态方法
        4.对类进行反射调用

    常见的符号：
        ？   表示可空类型
             var a: String = "abc"
             a = null // 编译错误
             var a: String ？= "abc"
             a = null // 编译通过
        ？.  安全调用操作符 （如果你觉得他可能为空，你还要对他进行操作，就用？.）
          eg:  val l = b.length // 错误：变量“b”可能为空
               val l = b?.length//ok，如果 b 非空，就返回 b.length，否则返回 null，{: .keyword }，这个表达式的类型是 Int?。
        ？:  Elvis 操作符
           eg: 在开发中我们经常的操作是，当b不为空时，我们返回另一个值y，当b为空时，我们返回一个值x，而不是null
               这样就用到Elvis操作符
               val l = b?.length?:-1//当b不为空时，返回b.length，当b为空时，返回-1
        !!操作符 (强制判空，因为我不允许他为空)
           eg: 如果我传的b为空，我希望系统直接给我抛出个NPE异常，就会用到!!操作符
           val l = b!!.length//如果b不为空，则返回b.length，如果b为空，则抛出异常NullPointerException
        $符号：拼接参数用的
        ==和===：
           eg：  ==判断值是否相等，===判断值及引用是否完全相等。
                val num: Int = 128;
                val a:Int? = num
                val b:Int? = num
                println(a == b)  //true
                print(a === b)   //true
        ..区间  （一般用于循环）
        _ 表示不使用
        :: 得到类的Class对象
            eg： startActivity(Intent(this@KotlinActivity, MainActivity::class.java))


 *
 *
 */

class ObjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object)
    }


    /**
     * 单例对象（具体可以看对行java 源码）
     *   public final class Singleton {
     *    ①  通过静态字段对外提供实例
          public static final Singleton INSTANCE;
         public final void xxx() {}
         ② 私有 的 构造函数
         private Singleton() {}
         ③ 静态代码块中直接初始化，线程安全 。
         static {
           Singleton var0 = new Singleton();
           INSTANCE = var0;}
       }

      问题： static 代码块在何时执行？
             按照上面反编译出来的 Java 代码，获得单例对象的方法是 Singleton.INSTANCE ，
             即调用 Singleon 类的静态字段 INSTANCE，就会触发类的初始化阶段，也就触发了 static 代码块的执行，
            从而完成了单例对象的实例化。
            同时，由于类加载过程天生线程安全，
           所以 Kotlin 的 object 单例活脱脱的就是一个线程安全的懒汉式单例(访问时初始化)。


     */
    object Singleton{
        fun xxx(){}
    }

    /**
     * 需要携带参数的单例类
     */
    class SingletonTwo private constructor(private val param: Int) {
        companion object {
            @Volatile
            private var instance: SingletonTwo? = null
            fun getInstance(property: Int) =
                    instance ?: synchronized(this) {
                        instance ?: SingletonTwo(property).also { instance = it }
                    }
        }
    }


}
