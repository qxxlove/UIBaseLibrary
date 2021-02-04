package com.bool.jetpackmvvm;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class ApplicationObserver implements LifecycleObserver {

    //在应用程序的整个生命周期中只会被调用一次
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public  void onCreate(){
        Log.e("application","Application onCreate方法执行了");
    }


    //在应用程序在前台出现时被调用
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public  void onStart(){
        Log.e("application","Application onStart方法执行了");
    }

    //在应用程序在前台出现时被调用
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public  void onResume(){
        Log.e("application","Application onResume方法执行了");
    }

    //在应用程序退出到后台时被调用
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public  void onPause(){
        Log.e("application","Application onPause方法执行了");
    }

    //在应用程序退出到后台时被调用
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public  void onStop(){
        Log.e("application","Application onStop方法执行了");
    }


    //永远不会被调用，系统不会分发调用ON_DESTROY事件
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public  void onDestroy(){
        Log.e("application","Application onDestroy方法执行了");
    }


}
