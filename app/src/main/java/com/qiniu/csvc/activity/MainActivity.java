package com.qiniu.csvc.activity;

import android.os.Bundle;
import android.widget.Button;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.qiniu.csvc.R;
import com.qiniu.csvc.base.BaseActivity;

import java.util.List;

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
        getPermission();
    }

    @OnClick(R.id.btn_to_shot_video)
    public void toShortVideo() {
        toActivity(ShortVideoActivity.class);
    }

    public void getPermission() {
        PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.MICROPHONE, PermissionConstants.STORAGE)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {
                        shouldRequest.again(true);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {

                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {

                        }
                    }
                }).request();
    }

}
