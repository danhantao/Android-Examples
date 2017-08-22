package com.pheth.android.a170816_gradientview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-08-17 08:00)
 * <p>
 * <p>
 * *************************************************************************
 */
public class LinearGradientTextView extends TextView {
    private Matrix matrix;
    private TextPaint paint;
    private LinearGradient linearGradint;

    private float translateX;
    private float deltaX = 20; // 每次移动距离

    public LinearGradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        paint = getPaint();
        // GradientSize=两个文字大小
        String text = getText().toString();
        final float textWidth = paint.measureText(text);
        int GradientSize = (int)(3*textWidth/text.length());
        linearGradint = new LinearGradient(-GradientSize, 0, 0, 0, new int[]{0x22ffffff,0xffffffff,0x22ffffff}, new float[]{0,0.5f,1}, Shader.TileMode.CLAMP);
        paint.setShader(linearGradint);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float textWidth = getPaint().measureText(getText().toString());
        translateX += deltaX;
        if (translateX > textWidth + 1 || translateX < 1){
            deltaX = - deltaX;
        }
        // 矩阵变换平移
        matrix.setTranslate(translateX,0);
        linearGradint.setLocalMatrix(matrix);
        postInvalidateDelayed(50);

    }
}
