package com.pheth.android.a0730_md;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-07-30 08:41)
 * <p>
 * <p>
 * *************************************************************************
 */
public class MainActivity01 extends AppCompatActivity {
    private RecyclerView recyclerview;
    private ImageButton fab;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main01);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        fab = (ImageButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("测试");

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            list.add("Item" + i);
        }
        RecyclerView.Adapter adapter = new FabRecyclerAdapter(list);
        recyclerview.setAdapter(adapter);


    }


    public void rotate(View v) {
        Snackbar.make(v, "test", Snackbar.LENGTH_LONG).setAction("Sure", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "thanks", Toast.LENGTH_LONG).show();

            }
        }).show();
    }

}
