package com.example.smalllemon;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.app.MyApplication;
import com.example.base.BaseActivity;
import com.example.base.BaseData;
import com.example.fragment.CommunityFragment;
import com.example.fragment.HomePageFragment;
import com.example.fragment.MineFragment;
import com.example.utils.CommonUtils;
import com.example.utils.DBUtils;
import com.example.view.NoScrollViewPager;
import com.zhy.autolayout.utils.AutoUtils;

import org.xutils.ex.DbException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static android.R.attr.bitmap;

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
//        initData();
    }

    private void initData() {
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                MainActivity.this.data = data;
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        }.getDataForGet(this, "https://www.baidu.com", BaseData.NO_TIME);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(data)) {
         /*   initData();*/
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
        //查找下方导航
        main_rg = (RadioGroup) findViewById(R.id.main_rg);
        //手动适配
        for (int i = 0; i < main_rg.getChildCount(); i++) {
            AutoUtils.auto(main_rg.getChildAt(i));
        }

        /*//下方radioButton承载容器
        //定义RadioButton数组用来装RadioButton，改变drawableTop大小
        for (int i = 0; i < main_rg.getChildCount(); i++) {
            AutoUtils.auto(main_rg.getChildAt(i));
            drawables = ((RadioButton) main_rg.getChildAt(i)).getCompoundDrawables();
            Rect r = new Rect(0, 0, drawables[1].getMinimumWidth() / 2, drawables[1].getMinimumHeight() / 2);
            //定义一个Rect边界
            drawables[1].setBounds(r);
            ((RadioButton) main_rg.getChildAt(i)).setCompoundDrawables(null, drawables[1], null, null);
        }*/

    }

    private long mPressedTime = 0;

    /**
     * 双击退出应用
     */
    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if ((mNowTime - mPressedTime) > 2000) {//比较两次按键时间差
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        } else {//退出程序
            this.finish();
        }
    }


    /**
     * 相册回调
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("TAG", "------");
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:
                    /*Uri uri = data.getData();
                    Cursor cursor = this.getContentResolver().query(uri, null,
                            null, null, null);
                    cursor.moveToFirst();
                    String imgNo = cursor.getString(0); // 图片编号
                    String imgPath = cursor.getString(1); // 图片文件路径
                    String imgSize = cursor.getString(2); // 图片大小
                    String imgName = cursor.getString(3); // 图片文件名
                    cursor.close();
                    Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
                    Log.i("TAG", "------" + imgPath);*/

                    ContentResolver contentResolver = getContentResolver();
                    try {

                        Bitmap bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(data.getData()));
                        Log.i("TAG", "从相册回传bitmap："+bitmap);



                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                    Toast.makeText(MainActivity.this, "---" + bitmap, Toast.LENGTH_SHORT).show();
                    break;

                default:
                    return;
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            DBUtils.getDb().saveOrUpdate(MyApplication.CURRENT_USER);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
