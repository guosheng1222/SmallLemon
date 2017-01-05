package com.example.smalllemon;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.CheckBox;

public class DetailsActivity extends AppCompatActivity {

    private CheckBox detail_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_details);

        detail_image = (CheckBox) findViewById(R.id.detail_image);
        Drawable[] compoundDrawables = detail_image.getCompoundDrawables();
        Rect r = new Rect(0, 0, compoundDrawables[1].getMinimumWidth() * 2 / 5, compoundDrawables[1].getMinimumHeight() * 2 / 5);
        //定义一个Rect边界
        compoundDrawables[1].setBounds(r);
        //给每一个RadioButton设置图片大小
        detail_image.setCompoundDrawables(null, compoundDrawables[1], null, null);
    }
}