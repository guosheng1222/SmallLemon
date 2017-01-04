package com.example.smalllemon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.base.BaseData;
import com.example.bean.RegisterMessage;
import com.example.bean.VerificationCode;
import com.example.utils.IsPhoneLegal;
import com.example.utils.UrlUtils;
import com.google.gson.Gson;
import com.zhy.autolayout.utils.AutoUtils;

import static com.example.base.BaseData.NO_TIME;
import static com.example.smalllemon.R.id.activity_rigister_verification;
import static com.example.smalllemon.R.id.activity_rigister_verification_tv;
import static com.example.smalllemon.R.id.activity_rigster_phone;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatEditText etPhone;
    private AppCompatEditText verificationCode;
    private Button btCode;
    private AppCompatEditText login_et_password;
    private CheckBox look_password;
    private CheckBox cbAgreement;
    private int time = 60;
    private int isMorePoint = 0;
    private static final int ZERO = 0;
    private static final int ONE = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    time--;
                    btCode.setText(time + "秒后重发");
                    this.sendEmptyMessageDelayed(0, 1000);
                    btCode.setTextColor(Color.WHITE);
                    if (time == 0) {
                        this.removeCallbacksAndMessages(null);
                        btCode.setText("获取验证码");
                        time = 60;
                    }
                    break;
                case 1:
                    cardView_code.setCardBackgroundColor(Color.parseColor("#2B2B2B"));
                    toastMessage("收不到短信?屏蔽验证码。");
                    break;
            }


        }
    };
    private CardView cardView_code;

    private TextView jumpLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        cardView_code = (CardView) findViewById(R.id.cardView_Code);
        etPhone = (AppCompatEditText) findViewById(activity_rigster_phone);
        verificationCode = (AppCompatEditText) findViewById(activity_rigister_verification);
        btCode = (Button) findViewById(activity_rigister_verification_tv);
        btCode.setOnClickListener(this);
        login_et_password = (AppCompatEditText) findViewById(R.id.login_et_password);
        look_password = (CheckBox) findViewById(R.id.look_password);
        look_password.setOnClickListener(this);
        cbAgreement = (CheckBox) findViewById(R.id.agreement_chebox);
        cbAgreement.setOnClickListener(this);
        findViewById(R.id.user_login_register).setOnClickListener(this);
        findViewById(R.id.jump_login).setOnClickListener(this);
        autoView();
    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //注册
            case R.id.user_login_register:
                getRegisterMessage();
                break;
            //获取验证码
            case activity_rigister_verification_tv:
                getCode();
                break;
            //看密码
            case R.id.look_password:
                if (look_password.isChecked()) {
                    login_et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    login_et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            //跳转登录界面
            case R.id.jump_login:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    /**
     * 获得注册信息
     */
    private void getRegisterMessage() {
        final String verification = verificationCode.getText().toString();
        if (TextUtils.isEmpty(getPhone())) {
            toastMessage("请输入手机号码");
        } else if (!TextUtils.isEmpty(getPhone()) && !IsPhoneLegal.isPhoneLegal(getPhone())) {
            toastMessage("手机号码格式不对");
        } else if (TextUtils.isEmpty(verification)) {
            toastMessage("请输入验证码");
        } else if (TextUtils.isEmpty(getPassword()) || getPassword().length() < 6) {
            toastMessage("密码不能小于6位哦!");
        } else if (!cbAgreement.isChecked()) {
            toastMessage("请同意用户协议哦!");
        } else {
             /*验证用户短信是否正确*/
           /* new BaseData() {
               @Override
               public void onSuccessData(String data) {
                   VerificationCode verificationCode = new Gson().fromJson(data, VerificationCode.class);
                   switch (verificationCode.getStatus()) {
                       case "ok":
                           toastMessage("验证码验证成功，发送注册请求");
                           break;
                       case "error":
                           toastMessage(verificationCode.getData().getMessage());
                           break;
                   }
               }
           }.getDataForGet(RegisterActivity.this, "http://114.112.104.151:8203/LvScore_Service/visit/user_checkVerificationCode.do?telNum=" + getPhone() + "&verCode=" + verification, BaseData.NO_TIME);*/
            //创建volley对象
            RequestQueue mQueue = Volley.newRequestQueue(this);
            //创建请求
            StringRequest stringRequest = new StringRequest("http://114.112.104.151:8203/LvScore_Service/visit/user_checkVerificationCode.do?telNum=" + getPhone() + "&verCode=" + verification,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            VerificationCode verificationCode = new Gson().fromJson(response, VerificationCode.class);
                            switch (verificationCode.getStatus()) {
                                case "ok":
                                    saveUserMessage(getPhone(), getPassword());
                                    break;
                                case "error":
                                    toastMessage(verificationCode.getData().getMessage());
                                    break;
                            }
                        }
                    }
                    , new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    toastMessage("链接超时");
                }
            }
            );
            //加入请求队列
            mQueue.add(stringRequest);
        }
    }

    /**
     * 保存用户信息到服务器
     */
    private void saveUserMessage(final String phone, String password) {
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                RegisterMessage registerMessage = new Gson().fromJson(data, RegisterMessage.class);
                String image_url = registerMessage.getData().getImage_url();
                Log.i("A", "onSuccessData: 头像:" + image_url + " 用户手机:" + registerMessage.getData().getPhone());
                toastMessage("注册成功");
            }
        }.getDataForGet(RegisterActivity.this, "http://114.112.104.151:8203/LvScore_Service/visit/user_register.do?telNum=" + phone + "&name=godboy&password=" + password, NO_TIME);
    }

    /**
     * 获取验证码
     */
    private void getCode() {
        if (!TextUtils.isEmpty(getPhone()) && !IsPhoneLegal.isPhoneLegal(getPhone())) {
            toastMessage("手机号码格式不对");
        } else if (TextUtils.isEmpty(getPhone())) {
            toastMessage("请输入手机号码");
        } else {
            if (time == 60) {
                handler.sendEmptyMessageDelayed(ZERO, 1000);
            }
            isMorePoint++;
            if (isMorePoint < 3) {
                  /*获取验证码*/
                new BaseData() {
                    @Override
                    public void onSuccessData(String data) {
                        VerificationCode verificationCode = new Gson().fromJson(data, VerificationCode.class);
                        switch (verificationCode.getStatus()) {
                            //正确
                            case "ok":
                                toastMessage("已发送至手机");
                                break;
                            //错误
                            case "error":
                                toastMessage(verificationCode.getData().getMessage());
                                break;
                        }
                    }
                }.getDataForGet(RegisterActivity.this, UrlUtils.getCode + getPhone(), NO_TIME);
            } else {//收不到短信
                handler.obtainMessage(ONE).sendToTarget();
            }

        }
    }

    /**
     * 获取用户的手机号
     */
    public String getPhone() {
        return etPhone.getText().toString().trim();
    }

    public String getPassword() {
        return login_et_password.getText().toString().trim();
    }


    /**
     * 吐司信息
     */
    public void toastMessage(String data) {
        Toast.makeText(RegisterActivity.this, data, Toast.LENGTH_SHORT).show();
    }

    private void autoView() {
        AutoUtils.auto(findViewById(R.id.cardView_Code));
        AutoUtils.auto(findViewById(R.id.user_login_register));
    }
}
