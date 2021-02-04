package com.bool.jetpackmvvm.lifecycle;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyLocationListener implements LifecycleObserver {

    private Context context;
    public MyLocationListener(Activity context, OnLocationChangeListener listener) {
      this.context = context;
        //初始化操作
        iniLocationManager();
    }

    private void iniLocationManager() {
    }


    //当Activity执行onResume()方法时，该方法会被自动调用
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private  void startOnCreate(){
        Toast.makeText(context,"ON_CREATE被调用了",Toast.LENGTH_SHORT).show();
    }

    //当Activity执行onResume()方法时，该方法会被自动调用
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private  void startGetLocation(){
        Log.e("true","onResume"+"被调用了");
        Toast.makeText(context,"onResume被调用了",Toast.LENGTH_SHORT).show();
    }

    //当Activity执行onPause()方法时，该方法会被自动调用
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private  void stopGetLocation(){
        Log.e("true","onPause"+"被调用了");
    }

    //当Activity执行onDestroy()方法时，该方法会被自动调用
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private  void delGetLocation(){
        Log.e("true","onDestroy"+"被调用了");
    }
    //当地理位置发送改变时，通过该接口通知调用者
    public  interface  OnLocationChangeListener{
        void  onChanged(double latitude,double longitude);
    }

}
