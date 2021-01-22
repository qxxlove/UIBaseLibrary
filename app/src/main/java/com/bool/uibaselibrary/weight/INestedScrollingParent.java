package com.bool.uibaselibrary.weight;

import android.view.View;

import androidx.core.view.ViewCompat;

import io.reactivex.annotations.NonNull;

/**
 * 了解接口所有的方法
 *  处理嵌套的滚动时应该使用  `ViewCompat`，`ViewGroupCompat`或`ViewParentCompat` 中的方法来处理，这是一些兼容库，
 *  他们保证 Android 5.0之前的兼容性垫片的静态方法，这样可以兼容 Android 5.0 之前的版本。
 *
 *
 *
 */


public interface INestedScrollingParent {


    /**
     *
     * 调用时机： 当子视图调用 startNestedScroll(View, int) 后调用该方法。返回 true 表示响应子视图的滚动。
     * 实现这个方法来声明支持嵌套滚动，如果返回 true，那么这个视图将要配合子视图嵌套滚动。
     * 当嵌套滚动结束时会调用到 onStopNestedScroll(View)。
     *
     *
     * 对NestedScrollingChild发起嵌套滑动作出应答
     * @param child 布局中包含下面target的直接父View (可滚动的子视图)
     * @param target 发起嵌套滑动的NestedScrollingChild的View （一般就是child）
     * @param axes 滑动方向(包含 ViewCompat#SCROLL_AXIS_HORIZONTAL, ViewCompat#SCROLL_AXIS_VERTICAL 或者两个值都有。)
     * @return  返回NestedScrollingParent是否配合处理嵌套滑动
     */
    boolean onStartNestedScroll(@NonNull View child, @NonNull View target, @ViewCompat.ScrollAxis int axes);



    /**
     * 调用时机： 如果 onStartNestedScroll 返回 true ，然后走该方法，这个方法里可以做一些初始化
     *
     * NestedScrollingParent配合处理嵌套滑动回调此方法
     * @param child 同上
     * @param target 同上
     * @param axes 同上
     */
    void onNestedScrollAccepted(@NonNull View child, @NonNull View target, @ViewCompat.ScrollAxis int axes);




    /**
     *
     * 调用时机： 这个方法表示子视图正在滚动，并且把滚动距离回调用到该方法，前提是 onStartNestedScroll 返回了 true。
     *
     * NestedScrollingChild滑动完成后将滑动值分发给NestedScrollingParent回调此方法
     * @param target 同上
     * @param dxConsumed 水平方向消费的距离 (手指产生的触摸距离中，子视图消耗的x方向的距离)
     * @param dyConsumed 垂直方向消费的距离 (手指产生的触摸距离中，子视图消耗的y方向的距离 ，
     *                   如果 onNestedPreScroll 中 dy = 20， consumed[0] = 8，那么 dy = 12)
     * @param dxUnconsumed 水平方向剩余的距离  （手指产生的触摸距离中，未被子视图消耗的x方向的距离）
     * @param dyUnconsumed 垂直方向剩余的距离   (手指产生的触摸距离中，未被子视图消耗的y方向的距离)
     */
    void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed,
                        int dxUnconsumed, int dyUnconsumed);



    /**
     *
     * 调用时机：子视图开始滚动前会调用这个方法。这时候父布局（也就是当前的 NestedScrollingParent 的实现类）
     *         可以通过这个方法来配合子视图同时处理滚动事件。
     *
     * NestedScrollingChild滑动完之前将滑动值分发给NestedScrollingParent回调此方法
     * @param target 同上
     * @param dx 水平方向的距离 (绝对值为手指在x方向滚动的距离，dx<0 表示手指在屏幕向右滚动)
     * @param dy 垂直方向的距离 (绝对值为手指在y方向滚动的距离，dy<0 表示手指在屏幕向下滚动)
     * @param consumed 返回NestedScrollingParent是否消费部分或全部滑动值
     *                 一个数组，值用来表示父布局消耗了多少距离，未消耗前为[0,0], 如果父布局想处理滚动事件，
     *                  就可以在这个方法的实现中为consumed[0]，consumed[1]赋值。分别表示x和y方向消耗的距离。
     *                  如父布局想在竖直方向（y）完全拦截子视图，那么让 consumed[1] = dy，就把手指产生的触摸事件给拦截了，子视图便响应不到触摸事件了 。
     */
    void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed);


    /**
     * 嵌套滑动结束
     *
     * 调用时机：当一个嵌套滚动结束后（如MotionEvent#ACTION_UP， MotionEvent#ACTION_CANCEL）会调用该方法，
     * 在这里可有做一些收尾工作，比如变量重置
     *
     * @param target 同上
     */
    void onStopNestedScroll(@NonNull View target);


    /**
     *
     * 调用时机： 子视图fling 时回调，父布局可以选择监听子视图的 fling。
     *  true 表示父布局处理 fling，false表示父布局监听子视图的fling
     *
     * NestedScrollingChild在惯性滑动之前，将惯性滑动的速度和NestedScrollingChild
     * 自身是否需要消费此惯性滑动分
     *
     * 发给NestedScrollingParent回调此方法
     * @param target 同上
     * @param velocityX 水平方向的速度（px/s）
     * @param velocityY 垂直方向的速度
     * @param consumed NestedScrollingChild自身是否需要消费此惯性滑动
     * @return 返回NestedScrollingParent是否消费全部惯性滑动
     */
    boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed);


    /**
     * 调用时机： 手指在屏幕快速滑触发Fling前回调，如果前面 onNestedPreScroll 中父布局消耗了事件，
     *              那么这个也会被触发。返回true表示父布局完全处理 fling 事件
     *
     * NestedScrollingChild在惯性滑动之前,将惯性滑动的速度分发给NestedScrollingParent
     * @param target 同上
     * @param velocityX 同上
     * @param velocityY 同上
     * @return 返回NestedScrollingParent是否消费全部惯性滑动
     */
    boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY);



    /**
     * 返回当前 NestedScrollingParent 的滚动方向，
     * @return
     * @see ViewCompat#SCROLL_AXIS_HORIZONTAL
     * @see ViewCompat#SCROLL_AXIS_VERTICAL
     * @see ViewCompat#SCROLL_AXIS_NONE
     */
    int getNestedScrollAxes();


}
