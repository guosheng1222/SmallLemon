package com.example.smalllemon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;

public class EmotionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView hide,title_name;
    private AutoLinearLayout single;
    private AutoLinearLayout love;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_emotion);

        hide = (TextView) findViewById(R.id.hide);
        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText("情感状况");
        hide.setVisibility(View.VISIBLE);
        hide.setText("完成");
        //单身期
        single = (AutoLinearLayout) findViewById(R.id.single);
        //热恋期
        love = (AutoLinearLayout) findViewById(R.id.love);

        single.setOnClickListener(this);
        love.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.single:
                setVisibility(0);
                break;
            case R.id.love:
                setVisibility(1);
                break;
        }
    }

    private void setVisibility(int position) {
        AutoLinearLayout requestion = (AutoLinearLayout) findViewById(R.id.fall_in_love);
        for (int i = 0; i < requestion.getChildCount(); i++) {
            View childAt = ((LinearLayout) requestion.getChildAt(i)).getChildAt(1);
            if (i == position) {
                childAt.setVisibility(View.VISIBLE);
            } else {
                childAt.setVisibility(View.INVISIBLE);
            }

        }
    }
}
