package com.daiqu.cm.daiqu.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.global.Constast;
import com.daiqu.cm.daiqu.inter.GoOtherActivity;
import com.daiqu.cm.daiqu.ui.OrderInfoActivity;

/**
 * Created by CM on 2017/7/24.
 */

public class MyFragment extends Fragment {

    public static MyFragment newInstance(String s){
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constast.NAME,s);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    private LinearLayout order_linear;
    private GoOtherActivity goOtherActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_fragment,container,false);

        order_linear = v.findViewById(R.id.my_order);
        order_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOtherActivity.goOhter(new OrderInfoActivity());
            }
        });
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        goOtherActivity = (GoOtherActivity) activity;
    }
}
