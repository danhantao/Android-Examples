package com.pheth.android.a170731_md_custombehavior;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        final View viewById = findViewById(R.id.tv1);
//        viewById.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ViewCompat.offsetTopAndBottom(v,3);
//            }
//        });
        setContentView(R.layout.activity_main1);
    }
}
