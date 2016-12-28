package com.example.smalllemon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private ViewPager vp;
    int[] s=new int[]{R.mipmap.aa,R.mipmap.bb,R.mipmap.cc,R.mipmap.dd,R.mipmap.ee,R.mipmap.ff};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.main_vp);
        vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return s.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView image=new ImageView(MainActivity.this);
                image.setImageResource(s[position]);
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                image.setFitsSystemWindows(true);
                container.addView(image);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in=new Intent(MainActivity.this,LunchActivity.class);
                        startActivity(in);
                    }
                });
                return image;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }
}
