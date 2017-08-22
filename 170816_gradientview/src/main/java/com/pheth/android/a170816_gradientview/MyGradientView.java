package com.pheth.android.a170816_gradientview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-08-16 07:50)
 * <p>
 * <p>
 * *************************************************************************
 */
public class MyGradientView extends View {
    private Bitmap bitmap;
    private Paint paint;
    private BitmapShader bitmapShader;
    private int width;
    private int height;
    private int[] colors = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};



    public MyGradientView(Context context) {
        super(context);
        bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.avatar3)).getBitmap();
        paint = new Paint();
        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        /**
         * TileMode.CLAMP 拉伸最后一个像素去铺满剩下的地方
         * TileMode.MIRROR 通过镜像翻转铺满剩下的地方。
         * TileMode.REPEAT 重复图片平铺整个画面（电脑设置壁纸）
         */
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
        paint.setAntiAlias(true);
//        canvas.drawRect(0,0,800,1000,paint);

//       canvas.drawCircle(width/2,width/2,width/2,paint);
//       canvas.drawCircle(height/2,height/2,height/2,paint);

        // 设置像素矩阵，调整宽高不一致问题
//        float scale = Math.max(width,height)*1.0f/Math.min(width,height);
//        Matrix matrix = new Matrix();
//        matrix.setScale(scale,scale);
//        bitmapShader.setLocalMatrix(matrix);

//        canvas.drawCircle(scale * Math.max(width,height)/2f,scale * Math.max(width,height)/2f,Math.max(width,height)/2f,paint);

//        canvas.drawOval(new RectF(0,0,width,height),paint);
//        canvas.drawOval(new RectF(0,0,width,width),paint);

        /**
         * 线性渐变
         */
        LinearGradient linearGradient = new LinearGradient(0, 0, 400, 400, colors, null, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        canvas.drawRect(0, 0, 400, 400, paint);

        //通过shapeDrawable也可以实现
//		ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
//		shapeDrawable.getPaint().setShader(bitmapShader);
//		shapeDrawable.setBounds(0, 0, width, width);
//		shapeDrawable.draw(canvas);

//        final RadialGradient radialGradient = new RadialGradient(300, 300, 100, colors, null, Shader.TileMode.REPEAT);
//        paint.setShader(radialGradient);
//        canvas.drawCircle(300,300,300,paint);

//        final SweepGradient sweepGradient = new SweepGradient(300, 300, colors, null);
//        paint.setShader(sweepGradient);
//        canvas.drawCircle(300,300,300,paint);

        final ComposeShader composeShader = new ComposeShader(linearGradient, bitmapShader, PorterDuff.Mode.SRC_OVER);
        paint.setShader(composeShader);
        canvas.drawRect(0,0,800,1000,paint);


    }
}
