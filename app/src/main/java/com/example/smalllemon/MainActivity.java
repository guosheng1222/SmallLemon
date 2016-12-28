package com.example.smalllemon;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.base.BaseActivity;
import com.example.fragment.CommunityFragment;
import com.example.fragment.HomePageFragment;
import com.example.fragment.MineFragment;
import com.example.util.CommonUtils;
import com.example.view.NoScrollViewPager;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private NoScrollViewPager main_vp;
    private RadioGroup main_rg;
    private ArrayList<Fragment> fragmentList;
    private int blackColor;
    private int grayColor;
    private RadioButton[] rb;
    private Drawable[] drawables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSystemBar(this);
        blackColor = CommonUtils.getResourseColor(R.color.colorBlackText);
        grayColor = CommonUtils.getResourseColor(R.color.colorGrayText);

        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //对main_vp的操作
        initMainVp();

        //对main_rg的操作
        initMainRg();

    }

    /**
     * 对RadioGroup的操作
     */

    private void initMainRg() {

        main_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                for (int j = 0; j < main_rg.getChildCount(); j++) {
                    RadioButton childRb = (RadioButton) main_rg.getChildAt(j);
                    if (childRb.getId() == checkId) {
                        main_vp.setCurrentItem(j);
                        childRb.setTextColor(blackColor);
                        childRb.setChecked(true);
                    } else {
                        childRb.setTextColor(grayColor);
                        childRb.setChecked(false);
                    }
                }
            }
        });


    }

    /**
     * 对main_vp进行的一些操作
     */
    private void initMainVp() {
        //专门装Fragment的集合
        fragmentList = new ArrayList<>();
        //new 出Fragment并添加到集合中
        if (fragmentList.size() == 0) {
            HomePageFragment homePageFragment = new HomePageFragment();
            CommunityFragment communityFragment = new CommunityFragment();
            MineFragment mineFragment = new MineFragment();
            fragmentList.add(homePageFragment);
            fragmentList.add(communityFragment);
            fragmentList.add(mineFragment);
        }

        //为main_vp设置适配器
        main_vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

    }

    /**
     * 初始化控件
     */
    private void initView() {
        //定义RadioButton数组用来装RadioButton，改变drawableTop大小
        rb = new RadioButton[3];
        main_vp = (NoScrollViewPager) findViewById(R.id.main_vp);
        main_rg = (RadioGroup) findViewById(R.id.main_rg);
        rb[0] = (RadioButton) findViewById(R.id.rb_home_page);
        rb[1] = (RadioButton) findViewById(R.id.rb_community);
        rb[2] = (RadioButton) findViewById(R.id.rb_mine);

        for (RadioButton childRb : rb) {
            //挨着给每个RadioButton加入drawable限制边距以控制显示大小
            drawables = childRb.getCompoundDrawables();
            //获取drawables
            Rect r = new Rect(0, 0, drawables[1].getMinimumWidth() * 2 / 5, drawables[1].getMinimumHeight() * 2 / 5);
            //定义一个Rect边界
            drawables[1].setBounds(r);


            childRb.setCompoundDrawables(null, drawables[1], null, null);
        }

    }

}
