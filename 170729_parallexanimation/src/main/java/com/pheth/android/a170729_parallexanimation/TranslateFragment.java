package com.pheth.android.a170729_parallexanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-07-29 20:49)
 * <p>
 * <p>
 * *************************************************************************
 */
public class TranslateFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int layoutIndex = bundle.getInt(Constants.LAYOUT_ID);
        int pageIndex = bundle.getInt(Constants.PAGE_INDEX);
        View currentView = inflater.inflate(layoutIndex, null);
        currentView.setTag(pageIndex);
        return currentView;
    }
}
