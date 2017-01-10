package com.example.smalllemon;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.adapter.RecyclerAdapter;
import com.example.base.BaseData;
import com.example.bean.Job;
import com.example.utils.UrlUtils;
import com.google.gson.Gson;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

public class JobActivity extends AppCompatActivity {

    private TextView hide, title_name;
    private RecyclerView show_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_job);

        //显示完成文字并点击进行更改
        hide = (TextView) findViewById(R.id.hide);
        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText("选择职业");
        //提交
        hide.setVisibility(View.VISIBLE);
        hide.setText("取消");
        //解析数据
        inData();

        show_lv = (RecyclerView) findViewById(R.id.show_lv);
        show_lv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void inData() {
        new BaseData() {
            @Override
            public void onSuccessData(String data) {
                Gson gson = new Gson();
                final Job job = gson.fromJson(data, Job.class);
                List<Job.DataBean> dataBeen = job.getData();
                RecyclerAdapter<Job.DataBean> recyclerAdapter = new RecyclerAdapter<Job.DataBean>(JobActivity.this, dataBeen, R.layout.job_layout) {
                    @Override
                    public void convert(RecyclerHolder holder, Job.DataBean data, int position) {
                        final TextView job_title = holder.findView(R.id.job_title);
                        job_title.setText(data.getTitle());
                        final RecyclerView recycler = holder.findView(R.id.recycler);
                        recycler.setLayoutManager(new LinearLayoutManager(JobActivity.this));
                        List<Job.DataBean.ChildrenBean> children = data.getChildren();
                        recycler.setAdapter(new RecyclerAdapter<Job.DataBean.ChildrenBean>(JobActivity.this, children, R.layout.job_item_layout) {

                            @Override
                            public void convert(RecyclerHolder holder, Job.DataBean.ChildrenBean data, int position) {
                                TextView job_name = holder.findView(R.id.job_name);
                                AutoLinearLayout recycler_line = holder.findView(R.id.recycler_line);
                                job_name.setText(data.getShowName());
                                startAnimation(holder.getItemView());
                            }
                        });

                    }
                };
                show_lv.setAdapter(recyclerAdapter);
            }

            @Override
            public void onErrorData(String data) {

            }
        }.getDataForGet(JobActivity.this, UrlUtils.JobUrl, BaseData.NO_TIME);
    }

    public void startAnimation(View view) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 400, 300, 0);
        objectAnimator.setDuration(500);
        objectAnimator.start();
    }


}
