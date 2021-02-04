package com.bool.jetpackmvvm.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyServiceObserver implements LifecycleObserver {

    //当Service的onCreate()方法被调用时，该方法会被调用
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private  void startGetLocation(){
        Log.e("service","Service中的oncreate方法执行了");
    }

    //当Service的onDestroy()方法被调用时，该方法会被调用
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private  void stopGetLocation(){
        Log.e("service","Service中的onDestroy方法执行了");
    }

}
