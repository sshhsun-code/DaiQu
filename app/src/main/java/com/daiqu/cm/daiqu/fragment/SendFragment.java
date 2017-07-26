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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.daiqu.cm.daiqu.R;

/**
 * Created by CM on 2017/7/25.
 */

public class SendFragment extends Fragment implements View.OnClickListener{

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
    private Button pick_uo_address_btn;
    private EditText pick_up_address;
    private EditText pick_up_code;
    private EditText arrive_address;
    private Button arrive_address_btn;
    private EditText arrive_time_begin;
    private EditText arrive_time_end;
    private Button confirm_btn;
    private RadioGroup radioGroup;
    private RadioButton price_small_rb;
    private RadioButton price_big_rb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.send_fragment,container,false);
        backButton = view.findViewById(R.id.send_back);


        name_edit = view.findViewById(R.id.send_name_edit);
        phone_edit = view.findViewById(R.id.send_phone_editText);
        company_spinner = view.findViewById(R.id.send_company_spinner);
        pick_up_address = view.findViewById(R.id.send_pick_up_address_edit);
        pick_uo_address_btn = view.findViewById(R.id.send_pick_up_btn);
        pick_up_code = view.findViewById(R.id.send_code_editText);
        arrive_address = view.findViewById(R.id.send_arrive_address_edit);
        arrive_address_btn = view.findViewById(R.id.send_arrive_address_btn);
        arrive_time_begin = view.findViewById(R.id.send_arrive_time_edit_begin);
        arrive_time_end = view.findViewById(R.id.send_arrive_time_edit_end);
        confirm_btn = view.findViewById(R.id.send_confirm_btn);
        radioGroup = view.findViewById(R.id.send_price_radioGroup);



        backButton.setOnClickListener(this);
        pick_up_address.setOnClickListener(this);
        arrive_address.setOnClickListener(this);
        arrive_time_begin.setOnClickListener(this);
        arrive_time_end.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.send_pick_up_address_edit:

                break;
            case R.id.send_pick_up_btn:

                break;
            case R.id.send_arrive_address_btn:

                break;
            case R.id.send_arrive_address_edit:

                break;
            case R.id.send_back:
                FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
                beginTransaction.replace(R.id.main_content,
                        HomeFragment.newInstance(getString(R.string.home)));
                beginTransaction.commit();
                break;
            case R.id.send_confirm_btn:

                break;
            case R.id.send_arrive_time_edit_begin:

                break;
            case R.id.send_arrive_time_edit_end:

                break;
        }
    }
}
