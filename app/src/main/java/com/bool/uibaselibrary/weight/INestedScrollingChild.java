package com.bool.uibaselibrary.weight;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

/**
 * 了解接口所有的方法
 */

public interface INestedScrollingChild {

    /**
     * @param enabled 开启或关闭嵌套滑动
     */
    void setNestedScrollingEnabled(boolean enabled);

    /**
     * @return 返回是否开启嵌套滑动
     */
    boolean isNestedScrollingEnabled();

    /**
     * 沿着指定的方向开始滑动嵌套滑动
     * @param axes 滑动方向
     * @return 返回是否找到NestedScrollingParent配合滑动
     */
    boolean startNestedScroll(@ViewCompat.ScrollAxis int axes);

    /**
     * 停止嵌套滑动
     */
    void stopNestedScroll();

    /**
     * @return 返回是否有配合滑动NestedScrollingParent
     */
    boolean hasNestedScrollingParent();

    /**
     * 滑动完成后，将已经消费、剩余的滑动值分发给NestedScrollingParent
     * @param dxConsumed 水平方向消费的距离
     * @param dyConsumed 垂直方向消费的距离
     * @param dxUnconsumed 水平方向剩余的距离
     * @param dyUnconsumed 垂直方向剩余的距离
     * @param offsetInWindow 含有View从此方法调用之前到调用完成后的屏幕坐标偏移量，
     * 可以使用这个偏移量来调整预期的输入坐标（即上面4个消费、剩余的距离）跟踪，此参数可空。
     * @return 返回该事件是否被成功分发
     */
    boolean dispatchNestedScroll(int dxConsumed, int dyConsumed,
                                 int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow);

    /**
     * 在滑动之前，将滑动值分发给NestedScrollingParent
     * @param dx 水平方向消费的距离
     * @param dy 垂直方向消费的距离
     * @param consumed 输出坐标数组，consumed[0]为NestedScrollingParent消耗的水平距离、
     * consumed[1]为NestedScrollingParent消耗的垂直距离，此参数可空。
     * @param offsetInWindow 同上dispatchNestedScroll
     * @return 返回NestedScrollingParent是否消费部分或全部滑动值
     */
    boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed,
                                    @Nullable int[] offsetInWindow);

    /**
     * 将惯性滑动的速度和NestedScrollingChild自身是否需要消费此惯性滑动分发给NestedScrollingParent
     * @param velocityX 水平方向的速度
     * @param velocityY 垂直方向的速度
     * @param consumed NestedScrollingChild自身是否需要消费此惯性滑动
     * @return 返回NestedScrollingParent是否消费全部惯性滑动
     */
    boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed);

    /**
     * 在惯性滑动之前，将惯性滑动值分发给NestedScrollingParent
     * @param velocityX 水平方向的速度
     * @param velocityY 垂直方向的速度
     * @return 返回NestedScrollingParent是否消费全部惯性滑动
     */
    boolean dispatchNestedPreFling(float velocityX, float velocityY);


   /*
     下面是NestedScrollingChildHelper 源码，只是为了看日志说明
     ① startNestedScroll 这个方法首先会判断是否已经找到了配合处理滑动的NestedScrollingParent、若找到了则返回true，
     否则会判断是否开启嵌套滑动，若开启了则通过构造函数注入的View来循环往上层寻找配合处理滑动的NestedScrollingParent，
     循环条件是通过ViewParentCompat这个兼容类判断p是否实现NestedScrollingParent，
     若是则将p转为NestedScrollingParent类型调用onStartNestedScroll()方法
     如果返回true则证明找配合处理滑动的NestedScrollingParent，
     所以接下来同样借助ViewParentCompat调用NestedScrollingParent的onNestedScrollAccepted()。

     public boolean startNestedScroll(int axes) {
        //判断是否找到配合处理滑动的NestedScrollingParent
        if (hasNestedScrollingParent()) {
            // Already in progress
            return true;
        }
        if (isNestedScrollingEnabled()) {//判断是否开启滑动嵌套
            ViewParent p = mView.getParent();
            View child = mView;
            //循环往上层寻找配合处理滑动的NestedScrollingParent
            while (p != null) {
                //ViewParentCompat.onStartNestedScroll()会判断p是否实现NestedScrollingParent，
                //若是则将p转为NestedScrollingParent类型调用onStartNestedScroll()方法
                if (ViewParentCompat.onStartNestedScroll(p, child, mView, axes)) {
                    mNestedScrollingParent = p;
                    //通过ViewParentCompat调用p的onNestedScrollAccepted()方法
                    ViewParentCompat.onNestedScrollAccepted(p, child, mView, axes);
                    return true;
                }
                if (p instanceof View) {
                    child = (View) p;
                }
                p = p.getParent();
            }
        }
        return false;
    }


    ② dispatchNestedPreScroll 方法源码
      这个方法首先会判断是否开启嵌套滑动并找到配合处理滑动的NestedScrollingParent，
      若符合这两个条件则会根据参数dx、dy滑动值判断是否有水平或垂直方向滑动，
      若有滑动调用mView.getLocationInWindow()将View当前的在Window上的x、y坐标值赋值进offsetInWindow数组并以startX、startY记录，接下来初始化输出数组consumed、并通过ViewParentCompat调用NestedScrollingParent的onNestedPreScroll()，
      再次调用mView.getLocationInWindow()将调用NestedScrollingParent的onNestedPreScroll()后的View在Window上的x、y坐标值赋值进offsetInWindow数组并与之前记录好的startX、startY相减计算得出偏移量，
      接着以consumed数组的两个元素的值有其中一个不为0作为boolean值返回，若条件为true说明NestedScrollingParent消耗的部分或者全部滑动值。


     public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        if (isNestedScrollingEnabled() && mNestedScrollingParent != null) {//如果开启嵌套滑动并找到配合处理滑动的NestedScrollingParent
            if (dx != 0 || dy != 0) {//如果有水平或垂直方向滑动
                int startX = 0;
                int startY = 0;
                if (offsetInWindow != null) {
                    //先记录View当前的在Window上的x、y坐标值
                    mView.getLocationInWindow(offsetInWindow);
                    startX = offsetInWindow[0];
                    startY = offsetInWindow[1];
                }
                //初始化输出数组consumed
                if (consumed == null) {
                    if (mTempNestedScrollConsumed == null) {
                        mTempNestedScrollConsumed = new int[2];
                    }
                    consumed = mTempNestedScrollConsumed;
                }
                consumed[0] = 0;
                consumed[1] = 0;
                //通过ViewParentCompat调用NestedScrollingParent的onNestedPreScroll()方法
                ViewParentCompat.onNestedPreScroll(mNestedScrollingParent, mView, dx, dy, consumed);

                if (offsetInWindow != null) {
                    //将之前记录好的x、y坐标减去调用NestedScrollingParent的onNestedPreScroll()后View的x、y坐标，计算得出偏移量并赋值进offsetInWindow数组
                    mView.getLocationInWindow(offsetInWindow);
                    offsetInWindow[0] -= startX;
                    offsetInWindow[1] -= startY;
                }
                //consumed数组的两个元素的值有其中一个不为0则说明NestedScrollingParent消耗的部分或者全部滑动值
                return consumed[0] != 0 || consumed[1] != 0;
            } else if (offsetInWindow != null) {
                offsetInWindow[0] = 0;
                offsetInWindow[1] = 0;
            }
        }
        return false;
    }

    ③  dispatchNestedScroll() 源码
       这个方法与上面的dispatchNestedPreScroll()方法十分类似，这里就不细说了。

       public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed,
            int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        if (isNestedScrollingEnabled() && mNestedScrollingParent != null) {//如果开启嵌套滑动并找到配合处理滑动的NestedScrollingParent
            if (dxConsumed != 0 || dyConsumed != 0 || dxUnconsumed != 0 || dyUnconsumed != 0) {//如果有消费滑动值或者有剩余滑动值
                int startX = 0;
                int startY = 0;
                if (offsetInWindow != null) {
                    //先记录View当前的在Window上的x、y坐标值
                    mView.getLocationInWindow(offsetInWindow);
                    startX = offsetInWindow[0];
                    startY = offsetInWindow[1];
                }
                //通过ViewParentCompat调用NestedScrollingParent的onNestedScroll()方法
                ViewParentCompat.onNestedScroll(mNestedScrollingParent, mView, dxConsumed,
                        dyConsumed, dxUnconsumed, dyUnconsumed);

                if (offsetInWindow != null) {
                    //将之前记录好的x、y坐标减去调用NestedScrollingParent的onNestedScroll()后View的x、y坐标，计算得出偏移量并赋值进offsetInWindow数组
                    mView.getLocationInWindow(offsetInWindow);
                    offsetInWindow[0] -= startX;
                    offsetInWindow[1] -= startY;
                }
                //返回true表明NestedScrollingChild的dispatchNestedScroll事件成功分发NestedScrollingParent
                return true;
            } else if (offsetInWindow != null) {
                // No motion, no dispatch. Keep offsetInWindow up to date.
                offsetInWindow[0] = 0;
                offsetInWindow[1] = 0;
            }
        }
        return false;
    }

   ④ dispatchNestedPreFling()、dispatchNestedFling() 方法
     这两方法都是通过ViewParentCompat调用NestedScrollingParent对应的fling方法来返回NestedScrollingParent是否消费全部惯性滑动。

     public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        if (isNestedScrollingEnabled() && mNestedScrollingParent != null) {
            //通过ViewParentCompat调用NestedScrollingParent的onNestedPreFling()方法，返回值表示NestedScrollingParent是否消费全部惯性滑动
            return ViewParentCompat.onNestedPreFling(mNestedScrollingParent, mView, velocityX,
                    velocityY);
        }
        return false;
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        if (isNestedScrollingEnabled() && mNestedScrollingParent != null) {
            //通过ViewParentCompat调用NestedScrollingParent的onNestedFling()方法，返回值表示NestedScrollingParent是否消费全部惯性滑动
            return ViewParentCompat.onNestedFling(mNestedScrollingParent, mView, velocityX,
                    velocityY, consumed);
        }
        return false;
    }


*/





}
