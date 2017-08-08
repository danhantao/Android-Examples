package com.pheth.android.a170809_toucheventconflict;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-08-09 06:35)
 * <p>
 * <p>
 * *************************************************************************
 */
public class MyViewPager extends ViewGroup {
    private float downX;
    private float xdiff;
    private float moveX;
    private float lastMoveX;
    private int leftBound;
    private int rightBound;
    int mTouchSlop;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewPager();
    }

    private void initViewPager() {
        Context context = getContext();
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        final float density = context.getResources().getDisplayMetrics().density;

        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        if (b) {
            int size = getChildCount();
            for (int count = 0; count < size; ++count) {
                final View childView = getChildAt(i);
                childView.layout(count * childView.getMeasuredWidth(), 0, (count + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());

            }
        }

        //左边界
        leftBound = getChildAt(0).getLeft();
        //右边界
        rightBound = getChildAt(getChildCount()-1).getRight();
    }

    private void requestParentDisallowInterceptTouchEvent(boolean disallowIntercept) {
        final ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = getChildCount();
        for (int i = 0; i < size; ++i) {
            final View childAt = getChildAt(i);
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
        }
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getRawX();
                lastMoveX = downX;
                requestParentDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                xdiff = Math.abs(ev.getRawX() - downX);
                lastMoveX = moveX;
                if (xdiff > mTouchSlop) {
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //处理滑动的逻辑
        /**
         * 如何有滑动的效果？
         * View.scrollTo(x,y);
         * 		让View相对于它初始的位置滚动一段距离。
         * View.scrollBy(x,y);
         * 		让View相对于它现在的位置滚动一段距离。
         * 注意：上面两种方法都是滑动View里面的内容，即里面的所有子控件。
         **/

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int scrollDx = (int) (lastMoveX - moveX);
                //左边界判断，如果不断拖拽到达了左边界则拖拽就失效了
                if (getScrollX()+scrollDx < leftBound) {
                    scrollTo(leftBound, 0);
                    return true;
                } else if (getScrollX()+scrollDx+getWidth() > rightBound) {
                    scrollTo(rightBound - getWidth(), 0);
                    return true;
                }
                scrollBy(scrollDx, 0);
                lastMoveX = moveX;
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
