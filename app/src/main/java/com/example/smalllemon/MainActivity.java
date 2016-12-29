package com.example.smalllemon;

import android.Manifest;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.base.BaseData;
import com.example.fragment.CommunityFragment;
import com.example.fragment.HomePageFragment;
import com.example.fragment.MineFragment;
import com.example.utils.CommonUtils;
import com.example.view.NoScrollViewPager;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private int blackColor;
    private String data;
    private int grayColor;

    private ArrayList<Fragment> fragmentList;
    private Drawable[] drawables;

    private NoScrollViewPager main_vp;
    private RadioGroup main_rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        blackColor = CommonUtils.getResourseColor(R.color.colorBlackText);
        grayColor = CommonUtils.getResourseColor(R.color.colorGrayText);

        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //对main_vp的操作
        initMainVp();
        //对main_rg的操作
        initMainRg();
        //请求权限测试
//        requestPerssion();
        //获取数据测试
        //有网网络获取成功
        //无网网络获取    --提示跳转wifi设置界面
        initData();
    }

    private void initData() {
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                MainActivity.this.data = data;
            }
        }.getDataForGet(this, "http://www.baidu.com", BaseData.NO_TIME);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(data)) {
            initData();
        }
    }

    private void requestPerssion() {
        requestPermission(0, new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA}, new Runnable() {
            //将要执行的逻辑
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "权限被同意了", Toast.LENGTH_SHORT).show();
            }
        });
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
        //主页面承载容器
        main_vp = (NoScrollViewPager) findViewById(R.id.main_vp);
        //全部加载出来。避免多次创建
        main_vp.setOffscreenPageLimit(2);

        //下方radioButton承载容器
        main_rg = (RadioGroup) findViewById(R.id.main_rg);
        //定义RadioButton数组用来装RadioButton，改变drawableTop大小
        for (int i = 0; i < main_rg.getChildCount(); i++) {
            AutoUtils.auto(main_rg.getChildAt(i));
            drawables = ((RadioButton) main_rg.getChildAt(i)).getCompoundDrawables();
            Rect r = new Rect(0, 0, drawables[1].getMinimumWidth() / 2, drawables[1].getMinimumHeight() / 2);
            //定义一个Rect边界
            drawables[1].setBounds(r);
            ((RadioButton) main_rg.getChildAt(i)).setCompoundDrawables(null, drawables[1], null, null);
        }

    }

}
