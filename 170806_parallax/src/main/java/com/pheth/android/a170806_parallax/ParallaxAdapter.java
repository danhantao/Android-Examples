package com.pheth.android.a170806_parallax;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-08-06 15:40)
 * <p>
 * <p>
 * *************************************************************************
 */
public class ParallaxAdapter extends FragmentPagerAdapter{
    private List<ParallaxFragment> fragments;
    public ParallaxAdapter(FragmentManager fm,List<ParallaxFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
