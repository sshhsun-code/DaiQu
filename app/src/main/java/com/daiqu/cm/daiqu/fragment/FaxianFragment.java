package com.daiqu.cm.daiqu.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.global.Constast;

/**
 * Created by CM on 2017/7/24.
 */

public class FaxianFragment extends Fragment {

    private Toast toast = null;

    public static FaxianFragment newInstance(String s){
        FaxianFragment faxianFragment = new FaxianFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constast.NAME,s);
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
        View v = inflater.inflate(R.layout.faxian_fragment,container,false);
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (toast == null) {
                    toast = Toast.makeText(getActivity(),"开发小哥加班到吐血。。。正在医院抢救。这个功能下一版再上。"
                            ,Toast.LENGTH_SHORT);
                }else {
                    toast.setText("开发小哥加班到吐血。。。正在医院抢救。这个功能下一版再上。");
                }
                toast.show();
                return false;
            }
        });
        return v;
    }

}
