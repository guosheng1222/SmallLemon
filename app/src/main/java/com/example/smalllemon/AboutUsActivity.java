package com.example.smalllemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.zhy.autolayout.AutoLinearLayout;

public class AboutUsActivity extends BaseActivity {

    private TextView title_name;
    private AutoLinearLayout welcome_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about_us);

        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText("关于我们");
        //欢迎页
        welcome_page = (AutoLinearLayout) findViewById(R.id.welcome_page);


    }
}
