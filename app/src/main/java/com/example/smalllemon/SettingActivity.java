package com.example.smalllemon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.zhy.autolayout.AutoRelativeLayout;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private AutoRelativeLayout update_password, invite_friend, about_us, version_update;
    private Button button_exist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting);
        //修改密码
        update_password = (AutoRelativeLayout) findViewById(R.id.update_password);
        //邀请好友
        invite_friend = (AutoRelativeLayout) findViewById(R.id.invite_friend);
        //关于我们
        about_us = (AutoRelativeLayout) findViewById(R.id.about_us);
        //版本更新
        version_update = (AutoRelativeLayout) findViewById(R.id.version_update);
        //退出登录
        button_exist = (Button) findViewById(R.id.button_exist);

        update_password.setOnClickListener(this);
        invite_friend.setOnClickListener(this);
        about_us.setOnClickListener(this);
        version_update.setOnClickListener(this);
        button_exist.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //跳转修改密码界面
            case R.id.update_password:
                enterActivity(SettingActivity.this, UpdatePasswordActivity.class);
                break;
            //跳转邀请好友界面
            case R.id.invite_friend:
                enterActivity(SettingActivity.this, InviteFriendActivity.class);
                break;
            //跳转关于我们界面
            case R.id.about_us:
                enterActivity(SettingActivity.this, AboutUsActivity.class);
                break;
            //跳转版本更新界面
            case R.id.version_update:
                Toast.makeText(this, "当前已是最新版本", Toast.LENGTH_SHORT).show();
                break;
            //退出登录
            case R.id.button_exist:

                Intent intent=new Intent(SettingActivity.this,DialogActivity.class);
                startActivity(intent);
                break;

        }
    }

//    public void rotateyAnimRun(Class<DialogActivity> view) {
//        ObjectAnimator
//                .ofFloat(view, "rotationX", -50.f, -30f, -50.0f, -30.0f, 0.0f)
//                .setDuration(1500)
//                .start();
//    }

    //跳转
    private void enterActivity(SettingActivity settingActivity, Class c) {
        Intent intent = new Intent(settingActivity, c);
        startActivity(intent);
    }


}
