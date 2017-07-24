package com.daiqu.cm.daiqu.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by CM on 2017/7/24.
 */

public class FaxianFragment extends Fragment {
    public static FaxianFragment newInstance(String s){
        FaxianFragment faxianFragment = new FaxianFragment();
        Bundle bundle = new Bundle();
        bundle.putString("",s);
        faxianFragment.setArguments(bundle);
        return faxianFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
