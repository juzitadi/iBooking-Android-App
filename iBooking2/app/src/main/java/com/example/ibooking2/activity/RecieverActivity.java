package com.example.ibooking2.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ibooking2.R;

public class RecieverActivity extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciever);

        mTextView=(TextView) findViewById(R.id.tv_reciever);  //创建空间存储后面的text

        //接受所传递的信息
        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("name");
        
        //存储所获取信息
        mTextView.setText("name is "+name);
    }
}