package com.example.fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.app.MyApplication;
import com.example.base.BaseActivity;
import com.example.bean.LoginBean;
import com.example.smalllemon.BasicDocumentActivity;
import com.example.smalllemon.FeedBackActivity;
import com.example.smalllemon.R;
import com.example.smalllemon.SettingActivity;
import com.example.utils.CommonUtils;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.io.FileNotFoundException;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

import static android.app.Activity.RESULT_OK;

/**
 * Created by lenovo on 2016/12/28.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private LoginBean.DataBean current_user;
    private View view;
    private ImageView iv_user_head;
    private TextView tv_user_name;
    private TextView tv_guanzhu;
    private TextView tv_guanzhu_count;
    private AutoLinearLayout base_info;
    private AutoLinearLayout mine_card;
    private AutoLinearLayout suggest_back;
    private AutoLinearLayout setting;
    private RecyclerView mine_recyclerView;
    private View pop_camera;
    private TextView take_picture;
    private TextView photo_album;
    private PopupWindow popupWindow;
    private ImageView gender;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, null);
        //动态设置状态栏
        if (21 > android.os.Build.VERSION.SDK_INT) {
            view.setPadding(0, 0, 0, 0);
        }
        //初始化控件
        initView();
        autoView();
        return view;
    }

    private void autoView() {
    }

    /**
     * 初始化控件
     */
    private void initView() {
        iv_user_head = (ImageView) view.findViewById(R.id.iv_user_head);
        tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
        tv_guanzhu = (TextView) view.findViewById(R.id.tv_guanzhu);
        tv_guanzhu_count = (TextView) view.findViewById(R.id.tv_guanzhu_count);
        base_info = (AutoLinearLayout) view.findViewById(R.id.base_info);
        mine_card = (AutoLinearLayout) view.findViewById(R.id.mine_card);
        suggest_back = (AutoLinearLayout) view.findViewById(R.id.suggest_back);
        setting = (AutoLinearLayout) view.findViewById(R.id.setting);
        mine_recyclerView = (RecyclerView) view.findViewById(R.id.mine_recyclerView);
        gender = (ImageView) view.findViewById(R.id.gender);
        iv_user_head.setOnClickListener(this);

        tv_guanzhu.setOnClickListener(this);
        base_info.setOnClickListener(this);
        mine_card.setOnClickListener(this);
        suggest_back.setOnClickListener(this);
        setting.setOnClickListener(this);
        /**
         * 初始化界面
         */
        if (MyApplication.CURRENT_USER != null) {
            current_user = MyApplication.CURRENT_USER;
            Glide.with(getActivity()).load(current_user.getImg()).into(iv_user_head);
            tv_user_name.setText(current_user.getUserName());
            tv_guanzhu_count.setText(current_user.getFansCount() + "");
            if (current_user.getGender() == 1) {
                //设置男
                gender.setImageResource(R.drawable.profile_male);
            } else {
                //设置女
                gender.setImageResource(R.drawable.profile_female);
            }
        }


        EventBus.getDefault().register(this);

    }

    @Subscribe
    public void onEventMainThread(String data){
        tv_user_name.setText(current_user.getUserName());
        Toast.makeText(getActivity(), "---88"+data, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_head:
                initPopView();
                //创建PopupWindow,宽高为包裹内容
                popupWindow = new PopupWindow(pop_camera, LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                //获得焦点
                popupWindow.setFocusable(true);
                //设置外部可点击效果
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                //让PopupWindow显示在屏幕中央
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                //弹出是背景变暗
                backgroundAlpha(0.6f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        //消失后背景变浅
                        backgroundAlpha(1.0f);
                    }
                });
                break;
            case R.id.tv_guanzhu:

                break;
            case R.id.base_info:
                enterActivity(BasicDocumentActivity.class);
                break;
            case R.id.mine_card:
                break;
            case R.id.suggest_back:
                enterActivity(FeedBackActivity.class);
                break;
            case R.id.setting:
                enterActivity(SettingActivity.class);
                break;
        }
    }

    private synchronized void initPopView() {
        //创建PopupWindow布局
        if (pop_camera == null) {
            pop_camera = CommonUtils.inflate(R.layout.pop_camera_layout);
            AutoUtils.auto(pop_camera.findViewById(R.id.cardView1));
            take_picture = (TextView) pop_camera.findViewById(R.id.take_picture);
            photo_album = (TextView) pop_camera.findViewById(R.id.photo_album);
        }

        take_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BaseActivity) getActivity()).requestPermission(20, new String[]{Manifest.permission.CAMERA}, new Runnable() {
                    @Override
                    public void run() {
                        //相机意图
                        Intent intent = new Intent();
                        intent.setAction("android.media.action.IMAGE_CAPTURE");
                        intent.addCategory("android.intent.category.DEFAULT");
                        startActivityForResult(intent, 101);
                        popupWindow.dismiss();
                    }
                });
            }
        });

        photo_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //图库意图
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 100);
                popupWindow.dismiss();
            }
        });

    }

    /**
     * 背景变暗
     *
     * @param bgAlpha 变暗程度
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
    }


    /**
     * 相册回调
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100:
                    ContentResolver contentResolver = getActivity().getContentResolver();
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(data.getData()));
                        iv_user_head.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 101:
                    if (requestCode == 101) {    //拍照取图
                        Bundle bundle = data.getExtras();   //获取data数据集合
                        Bitmap bitmap = (Bitmap) bundle.get("data");        //获得data数据
                        iv_user_head.setImageBitmap(bitmap);
                        //上传服务器
                    }
                    break;
                default:
                    return;
            }

        }
    }


    /**
     * 跳转界面
     */
    private void enterActivity(Class c) {
        Intent intent = new Intent(getActivity(), c);
        getActivity().startActivity(intent);
    }

}
