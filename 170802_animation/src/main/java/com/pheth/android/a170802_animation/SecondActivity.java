package com.pheth.android.a170802_animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-08-02 06:28)
 * <p>
 * <p>
 * *************************************************************************
 */
public class SecondActivity extends AppCompatActivity{

    private View first_View;
    private View second_View;
    private Button bt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main03);
        first_View = findViewById(R.id.first);
        second_View = findViewById(R.id.second);
        bt = (Button)findViewById(R.id.bt);


    }

    public void startFirstAnimation(View view){
        /**
         * first动画 1.翻转动画 2.透明度动画 3.缩放动画
         *
         */
        // 1.翻转
        ObjectAnimator firstObj = ObjectAnimator.ofFloat(first_View,"rotationX",0f,25f);
        firstObj.setDuration(300);
//        firstObj.start();
        // 2.透明度
        ObjectAnimator firstAlphaAnim = ObjectAnimator.ofFloat(first_View,"alpha",1f,0.5f);
        firstAlphaAnim.setDuration(200);
        // 3.缩放动画
        ObjectAnimator firstScaleXAnim = ObjectAnimator.ofFloat(first_View,"scaleX",1.0f,0.8f);
        firstScaleXAnim.setDuration(300);
        ObjectAnimator firstScaleYAnim = ObjectAnimator.ofFloat(first_View,"scaleY",1.0f,0.8f);
        firstScaleYAnim.setDuration(300);
        // 执行翻转动画
//        firstObj.addUpdateListener();
        ObjectAnimator firstResumeRotationAnim = ObjectAnimator.ofFloat(first_View, "rotationX",25f, 0f);
        firstResumeRotationAnim.setDuration(200);
        firstResumeRotationAnim.setStartDelay(200);//延迟执行
        // 平移上去
        ObjectAnimator firstTranslationAnim = ObjectAnimator.ofFloat(first_View,"translationY",0f,-0.1f*first_View.getHeight());
        firstAlphaAnim.setDuration(200);

        // 第二个View执行平移动画 --- 向上平移 (不可见到，可见)
        ObjectAnimator secondTranslationAnim = ObjectAnimator.ofFloat(second_View,"translationY",second_View.getHeight(),0f);
        secondTranslationAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                second_View.setVisibility(View.VISIBLE);
                bt.setClickable(false);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(firstObj,firstAlphaAnim,firstScaleXAnim,firstScaleYAnim,firstResumeRotationAnim,firstTranslationAnim,secondTranslationAnim);
        animatorSet.start();
    }

    public void startSecondAnimation(View view){
        // 相反
    }

}
