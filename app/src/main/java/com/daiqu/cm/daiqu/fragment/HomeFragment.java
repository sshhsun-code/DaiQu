package com.daiqu.cm.daiqu.fragment;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by CM on 2017/7/24.
 */

public class HomeFragment extends Fragment {
    public static HomeFragment newInstance(String s){
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("",s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }
}
