package com.qiniu.csvc.activity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.qiniu.csvc.R;
import com.qiniu.csvc.base.BaseActivity;
import com.qiniu.pili.droid.shortvideo.PLAudioEncodeSetting;
import com.qiniu.pili.droid.shortvideo.PLCameraSetting;
import com.qiniu.pili.droid.shortvideo.PLFaceBeautySetting;
import com.qiniu.pili.droid.shortvideo.PLMicrophoneSetting;
import com.qiniu.pili.droid.shortvideo.PLRecordSetting;
import com.qiniu.pili.droid.shortvideo.PLRecordStateListener;
import com.qiniu.pili.droid.shortvideo.PLShortVideoRecorder;
import com.qiniu.pili.droid.shortvideo.PLVideoEncodeSetting;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShortVideoActivity extends BaseActivity implements PLRecordStateListener {

    @BindView(R.id.glv_short_video_activity) GLSurfaceView mGLSurfaceView;
    private PLShortVideoRecorder mShortVideoRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_video);
        ButterKnife.bind(this);
        getPermission();
    }

    public void getPermission() {
        PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.MICROPHONE)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {
                        shouldRequest.again(true);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        initCamera();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {

                        }
                    }
                }).request();
    }

    public void initCamera() {
        mShortVideoRecorder = new PLShortVideoRecorder();
        mShortVideoRecorder.setRecordStateListener(this);
        // 摄像头采集选项
        PLCameraSetting cameraSetting = new PLCameraSetting();
        cameraSetting.setCameraId(PLCameraSetting.CAMERA_FACING_ID.CAMERA_FACING_FRONT);
        cameraSetting.setCameraPreviewSizeRatio(PLCameraSetting.CAMERA_PREVIEW_SIZE_RATIO.RATIO_4_3);
        cameraSetting.setCameraPreviewSizeLevel(PLCameraSetting.CAMERA_PREVIEW_SIZE_LEVEL.PREVIEW_SIZE_LEVEL_480P);
        // 麦克风采集选项
        PLMicrophoneSetting microphoneSetting = new PLMicrophoneSetting();
        // 视频编码选项
        PLVideoEncodeSetting videoEncodeSetting = new PLVideoEncodeSetting(this);
        videoEncodeSetting.setEncodingSizeLevel(PLVideoEncodeSetting.VIDEO_ENCODING_SIZE_LEVEL.VIDEO_ENCODING_SIZE_LEVEL_480P_1); // 480x480
        videoEncodeSetting.setEncodingBitrate(1000 * 1024); // 1000kbps
        videoEncodeSetting.setEncodingFps(25);
        videoEncodeSetting.setHWCodecEnabled(true); // true:硬编 false:软编
        // 音频编码选项
        PLAudioEncodeSetting audioEncodeSetting = new PLAudioEncodeSetting();
        audioEncodeSetting.setHWCodecEnabled(true); // true:硬编 false:软编
        // 美颜选项
        PLFaceBeautySetting faceBeautySetting = new PLFaceBeautySetting(1.0f, 0.5f, 0.5f);
        // 录制选项
        PLRecordSetting recordSetting = new PLRecordSetting();
        recordSetting.setMaxRecordDuration(10 * 1000); // 10s
        recordSetting.setVideoCacheDir("/sdcard");
        recordSetting.setVideoFilepath("/sdcard/record.mp4");
        mShortVideoRecorder.prepare(mGLSurfaceView, cameraSetting, microphoneSetting, videoEncodeSetting, audioEncodeSetting, faceBeautySetting, recordSetting);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mShortVideoRecorder!=null){
            mShortVideoRecorder.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mShortVideoRecorder!=null){
            mShortVideoRecorder.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mShortVideoRecorder!=null){
            mShortVideoRecorder.destroy();
        }
    }

    @Override
    public void onReady() {

    }

    @Override
    public void onError(int i) {

    }

    @Override
    public void onDurationTooShort() {

    }

    @Override
    public void onRecordStarted() {

    }

    @Override
    public void onSectionRecording(long l, long l1, int i) {

    }

    @Override
    public void onRecordStopped() {

    }

    @Override
    public void onSectionIncreased(long l, long l1, int i) {

    }

    @Override
    public void onSectionDecreased(long l, long l1, int i) {

    }

    @Override
    public void onRecordCompleted() {

    }
}
