package com.pheth.android.a170803_materialdesign_transitation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置允许使用转场动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = (ImageView) findViewById(R.id.iv1);

    }

    public void jump(View view) {
//        startActivity(new Intent(this, SecondActivity.class));
//        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

//        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,iv1,"iv_meinv3");
//        startActivity(new Intent(this,SecondActivity.class),optionsCompat.toBundle());

        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide); // 出去动画
        getWindow().setEnterTransition(slide); // 进来动画
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, iv1, "iv_meinv3");
        startActivity(new Intent(this, SecondActivity.class), optionsCompat.toBundle());

    }

}
