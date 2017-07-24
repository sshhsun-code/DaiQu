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

public class MyFragment extends Fragment {

    public static MyFragment newInstance(String s){
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constast.name,s);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_fragment,container,false);
        return v;
    }
}
