package com.example.smalllemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.zhy.autolayout.AutoRelativeLayout;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoRelativeLayout update_password,invite_friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting);
        //修改密码
        update_password = (AutoRelativeLayout) findViewById(R.id.update_password);
        //邀请好友
        invite_friend = (AutoRelativeLayout) findViewById(R.id.invite_friend);
        update_password.setOnClickListener(this);
        invite_friend.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //返回
            case R.id.return_back:
                finish();
                break;
            //跳转修改密码界面
            case R.id.update_password:
                enterActivity(SettingActivity.this,UpdatePasswordActivity.class);
                break;
            //跳转邀请好友界面
            case R.id.invite_friend:
                enterActivity(SettingActivity.this,InviteFriendActivity.class);
                break;
        }
    }

    //跳转
    private void enterActivity(SettingActivity settingActivity, Class c) {
        Intent intent=new Intent(settingActivity,c);
        startActivity(intent);
    }
}
