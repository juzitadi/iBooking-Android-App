package com.example.ibooking2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ibooking2.R;
import com.example.ibooking2.database.DBOpenHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private DBOpenHelper mDBOpenHelper;
    private Button mBtRegisteractivityRegister;
    private Button mIvRegisteractivityBack;
    private EditText mEtRegisteractivityUsername;
    private EditText mEtRegisteractivityPassword1;
    private EditText mEtRegisteractivityPassword2;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        mEtRegisteractivityUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String name =mEtRegisteractivityUsername.getText().toString();
                if(name.length()<4){
                    Toast.makeText(RegisterActivity.this,"Username can't less than 4",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mEtRegisteractivityPassword1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String pwd =mEtRegisteractivityPassword1.getText().toString();
                if(pwd.length()<6 || pwd.length()>12){
                    Toast.makeText(RegisterActivity.this,"Password length should between 6 and 12",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mEtRegisteractivityPassword2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String pwd =mEtRegisteractivityPassword2.getText().toString();
                if(pwd.length()<6 || pwd.length()>12){
                    Toast.makeText(RegisterActivity.this,"Password length should between 6 and 12",Toast.LENGTH_SHORT).show();
                }
                if(!mEtRegisteractivityPassword1.getText().toString().equals(mEtRegisteractivityPassword2.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"Different entered password, try again",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        mDBOpenHelper = new DBOpenHelper(this);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked)  //取消***
                {
                    mEtRegisteractivityPassword1.setTransformationMethod(null);
                }
                else  //设置上***
                {
                    mEtRegisteractivityPassword1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

                mEtRegisteractivityPassword1.setSelection(mEtRegisteractivityPassword1.getText().length());  //光标保证在最后

            }
        });


    }

    private void initView(){
        mBtRegisteractivityRegister = findViewById(R.id.bt_registeractivity_register);
        mIvRegisteractivityBack = findViewById(R.id.iv_registeractivity_back);
        mEtRegisteractivityUsername = findViewById(R.id.et_registeractivity_username);
        mEtRegisteractivityPassword1 = findViewById(R.id.et_registeractivity_password1);
        mEtRegisteractivityPassword1.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mEtRegisteractivityPassword2 = findViewById(R.id.et_registeractivity_password2);
        checkBox=findViewById(R.id.check);



        mIvRegisteractivityBack.setOnClickListener(this);

        mBtRegisteractivityRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_registeractivity_back: //返回登录页面
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.bt_registeractivity_register:    //注册按钮
                //获取用户输入的用户名、密码、验证码
                String username = mEtRegisteractivityUsername.getText().toString().trim();
                String password = mEtRegisteractivityPassword1.getText().toString().trim();

                //注册验证
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)  ) {

                    //将用户名和密码加入到数据库中
                    mDBOpenHelper.add(username, password);
                    Intent intent2 = new Intent(this, LoginActivity.class);
                    startActivity(intent2);
                    finish();
                    Toast.makeText(this,  "Register successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Not finish all information, register fail", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
