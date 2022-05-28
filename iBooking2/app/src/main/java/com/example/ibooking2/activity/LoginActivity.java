package com.example.ibooking2.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
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
import com.example.ibooking2.object.User;

import java.util.ArrayList;
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private DBOpenHelper mDBOpenHelper;
    private Button mBtLoginactivityRegister;
    private EditText mEtLoginactivityUsername;
    private EditText mEtLoginactivityPassword;
    private Button mBtLoginactivityLogin;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        mDBOpenHelper = new DBOpenHelper(this);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked)  //取消***
                {
                    mEtLoginactivityPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else  //设置上***
                {
                    mEtLoginactivityPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

                mEtLoginactivityPassword.setSelection(mEtLoginactivityPassword.getText().length());  //光标保证在最后
            }
        });
    }
    private void initView() {
        // 初始化控件
        mBtLoginactivityLogin = findViewById(R.id.bt_loginactivity_login);
        mBtLoginactivityRegister = findViewById(R.id.bt_loginactivity_register);
        mEtLoginactivityUsername = findViewById(R.id.et_loginactivity_username);
        mEtLoginactivityPassword = findViewById(R.id.et_loginactivity_password);
        mEtLoginactivityPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        checkBox=findViewById(R.id.check);

        // 设置点击事件监听器
        mBtLoginactivityLogin.setOnClickListener(this);
        mBtLoginactivityRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            // 跳转到注册界面
            case R.id.bt_loginactivity_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
            case R.id.bt_loginactivity_login:
                String name = mEtLoginactivityUsername.getText().toString().trim();
                String password = mEtLoginactivityPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mDBOpenHelper.getAllData();
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        /// 保存当前登录人userName
                        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();//获取编辑器
                        editor.putString("loginName",name);
                        editor.putString(name, password);
                        editor.commit();

                        Toast.makeText(this, "Log in successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();//销毁此Activity
                    } else {
                        Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please enter your account", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
