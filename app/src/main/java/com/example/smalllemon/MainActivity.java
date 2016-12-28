package com.example.smalllemon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.base.BaseActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ViewPager vp;
    private ArrayList<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.main_vp);
    }
}
