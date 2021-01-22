package com.bool.jetpackmvvm.viewmodel.model;

import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;


/**
 * ViewModel
 *  验证： ViewModel最重要的作用是将视图与数据分离，并独立于Activity的重建
 *
 *  1. Activity 中的两个或更多 Fragment 需要相互通信是一种很常见的情况
 *     新思路：
 *     ViewModel可以很好的解决Fragment之间通信的问题。
 *     这两个Fragment可以使用其Activity范围共享ViewModel来处理此类通信.
 *
 */

public class TimerViewModel extends ViewModel {

    private Timer timer;
    private  int currentSecond;


    @Override
    protected void onCleared() {
        super.onCleared();
        //清理资源
        timer.cancel();
    }


    //开始计时
    public  void startTiming(){
        if(timer == null){
            currentSecond = 0;
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    currentSecond++;
                    if(onTimeChangeListener != null){
                        onTimeChangeListener.onTimeChanged(currentSecond);
                    }
                }
            };
            timer.schedule(timerTask,1000,1000);
        }
    }

    private  OnTimeChangeListener onTimeChangeListener;

    public void setOnTimeChangeListener(OnTimeChangeListener onTimeChangeListener) {
        this.onTimeChangeListener = onTimeChangeListener;
    }

    /**
     * 我们可以在onCleared()对定时器资源的释放，防止造成内存泄露。
     * 通过接口的方式，完成对调用者的通知，实际上这种方式不是很好，
     * 更好的方式是通过LiveData组件来实现。
     */
    public  interface  OnTimeChangeListener{
        void onTimeChanged(int second);
    }

}
