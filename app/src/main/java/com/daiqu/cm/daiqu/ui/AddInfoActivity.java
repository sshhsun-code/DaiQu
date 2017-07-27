package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daiqu.cm.daiqu.MainActivity;
import com.daiqu.cm.daiqu.R;
import com.daiqu.cm.daiqu.global.Constast;
import com.daiqu.cm.daiqu.global.GlobalPref;


/**
 * Created by CM on 2017/7/27.
 */

public class AddInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "AddInfoActivity";
    
    private Button back;
    private EditText name;
    private EditText user_name;
    private EditText phone;
    private EditText school;
    private EditText common_address;
    private Button sure_btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_info_activity);
        Log.d(TAG, "onCreate: ");
        back = (Button) findViewById(R.id.add_back);
        name = (EditText) findViewById(R.id.add_name);
        user_name = (EditText) findViewById(R.id.add_user_name);
        phone = (EditText) findViewById(R.id.add_phone);
        school = (EditText) findViewById(R.id.add_school);
        common_address = (EditText) findViewById(R.id.common_address);
        sure_btn = (Button) findViewById(R.id.add_btn);

        back.setOnClickListener(this);
        sure_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_back:
                finish();
                break;
            case R.id.add_btn:
                if (save()){
                    showDialog();

                }else {
                    Toast.makeText(this,"喵~ 必须全部填写完整哦！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void showDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage(R.string.sure_message); //设置内容
        builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                Intent intent = new Intent(AddInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }

    private boolean save(){
        GlobalPref sp = GlobalPref.getInstance(this);
        if (isEmpty(name)) return false;
        sp.putString(Constast.NAME,name.getText().toString());
        if (isEmpty(user_name)) return false;
        sp.putString(Constast.USER_NAME,user_name.getText().toString());
        if (isEmpty(phone)) return false;
        sp.putString(Constast.PHONE,phone.getText().toString());
        if (isEmpty(school)) return false;
        sp.putString(Constast.SCHOOL,school.getText().toString());
        if (isEmpty(common_address)) return false;
        sp.putString(Constast.COMMON_ADDRESS,common_address.getText().toString());
        GlobalPref.getInstance().putBoolean(Constast.HAS_ADDED,true);
        return true;
    }

    private boolean isEmpty(EditText editText){
        if (editText == null || editText.getText().toString().equals("")){
            return true;
        }
        return false;
    }
}
