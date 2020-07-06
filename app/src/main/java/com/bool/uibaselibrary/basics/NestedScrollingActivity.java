package com.bool.uibaselibrary.basics;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;

import android.os.Bundle;

import com.bool.uibaselibrary.R;

/**
 *   浅析NestedScrolling嵌套滑动机制之基础篇
 *   https://juejin.im/post/5ede31496fb9a047a226a44a
 *
 *   NestedScrolling是Android5.0推出的嵌套滑动机制，能够让父View和子View在滑动时相互协调配合可以实现连贯的嵌套滑动，
 *   它基于原有的触摸事件分发机制上为ViewGroup和View增加处理滑动的方法提供调用，
 *   后来为了向前兼容到Android1.6，在Revision 22.1.0的android.support.v4兼容包中
 *   提供了从View、ViewGroup抽取出NestedScrollingChild、NestedScrollingParent两个接口和NestedScrollingChildHelper、NestedScrollingParentHelper两个辅助类来帮助控件实现嵌套滑动，
 *   CoordinatorLayout便是基于这个机制实现各种神奇的滑动效果。
 *
 *   Android 5.0及以上的View、ViewGroup自身分别就有NestedScrollingChild和NestedScrollingParent的方法，
 *   而方法逻辑就是对应的NestedScrollingChildHelper和NestedScrollingParentHelper的具体方法实现，
 *   所以View、ViewGroup的NestedScrolling机制相关内容，请自行查看源码。
 *
 *   NestedScrollingChildHelper对NestedScrollingChild的接口方法做了代理，您可以结合实际情况借助它来实现。
 *   NestedScrollingParentHelper只提供对应NestedScrollingParent相关的onNestedScrollAccepted()和onStopNestedScroll()方法，
 *   主要维护mNestedScrollAxes管理滑动的方向字段。
 *
 *
 *
 *
 *
 *   例子：
 *    1.  比如ViewPaper和Fragment搭配，而Fragment里往往是一个竖直滑动的ListView这种情况是就会产生滑动冲突,
 *        但是由于ViewPaper本身已经处理好了滑动冲突，所以我们无需考虑，不过若是换成ScrollView，我们就得自己处理滑动冲突了。
 *        思考：  ViewPager 是如何处理横向和纵向的冲突的？
 *        网上结论：
 *        第一种的冲突主要是一个横向的,一个竖向的，所以在开发中我们只要判断滑动方向是竖向还是横向的，
 *        再让对应的View滑动即可。判断的方法有很多，比如竖直距离与横向距离的大小比较,哪个距离大就判定为向哪个方向滑动的；滑动路径与水平形成的夹角等等。
 *    2.  ListView下拉刷新功能，需要ListView自身滑动实现滑动，但是当滑动到头部时需要ListView和Header一起滑动，也就是整个父容器的滑动,
 *        这就涉及到滑动冲突问题了,如果不处理好滑动冲突，就会出现各种意想不到情况。
 *
 *    3. 属于传统方法：内部拦截法(requestDisallowInterceptTouchEvent)和外部拦截法。
 *
 *
 * */

public class NestedScrollingActivity extends AppCompatActivity  implements NestedScrollingChild{

    /**需要实例化，否则报空*/
    private NestedScrollingChildHelper nestedScrollingChildHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scrolling);

    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {

    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return false;
    }

    @Override
    public boolean startNestedScroll(int axes) {
        /**此处只是为了分析源码：startNestedScroll 做了什么*/
        return nestedScrollingChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {

    }

    @Override
    public boolean hasNestedScrollingParent() {
        return false;
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
        return false;
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
        return false;
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return false;
    }





}