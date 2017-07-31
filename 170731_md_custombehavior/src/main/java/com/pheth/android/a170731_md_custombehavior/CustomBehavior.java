package com.pheth.android.a170731_md_custombehavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-07-31 06:18)
 * <p>
 * <p>
 * *************************************************************************
 */
public class CustomBehavior extends CoordinatorLayout.Behavior<View> {

    public CustomBehavior(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }

    public CustomBehavior() {
    }

    /**
     * 用来决定需要监听哪些控件或者容器的状态（1.知道监听谁；2.什么状态改变）
     * CoordinatorLayout parent ，父容器
     * View child, 子控件---需要监听dependency这个view的视图们---观察者
     * View dependency，你要监听的那个View
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child,
                                   View dependency) {
        //还可以根据ID或者TAG来判断
        return dependency instanceof TextView || super.layoutDependsOn(parent, child, dependency);
    }

    /**
     * 当被监听的view发生改变的时候回调
     * 可以在此方法里面做一些响应的联动动画等效果。
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child,
                                          View dependency) {
        //获取被监听的view的状态---垂直方向位置
        int offset = dependency.getTop() - child.getTop();
        //让child进行平移
        ViewCompat.offsetTopAndBottom(child, offset);
        child.animate().rotation(child.getTop() * 20);
        return true;
    }


}