package com.example.smalllemon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateNickNameActivity extends AppCompatActivity {

    private TextView hide,title_name;
    private EditText nickname_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_update_nick_name);

        hide = (TextView) findViewById(R.id.hide);
        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText("修改昵称");
        hide.setVisibility(View.VISIBLE);
        hide.setText("完成");
        //修改昵称
        nickname_edit_text = (EditText) findViewById(R.id.nickname_edit_text);

    }
}
