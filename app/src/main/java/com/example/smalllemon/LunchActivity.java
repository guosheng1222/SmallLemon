package com.example.smalllemon;

import android.content.Intent;
import android.os.Bundle;

import com.example.base.BaseActivity;

public class LunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
    }
    /**
     * 跳转登陆界面
     */
    private void jump() {
        Intent in = new Intent(LunchActivity.this, LoginActivity.class);
        startActivity(in);
    }
}
