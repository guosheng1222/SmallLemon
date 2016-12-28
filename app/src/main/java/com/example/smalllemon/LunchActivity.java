package com.example.smalllemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LunchActivity extends AppCompatActivity {

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
