package com.qiniu.csvc.base;

import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.qiniu.csvc.utils.ToastHelper;


public class CCApplication extends MultiDexApplication {

    private static CCApplication INSTANCE;
    private boolean mIsInForeground = false;

    public static CCApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        Utils.init(this);
        ToastHelper.init(this);
    }
}