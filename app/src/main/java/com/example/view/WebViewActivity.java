package com.example.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.base.BaseActivity;
import com.example.smalllemon.R;

public class WebViewActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton praise;
    private RadioButton share;
    private RadioButton comments;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        initView();

    }

    private void initView() {
        WebView webView = (WebView) findViewById(R.id.web);
        intent = getIntent();
        String Url = intent.getStringExtra("url");
        int pl = intent.getIntExtra("comments", 1);
        int zan = intent.getIntExtra("praise", 1);
        webView.loadUrl(Url);

        ImageView imageView = (ImageView) findViewById(R.id.web_img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //评论
        comments = (RadioButton) findViewById(R.id.web_comments);
        //点赞
        praise = (RadioButton) findViewById(R.id.web_praise);
        //分享
        share = (RadioButton) findViewById(R.id.web_share);
        comments.setText(pl + "");
        praise.setText(zan + "");
        share.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.web_comments:

                break;
            case R.id.web_praise:

                break;
            case R.id.web_share:
                /*Intent shareintent=new Intent(getActivity(),ShareActivity.class);
                startActivity(shareintent);*/
                break;
        }
    }
}
