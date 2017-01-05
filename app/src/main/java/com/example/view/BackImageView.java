package com.example.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.smalllemon.R;

/**
 * Created by asus on 2016/12/29.
 */

public class BackImageView extends ImageView {


    public BackImageView(Context context) {
        super(context);
        use();
    }

    public BackImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        use();
    }

    public BackImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        use();
    }

    private void use() {
        this.setImageResource(R.mipmap.return_back);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) getContext()).finish();
            }
        });
    }
}
