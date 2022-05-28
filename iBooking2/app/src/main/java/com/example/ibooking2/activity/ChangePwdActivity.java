package com.example.ibooking2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ibooking2.R;
import com.example.ibooking2.database.DBOpenHelper;

import static android.content.ContentValues.TAG;


public class ChangePwdActivity extends AppCompatActivity implements View.OnClickListener,MyCustomDialog1.ExampleDialogListener {

    private Button button_back;

    private EditText et_original_psw;
    private EditText et_new_psw;
    private EditText et_new_psw_again;
    private Button btn_save;
    private String originalPsw;
    private String newPsw;
    private String newPswAgain;
    private String userName;
    private DBOpenHelper mDBOpenHelper;
    private CheckBox checkBox1;
    private CheckBox checkBox2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        init();

        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        userName = sp.getString("loginName","");

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked)  //取消***
                {
                    et_original_psw.setTransformationMethod(null);
                }
                else  //设置上***
                {
                    et_original_psw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

                et_original_psw.setSelection(et_original_psw.getText().length());  //光标保证在最后

            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked)  //取消***
                {
                    et_new_psw.setTransformationMethod(null);
                }
                else  //设置上***
                {
                    et_new_psw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

                et_new_psw.setSelection(et_new_psw.getText().length());  //光标保证在最后

            }
        });
    }
    //  获取界面控件并处理相关控件的处理事件
    private void init() {
        checkBox1=findViewById(R.id.check1);
        checkBox2=findViewById(R.id.check2);

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePwdActivity.this.finish();//关闭当前界面
            }
        });

        et_original_psw = (EditText) findViewById(R.id.et_original_psw);
        et_original_psw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        et_new_psw = (EditText) findViewById(R.id.et_new_psw);
        et_new_psw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        et_new_psw_again = (EditText) findViewById(R.id.et_new_psw_again);
        btn_save = (Button)  findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(originalPsw)){
                    Toast.makeText(ChangePwdActivity.this,"请输入原始密码",Toast.LENGTH_SHORT).show();
//                }else if (!MD5Utils.md5(originalPsw).equals(readPsw())){
                }else if (!originalPsw.equals(readPsw())){
                    Toast.makeText(ChangePwdActivity.this,"输入的密码与原始密码不一致",Toast.LENGTH_SHORT).show();
//                }else if (MD5Utils.md5(newPsw).equals(readPsw())){
                }else if (newPsw.equals(readPsw())){
                    Toast.makeText(ChangePwdActivity.this,"输入的新密码与原始密码不能一致",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(newPsw)){
                    Toast.makeText(ChangePwdActivity.this,"请输入新密码",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(newPswAgain)){
                    Toast.makeText(ChangePwdActivity.this,"请再次输入新密码",Toast.LENGTH_SHORT).show();
                }else if (!newPsw.equals(newPswAgain)){
                    Toast.makeText(ChangePwdActivity.this,"两次输入的新密码不一致",Toast.LENGTH_SHORT).show();
                }else{
                    ///!!
                    openDialog();
                }
            }
        });

    }
    //修改登录成功后保存在SharedPreferences中的密码
    private void modifyPsw(String newPsw) {
//        String md5Psw = MD5Utils.md5(newPsw);//对新密码进行加密
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();//获取编辑器
        editor.putString(userName,newPsw);
        editor.commit();

        mDBOpenHelper = new DBOpenHelper(this);
        mDBOpenHelper.update(newPsw);
    }
    //从SharedPreferences中读取原始密码
    private String readPsw() {
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw = sp.getString(userName,"");
        return spPsw;

    }

    //h获取控件上的字符串
    private void getEditString() {
        originalPsw = et_original_psw.getText().toString().trim();
        newPsw = et_new_psw.getText().toString().trim();
        newPswAgain = et_new_psw_again.getText().toString().trim();
    }

    @Override
    public void onClick(View view) {

    }

//    @Override
//    public void sendInput(Boolean input) {
//        changePassword();
//    }

    public void changePassword(){
        Toast.makeText(ChangePwdActivity.this,"新密码设置成功",Toast.LENGTH_SHORT).show();
        //修改登录成功后保存在SharedPreferences中的密码
        modifyPsw(newPsw);
        Intent intent = new Intent(ChangePwdActivity.this, MainActivity.class);
        startActivity(intent);
        ChangePwdActivity.this.finish();//关闭当前界面
//                    MainActivity.finish();//关闭设置界面
    }

    public void openDialog(){
        Log.d(TAG,"onClick: opening dialog");

        MyCustomDialog1 dialog=new MyCustomDialog1();

        dialog.show(getSupportFragmentManager(),"MyCustomDialog");
    }

    @Override
    public void onYesClicked() {
        changePassword();
    }
}