package com.example.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.app.MyApplication;
import com.example.base.BaseData;
import com.example.bean.BeanHoliday;
import com.example.bean.HomeRadioStation;
import com.example.smalllemon.NoteActivity;
import com.example.smalllemon.R;
import com.example.smalllemon.RadioStationActivity;
import com.example.utils.CommonUtils;
import com.example.utils.UrlUtils;
import com.example.view.InfoView;
import com.example.view.RotateDownPageTransformer;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/12/28.
 */

public class HomePageFragment extends Fragment implements View.OnClickListener {

    private InfoView infoView;
    int[] dotArray1 = new int[]{R.mipmap.recipes_select_true, R.mipmap.recipes_select_false};
    int[] dotArray = new int[]{R.mipmap.navpoint_selected2x, R.mipmap.navpoint_unselected2x};
    ArrayList<ImageView> dotList = new ArrayList<>();
    private LinearLayout main_dot_lin;
    private ViewPager home_community_vp;
    private LinearLayout home_community_dot_lin;
    private ImageView home_holiday_image;
    private TextView home_holiday_time;
    private TextView home_holiday_name;
    private TextView home_holiday_date;
    private ImageView noteLogo;
    private TextView main_title_text;
    private CheckBox home_cb_left;
    private CheckBox home_cb_right;
    private TextView home_popup_sure_tv;
    private PopupWindow pop;
    private HomeRadioStation homeRadioStation;
    private CardView home_holiday_cardview;
    private BeanHoliday beanHoliday;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView();
        //动态设置状态栏
        if (21 > android.os.Build.VERSION.SDK_INT) {
            view.setPadding(0, 0, 0, 0);
        }
        //开启五个任务
        initInfoView(view);
        initHoliday();
        //initCommunityVp();
        return view;
    }


    private void initCommunityVp() {
        home_community_vp.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment f1 = HomeCommFragment.getFragment(MyApplication.CURRENT_USER.getGender() == 1 ? "恋爱期" : "单身期", position);
                return f1;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }

    /**
     * 助攻节日
     */
    private void initHoliday() {
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                Gson gson = new Gson();
                beanHoliday = gson.fromJson(data, BeanHoliday.class);
                Glide.with(getActivity()).load(beanHoliday.getData().getImg()).into(home_holiday_image);
                home_holiday_name.setText(beanHoliday.getData().getName().substring(0, 2));
                home_holiday_date.setText(beanHoliday.getData().getName().substring(3));
                long festivalTime = beanHoliday.getData().getFestivalTime();
                long l = System.currentTimeMillis();
                long l1 = festivalTime - l;
                long l2 = l1 / 86400000L + 1;
                if (l2 == 1) {
                    home_holiday_time.setText("今");
                } else {
                    home_holiday_time.setText(l2 + "");
                }
            }
        }.getDataForGet(getActivity(), UrlUtils.holiday1);
    }

    /**
     * 初始化view
     *
     * @return
     */
    private View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_home_page, null);
        main_dot_lin = (LinearLayout) view.findViewById(R.id.main_dot_lin);
        main_title_text = (TextView) view.findViewById(R.id.main_title_text);
        home_holiday_cardview = (CardView) view.findViewById(R.id.home_holiday_cardview);
        home_holiday_image = (ImageView) view.findViewById(R.id.home_holiday_image);
        home_holiday_name = (TextView) view.findViewById(R.id.home_holiday_name);
        home_holiday_date = (TextView) view.findViewById(R.id.home_holiday_date);
        home_holiday_time = (TextView) view.findViewById(R.id.home_holiday_time);
        home_community_vp = (ViewPager) view.findViewById(R.id.home_community_vp);
        home_community_dot_lin = (LinearLayout) view.findViewById(R.id.home_community_dot_lin);
        noteLogo = (ImageView) view.findViewById(R.id.imageView2);
        main_title_text.setText(MyApplication.CURRENT_USER.getGender() == 1 ? "恋爱期" : "单身期");
        noteLogo.setOnClickListener(this);
        main_title_text.setOnClickListener(this);
        home_holiday_cardview.setOnClickListener(this);
        return view;
    }

    //初始化小点
    private void initDot(ArrayList<HomeRadioStation.DataBean> adlList, int p) {
        dotList.clear();
        main_dot_lin.removeAllViews();
        for (int i = 0; i < adlList.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            if (i == p) {
                imageView.setImageResource(dotArray[0]);
            } else {
                imageView.setImageResource(dotArray[1]);
            }
            dotList.add(imageView);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 20, 10, 20);
            main_dot_lin.addView(imageView, params);
        }
    }

    /**
     * 初始化轮播图
     *
     * @param view
     */
    private void initInfoView(View view) {
        infoView = (InfoView) view.findViewById(R.id.home_vp);
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                homeRadioStation = new Gson().fromJson(data, HomeRadioStation.class);
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < homeRadioStation.getData().size(); i++) {
                    String substring = homeRadioStation.getData().get(i).getImg().substring(0, homeRadioStation.getData().get(i).getImg().indexOf("#"));
                    strings.add(substring);
                }
                if (strings.size() == 0)
                    return;
                infoView.setInfoViewData(strings);
                infoView.setOffscreenPageLimit(2);
                infoView.setCurrentItem(1);
                infoView.setPageTransformer(false, new RotateDownPageTransformer());
                initDot((ArrayList<HomeRadioStation.DataBean>) homeRadioStation.getData(), infoView.getCurrentItem());
                infoView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        for (int i = 0; i < main_dot_lin.getChildCount(); i++) {
                            ImageView image = (ImageView) main_dot_lin.getChildAt(i);
                            if (position % dotList.size() == i) {
                                image.setImageResource(dotArray[0]);
                            } else {
                                image.setImageResource(dotArray[1]);
                            }
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });
            }
        }.getDataForGet(getActivity(), UrlUtils.main_viewager, BaseData.NO_TIME);

        /**
         * 点击ViewPager的每一个条目
         */
        infoView.setOnSingleItemListener(new InfoView.OnSingleItemListener() {
            @Override
            public void onSingleItemListener(int position) {

//                跳转电台的activity并传值
                Intent intent = new Intent(getActivity(), RadioStationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("radioInfo", homeRadioStation.getData().get(position));
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


    }

    /**
     * 点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageView2:
                Intent intent = new Intent(getActivity(), NoteActivity.class);
                startActivity(intent);
                break;
            case R.id.main_title_text:
                View pop_view = CommonUtils.inflate(R.layout.home_fragment_popupwindow);
                home_cb_left = (CheckBox) pop_view.findViewById(R.id.home_cb_left);
                home_cb_right = (CheckBox) pop_view.findViewById(R.id.home_cb_right);
                home_popup_sure_tv = (TextView) pop_view.findViewById(R.id.home_popup_sure_tv);
                if (MyApplication.CURRENT_USER.getGender() == 1) {
                    //恋爱期
                    home_cb_left.setChecked(true);
                } else {
                    home_cb_right.setChecked(true);
                }
                pop = new PopupWindow(pop_view, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                pop.setBackgroundDrawable(new BitmapDrawable());
                pop.setOutsideTouchable(true);
                pop.setFocusable(true);
                backgroundAlpha(100);
                pop.showAtLocation(view, Gravity.CENTER, 0, 0);
                pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        backgroundAlpha(10);
                    }
                });
                home_cb_left.setOnClickListener(this);
                home_cb_right.setOnClickListener(this);
                home_popup_sure_tv.setOnClickListener(this);
                break;
            case R.id.home_popup_sure_tv:
                if (home_cb_left.isChecked()) {
                    MyApplication.CURRENT_USER.setGender(1);
                } else if (home_cb_right.isChecked()) {
                    MyApplication.CURRENT_USER.setGender(0);
                }
                main_title_text.setText(MyApplication.CURRENT_USER.getGender() == 1 ? "恋爱期" : "单身期");
                pop.dismiss();
                break;
            case R.id.home_cb_left:
                home_cb_right.setChecked(false);
                home_cb_left.setChecked(true);
                break;
            case R.id.home_cb_right:
                home_cb_left.setChecked(false);
                home_cb_right.setChecked(true);
                break;
            case R.id.home_holiday_cardview:


                break;
        }
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
}
