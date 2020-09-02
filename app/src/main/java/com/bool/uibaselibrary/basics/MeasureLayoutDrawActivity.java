package com.bool.uibaselibrary.basics;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bool.uibaselibrary.R;
import com.bool.uibaselibrary.weight.MGestureDetector;

/**
 * Android GestureDetector
 * https://www.jianshu.com/p/2cb7ec3d3d5a
 */

public class MeasureLayoutDrawActivity extends AppCompatActivity {

    private  MGestureDetector mGestureDetector;
    private  TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_measure_layout_draw);

         // 步骤1：创建
         mGestureDetector = new MGestureDetector(this, new GestureDetector.OnGestureListener() {
            // 1. 用户轻触触摸屏
            public boolean onDown(MotionEvent e) {
                Log.e("MyGesture", "onDown");
                return false;
            }

            // 2. 用户轻触触摸屏，尚未松开或拖动
            // 与onDown()的区别：无松开 / 拖动
            // 即：当用户点击的时，onDown（）就会执行，在按下的瞬间没有松开 / 拖动时onShowPress就会执行
            public void onShowPress(MotionEvent e) {
                Log.e("MyGesture", "onShowPress");
            }

            // 3. 用户长按触摸屏
            public void onLongPress(MotionEvent e) {
                Log.e("MyGesture", "onLongPress");
            }

            // 4. 用户轻击屏幕后抬起
            public boolean onSingleTapUp(MotionEvent e) {
                Log.e("MyGesture", "onSingleTapUp");
                return true;
            }

            // 5. 用户按下触摸屏 & 拖动
            public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                    float distanceX, float distanceY) {
                Log.e("MyGesture", "onScroll:");
                return true;
            }

            // 6. 用户按下触摸屏、快速移动后松开
            // 参数：
            // e1：第1个ACTION_DOWN MotionEvent
            // e2：最后一个ACTION_MOVE MotionEvent
            // velocityX：X轴上的移动速度，像素/秒
            // velocityY：Y轴上的移动速度，像素/秒
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                Log.e("MyGesture", "onFling");
                return true;
            }
        });

         // 步骤2：创建 & 设置OnDoubleTapListener接口实现类
         mGestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {

            // 1. 单击事件
            // 关于OnDoubleTapListener.onSingleTapConfirmed（）和 OnGestureListener.onSingleTapUp()的区别
            // onSingleTapConfirmed：再次点击（即双击），则不会执行
            // onSingleTapUp：手抬起就会执行
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Log.i("MyGesture", "onSingleTapConfirmed");
                return false;
            }

            // 2. 双击事件
            public boolean onDoubleTap(MotionEvent e) {
                Log.i("MyGesture", "onDoubleTap");
                return false;
            }
            // 3. 双击间隔中发生的动作
            // 指触发onDoubleTap后，在双击之间发生的其它动作，包含down、up和move事件；
            public boolean onDoubleTapEvent(MotionEvent e) {
                Log.i("MyGesture", "onDoubleTapEvent");
                return false;
            }
        });



        // 步骤3：让TextView检测手势：重写View的onTouch函数，将触屏事件交给GestureDetector处理，
        // 从而对用户手势作出响应
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                return false;
            }
        });
    }





    /**
     * 步骤2-2：让某个Activity检测手势：重写Activity的dispatchTouchEvent函数，
     * 将触屏事件交给GestureDetector处理，从而对用户手势作出响应
     * @param ev
     * @return
     */
   /* @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mGestureDetector.onTouchEvent(ev); // 让GestureDetector响应触碰事件
        super.dispatchTouchEvent(ev); // 让Activity响应触碰事件
        return false;
    }*/
}