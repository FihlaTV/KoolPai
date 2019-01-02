package com.qiniu.csvc.activity;

import android.os.Bundle;

import com.qiniu.csvc.R;
import com.qiniu.csvc.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welecome);
        toActivity(MainActivity.class);
    }
}
