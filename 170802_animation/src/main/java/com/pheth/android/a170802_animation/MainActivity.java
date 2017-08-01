package com.pheth.android.a170802_animation;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bt1;
    private Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_02);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //圆形水波纹揭露效果
//				ViewAnimationUtils.createCircularReveal(
//						view, //作用在哪个View上面
//						centerX, centerY, //扩散的中心点
//						startRadius, //开始扩散初始半径
//						endRadius)//扩散结束半径
                final Animator circularReveal = ViewAnimationUtils.createCircularReveal(bt1, bt1.getWidth() / 2, bt1.getHeight() / 2, 0, bt1.getHeight());
                circularReveal.setDuration(500);
                circularReveal.setInterpolator(new AccelerateInterpolator());
                circularReveal.start();
//                Math.hypot(x,y) 计算三角形斜边长
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Animator animator = ViewAnimationUtils.createCircularReveal(bt2, 0, 0, 0, (float) Math.hypot(bt2.getWidth(), bt2.getHeight()));
                animator.setDuration(1000);
                animator.setInterpolator(new AccelerateInterpolator());
                animator.start();

            }
        });
        startActivity(new Intent(this, SecondActivity.class));
    }
}
