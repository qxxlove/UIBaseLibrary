package com.bool.kotlinstudy.grammar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bool.kotlinstudy.R


/**
 *  Lambda 规定接口中只能有一个需要被实现的方法，不是规定接口中只能有一个方法
 *  lambda语法形式为 () -> {}，
 *        其中 () 用来描述参数列表，{} 用来描述方法体，-> 为 lambda运算符 ，读作(goes to)。
 *
 *  Lambda 是一个匿名函数，可以把 Lambda表达式 理解为是一段可以传递的代码 (将代码像数据一样进行传递)。
 *
 *  Lambda表达式 详解
 *  https://blog.csdn.net/chenshun123/article/details/78122467?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase
 */

class LambdaBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda_base2)

        initOne()
    }


    /**
     * 常用示例
     */
    private fun initOne() {

    }




    /**多参数无返回
     *   注解：FunctionalInterface :
     *     修饰函数式接口的，要求接口中的抽象方法只有一个。
     *     这个注解往往会和 lambda 表达式一起出现。
     *
     *     函数式接口 : 接口中只有一个抽象方法的接口，称为函数式接口，
     *     可以通过 Lambda 表达式来创建该接口的对象 (若 Lambda表达式抛出一个受检异常，那么该异常需要在目标接口的抽象方法上进行声明) 可以使用注解 @FunctionalInterface 修饰可以检查是否是函数式接口，
           同时 javadoc 也会包含一条声明，说明这个接口是一个函数式接口

           作用是什么？
     * */
    @FunctionalInterface
    interface NoReturnMultiParam {
        fun method(a: Int, b: Int)

    }

    /**无参无返回值 */
    @FunctionalInterface
    interface NoReturnNoParam {
        fun method()
    }

    /**一个参数无返回 */
    @FunctionalInterface
    interface NoReturnOneParam {
        fun method(a: Int)
    }

    /**多个参数有返回值 */
    @FunctionalInterface
    interface ReturnMultiParam {
        fun method(a: Int, b: Int): Int
    }

    /*** 无参有返回 */
    @FunctionalInterface
    interface ReturnNoParam {
        fun method(): Int
    }

    /**一个参数有返回值 */
    @FunctionalInterface
    interface ReturnOneParam {
        fun method(a: Int): Int
    }
}