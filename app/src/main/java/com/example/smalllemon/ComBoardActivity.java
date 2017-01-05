package com.example.smalllemon;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adapter.BoardRecyclerAdapter;
import com.example.base.BaseActivity;
import com.example.base.BaseData;
import com.example.bean.CommunityBean;
import com.example.utils.UrlUtils;
import com.example.view.CircleImageView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 板块多条目展示
 *
 * @author :  张鸿鹏
 * @date :   2017/1/5
 */

public class ComBoardActivity extends BaseActivity {

    private CircleImageView board_iv;
    private TextView board_title;
    private TextView board_detail;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private List<CommunityBean.DataBean> topList = new ArrayList<>();
    private List<CommunityBean.DataBean> contentList = new ArrayList<>();
    private BoardRecyclerAdapter boardRecyclerAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (topList.size() > 0 && contentList.size() > 0 && boardRecyclerAdapter == null) {
                //添加适配器
                boardRecyclerAdapter = new BoardRecyclerAdapter(topList, contentList, ComBoardActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(ComBoardActivity.this));
                recyclerView.setAdapter(boardRecyclerAdapter);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_board);
        initView();
        titleMessage();


    }

    /**
     * 请求网络数据
     */
    private void requestTopData(int topType) {
        final Gson gson = new Gson();
        //上方标题
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                CommunityBean communityBean = gson.fromJson(data, CommunityBean.class);
                List<CommunityBean.DataBean> data1 = communityBean.getData();
                if (data1 != null && data1.size() > 0) {
                    topList.clear();
                    topList.addAll(data1);
                }
                handler.sendEmptyMessage(0);
            }
        }.getDataForGet(this, UrlUtils.moonTop + topType, BaseData.NORMAL_TIME);
        //内容请求
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                CommunityBean communityBean = gson.fromJson(data, CommunityBean.class);

                List<CommunityBean.DataBean> data1 = communityBean.getData();

                if (data1 != null && data1.size() > 0) {
                    contentList.clear();
                    contentList.addAll(data1);
                }
                handler.sendEmptyMessage(0);

            }
        }.getDataForGet(this, UrlUtils.moonContent + topType, BaseData.NORMAL_TIME);
    }

    /**
     * 标题信息
     */
    private void titleMessage() {
        int tag = getIntent().getIntExtra("tag", 0);
        if (tag == 0) {
            Data(R.mipmap.forum_section_first_logo, "你的月亮我的心", "最走心的情感答疑电台");
            requestTopData(10);
        } else if (tag == 1) {
            Data(R.mipmap.forum_section_second_logo, "恋爱羞羞事", "春风十里睡你");
            requestTopData(11);
        } else if (tag == 2) {
            Data(R.mipmap.forum_section_third_logo, "约会必杀技", "约会套路一网打尽");
            requestTopData(12);
        } else if (tag == 3) {
            Data(R.mipmap.forum_section_four_logo, "主要看颜值", "不整容,颜值照样up");
            requestTopData(13);
        } else if (tag == 4) {
            Data(R.mipmap.forum_section_five_logo, "恋爱直播间", "八卦?狗血?全都有");
            requestTopData(14);
        }

    }

    /**
     * 标题控件添加数据
     */
    public void Data(int url, String title, String detail) {
        Glide.with(ComBoardActivity.this).load(url).into(board_iv);
        board_title.setText(title);
        board_detail.setText(detail);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.forum_RecyclerView);
        board_iv = (CircleImageView) findViewById(R.id.board_iv);
        board_title = (TextView) findViewById(R.id.board_title);
        board_detail = (TextView) findViewById(R.id.board_detail);
        findViewById(R.id.forum_back2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
