package com.example.ibooking2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ibooking2.R;

public class ForhelpActivity extends AppCompatActivity implements View.OnClickListener {
    private Button feedback_button;
    private Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forhelp);
        initView();
    }

    private void initView() {

        this.feedback_button = findViewById(R.id.get_feedback);
        this.back_button = findViewById(R.id.get_back);

        feedback_button.setOnClickListener(this);
        back_button.setOnClickListener(this);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_back: //返回登录页面
                setResult(1);
                finish();
                break;
            case R.id.get_feedback:
                Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}