package com.pheth.android.a170823_maskfilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-08-24 06:05)
 * <p>
 * <p>
 * *************************************************************************
 */
public class MaskFilterView extends View{
    public MaskFilterView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        RectF rectF = new RectF(100,100,300,300);
        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER));
//        canvas.drawRect(rectF,paint);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chrysanthemum2);
        canvas.drawBitmap(bitmap,100,300,paint);

    }
}
