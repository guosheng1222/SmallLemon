package com.example.smalllemon;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.zhy.autolayout.AutoLinearLayout;

public class FeedBackActivity extends BaseActivity implements View.OnClickListener {

    private TextView hide, title_name;
    private ImageView one;
    private AutoLinearLayout select_one, select_two, select_three, select_four, select_five;
    private EditText edit_text_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_feed_back);

        //显示完成文字并点击进行更改
        hide = (TextView) findViewById(R.id.hide);
        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText("意见反馈");
        //提交
        hide.setVisibility(View.VISIBLE);
        hide.setTextSize(20);
        hide.setTextColor(Color.GRAY);
        hide.setText("提交");

        //布局控件
        select_one = (AutoLinearLayout) findViewById(R.id.select_one);
        select_two = (AutoLinearLayout) findViewById(R.id.select_two);
        select_three = (AutoLinearLayout) findViewById(R.id.select_three);
        select_four = (AutoLinearLayout) findViewById(R.id.select_four);
        select_five = (AutoLinearLayout) findViewById(R.id.select_five);

        select_one.setOnClickListener(this);
        select_two.setOnClickListener(this);
        select_three.setOnClickListener(this);
        select_four.setOnClickListener(this);
        select_five.setOnClickListener(this);

        //输入手机号/微信号/QQ
        edit_text_contact = (EditText) findViewById(R.id.edit_text_contact);



    }

    private void setVisibility(int position) {
        AutoLinearLayout requestion = (AutoLinearLayout) findViewById(R.id.requestion_type);
        for (int i = 0; i < requestion.getChildCount(); i++) {
            View childAt = ((LinearLayout) requestion.getChildAt(i)).getChildAt(1);
            if (i == position) {
                childAt.setVisibility(View.VISIBLE);
            } else {
                childAt.setVisibility(View.INVISIBLE);
            }

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_one:
                setVisibility(0);
                break;
            case R.id.select_two:
                setVisibility(1);
                break;
            case R.id.select_three:
                setVisibility(2);
                break;
            case R.id.select_four:
                setVisibility(3);
                break;
            case R.id.select_five:
                setVisibility(4);
                break;
        }
    }
}
