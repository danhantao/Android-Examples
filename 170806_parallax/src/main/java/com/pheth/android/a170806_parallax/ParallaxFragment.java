package com.pheth.android.a170806_parallax;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * *************************************************************************
 *
 * @创建人: danhantao
 * @创建时间: danhantao(2017-08-06 15:35)
 * <p>
 * <p>
 * *************************************************************************
 */
public class ParallaxFragment extends Fragment{
    private List<View> views = new ArrayList<View>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        final int layoutId = bundle.getInt("layoutId");
//        final View view = inflater.inflate(layoutId, container);
        ParallaxLayoutInflater layoutInflater = new ParallaxLayoutInflater(inflater,getActivity(),this);
        return layoutInflater.inflate(layoutId,null);
    }

    public List<View> getViews(){
        return views;
    }
}
