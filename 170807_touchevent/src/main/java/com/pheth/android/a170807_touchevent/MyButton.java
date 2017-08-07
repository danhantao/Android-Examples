package com.pheth.android.a170807_touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-08-07 06:18)
 * <p>
 * <p>
 * *************************************************************************
 */
public class MyButton extends Button {
    public static final String TAG = MyButton.class.getSimpleName();

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouch event:" + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent event:" + event.getAction());
        return super.onTouchEvent(event);
    }
}
