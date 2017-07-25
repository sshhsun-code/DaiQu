package com.daiqu.cm.daiqu.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by CM on 2017/7/25.
 */

public class SendFragment extends Fragment {

    public static SendFragment newInstance() {

        Bundle args = new Bundle();

        SendFragment fragment = new SendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {



        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
