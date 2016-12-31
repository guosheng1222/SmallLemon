package com.example.smalllemon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;

public class LoginForgetActivity extends AppCompatActivity {

    private AppCompatEditText activity_rigster_phone;
    private AppCompatEditText activity_rigister_verification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forget);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        activity_rigster_phone = (AppCompatEditText) findViewById(R.id.activity_rigster_phone);
        activity_rigister_verification = (AppCompatEditText) findViewById(R.id.activity_rigister_verification);
    }
}
