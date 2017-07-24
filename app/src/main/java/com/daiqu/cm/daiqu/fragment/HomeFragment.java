package com.daiqu.cm.daiqu.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.global.Constast;

/**
 * Created by CM on 2017/7/24.
 */

public class HomeFragment extends Fragment {
    public static HomeFragment newInstance(String s){
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constast.name,s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment,container,false);
        return v;
    }
}
