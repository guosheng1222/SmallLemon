package com.example.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.smalllemon.R;
import com.example.utils.CommonUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.io.FileNotFoundException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by lenovo on 2016/12/28.
 */

public class MineFragment extends Fragment implements View.OnClickListener {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_mine, null);

        //初始化控件
        initView();

        return view;
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
        iv_user_head.setOnClickListener(this);

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
                backgroundAlpha(100);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        //消失后背景变浅
                        backgroundAlpha(10);
                    }
                });

                break;
        }
    }

    private void initPopView() {
        //创建PopupWindow布局
        if (pop_camera == null) {
            pop_camera = CommonUtils.inflate(R.layout.pop_camera_layout);
            take_picture = (TextView) pop_camera.findViewById(R.id.take_picture);
            photo_album = (TextView) pop_camera.findViewById(R.id.photo_album);
        }

        take_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //相机意图
                Intent intent = new Intent();
                intent.setAction("android.media.action.IMAGE_CAPTURE");
                intent.addCategory("android.intent.category.DEFAULT");
                startActivityForResult(intent,101);
                popupWindow.dismiss();
            }
        });

        photo_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "跳了跳了: ");

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
                        Log.i("TAG", "拍照回传bitmap："+bitmap);
                        iv_user_head.setImageBitmap(bitmap);

                    }



                    break;

                default:
                    return;
            }

        }
    }
}
