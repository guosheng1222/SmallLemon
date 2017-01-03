package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.base.BaseData;
import com.example.bean.HomeRadioStation;
import com.example.smalllemon.R;
import com.example.utils.UrlUtils;
import com.example.view.InfoView;
import com.example.view.RotateDownPageTransformer;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/12/28.
 */

public class HomePageFragment extends Fragment {

    private InfoView infoView;
    int[] dotArray = new int[]{R.mipmap.navpoint_selected2x, R.mipmap.navpoint_unselected2x};
    ArrayList<ImageView> dotList = new ArrayList<>();
    private LinearLayout main_dot_lin;
    private ViewPager home_community_vp;
    private LinearLayout home_community_dot_lin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView();
        //动态设置状态栏
        if (21 > android.os.Build.VERSION.SDK_INT) {
            view.setPadding(0, 0, 0, 0);
        }
        initInfoView(view);
        return view;
    }

    private View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_home_page, null);
        main_dot_lin = (LinearLayout) view.findViewById(R.id.main_dot_lin);
        home_community_vp = (ViewPager) view.findViewById(R.id.home_community_vp);
        home_community_dot_lin = (LinearLayout) view.findViewById(R.id.home_community_dot_lin);
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

    private void initInfoView(View view) {
        infoView = (InfoView) view.findViewById(R.id.home_vp);
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                HomeRadioStation homeRadioStation = new Gson().fromJson(data, HomeRadioStation.class);
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

    }
}
