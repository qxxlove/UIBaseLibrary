package com.bool.jetpackmvvm.mvp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bool.jetpackmvvm.R

/**
 * 最普通的MVP
 *   同时为了考虑Presenter中的持有Activity的引用，导致Activity 不能及时销毁，导致内存泄漏
 *   因此我们在P 层实现了Activity 的相关生命周期。
 *
 *   紧接着 Lifecycle 生命周期框架出来了
 *
 *
 */

class FirstActivity : AppCompatActivity() {

    /*companion object {
        val TAG = FirstActivity.javaClass.simpleName
    }*/

    private var mFirstPresenter: FirstPresenter? = null
    private var mPresenter: FirstPresenterLife? =  FirstPresenterLife(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**1,最普通的处理方法*/
        mFirstPresenter = FirstPresenter(this)
        mFirstPresenter?.onCreate()
        /**2,Lifecycle 的引入*/
        //mPresenter = FirstPresenterLife(this)
        mPresenter?.let { getLifecycle().addObserver(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("", "onDestroy()")
        mFirstPresenter?.onDestroy()
    }
}