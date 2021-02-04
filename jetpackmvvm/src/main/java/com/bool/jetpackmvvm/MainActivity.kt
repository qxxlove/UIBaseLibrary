package com.bool.jetpackmvvm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bool.jetpackmvvm.lifecycle.LifecycleActivity
import com.bool.jetpackmvvm.navigation.NavHostActivity
import com.bool.jetpackmvvm.room.RoomActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 *  带你领略Android Jetpack组件的魅力
 *  https://blog.csdn.net/Alexwll/article/details/83302173
 *
 *    1. Lifecycler
 *       Lifecycler的实现主要使用两个主要枚举来跟踪其关联组件的生命周期状态
            Event：从框架和Lifecycle类派发的生命周期事件。 这些事件映射到活动和片段中的回调事件。
            State：由Lifecycle对象跟踪的组件的当前状态。
 *    2. LiveData是一个可观察的数据持有者类。
 *    与常规的可观察对象不同，LiveData是生命周期感知的，
 *    这意味着它尊重其他应用程序组件(如活动、片段或服务)的生命周期。
 *    这种意识确保LiveData只更新处于活动生命周期状态的应用程序组件观察者


 *
 */


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_one.setOnClickListener {
            val intent = Intent()
            //获取intent对象
            intent.setClass(this, NavHostActivity::class.java)
            // 获取class是使用::反射
            startActivity(intent) }
        tv_two.setOnClickListener {
            val intent = Intent()
            //获取intent对象
            intent.setClass(this, LifecycleActivity::class.java)
            // 获取class是使用::反射
            startActivity(intent) }
        tv_room.setOnClickListener {
            val intent = Intent()
            //获取intent对象
            intent.setClass(this, RoomActivity::class.java)
            // 获取class是使用::反射
            startActivity(intent) }
    }
}
