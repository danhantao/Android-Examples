package com.pheth.android.a0730_md;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class FabBehavior extends FloatingActionButton.Behavior {
    private boolean visible = true;//是否可见

    public FabBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButton child, View directTargetChild, View target,
                                       int nestedScrollAxes) {
        // 当观察的View（RecyclerView）发生滑动的开始的时候回调的
        //nestedScrollAxes:滑动关联轴， 我们现在只关心垂直的滑动。
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild,
                target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout,
                               FloatingActionButton child, View target, int dxConsumed,
                               int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed);
        // 当观察的view滑动的时候回调的
        //根据情况执行动画
        if (dyConsumed > 0 && visible) {
            //show
            visible = false;
            onHide(child);
        } else if (dyConsumed < 0) {
            //hide
            visible = true;
            onShow(child);
        }

    }

    // 添加动画
    public void onHide(FloatingActionButton fab) {
        // 隐藏动画--属性动画
//		toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(3));
        MarginLayoutParams layoutParams = (MarginLayoutParams) fab.getLayoutParams();

        fab.animate().translationY(fab.getHeight() + layoutParams.bottomMargin).setInterpolator(new AccelerateInterpolator(3));
//		ViewCompat.animate(fab).scaleX(0f).scaleY(0f).start();
    }

    // 添加动画
    public void onShow(FloatingActionButton fab) {
        // 显示动画--属性动画
//		toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));

        MarginLayoutParams layoutParams = (MarginLayoutParams) fab.getLayoutParams();
        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
//		ViewCompat.animate(fab).scaleX(1f).scaleY(1f).start();
    }

}
