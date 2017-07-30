package com.pheth.android.a0730_md;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-07-30 10:29)
 * <p>
 * <p>
 * *************************************************************************
 */
public class MainActivity03 extends AppCompatActivity{

    private TabLayout tabLayout;
    private String[] title = {
            "头条",
            "新闻",
            "娱乐",
            "体育",
            "科技",
            "美女",
            "财经",
            "汽车",
            "房子",
            "头条"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main03);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        PhPagerAdapter adapter = new PhPagerAdapter(getSupportFragmentManager());
        //1.TabLayout和Viewpager关联
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 选中时回调
                viewPager.setCurrentItem(tab.getPosition(),true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //2.ViewPager滑动关联tabLayout
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //设置tabLayout的标签来自于PagerAdapter
        tabLayout.setTabsFromPagerAdapter(adapter);
        viewPager.setAdapter(adapter);


    }

    class PhPagerAdapter extends FragmentPagerAdapter{

        public PhPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = new NewDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", title[position]);
            f.setArguments(bundle);
            return f;
        }

        // 显示tablayout
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public int getCount() {
            return title.length;
        }
    }

}
