package com.pheth.android.a170805_animationframe;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView md_star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        md_star = (ImageView) findViewById(R.id.md_star);
        startActivity(new Intent(this,SecondActivity.class));
    }

    public void animation(View view){
        ImageView iv = (ImageView) view;
        Drawable drawable = iv.getDrawable();
        if (drawable instanceof Animatable){
            ((Animatable)drawable).start();
        }
    }
}
