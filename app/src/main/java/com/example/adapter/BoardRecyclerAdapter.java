package com.example.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.base.BaseHolder;
import com.example.bean.CommunityBean;
import com.example.fragment.PhotoFragment;
import com.example.holder.BoardContentHolder;
import com.example.holder.BoardTopHolder;
import com.example.smalllemon.ComBoardActivity;
import com.example.smalllemon.R;

import java.text.SimpleDateFormat;
import java.util.List;

import static android.R.attr.data;

/**
 * @author : 张鸿鹏
 * @date : 2017/1/4.
 */

public class BoardRecyclerAdapter extends RecyclerView.Adapter<BaseHolder> {
    private List<CommunityBean.DataBean> contentList;
    private List<CommunityBean.DataBean> topList;
    private Context context;
    private static final int TOP = 0;
    private static final int NOMAL = 1;
    private static int lastPosition = -1;

    public BoardRecyclerAdapter(List<CommunityBean.DataBean> topList, List<CommunityBean.DataBean> contentList, Context context) {
        this.topList = topList;
        this.context = context;
        this.contentList = contentList;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseHolder baseHolder = null;
        View view = null;
        switch (viewType) {
            case TOP:
                view = LayoutInflater.from(context).inflate(R.layout.boardtop_recycle, parent, false);
                baseHolder = new BoardTopHolder(view);
                break;
            case NOMAL:
                view = LayoutInflater.from(context).inflate(R.layout.community, parent, false);
                baseHolder = new BoardContentHolder(view);
                break;
        }
        return baseHolder;
    }

    private SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日 HH:mm:ss");

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TOP:
                BoardTopHolder holder1 = (BoardTopHolder) holder;
                holder1.top_recycleView.setLayoutManager(new LinearLayoutManager(context));
                holder1.top_recycleView.setAdapter(new RecyclerAdapter<CommunityBean.DataBean>(context, topList, R.layout.layout_top_firstitem) {
                    @Override
                    public void convert(RecyclerHolder holder, CommunityBean.DataBean data, int position) {
                        holder.setText(R.id.tv_topTitle, data.getTitle());
                    }
                });
                break;
            case NOMAL:
                BoardContentHolder contentHolder = (BoardContentHolder) holder;
                View itemView = contentHolder.itemView;
                if (position > lastPosition) {
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(itemView, View.TRANSLATION_Y, 300, 200, 100, 0);
                    objectAnimator.setDuration(500);
                    objectAnimator.start();
                    lastPosition = position;
                }
                contentHolder.title_tv.setText(contentList.get(position - 1).getTitle());
                String content = contentList.get(position - 1).getContent();
                contentHolder.title_tv.setText(contentList.get(position - 1).getTitle());
                if (content.length() > 60) {
                    contentHolder.content_tv.setText(content.substring(0, 60) + "....");
                } else {
                    contentHolder.content_tv.setText(content);
                }
                //图片
                final List<CommunityBean.DataBean.ImgsBean> imgs = (List<CommunityBean.DataBean.ImgsBean>) contentList.get(position - 1).getImgs();


                if (imgs == null || imgs.size() == 0) {
                    contentHolder.view_group.setVisibility(View.GONE);
                } else {
                    contentHolder.view_group.setVisibility(View.VISIBLE);
                    for (int i = 0; i < 3; i++) {
                        ImageView childAt = (ImageView) contentHolder.view_group.getChildAt(i);
                        if (imgs.size() > i && !TextUtils.isEmpty(imgs.get(i).getOriginalImg())) {
                            childAt.setVisibility(View.VISIBLE);
                            final int finalI = i;
                            childAt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    DialogFragment photoViewInstance = PhotoFragment.getPhotoViewInstance(imgs.get(finalI).getOriginalImg());
                                    ComBoardActivity boardActivity = (ComBoardActivity) BoardRecyclerAdapter.this.context;
                                    photoViewInstance.show(boardActivity.getSupportFragmentManager(), "dialogFragment");
                                }
                            });
                            Glide.with(context).load(imgs.get(i).getOriginalImg()).into(childAt);
                        } else {
                            childAt.setVisibility(View.GONE);
                        }
                    }
                }
                //电台
                contentHolder.broadcasting_tv.setText(contentList.get(position - 1).getUserName());
                //时间
                String str = formatter.format(contentList.get(position - 1).getCreateTime());
                contentHolder.timer_tv.setText(str);
                //点击评论

                contentHolder.go_to_comment_iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "data:" + data, Toast.LENGTH_SHORT).show();
                    }
                });
                //评论数
                contentHolder.comment_number_tv.setText(contentList.get(position - 1).getReplyTimes() + "");
                break;
        }


    }


    @Override
    public int getItemCount() {
        return contentList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TOP) {
            return TOP;
        }
        return NOMAL;
    }


}
