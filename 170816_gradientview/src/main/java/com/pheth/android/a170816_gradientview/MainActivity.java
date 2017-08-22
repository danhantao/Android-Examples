package com.pheth.android.a170816_gradientview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyGradientView myGradientView = new MyGradientView(this);
//        setContentView(R.layout.activity_main);
        setContentView(myGradientView);
//        setContentView(R.layout.activity_main2);
    }
}
