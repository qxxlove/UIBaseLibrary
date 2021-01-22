package com.bool.jetpackmvvm.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;


/**
 *   概念：
 *       LivaData是一个可被观察的数据容器类。具体来说，可以将LiveData理解为一个数据的容器，
 *         它将数据包装起来，使数据成为被观察者，当该数据发生变化时，观察者(LiveData)能够获得通知
 *         与常规的可观察类不同，LiveData可以感知(如Activity、Fragment或Service)的生命周期。
 *    优点：
 *       1. LiveData 遵循观察者模式。当生命周期状态发生变化时，
 *          LiveData 会通知 Observer(观察者) 对象，可以在这些Observer对象中更新界面
 *
 *          验证： 是只要有生命周期状态发生变化时，就会触发后面，还是必须有数据变化？
 *
 *       2. 不会发送内存泄露
 *       3. 如果观察者的 生周期 处于非活跃状态(如返回栈中的Activity),则它不会接收任何LivaData事件，
 *          但是，当非活跃状态变成活跃状态时会立刻接收最新的数据（后台的Activity返回前台时）
 *
 *       4. 当config导致Activity/Fragment重建时，不需要再手动的管理数据的存储与恢复。
 *
 *     使用：
 *        1. LiveData是一个抽象类，不能直接使用。我们通常使用的是它的直接子类MutableLiveData
 *
 *        2. LiveData.observeForever()方法   （从方法名可以看出来，是如果不手动停止就永远观察）
 *         如果你想无论页面处于何种生命周期，setValue/postValue之后立刻回到数据。
 *         那么可以使用observerForever()方法，使用起来与observer()没有太大差别.
 *         因为AlwaysActiveObserver没有实现GenericLifecycleObserver 接口，不能感应生命周期
 *
 *         但是需要注意的是，在用完之后，一定要记得在onDestroy()方法中调用removeObserver()方法来停止对LiveData的观察，
 *         否则LiveData会一直处于激活状态，Activity则永远不会被系统自动回收，会造成内存泄露
 *
 */

public class LivadataViewModel extends ViewModel {

    private MutableLiveData<Integer> currentSecond;
    private Timer timer;
    private  int current;

    @Override
    protected void onCleared() {
        super.onCleared();
        //释放资源
        timer.cancel();
    }

    public LiveData<Integer> getCurrentSecond(){
        if(currentSecond == null){
            currentSecond = new MutableLiveData<>();
        }
        return  currentSecond;
    }

    //开始定时器
    public  void startTiming(){
        if(timer == null){
            current = 0;
            timer = new Timer();
            /**postValue()方法用在非UI线程中, 而setValue()方法用在UI线程中*/
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if(currentSecond!=null){
                        currentSecond.postValue(current++);
                    }
                }
            };
            timer.schedule(timerTask,1000,1000);
        }
    }

    //关闭定时器
    public  void stopTiming(){
        timer.cancel();
    }

}
