package com.qiniu.csvc.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.telephony.TelephonyManager;

import com.qiniu.csvc.base.CCApplication;

public class WebConncetedUtils {
    public static final int NETWORK_TYPE_WIFI = 1;
    public static final int NETWORK_TYPE_2G = 2;
    public static final int NETWORK_TYPE_3G = 3;
    public static final int NETWORK_TYPE_4G = 4;
    public static final int NETWORK_TYPE_5G = 5;
    public static final int NETWORK_TYPE_ETHERNET = 6;
    public static final int NETWORK_TYPE_OTHER = 10;

//    public static NoNetConnectDialog noNetConnectDialog;

    //是否连接WIFI
    public boolean isWifiConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) CCApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 检查当前网络是否可用
     *
     * @param
     * @return
     */

    public static boolean isNetConnect() {
        ConnectivityManager connectivity = (ConnectivityManager) CCApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    public static void isNetAvaliable(FragmentManager fm, boolean mHasSavedInstanceState) {
//        if (!isNetConnect()) {
//            if (noNetConnectDialog == null) {
//                noNetConnectDialog = new NoNetConnectDialog();
//            }
//            noNetConnectDialog.setSavedInstanceState(mHasSavedInstanceState);
//            noNetConnectDialog.tryShow(fm);
//        }
    }

    public static void isNetAvaliable(FragmentManager fm) {
//        if (!isNetConnect()) {
//            if (noNetConnectDialog == null) {
//                noNetConnectDialog = new NoNetConnectDialog();
//            }
//            noNetConnectDialog.tryShow(fm);
//        }
    }

    public static boolean isMobile(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    public static int getNetworkType(Context context) {
        int ret;
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                ret = NETWORK_TYPE_WIFI;
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String subtypeName = networkInfo.getSubtypeName();
                // TD-SCDMA   networkType is 17
                final int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        ret = NETWORK_TYPE_2G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        ret = NETWORK_TYPE_3G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        ret = NETWORK_TYPE_4G;
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
//                        if (!TextUtils.isEmpty(subtypeName)) {
//                            if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
//                                ret = NETWORK_TYPE_3G;
//                            } else {
//                                ret = NETWORK_TYPE_OTHER;
//                            }
//                        }
                        ret = NETWORK_TYPE_3G;
                        break;
                }
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {
                ret = NETWORK_TYPE_ETHERNET;
            } else {
                ret = NETWORK_TYPE_OTHER;
            }
        } else {
            ret = 0;
        }
        return ret;
    }
}

