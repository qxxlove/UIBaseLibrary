package com.bool.uibaselibrary.weight;

import android.view.View;

import androidx.core.view.ViewCompat;

import io.reactivex.annotations.NonNull;

/**
 * 了解接口所有的方法
 */


public interface INestedScrollingParent {


    /**
     * 对NestedScrollingChild发起嵌套滑动作出应答
     * @param child 布局中包含下面target的直接父View
     * @param target 发起嵌套滑动的NestedScrollingChild的View
     * @param axes 滑动方向
     * @return 返回NestedScrollingParent是否配合处理嵌套滑动
     */
    boolean onStartNestedScroll(@NonNull View child, @NonNull View target, @ViewCompat.ScrollAxis int axes);

    /**
     * NestedScrollingParent配合处理嵌套滑动回调此方法
     * @param child 同上
     * @param target 同上
     * @param axes 同上
     */
    void onNestedScrollAccepted(@NonNull View child, @NonNull View target, @ViewCompat.ScrollAxis int axes);

    /**
     * 嵌套滑动结束
     * @param target 同上
     */
    void onStopNestedScroll(@NonNull View target);

    /**
     * NestedScrollingChild滑动完成后将滑动值分发给NestedScrollingParent回调此方法
     * @param target 同上
     * @param dxConsumed 水平方向消费的距离
     * @param dyConsumed 垂直方向消费的距离
     * @param dxUnconsumed 水平方向剩余的距离
     * @param dyUnconsumed 垂直方向剩余的距离
     */
    void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed,
                        int dxUnconsumed, int dyUnconsumed);

    /**
     * NestedScrollingChild滑动完之前将滑动值分发给NestedScrollingParent回调此方法
     * @param target 同上
     * @param dx 水平方向的距离
     * @param dy 水平方向的距离
     * @param consumed 返回NestedScrollingParent是否消费部分或全部滑动值
     */
    void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed);

    /**
     * NestedScrollingChild在惯性滑动之前，将惯性滑动的速度和NestedScrollingChild自身是否需要消费此惯性滑动分
     * 发给NestedScrollingParent回调此方法
     * @param target 同上
     * @param velocityX 水平方向的速度
     * @param velocityY 垂直方向的速度
     * @param consumed NestedScrollingChild自身是否需要消费此惯性滑动
     * @return 返回NestedScrollingParent是否消费全部惯性滑动
     */
    boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed);

    /**
     * NestedScrollingChild在惯性滑动之前,将惯性滑动的速度分发给NestedScrollingParent
     * @param target 同上
     * @param velocityX 同上
     * @param velocityY 同上
     * @return 返回NestedScrollingParent是否消费全部惯性滑动
     */
    boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY);

    /**
     * @return 返回当前嵌套滑动的方向
     */
    int getNestedScrollAxes();


}
