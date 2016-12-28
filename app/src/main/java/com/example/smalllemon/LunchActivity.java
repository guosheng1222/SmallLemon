package com.example.smalllemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class LunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
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
