package com.example.smalllemon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.BaseActivity;

public class UpdatePasswordActivity extends BaseActivity implements View.OnClickListener {

    private TextView hide,title_name;
    private ImageView return_back;
    private EditText old_password,new_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_update_password);

        //显示完成文字并点击进行更改
        hide = (TextView) findViewById(R.id.hide);
        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText("修改密码");
        hide.setVisibility(View.VISIBLE);
        hide.setText("完成");
        //返回
        return_back = (ImageView) findViewById(R.id.return_back);
        //旧密码
        old_password = (EditText) findViewById(R.id.old_password);
        //新密码
        new_password = (EditText) findViewById(R.id.new_password);
        //点击事件
        hide.setOnClickListener(this);
        return_back.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.return_back:
                finish();
                break;
            //完成
            case R.id.hide:
                break;
        }

    }
}
