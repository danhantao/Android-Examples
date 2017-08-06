package com.pheth.android.a170806_parallax;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-08-06 12:39)
 * <p>
 * <p>
 * *************************************************************************
 */
public class ParallaxContainer extends FrameLayout implements ViewPager.OnPageChangeListener {
    private List<ParallaxFragment> fragments;
    private float containerWidth;


    public ParallaxContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public void setUp(int... ids) {
        fragments = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ParallaxFragment fragment = new ParallaxFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("layoutId", ids[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        // 1.viewpager
        ViewPager viewPager = new ViewPager(getContext());
        viewPager.setId(R.id.parallax_pager);
        viewPager.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        ParallaxAdapter parallaxAdapter = new ParallaxAdapter(((FragmentActivity) getContext()).getSupportFragmentManager(), fragments);
        viewPager.setAdapter(parallaxAdapter);
        addView(viewPager, 0);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        containerWidth = getWidth();
        //进入的页面
        ParallaxFragment inFragment = null;
        try {
            inFragment = fragments.get(position - 1);
        } catch (Exception e) {
        }
        //退出的页面
        ParallaxFragment outFragment = null;
        try {
            outFragment = fragments.get(position);
        } catch (Exception e) {
        }

        if (inFragment != null) {
            List<View> views = inFragment.getViews();
            for (int i = 0; i < views.size(); i++) {
                View view = views.get(i);
                ParallxViewTag tag = (ParallxViewTag) view.getTag(R.id.parallax_view_tag);
                if (tag == null) continue;
                //进来动画
                view.setTranslationX((containerWidth - positionOffsetPixels) * tag.xIn);
                view.setTranslationY((containerWidth - positionOffsetPixels) * tag.yIn);

            }
        }
        if (outFragment != null) {
            List<View> views = outFragment.getViews();
            for (int i = 0; i < views.size(); i++) {
                View view = views.get(i);
                ParallxViewTag tag = (ParallxViewTag) view.getTag(R.id.parallax_view_tag);
                if (tag == null) continue;
                //进来动画
                view.setTranslationX((-positionOffsetPixels) * tag.xOut);
                view.setTranslationY((-positionOffsetPixels) * tag.yOut);

            }
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
