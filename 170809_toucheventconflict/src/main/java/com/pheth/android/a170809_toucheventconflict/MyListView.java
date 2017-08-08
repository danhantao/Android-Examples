package com.pheth.android.a170809_toucheventconflict;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-08-09 06:13)
 * <p>
 * <p>
 * *************************************************************************
 */
public class MyListView extends ListView {
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // ListView全部展开
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int size = MeasureSpec.getSize(heightMeasureSpec);
        final int mode = MeasureSpec.getMode(heightMeasureSpec);
        int expandedHeight = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec,expandedHeight);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
