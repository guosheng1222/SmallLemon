package com.example.smalllemon;

import android.os.Bundle;

import com.example.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSystemBar(this);
        setContentView(R.layout.activity_main);

    }
}
