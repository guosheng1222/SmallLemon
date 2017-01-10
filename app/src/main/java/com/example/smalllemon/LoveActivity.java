package com.example.smalllemon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.adapter.RecyclerAdapter;
import com.example.base.BaseActivity;
import com.example.base.BaseData;
import com.example.bean.LoveBean;
import com.example.utils.CommonUtils;
import com.example.utils.UrlUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

public class LoveActivity extends BaseActivity {
    private static final String TAG = "LoveActivity";
    private RecyclerView love_rv;
    private SwipeRefreshLayout love_sw;
    private RecyclerAdapter<LoveBean.DataBean> recyclerAdapter;
    private ArrayList<LoveBean.DataBean> dataList = new ArrayList<>();
    private ImageView love_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);

        initView();
        initData();
    }

    private void initView() {
        love_img = (ImageView) findViewById(R.id.love_img);
        love_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        love_sw = (SwipeRefreshLayout) findViewById(R.id.loce_swrefresh);
        //设置进度的颜色
        love_sw.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        love_sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        SystemClock.sleep((long) (3000));
                        CommonUtils.runOnMainThread(new Runnable() {
                            @Override
                            public void run() {
                                love_sw.setRefreshing(false);
                            }
                        });
                    }
                }.start();
            }
        });


        love_rv = (RecyclerView) findViewById(R.id.love_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        love_rv.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter<LoveBean.DataBean>(getApplication(), dataList, R.layout.love_recycler_item) {
            @Override
            public void convert(RecyclerHolder holder, LoveBean.DataBean data, final int position) {
                holder.setImageNet(R.id.love_recycler_img, dataList.get(position).getImg());
                //标题
                holder.setText(R.id.love_recycler_title, dataList.get(position).getTitle());
                //阅读
                holder.setText(R.id.love_recycler_reading, dataList.get(position).getClick() + "阅读");
                //评论
                holder.setText(R.id.love_recycler_comments, dataList.get(position).getReplyTimes() + "评论");
                //点赞
                holder.setText(R.id.love_recycler_praise, dataList.get(position).getStar() + "点赞");


                holder.findView(R.id.ripView).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                                intent.putExtra("url", dataList.get(position).getUrl());
                                intent.putExtra("comments",dataList.get(position).getReplyTimes());
                                intent.putExtra("praise",dataList.get(position).getStar());
                                startActivity(intent);
                            }
                        }.start();
                    }
                });
            }
        };


        love_rv.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
        love_rv.setAdapter(recyclerAdapter);
    }

    private int currentPosition = 0;

    /**
     * 初始化数据
     */
    private void initData() {
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                LoveBean loveBean = new Gson().fromJson(data, LoveBean.class);
                dataList.addAll(loveBean.getData());
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onErrorData(String data) {

            }
        }.getDataForGet(getActivity(), UrlUtils.lookMore + (++currentPosition));
    }
}
