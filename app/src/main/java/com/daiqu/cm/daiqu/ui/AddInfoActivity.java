package com.daiqu.cm.daiqu.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
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

public class AddInfoActivity extends Activity implements View.OnClickListener{

    private Button back;
    private EditText name;
    private EditText user_name;
    private EditText phone;
    private EditText school;
    private EditText common_address;
    private Button sure_btn;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.add_info_activity);
        back = findViewById(R.id.add_back);
        name = findViewById(R.id.add_name);
        user_name = findViewById(R.id.add_user_name);
        phone = findViewById(R.id.add_phone);
        school = findViewById(R.id.add_school);
        common_address = findViewById(R.id.common_address);
        sure_btn = findViewById(R.id.add_btn);

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
                    Toast.makeText(this,"喵~ 必须全部填写完整哦！",Toast.LENGTH_SHORT);
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
        return true;
    }

    private boolean isEmpty(EditText editText){
        if (editText == null || editText.getText().equals("")){
            return true;
        }
        return false;
    }
}
