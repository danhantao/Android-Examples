package com.pheth.android.a170807_touchevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener{
    private RelativeLayout relativeLayout;
    private Button button;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.releative_Layout);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);
        button.setOnTouchListener(this);
        relativeLayout.setOnTouchListener(this);
    }


    @Override
    public void onClick(View view) {
        Log.i(TAG,"onClickListener ---" + view);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.i(TAG,"onTouchListener ---" + motionEvent.getAction() + " " + view);
        return true;
    }
}
