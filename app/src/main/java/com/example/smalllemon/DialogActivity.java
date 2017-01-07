package com.example.smalllemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.app.MyApplication;
import com.example.utils.DBUtils;

import org.xutils.ex.DbException;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogActivity.this.finish();
            }
        });
        findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DBUtils.getDb().delete(MyApplication.CURRENT_USER);
                    MyApplication.CURRENT_USER = null;
                    Intent intent = new Intent(DialogActivity.this, LoginActivity.class);
                    DialogActivity.this.startActivity(intent);
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
