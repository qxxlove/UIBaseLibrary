package com.bool.jetpackmvvm.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;

import com.bool.jetpackmvvm.R;

/**
 *
 *  1. LifeCycle不只对Activity/Fragment有用，
 *     在Service和Application中也能大显身手
 *
 *  2. LifecycleOwner(被观察者)和LifecycleObserver(观察者)
 *     理解： 你想要获取什么，什么就是被观察者，
 *            比如我想要获取生命周期，生命周期就是被观察者，我就是观察者。
 *
 *  3. 在MainActivity中，只需要引用MyLocationListener即可，
 *          不用再关心Activity生命周期变化对该组件所带来的影响。
 *     生命周期的管理完全交给MyLocationListener内部自行处理。
 *     在Activity中要做的只是通过getLifecycle().addObserver()方法，
 *     将观察者与被观察者绑定起来
 *
 *   4. ProcessLifecycleOwner
 *      具有生命周期的系统组件除Activity、Fragment、Service外，还有Application。
 *      很多时候，我们会遇到这样的需求：我们想知道应用程序当前处在前台还是后台，
 *      或者当应用程序从后台回到前台时，我们能够得到通知。
 *      LifeCycle提供了一个名为ProcessLifecycleOwner的类，以方便我们知道整个应用程序的生命周期情况
 *
 *   5. 自定义
 *      Support Library 26.1.0 及更高版本中的 Fragment 和 Activity 已实现 LifecycleOwner 接口。
 *      Support Library 26.1.0以下可以自定义LifecleOwner;
 *      Support Library 26.1.0 及更高版本自定义LifecycleOwner会报错，亲测。
 *
 *      ① 如果是自定义的LifecycleOwner，想要实现对某个生命周期进行监听的话，
 *         需要使用lifecycleRegistry.markState(Lifecycle.State.xxxx);加入到监听队伍中.
 *      ② 比如说，我想对Activity中的onStart()方法进行监听，那么就需要将onStart()方法加入到监听队伍中,
 *        lifecycleRegistry.markState(Lifecycle.State.STARTED);
 *      ③ 上面不是已经说明，LifecycleOwner必须需要实现一个getLifecycle()方法，
 *        改方法需要返回一个Lifecycle类型的值，也就是我们创建的lifecycleRegistry
 *
 *        暂时还没遇到自定义的使用场景。
 *
 *
 *
 *
 */


public class LifecycleActivity extends AppCompatActivity {

    private  MyLocationListener myLocationListener;

    private LifecycleRegistry lifecycleRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        if(lifecycleRegistry != null){
            lifecycleRegistry.markState(Lifecycle.State.CREATED);
        }


        myLocationListener = new MyLocationListener(this,
                new MyLocationListener.OnLocationChangeListener() {
            @Override
            public void onChanged(double latitude, double longitude) {
                //展示收到的位置信息
            }
        });

        //将观察者与被观察者绑定(被观察者添加观察者)
        getLifecycle().addObserver(myLocationListener);

        initClick();
    }

    private void initClick() {
        findViewById(R.id.tv_start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动服务
                Intent intent = new Intent(LifecycleActivity.this,
                        MyLifeService.class);
                startService(intent);

            }
        });
        findViewById(R.id.tv_end_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //停止服务
                Intent intent = new Intent(LifecycleActivity.this,
                        MyLifeService.class);
                stopService(intent);
            }
        });
    }


   /* @Override
    protected void onResume() {
        super.onResume();
        lifecycleRegistry.markState(Lifecycle.State.RESUMED);
    }
    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        lifecycleRegistry = new LifecycleRegistry(this);
        return lifecycleRegistry;
    }
*/

}