package com.example.smalllemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
    }
    private void jump(Class activity)
    {
        Intent in=new Intent(LunchActivity.this,activity);
        startActivity(in);
    }
}
