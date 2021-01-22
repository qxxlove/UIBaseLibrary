package com.bool.jetpackmvvm.viewmodel.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bool.jetpackmvvm.R;
import com.bool.jetpackmvvm.livedata.LivadataViewModel;
import com.bool.jetpackmvvm.viewmodel.model.TimerViewModel;


/**
 *   1. ViewModel有自己独立的生命周期，屏幕旋转所导致的Activity重建，
 *     并不会影响ViewModel的生命周期
 *
 *   2. ViewModel是一个抽象类，其中只有一个onCleared()方法。
 *     当ViewModel不再被需要，即 与之相关的Activity 都 被销毁时，该方法会被系统调用
 *     (当屏幕旋转而导致的Activity重建，并不会调用该方法)
 *
 *   3. 原理：
 *         ViewModelProvider会去HashMap中检查该ViewModel是否已经存在缓存中，
 *         若存在，则直接返回，否则，则实例化一个
 *
 *   4. 注意：
 *     不要向ViewModel中传入任何类型的Context或带有Context引用的对象，
 *     可能会导致页面无法销毁，从而引发内存泄露。
 *     需要注意的是，除了Activity,Fragment也默认实现了ViewModelStoreOwner接口，
 *     因此，我们也可以在Fragment中正常使用ViewModel
 *
 *   5. onSaveInstanceState()方法
 *          onSaveInstanceState()方法只能保存少量的、能支持序列化的数据，
 *          但是onSaveInstanceState()方法可以 持久化 页面的数据。
 *          持久化：一直存在吗？
 *      ViewModel
 *          ViewModel能支持页面中所有的数据，但是需要注意的是，ViewModel不支持数据的持久化，
 *          当页面被彻底销毁时，ViewModel及其持有的数据就不存在。
 *
 *
 *
 */


public class TimerActivity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        tvResult = findViewById(R.id.tv_timer_result);
        iniComponent();
        iniComponent2();
    }


    /**
     *  1. 从源码可以看出，Observer()方法接收的第一个参数是一个LifecleOwner对象，
     *     我们传入的是this，因为this的祖父类实现了这个接口，也正是LifecleOwner对象，
     *     LiveData才会具体生命周期感知能力。
     *
     *  2. 源码 :当调用 LifecycleBoundObserver wrapper = new LifecycleBoundObserver(owner, observer),
     *     本质是通过 ObserverWrapper 将 observer 包装起来，得以 LiveData 能对生命周期状态得以进行监听，
     *        是通过 onStateChanged 和 shouldBeActive 方法
     *
     *        ① shouldBeActive 这里调用LiftCycle的方法，
     *          表达如果当前生命周期的状态为onStart,onResume,onPause时，返回true，
     *             也就是说只有这三个状态可以接收数据更新。
     *        ② onStateChanged 是LifecycleEventObserver接口的方法，
     *          当生命周期发送变化的时候会回调它，如果当前生命周期状态是destory,就会直接移除观察者，
     *          否则就会调用activeStateChanged(shouldBeActive());方法激活观察者.
     *
     *   3. 源码 : 通过setValue 方法跟进去，就可以看到最后会回调onChanged 方法。
     *
     *   4. 源码 : postValue 方法通过 postToMainThread();
     *      创建一个Handler将子线程中的任务发送到主线程去执行,然后在调用setValue()方法
     *
     */
    private void iniComponent2() {
        //通过ViewModelProvider得到ViewModel
        final LivadataViewModel viewModel =
                new ViewModelProvider(this).get(LivadataViewModel.class);

        //得到ViewModel中的LiveData
        final MutableLiveData<Integer> liveData =
                (MutableLiveData<Integer>) viewModel.getCurrentSecond();

        //通过liveData.observer()观察ViewModel中数据的变化
        // LiveData是通过观察者模式实现的。
        // 当数据发送改变的时候,会回调Observer的onChanged(),
        liveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                //收到回调后更新UI界面
                TextView tv = findViewById(R.id.tv_timer_liveData);
                tv.setText("小鑫啊"+integer);
            }
        });

        //关闭定时器
        findViewById(R.id.tv_timer_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过LiveData.setValue()/LiveData.postValue()
                //完成对ViewModel中数据的更新
                liveData.setValue(0);
                //关闭定时器
                viewModel.stopTiming();
            }
        });

        //计时开始
        viewModel.startTiming();
    }


    /**
     * 当我们旋转屏幕导致Activity重建时，计时器并没有停止。
     * 这意味着,横/竖屏状态下的Activity所对应的ViewModel是同一个，并没有被销毁，
     * 所持有的数据也一直都存在。
     */
    private void iniComponent() {
        // this(TimerActivity)就是：ViewModelStoreOwner,因为其实现了ViewModelStoreOwner接口
        // 该接口提供了ViewModelStore : ViewModel存储，
        // 源码得知：ViewModel实际上是以HashMap<String,ViewModel>的形式被缓存起来了。
        // ViewModel与页面之间没有直接的关联，它们通过ViewModelProvider进行关联。
        // 当页面需要ViewModel时，会向ViewModelProvider索要，
        // 而ViewModelProvider会去HashMap中检查该ViewModel是否已经存在缓存中，
        // 若存在，则直接返回，否则，则实例化一个。因此，Activity由于屏幕旋转导致的销毁重建并不会影响ViewModel.
        TimerViewModel timerViewModel =
                new ViewModelProvider(this).get(TimerViewModel.class);
        timerViewModel.setOnTimeChangeListener(new TimerViewModel.OnTimeChangeListener() {
            @Override
            public void onTimeChanged(final int second) {
                //更新UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText("TIME:"+second);
                    }
                });
            }
        });
        timerViewModel.startTiming();
    }
}