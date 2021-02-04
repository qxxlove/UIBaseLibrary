package com.bool.jetpackmvvm.lifecycle;

import androidx.lifecycle.LifecycleService;

public class MyLifeService extends LifecycleService {

    private  MyServiceObserver myServiceObserver;

    public MyLifeService() {
        myServiceObserver = new MyServiceObserver();
        //将观察者与被观察者绑定
        getLifecycle().addObserver(myServiceObserver);
    }


}
