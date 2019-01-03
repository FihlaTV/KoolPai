package com.qiniu.csvc.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

import com.qiniu.csvc.R;
import com.qiniu.csvc.base.BaseActivity;
import com.qiniu.csvc.utils.ActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoEditActivity extends BaseActivity {

    @BindView(R.id.vv_video_edit_activity) VideoView vvVideoEditActivity;

    private String mMp4path;
    private int mPreviousOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        setContentView(R.layout.activity_video_edit);
        ButterKnife.bind(this);
        mPreviousOrientation = getIntent().getIntExtra(ActivityUtil.PREVIOUS_ORIENTATION, 1);
        mMp4path = getIntent().getStringExtra(ActivityUtil.MP4_PATH);
        vvVideoEditActivity.setVideoPath(mMp4path);
        vvVideoEditActivity.start();
        vvVideoEditActivity.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                vvVideoEditActivity.setVideoPath(mMp4path);
                vvVideoEditActivity.start();
            }
        });

    }
}
