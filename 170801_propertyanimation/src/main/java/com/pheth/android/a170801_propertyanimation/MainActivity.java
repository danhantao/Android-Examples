package com.pheth.android.a170801_propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import static android.R.attr.animation;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.button1);
//        iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"点击",Toast.LENGTH_LONG).show();
//            }
//        });
    }

    public void startAnimation(View v){
//        Animation animation = AnimationUtils.loadAnimation(this,R.anim.translate);
////        animation.start();
//        iv.startAnimation(animation);

        //属性动画
//		position += 100;
//		v.setTranslationX(position);
//		v.setAlpha((float)Math.random());
//      float... values: A set of values that the animation will animate between over time.
//        ObjectAnimator.ofFloat(v,"translationX",values)


//      ObjectAnimator oa = ObjectAnimator.ofFloat(v, "translationX", 0f,300f);
//		oa.setDuration(500);
//		oa.start();
//		ObjectAnimator oa = ObjectAnimator.ofFloat(v, "translationY", 0f,300f);
//		oa.setDuration(500);
//		oa.start();
//        ObjectAnimator oa = ObjectAnimator.ofFloat(v, "rotationX", 0f, 360f);
//        oa.setDuration(500);
//        oa.start();

        // 2.------ 多个动画同时执行 办法1:设置动画监听，开始第一个动画同时开启其他动画 ---
        // 方法1:
//        final ObjectAnimator animator = ObjectAnimator.ofFloat(v,"haha",0f,100f); // 没有这个属性的时候，就是valueanimator
//        animator.setDuration(300);
//        // 设置动画监听
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                // 动画执行过程中，不断的调用此方法
//                animator.getAnimatedFraction();// 百分比
//                float value = (float) valueAnimator.getAnimatedValue();//
//                //得到duration时间内 values当中的某一个中间值。0f~100f
//                iv.setAlpha(value/200); // 0~1
//                iv.setScaleX(0.5f + value / 200);//0.5~1
//                iv.setScaleY(0.5f + value / 200);//0.5~1
//            }
//        });
//        animator.start();

//        animator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });


        //2)方法2：用ValueAnimator
//		ValueAnimator animator = ValueAnimator.ofFloat(0f,200f);
//		animator.setDuration(200);
//		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
////				//动画在执行的过程当中，不断地调用此方法
//////			animation.getAnimatedFraction()//百分比
////			//得到duration时间内 values当中的某一个中间值。0f~100f
//				float value = (float) animation.getAnimatedValue();//
//				iv.setScaleX(0.5f+value/200);//0.5~1
//				iv.setScaleY(0.5f+value/200);//0.5~1
//			}
//		});
//		animator.start();

		//3）方法3
		//float... values:代表关键帧的值
//		PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("alpha", 1f,0.7f,1f);
//		PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleX", 1f,0.7f,1f);
//		PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleY", 1f,0.7f,1f);
////		PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("translationX", 0f,300f);
//
//		ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(iv, holder1,holder2,holder3);
//		animator.setDuration(1000);
//		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
//				float animatedValue = (float) animation.getAnimatedValue();
//				float animatedFraction = animation.getAnimatedFraction();
//				long playTime = animation.getCurrentPlayTime();
//
//				System.out.println("animatedValue:"+animatedValue+",  playTime:"+playTime);
//			}
//		});
//		animator.start();

		// 方法4: ------ 动画集合 ------
//		ObjectAnimator animator1 = ObjectAnimator.ofFloat(iv,"alpha",1f,0.7f,1f);
//		ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv,"scaleX",1f,0.7f,1f);
//		ObjectAnimator animator3 = ObjectAnimator.ofFloat(iv,"scaleY",1f,0.7f,1f);
//
//		AnimatorSet animatorSet = new AnimatorSet();
//		animatorSet.setDuration(500);
////		animatorSet.play(anim) // 执行动画
//		animatorSet.playTogether(animator1,animator2,animator3); // 同时执行
////		animatorSet.playSequentially(animator1,animator2,animator3);
//		animatorSet.start();

        // 4.---------- 实例:实现抛物线效果 -------
        /**
         * x:匀速
         * y:加速度 y=1/2*g*t*t
         * 使用估值器最好实现
         *
         */

//        ValueAnimator valueAnimator = new ValueAnimator();
//        valueAnimator.setDuration(4000);
////        valueAnimator.setFloatValues(value);
//        valueAnimator.setObjectValues(new PointF(0,0));
//        // 估值器 --- 定义计算规则
//        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
//
//            @Override
//            public PointF evaluate(float v, PointF startValue, PointF endValue) {
//				//拿到每一个时间点的坐标
//				PointF pointF = new PointF();
//                pointF.x = 100f * (v * 4);
//                pointF.y= 0.5f * 150f * (v * 4) * (v * 4);
//                return pointF;
//            }
//        });
//
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                //得到此时间点的坐标
//				PointF pointF = (PointF) valueAnimator.getAnimatedValue();
//				iv.setX(pointF.x);
//				iv.setY(pointF.y);
//            }
//        });
//        valueAnimator.start();

        // 5.加速度
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v,"translationY",0f,100f);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
//        objectAnimator.setInterpolator(new BounceInterpolator());
        objectAnimator.start();

    }

}
