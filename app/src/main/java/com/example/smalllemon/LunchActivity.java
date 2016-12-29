package com.example.smalllemon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.example.base.BaseActivity;

public class LunchActivity extends BaseActivity {
    private final String isFirst = "isFirst";
    private int[] pages = {R.mipmap.launch_page1, R.mipmap.launch_page2, R.mipmap.launch_page3, R.mipmap.launch_page4};
    private SharedPreferences sharedPreferences;
    private ViewPager viewPager;
    private RadioGroup launch_radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("page", MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(isFirst, true)) {     //非第一次登陆直接,直接跳转
            jump();
        } else {        //第一次登陆
            setContentView(R.layout.activity_lunch);
            initViewPage();
            setRadioButton();
        }
    }

    /**
     * 初始化viewPage
     */
    private void initViewPage() {
        viewPager = (ViewPager) findViewById(R.id.lunch_vp);
        viewPager.setAdapter(new LunchPageAdapter());
    }


    /**
     * 设置滑动小点
     */
    private void setRadioButton() {
        launch_radioGroup = (RadioGroup) findViewById(R.id.lunch_radioGroup);
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


    /**
     * @author : 张鸿鹏
     * @date : 2016/12/28.
     */
    class LunchPageAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return pages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View inflate = View.inflate(LunchActivity.this, R.layout.launch_item, null);
            ImageView launch_iv = (ImageView) inflate.findViewById(R.id.launch_iv);
            ImageView launch_iv2 = (ImageView) inflate.findViewById(R.id.launch_iv2);
            Glide.with(LunchActivity.this).load(pages[position]).into(launch_iv);
            for (int i = 0; i < pages.length; i++) {
                if (position == pages.length - 1) {
                    launch_iv2.setVisibility(View.VISIBLE);
                    launch_iv2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setNotFirstUse();
                            jump();
                        }
                    });
                }
            }
            container.addView(inflate);
            return inflate;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }


        //设置非第一次登陆
        private void setNotFirstUse() {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(isFirst, false);
            edit.apply();
        }

    }
}
