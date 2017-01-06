package com.example.interfaces;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zhiyuan on 16/12/27.
 */
public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {
    private final LinearLayoutManager linearLayoutManager;
    private int newState;
    /**
     * 可见条目个数
     */
    private int visibleItemCount;
    /**
     * 是否正在加载
     */
    private boolean isLoading;

    private int preTotalItem;

    //获取当前滑动的状态
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        this.newState = newState;

    }

    public OnLoadMoreListener(LinearLayoutManager linearLayoutManager) {
        super();
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);


        visibleItemCount = recyclerView.getChildCount();
        //可以看见的第一个条目索引
        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        int totalItemCount = linearLayoutManager.getItemCount();
        if (totalItemCount!=preTotalItem) {
            //可以进行加载了
            isLoading=false;
            //上一次的总个数等于当前个数----
            preTotalItem=totalItemCount;
        }
        if (!isLoading) {
            if (lastVisibleItemPosition == totalItemCount - 1) {
                //加载更过
                isLoading = true;
                onloadMore();
            }
        }
    }
    public abstract void onloadMore();
}
