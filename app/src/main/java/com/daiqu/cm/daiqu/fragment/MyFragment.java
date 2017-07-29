package com.daiqu.cm.daiqu.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.global.Constast;
import com.daiqu.cm.daiqu.inter.GoOtherActivity;
import com.daiqu.cm.daiqu.ui.OrderInfoActivity;

/**
 * Created by CM on 2017/7/24.
 */

public class MyFragment extends Fragment implements View.OnClickListener  {

    public static MyFragment newInstance(String s){
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constast.NAME,s);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    private LinearLayout order_linear;
    private GoOtherActivity goOtherActivity;

    private RelativeLayout titleLayout;
    private LinearLayout shopping;
    private LinearLayout setting;
    private LinearLayout give_money;

    private Toast toast;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_fragment,container,false);

        order_linear = v.findViewById(R.id.my_order);
        titleLayout = v.findViewById(R.id.title_my);
        shopping = v.findViewById(R.id.shopping);
        setting = v.findViewById(R.id.setting);
        give_money = v.findViewById(R.id.give_money);

        titleLayout.setOnClickListener(this);
        shopping.setOnClickListener(this);
        setting.setOnClickListener(this);
        give_money.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        if (toast == null) {
            toast = Toast.makeText(getActivity(),"开发小哥加班到吐血。。。正在医院抢救。这个功能下一版再上。"
                    ,Toast.LENGTH_SHORT);
        }else {
            toast.setText("开发小哥加班到吐血。。。正在医院抢救。这个功能下一版再上。");
        }
        toast.show();
    }

}
