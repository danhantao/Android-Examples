package com.pheth.android.helloworld;

import android.app.Application;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-07-31 12:07)
 * <p>
 * <p>
 * *************************************************************************
 */
public class PHApplication extends Application{
    static PHApplication phApplication;
    public static PHApplication getInstance(){
        return phApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        phApplication = this;
    }
}
