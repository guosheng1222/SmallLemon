package com.example.smalllemon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.base.BaseActivity;


public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private AppCompatEditText login_et_phone;
    private AppCompatEditText login_et_password;
    private TextView tv_phone_null;
    private TextView tv_password_null;
    private CheckBox look_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        initView();

    }

    /**
     * 初始化控件
     */
    private void initView() {
        findViewById(R.id.user_login).setOnClickListener(this);
        login_et_phone = (AppCompatEditText) findViewById(R.id.login_et_phone);
        login_et_phone.addTextChangedListener(new Watcher(login_et_phone));
        tv_phone_null = (TextView) findViewById(R.id.tv_phone_null);
        login_et_password = (AppCompatEditText) findViewById(R.id.login_et_password);
        login_et_password.addTextChangedListener(new Watcher(login_et_password));
        tv_password_null = (TextView) findViewById(R.id.tv_password_null);
        look_password = (CheckBox) findViewById(R.id.look_password);
        look_password.setOnClickListener(this);

        findViewById(R.id.tv_forget_password).setOnClickListener(this);
        findViewById(R.id.weiXin_iv).setOnClickListener(this);
        findViewById(R.id.tv_register).setOnClickListener(this);
    }

    /**
     * 各个的点击事件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //登录
            case R.id.user_login:
                getLoginMessage();
                break;
            //微信登录
            case R.id.weiXin_iv:
                break;
            //可见密码
            case R.id.look_password:
                if (look_password.isChecked()) {
                    login_et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    login_et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            //忘记密码
            case R.id.tv_forget_password:

                break;
            //注册
            case R.id.tv_register:
                intentActivity(RegisterActivity.class);
                break;
        }
    }

    /**
     * 跳转注册与忘记密码界面
     */
    public void intentActivity(Class c) {
        Intent intent = new Intent(LoginActivity.this, c);
        startActivity(intent);
    }

    /**
     * 获取登录信息
     */
    private void getLoginMessage() {
        String phone = login_et_phone.getText().toString();
        String password = login_et_password.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            tv_phone_null.setVisibility(View.VISIBLE);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            tv_password_null.setVisibility(View.VISIBLE);
            return;
        } else {
          /*  BaseData baseData = new BaseData() {
                @Override
                public void onSuccessData(String data) {
                    Gson gson = new Gson();
                    LoginMessage loginMessage = gson.fromJson(data, LoginMessage.class);
                    if (loginMessage.isSuccess()) {
                        Toast.makeText(LoginActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, loginMessage.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailData(Exception data) {
                    Toast.makeText(LoginActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();

                }
            };
            baseData.getDataForGet(LoginActivity.this, "http://www.yulin520.com/a2a/home/login/index?", BaseData.NO_TIME);*/
        }
    }

    /**
     * EditText文本变化监听的内部类
     */
    public class Watcher implements TextWatcher {
        private AppCompatEditText appCompatEditTextID = null;

        public Watcher(AppCompatEditText id) {
            appCompatEditTextID = id;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (appCompatEditTextID == login_et_phone) {
                if (!TextUtils.isEmpty(charSequence.toString())) {
                    tv_phone_null.setVisibility(View.INVISIBLE);
                }
            }
            if (appCompatEditTextID == login_et_password) {
                if (!TextUtils.isEmpty(charSequence.toString())) {
                    tv_password_null.setVisibility(View.INVISIBLE);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


}
