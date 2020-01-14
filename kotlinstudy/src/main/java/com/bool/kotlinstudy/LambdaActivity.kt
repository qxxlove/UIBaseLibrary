package com.bool.kotlinstudy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast

/**
 * Lambda 表达式
 */
class LambdaActivity : AppCompatActivity() {

    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda)
        initLambda()
    }

     fun   initLambda (){
         // 这里举例一个Android中最常见的按钮点击事件的例子
         btn.setOnClickListener(object : View.OnClickListener{
             override fun onClick(v: View?) {
                 //Toast.makeText(LambdaActivity.this,"onClick",Toast.LENGTH_SHORT).show()
             }
         })

         btn.setOnClickListener { Toast.makeText(this,"onClick",Toast.LENGTH_SHORT).show() }
    }

}
