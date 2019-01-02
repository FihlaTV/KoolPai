package com.qiniu.csvc.activity;

import android.os.Bundle;
import android.widget.Button;

import com.qiniu.csvc.R;
import com.qiniu.csvc.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_to_shot_video) Button mToShotVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_to_shot_video)
    public void toShortVideo() {
        toActivity(ShortVideoActivity.class);
    }
}
