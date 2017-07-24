package com.daiqu.cm.daiqu.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.provider.SyncStateContract;

/**
 * Created by CM on 2017/7/24.
 */

public class MyFragment extends Fragment {

    public static MyFragment newInstance(String s){
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("",s);
        myFragment.setArguments(bundle);
        return myFragment;
    }
}
