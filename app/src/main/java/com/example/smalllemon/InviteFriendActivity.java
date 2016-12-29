package com.example.smalllemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.zhy.autolayout.AutoLinearLayout;

public class InviteFriendActivity extends BaseActivity {

    private AutoLinearLayout share_qq,share_qqzone,share_wechat,share_friend,share_weibo;
    private TextView title_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_invite_friend);

        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText("邀请朋友");

        //空间分享
        share_qqzone = (AutoLinearLayout) findViewById(R.id.share_qqzone);
        //QQ分享
        share_qq = (AutoLinearLayout) findViewById(R.id.share_qq);
        //微信分享
        share_wechat = (AutoLinearLayout) findViewById(R.id.share_wechat);
        //朋友圈分享
        share_friend = (AutoLinearLayout) findViewById(R.id.share_friend);
        //微博分享
        share_weibo = (AutoLinearLayout) findViewById(R.id.share_weibo);

    }
}
