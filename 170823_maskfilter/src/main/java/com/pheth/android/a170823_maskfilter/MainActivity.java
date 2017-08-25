package com.pheth.android.a170823_maskfilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        MaskFilterView view = new MaskFilterView(this);
        setContentView(view);
    }
}
