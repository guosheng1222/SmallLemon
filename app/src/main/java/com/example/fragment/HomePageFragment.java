package com.example.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adapter.HomeCO2Adapter;
import com.example.app.MyApplication;
import com.example.base.BaseData;
import com.example.bean.BeanCO2;
import com.example.bean.BeanCold;
import com.example.bean.BeanHoliday;
import com.example.bean.HomeRadioStation;
import com.example.smalllemon.LoveActivity;
import com.example.smalllemon.NoteActivity;
import com.example.smalllemon.R;
import com.example.smalllemon.RadioStationActivity;
import com.example.smalllemon.WebViewActivity;
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
    private FragmentPagerAdapter fragmentPagerAdapter;
    private ImageView home_cold_image;
    private RecyclerView home_co2_recycle;
    private ArrayList<BeanCO2.DataBean> CO2List = new ArrayList<>();
    private HomeRadioStation homeRadioStation;
    private Button more;

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
        initCommunityVp();
        initColdImg();
        initCO2();
        return view;
    }

    /**
     * 初始化恋爱氧气
     */
    private void initCO2() {
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                Gson gson = new Gson();
                BeanCO2 beanCO2 = gson.fromJson(data, BeanCO2.class);
                CO2List = (ArrayList<BeanCO2.DataBean>) beanCO2.getData();
                home_co2_recycle.setLayoutManager(new LinearLayoutManager(getActivity()) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
                home_co2_recycle.setAdapter(new HomeCO2Adapter(CO2List, getActivity()));
            }

            @Override
            public void onErrorData(String data) {

            }
        }.getDataForGet(getActivity(), UrlUtils.lovingCO2);
    }

    /**
     * 初始化冷暖共知
     */
    private void initColdImg() {
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                Gson gson = new Gson();
                final BeanCold beanCold = gson.fromJson(data, BeanCold.class);
                Glide.with(getActivity()).load(beanCold.getData().get(0).getImg()).into(home_cold_image);
                home_cold_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in=new Intent(getActivity(), WebViewActivity.class);
                        in.putExtra("url",beanCold.getData().get(0).getUrl());
                        in.putExtra("praise",beanCold.getData().get(0).getClick());
                        startActivity(in);
                    }
                });
            }

            @Override
            public void onErrorData(String data) {

            }
        }.getDataForGet(getActivity(), UrlUtils.cold);
    }

    //初始化小点
    private void initCircle(int p) {
        home_community_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < home_community_dot_lin.getChildCount(); i++) {
                    ImageView image = (ImageView) home_community_dot_lin.getChildAt(i);
                    if (position == i) {
                        image.setImageResource(dotArray1[0]);
                    } else {
                        image.setImageResource(dotArray1[1]);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        home_community_dot_lin.removeAllViews();
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(getActivity());
            if (i == p) {
                imageView.setImageResource(dotArray1[0]);
            } else {
                imageView.setImageResource(dotArray1[1]);
            }
            dotList.add(imageView);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(40, 40);
            params.setMargins(10, 20, 10, 20);
            home_community_dot_lin.addView(imageView, params);
        }
    }

    /**
     * 初始化恋乎社区
     */
    private void initCommunityVp() {
        fragmentPagerAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment f1 = HomeCommFragment.getFragment(position);
                return f1;
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
        home_community_vp.setAdapter(fragmentPagerAdapter);
        home_community_vp.setCurrentItem(0);
        initCircle(0);
    }

    /**
     * 助攻节日
     */
    private void initHoliday() {
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                Gson gson = new Gson();
                BeanHoliday beanHoliday = gson.fromJson(data, BeanHoliday.class);
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

            @Override
            public void onErrorData(String data) {

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
        home_holiday_image = (ImageView) view.findViewById(R.id.home_holiday_image);
        home_holiday_name = (TextView) view.findViewById(R.id.home_holiday_name);
        home_holiday_date = (TextView) view.findViewById(R.id.home_holiday_date);
        home_holiday_time = (TextView) view.findViewById(R.id.home_holiday_time);
        home_community_vp = (ViewPager) view.findViewById(R.id.home_community_vp);
        home_community_dot_lin = (LinearLayout) view.findViewById(R.id.home_community_dot_lin);
        noteLogo = (ImageView) view.findViewById(R.id.imageView2);
        home_cold_image = (ImageView) view.findViewById(R.id.home_cold_image);
        home_co2_recycle = (RecyclerView) view.findViewById(R.id.home_CO2_recycle);
        more = (Button) view.findViewById(R.id.more);

        main_title_text.setText(MyApplication.CURRENT_USER.getEmotionStage() == 1 ? "恋爱期" : "单身期");
        noteLogo.setOnClickListener(this);
        main_title_text.setOnClickListener(this);
        more.setOnClickListener(this);
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

            @Override
            public void onErrorData(String data) {

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
                if (MyApplication.CURRENT_USER.getEmotionStage() == 1) {
                    //恋爱期
                    home_cb_left.setChecked(true);
                } else {
                    home_cb_right.setChecked(true);
                }
                pop = new PopupWindow(pop_view, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                pop.setBackgroundDrawable(new BitmapDrawable());
                pop.setOutsideTouchable(true);
                pop.setFocusable(true);
                backgroundAlpha(0.6f);
                pop.showAtLocation(view, Gravity.CENTER, 0, 0);
                pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        backgroundAlpha(1.0f);
                    }
                });
                home_cb_left.setOnClickListener(this);
                home_cb_right.setOnClickListener(this);
                home_popup_sure_tv.setOnClickListener(this);
                break;
            case R.id.home_popup_sure_tv:
                if (home_cb_left.isChecked()) {
                    MyApplication.CURRENT_USER.setEmotionStage(1);
                } else if (home_cb_right.isChecked()) {
                    MyApplication.CURRENT_USER.setEmotionStage(0);
                }
                main_title_text.setText(MyApplication.CURRENT_USER.getEmotionStage() == 1 ? "恋爱期" : "单身期");
                pop.dismiss();
                initCommunityVp();
                break;
            case R.id.home_cb_left:
                home_cb_right.setChecked(false);
                home_cb_left.setChecked(true);

                break;
            case R.id.home_cb_right:
                home_cb_left.setChecked(false);
                home_cb_right.setChecked(true);
                break;
            case R.id.more:
                enterActivity(LoveActivity.class);
                break;
        }
    }

    void enterActivity(Class c) {
        Intent intent = new Intent(getActivity(), c);
        getActivity().startActivity(intent);
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
