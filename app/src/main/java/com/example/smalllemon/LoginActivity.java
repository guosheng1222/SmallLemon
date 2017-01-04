package com.example.smalllemon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.base.BaseData;
import com.example.bean.RegisterMessage;
import com.google.gson.Gson;
import com.zhy.autolayout.utils.AutoUtils;


public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private AppCompatEditText login_et_phone;
    private AppCompatEditText login_et_password;
    private TextView tv_phone_null;
    private TextView tv_password_null;
    private CheckBox look_password;
    private ImageView weiXin_iv;
    private View login_anim;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //成功
                    login_anim.findViewById(R.id.loadView).setVisibility(View.GONE);
                    login_anim.findViewById(R.id.login_Successful).setVisibility(View.VISIBLE);
                    intentActivity(MainActivity.class);
                    finish();
                    break;
                case 1:
                    //失败
                    String message = (String) msg.obj;
                    login_anim.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    /**
     * 开始登录的缩放动画
     */
    public void startAnim() {
        //动画隐藏
        login_anim.setVisibility(View.VISIBLE);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
        scaleAnimation.setDuration(500);
        login_anim.startAnimation(scaleAnimation);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        //动画的一些控件
        login_anim = findViewById(R.id.login_anim);
        login_anim.setVisibility(View.GONE);

        login_et_phone = (AppCompatEditText) findViewById(R.id.login_et_phone);
        login_et_phone.addTextChangedListener(new Watcher(login_et_phone));
        tv_phone_null = (TextView) findViewById(R.id.tv_phone_null);
        weiXin_iv = (ImageView) findViewById(R.id.weiXin_iv);
        login_et_password = (AppCompatEditText) findViewById(R.id.login_et_password);
        login_et_password.addTextChangedListener(new Watcher(login_et_password));
        tv_password_null = (TextView) findViewById(R.id.tv_password_null);
        look_password = (CheckBox) findViewById(R.id.look_password);
        look_password.setOnClickListener(this);
        AutoUtils.auto(findViewById(R.id.auto_1));
        AutoUtils.auto(weiXin_iv);


        findViewById(R.id.user_login).setOnClickListener(this);
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
                intentActivity(LoginForgetActivity.class);
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
        } else if (TextUtils.isEmpty(password)) {
            tv_password_null.setVisibility(View.VISIBLE);
        } else {
            //动画显示登录界面隐藏
            startAnim();
            new BaseData() {
                @Override
                public void onSuccessData(String data) {
                    RegisterMessage registerMessage = new Gson().fromJson(data, RegisterMessage.class);
                    switch (registerMessage.getStatus()) {
                        //成功
                        case "ok":
                            //登录成功
                            handler.sendEmptyMessageDelayed(0, 3000);
                            break;
                        //失败
                        case "error":
                            Message message = handler.obtainMessage();
                            message.what = 1;
                            message.obj = registerMessage.getData().getMessage();
                            handler.sendMessageDelayed(message, 2000);
                            break;
                    }
                }
            }.getDataForGet(LoginActivity.this, "http://114.112.104.151:8203/LvScore_Service/visit/user_login.do?telNum=" + phone + "&password=" + password, BaseData.NO_TIME);
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
