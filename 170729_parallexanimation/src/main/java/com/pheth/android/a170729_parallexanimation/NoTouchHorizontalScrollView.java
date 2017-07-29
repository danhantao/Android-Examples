package com.pheth.android.a170729_parallexanimation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-07-29 20:57)
 * <p>
 * <p>
 * *************************************************************************
 */
public class NoTouchHorizontalScrollView extends HorizontalScrollView {
    public NoTouchHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 中断事件传递
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
