package com.qiniu.csvc.utils;

import android.app.Activity;
import android.content.Intent;

import com.qiniu.csvc.activity.PlaybackActivity;
import com.qiniu.csvc.activity.VideoEditActivity;


public class ActivityUtil {

    public static final String MP4_PATH = "MP4_PATH";
    public static final String PREVIOUS_ORIENTATION = "PREVIOUS_ORIENTATION";

    public static boolean isFinishing(Activity activity) {
        return (activity == null || activity.isFinishing());
    }

    public static void startActivity(Activity activity, Class targetClass) {
        Intent intent = new Intent(activity, targetClass);
        activity.startActivity(intent);
    }

    public static void startVideoEditActivity(Activity activity, String mp4Path, int previousOrientation) {
        if (activity == null) { return; }
        Intent intent = new Intent(activity, VideoEditActivity.class);
        intent.putExtra(MP4_PATH, mp4Path);
        intent.putExtra(PREVIOUS_ORIENTATION, previousOrientation);
        activity.startActivity(intent);
    }

    public static void startPlaybackActivity(Activity activity, String mp4Path, int previousOrientation) {
        if (activity == null) { return; }
        Intent intent = new Intent(activity, PlaybackActivity.class);
        intent.putExtra(MP4_PATH, mp4Path);
        intent.putExtra(PREVIOUS_ORIENTATION, previousOrientation);
        activity.startActivity(intent);
    }

}
