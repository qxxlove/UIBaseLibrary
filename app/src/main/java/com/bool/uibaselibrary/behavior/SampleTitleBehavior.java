package com.bool.uibaselibrary.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;



public class SampleTitleBehavior extends CoordinatorLayout.Behavior<View> {

    // 列表顶部 和 title底部重合时，列表的滑动距离。
    private float deltaY;

    public SampleTitleBehavior() {
    }

    public SampleTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 使用该Behavior的View要监听哪个类型的View的状态变化。
     *  其中参数parant代表CoordinatorLayout，
     *  child代表使用该Behavior的View， (要做出改变的View(Title))
     *  dependency代表要监听的View。
     *  这里要监听RecyclerView
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof RecyclerView;
    }


    /**
     * 当被监听的View状态变化时会调用该方法，参数和上一个方法一致。
     * 所以我们重写该方法，当RecyclerView的位置变化时，进而改变title的位置
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (deltaY == 0) {
            deltaY = dependency.getY() - child.getHeight();
        }

        float dy = dependency.getY() - child.getHeight();
        dy = dy < 0 ? 0 : dy;
        float y = -(dy / deltaY) * child.getHeight();
        child.setTranslationY(y);

        float alpha = 1 - (dy / deltaY);
        child.setAlpha(alpha);

        return true;
    }

}
