package com.example.smalllemon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.MyApplication;
import com.example.base.BaseData;
import com.example.utils.DBUtils;
import com.example.utils.MD5Utils;

import org.xutils.ex.DbException;

import java.util.HashMap;

import de.greenrobot.event.EventBus;


public class UpdateNickNameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView hide, title_name;
    private EditText et_nickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_update_nick_name);
        hide = (TextView) findViewById(R.id.hide);
        et_nickName = (EditText) findViewById(R.id.et_nickName);
        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText("修改昵称");
        hide.setVisibility(View.VISIBLE);
        hide.setText("完成");
        //修改昵称

        hide.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hide:

                final String nickName = et_nickName.getText().toString().trim();
                HashMap<String, String> requestMap = new HashMap<>();
                int l = (int) System.currentTimeMillis();
                requestMap.put("token", MyApplication.TOKEN);
                requestMap.put("userName", MD5Utils.MD5(nickName));
                requestMap.put("birthday", "");
                requestMap.put("ts", l + "");
                requestMap.put("sign", MD5Utils.MD5(l + "GOyV3qmT)CR5!Gee'zAj@7W"));
                new BaseData() {
                    @Override
                    public void onSuccessData(String data) {
                        Toast.makeText(UpdateNickNameActivity.this, "修改数据成功", Toast.LENGTH_SHORT).show();
                        MyApplication.CURRENT_USER.setUserName(nickName);
                        try {
                            DBUtils.getDb().saveOrUpdate(MyApplication.CURRENT_USER);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                        EventBus.getDefault().post(MyApplication.CURRENT_USER.getUserName());

                    }

                    @Override
                    public void onErrorData(String data) {
                        Toast.makeText(UpdateNickNameActivity.this, "错误请求", Toast.LENGTH_SHORT).show();
                    }
                }.getDataByPost(this, "http://www.yulin520.com/a2a/home/info/change/userInfo", requestMap);

                break;
        }
    }
}
