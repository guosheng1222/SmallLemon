package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smalllemon.R;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/12/28.
 */

public class CommunityFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager community_viewPager;
    String[] tabArray = new String[]{"版块", "精选", "全部"};
    private ArrayList<Fragment> fragmentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_community, null);
        if (21 > android.os.Build.VERSION.SDK_INT) {
            view.setPadding(0, 0, 0, 0);
        }
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        community_viewPager = (ViewPager) view.findViewById(R.id.community_viewPager);
        addFragment();
        setAdapter();

        return view;
    }

    /**
     * 适配器
     */
    private void setAdapter() {
        community_viewPager.setOffscreenPageLimit(2);
        community_viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabArray[position];
            }
        });
        community_viewPager.setCurrentItem(1);
    }

    /**
     * 添加fragment
     */
    private void addFragment() {
        //装Fragment的集合
        fragmentList = new ArrayList<>();
        Fragment comBoardFragment = new ComBoardFragment();
        Fragment comChoicenceFragment = new ComChoicenceFragment();
        Fragment comWholeFragment = new ComWholeFragment();
        fragmentList.add(comBoardFragment);
        fragmentList.add(comChoicenceFragment);
        fragmentList.add(comWholeFragment);
        tabLayout.setupWithViewPager(community_viewPager);
    }

}
