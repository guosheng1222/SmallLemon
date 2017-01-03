package com.example.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.adapter.RecyclerAdapter;
import com.example.base.BaseData;
import com.example.bean.CommunityBean;
import com.example.smalllemon.R;
import com.example.utils.CommonUtils;
import com.example.utils.UrlUtils;
import com.google.gson.Gson;
import com.melnykov.fab.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 精选帖子
 *
 * @author :   郗琛
 * @date :   2016/12/30
 */

public class ComChoicenceFragment extends Fragment {

    private RecyclerView content_rv;
    private SwipeRefreshLayout refresh_sr;
    private FloatingActionButton fab;
    private ArrayList<CommunityBean.DataBean> dataList = new ArrayList<>();
    private RecyclerAdapter<CommunityBean.DataBean> recyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_com_choicence, null);
        initView(view);
        initData();
        return view;
    }

    private void showFab(boolean isShow) {
        if (isShow) {
            fab.show();
        } else {
            fab.hide();
        }
    }


    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        refresh_sr = (SwipeRefreshLayout) view.findViewById(R.id.refresh_sr);
        content_rv = (RecyclerView) view.findViewById(R.id.content_rv);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        showFab(false);
        refresh_sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        SystemClock.sleep((long) ( 3000));
                        CommonUtils.runOnMainThread(new Runnable() {
                            @Override
                            public void run() {
                                refresh_sr.setRefreshing(false);
                            }
                        });
                    }
                }.start();
            }
        });
        content_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerAdapter = new RecyclerAdapter<CommunityBean.DataBean>(getActivity(), dataList, R.layout.community) {
            private SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日 HH:mm:ss");

            @Override
            public void convert(RecyclerHolder holder, final CommunityBean.DataBean data, int position) {
                //标题
                holder.setText(R.id.title_tv, data.getTitle());
                //内容
                TextView content = holder.findView(R.id.content_tv);
                if (TextUtils.isEmpty(data.getContent()))
                    content.setVisibility(View.GONE);
                else
                    content.setText(data.getContent());
                //图片
                List<CommunityBean.DataBean.ImgsBean> imgs = data.getImgs();
                ViewGroup imageGroup = holder.findView(R.id.view_group);
                if (imgs == null || imgs.size() == 0) {
                    imageGroup.setVisibility(View.GONE);
                } else {
                    imageGroup.setVisibility(View.VISIBLE);
                    for (int i = 0; i < 3; i++) {
                        ImageView childAt = (ImageView) imageGroup.getChildAt(i);
                        if (imgs.size() > (i + 1) && !TextUtils.isEmpty(imgs.get(i).getOriginalImg())) {
                            Glide.with(getActivity()).load(imgs.get(i).getOriginalImg()).into(childAt);
                        } else {
                            childAt.setVisibility(View.GONE);
                        }
                    }
                }
                //电台
                holder.setText(R.id.broadcasting_tv, data.getUserName());
                //时间
                String str = formatter.format(data.getCreateTime());
                holder.setText(R.id.timer_tv, str);
                //点击评论
                holder.findView(R.id.go_to_comment_iv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "data:" + data, Toast.LENGTH_SHORT).show();
                    }
                });
                //评论数
                holder.setText(R.id.comment_number_tv, data.getReplyTimes() + "");
            }

        };

        content_rv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                showFab(dy <= 0);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    int lastItemPosition = linearManager.findLastVisibleItemPosition();
                    if (lastItemPosition == dataList.size() - 1)
                        initData();
                }
            }
        });

        content_rv.setAdapter(recyclerAdapter);
    }

    private int currentPage = 0;

    /**
     * 初始化数据
     */
    private void initData() {
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                CommunityBean selectBean = new Gson().fromJson(data, CommunityBean.class);
                dataList.addAll(selectBean.getData());
                recyclerAdapter.notifyDataSetChanged();
            }
        }.getDataForGet(getActivity(), UrlUtils.SELECTED + (++currentPage));
    }
}
