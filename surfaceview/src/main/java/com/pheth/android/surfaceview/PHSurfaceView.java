package com.pheth.android.surfaceview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-08-18 18:57)
 * <p>
 * <p>
 * *************************************************************************
 */
public class PHSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

//    SufaceView在底层实现机制中就已经实现了双缓冲机制

    private SurfaceHolder holder;
    private MyThread myThread;

    public PHSurfaceView(Context context) {
        super(context);
        holder = this.getHolder();
        holder.addCallback(this);
        myThread = new MyThread(holder);//创建一个绘图线程
    }

//    surfaceview的核心在于提供了两个线程：UI线程和渲染线程。这里应注意：
//            1> 所有SurfaceView和SurfaceHolder.Callback的方法都应该在UI线程里调用，一般来说就是应用程序主线程。渲染线程所要访问的各种变量应该作同步处理。
//            2> 由于surface可能被销毁，它只在SurfaceHolder.Callback.surfaceCreated()和 SurfaceHolder.Callback.surfaceDestroyed()之间有效，所以要确保渲染线程访问的是合法有效的surface。

//    你可以通过SurfaceHolder接口访问这个surface，getHolder()方法可以得到这个接口。
//    surfaceview变得可见时，surface被创建；surfaceview隐藏前，surface被销毁。这样能节省资源。如果你要查看 surface被创建和销毁的时机，可以重载surfaceCreated(SurfaceHolder)和 surfaceDestroyed(SurfaceHolder)。


//    整个过程：继承SurfaceView并实现SurfaceHolder.Callback接口
//          ----> SurfaceView.getHolder()获得SurfaceHolder对象
//          ----> SurfaceHolder.addCallback(callback)添加回调函数
//          ----> SurfaceHolder.lockCanvas()获得Canvas对象并锁定画布
//          ----> Canvas绘画 ---->SurfaceHolder.unlockCanvasAndPost(Canvas canvas)结束锁定画图，并提交改变，将图形显示。


/*
SurfaceHolder
    这里用到了一个类SurfaceHolder,可以把它当成surface的控制器，用来操纵surface。处理它的Canvas上画的效果和动画，控制表面，大小，像素等。
    几个需要注意的方法：
            (1)、abstract void addCallback(SurfaceHolder.Callback callback);
            // 给SurfaceView当前的持有者一个回调对象。
            (2)、abstract Canvas lockCanvas();
            // 锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
            (3)、abstract Canvas lockCanvas(Rect dirty);
            // 锁定画布的某个区域进行画图等..因为画完图后，会调用下面的unlockCanvasAndPost来改变显示内容。
            // 相对部分内存要求比较高的游戏来说，可以不用重画dirty外的其它区域的像素，可以提高速度。
            (4)、abstract void unlockCanvasAndPost(Canvas canvas);
            // 结束锁定画图，并提交改变。
*/
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        myThread.isRun = true;
        myThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        myThread.isRun = false;
    }


    //线程内部类
    class MyThread extends Thread {
        private SurfaceHolder holder;
        public boolean isRun;

        public MyThread(SurfaceHolder holder) {
            this.holder = holder;
            isRun = true;
        }

        @Override
        public void run() {
            int count = 0;
            while (isRun) {
                Canvas c = null;
                try {
                    synchronized (holder) {
                        c = holder.lockCanvas();//锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
                        c.drawColor(Color.BLACK);//设置画布背景颜色
                        Paint p = new Paint(); //创建画笔
                        p.setColor(Color.WHITE);
                        Rect r = new Rect(100, 50, 300, 250);
                        c.drawRect(r, p);
                        c.drawText("这是第" + (count++) + "秒", 100, 310, p);
                        Thread.sleep(1000);//睡眠时间为1秒
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                } finally {
                    if (c != null) {
                        holder.unlockCanvasAndPost(c);//结束锁定画图，并提交改变。
                    }
                }
            }
        }
    }
}
