package com.pheth.android.a170729_parallexanimation;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-07-29 21:22)
 * <p>
 * <p>
 * *************************************************************************
 */
public class WelcomePagerTransformer implements ViewPager.PageTransformer, ViewPager.OnPageChangeListener {

    private static final float ROT_MOD = -15f;
    private int pageIndex;
    private boolean pageChanged = true;

    public static final String TAG = WelcomePagerTransformer.class.getSimpleName();


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.i(TAG, "onPageSelected:" + position);
        pageIndex = position;
        pageChanged = true;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.i(TAG, "onPageScrollStateChanged:" + state);
    }

    /**
     * ViewPager滑动过程中，回调方法PageTransformer
     * 滑动时，每一个页面的view都会回调该方法
     *
     * @param page     当前页面
     * @param position 滑动的位置
     *                 <p>
     *                 视差效果：在View正常滑动的情况下，给当前View或者当前view里面的每一个子view来一个加速度
     *                 而且每一个加速度大小不一样
     */
    @Override
    public void transformPage(View page, float position) {
        Log.i(TAG, "position:" + position);
        ViewGroup v = (ViewGroup) page.findViewById(R.id.rl);
        final NoTouchHorizontalScrollView mscv = (NoTouchHorizontalScrollView) v.findViewById(R.id.mscv);
        View bg1 = v.findViewById(R.id.imageView0);
        View bg2 = v.findViewById(R.id.imageView0_2);
        View bg_container = v.findViewById(R.id.bg_container);

        int bg1_green = page.getContext().getResources().getColor(R.color.bg1_green);
        int bg2_blue = page.getContext().getResources().getColor(R.color.bg2_blue);

        Integer tag = (Integer) page.getTag();
        View parent = (View) page.getParent();
        if (parent instanceof ViewPager) {
            Log.i(TAG, "Tag:" + tag + " postion:" + position);
        }
        //颜色估值器
        ArgbEvaluator evaluator = new ArgbEvaluator();
        int color = bg1_green;
        if (tag.intValue() == pageIndex) {
            switch (pageIndex) {
                case 0:
                    color = (int) evaluator.evaluate(Math.abs(position), bg1_green, bg2_blue);
                    break;
                case 1:
                    color = (int) evaluator.evaluate(Math.abs(position), bg2_blue, bg1_green);
                    break;
                case 2:
                    color = (int) evaluator.evaluate(Math.abs(position), bg1_green, bg2_blue);
                    break;
                default:
                    break;
            }
            //设置整个viewpager的背景颜色
            parent.setBackgroundColor(color);

            //动画 变色
//		    ObjectAnimator colorAnim = ObjectAnimator.ofInt(this, "backgroundColor", bg1_green, bg2_blue);
//		    ObjectAnimator colorAnim = ObjectAnimator.ofInt(this, "backgroundColor", CYAN, BLUE, RED);
//		    colorAnim.setTarget(parent);
//		    colorAnim.setEvaluator(new ArgbEvaluator());
//		    colorAnim.setRepeatCount(ValueAnimator.INFINITE);
//		    colorAnim.setRepeatMode(ValueAnimator.REVERSE);
//		    colorAnim.setDuration(3000);
//		    colorAnim.start();
        }

        if (position == 0) {
            Log.i(TAG,"transformPage position == 0");
            //pageChanged作用--解决问题：只有在切换页面的时候才展示平移动画，如果不判断则会只是移动一点点当前页面松开也会执行一次平移动画
            if (pageChanged) {
                bg1.setVisibility(View.VISIBLE);
                bg2.setVisibility(View.VISIBLE);

                // 左边划出
                ObjectAnimator animator_bg1 = ObjectAnimator.ofFloat(bg1, "translationX", 0, -bg1.getWidth());
                animator_bg1.setDuration(400);
                animator_bg1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mscv.smoothScrollTo((int) (mscv.getWidth() * animation.getAnimatedFraction()), 0);
                    }
                });
                animator_bg1.start();

                // 右边划出
                ObjectAnimator animator_bg2 = ObjectAnimator.ofFloat(bg2, "translationX", bg2.getWidth(), 0);
                animator_bg2.setDuration(400);
                animator_bg2.start();

                pageChanged = false;
            }
            //所有效果复原
        } else if (position == -1 || position == 1) {
            bg2.setTranslationX(0);
            bg1.setTranslationX(0);
            mscv.smoothScrollTo(0, 0);
        } else if (position < 1 && position > -1) {

            final float width = bg1.getWidth();
            final float height = bg1.getHeight();
            final float rotation = ROT_MOD * position * -1.25f;

//			bg1.setPivotX(width * 0.5f);
//			bg1.setPivotY(height);
//			bg1.setRotation(rotation);
//			bg2.setPivotX(width * 0.5f);
//			bg2.setPivotY(height);
//			bg2.setRotation(rotation);

            // 翻转动画
            //这里不去分别处理bg1、bg2，而是用包裹的父容器执行动画，目的是避免难以处理两个bg的属性位置恢复。
            bg_container.setPivotX(width * 0.5f);
            bg_container.setPivotY(height);
            bg_container.setRotation(rotation);
        }

    }
}
