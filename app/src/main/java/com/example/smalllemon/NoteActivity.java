package com.example.smalllemon;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.fragment.AnswerFragment;
import com.example.fragment.ConversationFragment;

public class NoteActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager note_viewPager;
    private TabLayout note_tabLayout;
    private TextView title_name;
    private ImageView return_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        initView();

        //设置TabLayout
        initTabLayout();

        //设置ViewPager
        initVpView();
    }

    private void initVpView() {

        note_viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String title;
            Fragment fragment = null;

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        fragment = new ConversationFragment();
                        break;

                    case 1:
                        fragment = new AnswerFragment();
                        break;

                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {

                switch (position) {
                    case 0:
                        title = "会话";
                        break;

                    case 1:
                        title = "回复";
                        break;
                }

                return title;
            }
        });


        note_tabLayout.setupWithViewPager(note_viewPager);

    }

    private void initTabLayout() {

        note_tabLayout.setTabMode(TabLayout.MODE_FIXED);
        note_tabLayout.addTab(note_tabLayout.newTab().setText("会话"));
        note_tabLayout.addTab(note_tabLayout.newTab().setText("回复"));


    }

    private void initView() {
        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText("消息");
        note_tabLayout = (TabLayout) findViewById(R.id.note_tabLayout);
        note_viewPager = (ViewPager) findViewById(R.id.note_viewPager);
        return_back = (ImageView) findViewById(R.id.return_back);
        return_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            //点击返回
            case R.id.return_back:
                finish();
                break;
        }

    }
}
