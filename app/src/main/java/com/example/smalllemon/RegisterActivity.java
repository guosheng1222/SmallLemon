package com.example.smalllemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

public class RegisterActivity extends AppCompatActivity {

    private TextView jumpLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        jumpLoginActivity = (TextView) findViewById(R.id.jumpLoginActivity);
        jumpLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        autoView();
    }


    private void autoView() {
        AutoUtils.auto(findViewById(R.id.auto_1));
        AutoUtils.auto(findViewById(R.id.auto_2));
    }
}
