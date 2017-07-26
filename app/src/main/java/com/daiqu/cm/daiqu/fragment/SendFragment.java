package com.daiqu.cm.daiqu.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.daiqu.cm.daiqu.R;

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

    private Button backButton;
    private EditText name_edit;
    private EditText phone_edit;
    private Spinner company_spinner;
    private EditText pick_up_address;
    private EditText pick_up_code;
    private EditText arrive_address;
    private EditText arrive_time_begin;
    private EditText arrive_time_end;
    private Button confirm_btn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.send_fragment,container,false);
        backButton = view.findViewById(R.id.send_back);
        backButton.setOnClickListener(back_clickListener);

        name_edit = view.findViewById(R.id.send_name_edit);
        phone_edit = view.findViewById(R.id.send_phone_editText);
        company_spinner = view.findViewById(R.id.send_company_spinner);
        pick_up_address = view.findViewById(R.id.send_pick_up_address_edit);
        pick_up_code = view.findViewById(R.id.send_code_editText);
        arrive_address = view.findViewById(R.id.send_arrive_address_edit);
        arrive_time_begin = view.findViewById(R.id.send_arrive_time_edit_begin);
        arrive_time_end = view.findViewById(R.id.send_arrive_time_edit_end);
        confirm_btn = view.findViewById(R.id.send_confirm_btn);

        return view;
    }

    private View.OnClickListener back_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.main_content,
                    HomeFragment.newInstance(getString(R.string.home)));
            beginTransaction.commit();
        }
    };


}
