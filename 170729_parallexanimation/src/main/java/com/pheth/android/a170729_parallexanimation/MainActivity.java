package com.pheth.android.a170729_parallexanimation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private ViewPager vp;
    private int[] layouts = {
            R.layout.welcome01,
            R.layout.welcome02,
            R.layout.welcome03
    };

    private WelcomePagerTransformer transformer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.viewpager);
        WelcomePagerAdapter adapter = new WelcomePagerAdapter(getSupportFragmentManager());
        Log.i(TAG, "offset:" + vp.getOffscreenPageLimit());
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(adapter);

        transformer = new WelcomePagerTransformer();
        vp.setPageTransformer(true, transformer);

        vp.addOnPageChangeListener(transformer);
    }

    class WelcomePagerAdapter extends FragmentPagerAdapter {

        public WelcomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = new TranslateFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.LAYOUT_ID, layouts[position]);
            bundle.putInt(Constants.PAGE_INDEX, position);
            f.setArguments(bundle);
            return f;
        }

        @Override
        public int getCount() {
            return 3;
        }

    }
}
