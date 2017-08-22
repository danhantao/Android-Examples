package com.pheth.android.helloworld;

import android.util.DisplayMetrics;
import android.util.Log;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-07-31 12:06)
 * <p>
 * <p>
 * *************************************************************************
 */
public class Utils {
    /**
     * 是否大于720p
     *
     * @return
     */
    public static boolean isLargeScreenDensity() {
        final DisplayMetrics displayMetrics = PHApplication.getInstance().getResources().getDisplayMetrics();
        Log.i("danhantao", "widthPixels:" + displayMetrics.widthPixels);
        Log.i("danhantao", "heightPixels:" + displayMetrics.heightPixels);
        // Nexus 5 1776x1080
        return displayMetrics != null && displayMetrics.heightPixels >= 1280 && displayMetrics.widthPixels >= 720;
    }


}
