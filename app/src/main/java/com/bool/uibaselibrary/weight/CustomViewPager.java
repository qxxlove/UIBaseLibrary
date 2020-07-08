package com.bool.uibaselibrary.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.bool.uibaselibrary.R;


/**
 * Android进阶之自定义ViewGroup—带你一步步轻松实现ViewPager
 * https://www.jianshu.com/p/af8e14ff5f0c
 *
 *
 * 存在待解决的问题：
 * 1、当快速切换时或一个界面多滑几次，页面会无法切换。
 */

public class CustomViewPager extends ViewPager {

    private Context mContext;
    private int[] images = {R.mipmap.one, R.mipmap.two, R.mipmap.three, R.mipmap.four};
    private GestureDetector mGestureDetector;

    private int scrollX;
    private int position;
    private Scroller mScroller;

    private int startX;
    private int startY;

    public CustomViewPager(@NonNull Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }


    private void init() {
        /**1. 为该自定的ViewGroup添加几个childView*/
        for (int i = 0; i < images.length; i++) {
            ImageView iv = new ImageView(getContext());
            iv.setBackgroundResource(images[i]);
            this.addView(iv);
        }

        //由于ViewGroup默认只测量下面一层的子View(所以我们直接在ViewGroup里面添加ImageView是可以直接显示出来的)，
        // 所以基本自定义ViewGroup都会要重写onMeasure方法，
        // 否则无法测量第一层View（这里是ScrollView）中的view，无法正常显示里面的内容。
        View testView = View.inflate(mContext, R.layout.test_viewpager_scrollview, null);
        addView(testView, 4);


        /** 3 我们已经处理好了子View的摆放位置，接下来就是处理如何让ViewGroup中的元素，跟着手的滑动而滑动了。
         * view可以通过onTouch事件来获取基本的触摸操作，但是对于较为复杂的手势，则需要手势识别器Gesturedetector来实现，在此，我们使用它来处理滑动事件。
         * */
        mGestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                    float distanceX, float distanceY) {
                //相对滑动：X方向滑动多少距离，view就跟着滑动多少距离
                scrollBy((int) distanceX, 0);
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });

        /**4. 还差两个小问题待解决：边界情况的处理和平滑的回弹到指定位置。*/

        /**6. 平滑的回弹到指定位置
         */
         mScroller = new Scroller(mContext);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            //如果是view:触发view的测量;如果是ViewGroup，触发测量ViewGroup中的子view
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
    }



    /**
     *  2. 重写onLayout（）方法，获取所有的子View，各自调用layout()方法，
     * 按下图排列方式(自己想要的效果：如 水平摆放)，确定它们各自的摆放位置。
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        Log.e("onLayout","子view大小:"+childCount);
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childView.layout(i * getWidth(), t, (i + 1) * getWidth(), b);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /**如果左右滑动, 就需要拦截, 上下滑动,不需要拦截*/
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                //这个时候还需要把将ACTION_DOWN传递给手势识别器，因为拦截了MOVE的事件后，
                // DOWN的事件还是要给手势识别器处理，否则会丢失事件，滑动的时候会存在bug。
                mGestureDetector.onTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getX();
                int endY = (int) ev.getY();
                int dx = endX - startX;
                int dy = endY - startY;
                if (Math.abs(dx) > Math.abs(dy)) {
                    /**左右滑动*/
                    return true;// 中断事件传递, 不允许孩子响应事件了, 由父控件处理
                }
                break;
            default:
                break;
        }
        return false;// 不拦截事件,优先传递给孩子(也就是ScrollView，让它正常处理上下滑动事件)处理

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将触摸事件传递手势识别器
        mGestureDetector.onTouchEvent(event);

        /**5. 边界情况的处理*/
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("ACTION_MOVE", "scrollX=" + getScrollX());
                scrollX = getScrollX();//相对于初始位置滑动的距离
                //你滑动的距离加上屏幕的一半，除以屏幕宽度，就是当前图片显示的pos.如果你滑动距离超过了屏幕的一半，这个pos就加1
                position = (getScrollX() + getWidth() / 2) / getWidth();

                /**因为加了一层ScrollView 子界面*/
                if (position >= images.length+1) {
                    position = images.length ;
                }
                //滑到最后一张的时候，不能出边界
              /*  if (position >= images.length) {
                    position = images.length-1 ;
                }*/

                if (position < 0) {
                    position = 0;
                }
                break;
            case MotionEvent.ACTION_UP:

                // 5. 绝对滑动，直接滑到指定的x,y的位置,较迟钝
               // scrollTo(position * getWidth(), 0);
                //由于它是让view直接滚动到参数x和y所标定的坐标 ,效果会迟钝

                // 6.滚动，startX, startY为开始滚动的位置，dx,dy为滚动的偏移量
                mScroller.startScroll(scrollX, 0, -(scrollX - position * getWidth()), 0);
                invalidate();//使用invalidate这个方法会有执行一个回调方法computeScroll，我们来重写这个方法

                if (mOnPageScrollListener != null) {
                    mOnPageScrollListener.onPageSelected(position);
                }

                 break;
        }

        return true;
    }


    /**
     * 其实Scroller的原理就是用ScrollTo()来一段一段的进行，最后看上去跟自然的一样，
     * 必须使用postInvalidate()，这样才会一直回调computeScroll()这个方法，直到滑动结束。
     */
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            Log.e("CurrX", "mScroller.getCurrX()=" + mScroller.getCurrX());
            postInvalidate();

            if (mOnPageScrollListener != null) {
                Log.e("TAG", "offset=" + (float) (getScrollX() * 1.0 / (getWidth())));
                mOnPageScrollListener.onPageScrolled((float) (mScroller.getCurrX() * 1.0 / ((1) * getWidth())), position);
            }
        }
    }


    /**
     * 加小圆点 步骤
     *
     * 1. 写一个接口，将滑动事件的偏移距离比和当前滑动到哪个页面的position提供出去
     */
    public interface OnPageScrollListener {
        /**
         * @param offsetPercent offsetPercent：getScrollX滑动的距离占屏幕宽度的百分比
         * @param position
         */
        void onPageScrolled(float offsetPercent, int position);

        void onPageSelected(int position);
    }

    private OnPageScrollListener mOnPageScrollListener;

    public void setOnPageScrollListener(OnPageScrollListener onPageScrollListener) {
        this.mOnPageScrollListener = onPageScrollListener;
    }

}
