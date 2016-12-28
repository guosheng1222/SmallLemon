package com.example.smalllemon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.adapter.LunchPageAdapter;
import com.example.base.BaseActivity;

public class LunchActivity extends BaseActivity {
    private int[] pages = {R.mipmap.launch_page1, R.mipmap.launch_page2, R.mipmap.launch_page3, R.mipmap.launch_page4};
    private SharedPreferences sharedPreferences;
    private ViewPager viewPager;
    private boolean isFirst = false;
    private RadioGroup launch_radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        viewPager = (ViewPager) findViewById(R.id.lunch_vp);
        launch_radioGroup = (RadioGroup) findViewById(R.id.lunch_radioGroup);
        sharedPreferences = getSharedPreferences("page", MODE_PRIVATE);
        isFirst = sharedPreferences.getBoolean("IsFirst", true);
        if (isFirst) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("IsFirst", false);
            edit.commit();
            viewPager.setAdapter(new LunchPageAdapter(pages, this));
            setRadioButton();
        } else {
            jump();
        }
    }

    /**
     * 设置滑动小点
     */
    private void setRadioButton() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < launch_radioGroup.getChildCount(); i++) {
                    RadioButton childAt = (RadioButton) launch_radioGroup.getChildAt(i);
                    if (i == position) {
                        childAt.setChecked(true);
                    } else {
                        childAt.setChecked(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        launch_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                    if (i == radioGroup.getChildAt(j).getId()) {
                        viewPager.setCurrentItem(j);
                    }
                }
            }
        });
    }

    /**
     * 跳转登陆界面
     */
    private void jump() {
        Intent in = new Intent(LunchActivity.this, MainActivity.class);
        startActivity(in);
        finish();
    }
}
