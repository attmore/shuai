package hu.zhuren.shuai.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.telephony.TelephonyManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuren.hu on 12/7/15.
 * 网络工具类
 */
public class NetUtils {
    private static final String TAG = NetUtils.class.getSimpleName();

    //流量统计
    private static Map<String, Long> sReceivedBytes = new HashMap<String, Long>();
    private static Map<String, Long> sSendBytes = new HashMap<String, Long>();

    public static String getNetworkTypeName(Context context) {
        String result = "(No Network)";

        try {
            final ConnectivityManager cm = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm == null) {
                return result;
            }

            final NetworkInfo info = cm.getActiveNetworkInfo();
            if (info == null || !info.isConnectedOrConnecting()) {
                return result;
            }

            result = info.getTypeName();
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                result += info.getSubtypeName();
//                        + "(" + info.getExtraInfo() + ")";
            } else {
//                result += "(" + info.getExtraInfo() + ")";
            }
        } catch (Throwable ignored) {
        }

        return result;
    }

    /**
     * 获取当前网络类型
     *
     * @return 返回网络类型
     */
    public static NetworkType getNetworkType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null || !info.isConnectedOrConnecting()) {
            return NetworkType.NONE;
        }
        int type = info.getType();
        if (ConnectivityManager.TYPE_WIFI == type) {
            return NetworkType.WIFI;
        } else if (ConnectivityManager.TYPE_MOBILE == type) {
            return NetworkType.MOBILE;
        } else {
            return NetworkType.OTHER;
        }
    }

    /**
     * @param context
     * @return
     */
    public static String getOperator(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkOperator();
    }

    /**
     * 网络连接是否断开
     *
     * @param context Context
     * @return 是否断开s
     */
    public static boolean isNotConnected(Context context) {
        return !isConnected(context);
    }

    /**
     * 是否有网络连接
     *
     * @param context Context
     * @return 是否连接
     */
    public static boolean isConnected(Context context) {
        if (context == null) {
            return true;
        }
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    /**
     * 当前是否是WIFI网络
     *
     * @param context Context
     * @return 是否WIFI
     */
    public static boolean isWifi(Context context) {
        return NetworkType.WIFI.equals(getNetworkType(context));
    }

    /**
     * 当前是否移动网络
     *
     * @param context Context
     * @return 是否移动网络
     */
    public static boolean isMobile(Context context) {
        return NetworkType.MOBILE.equals(getNetworkType(context));
    }

    public enum NetworkType {
        WIFI, MOBILE, OTHER, NONE
    }


    /**
     * 开始流量统计
     *
     * @param context Context
     * @param tag     traffic tag
     * @return received bytes
     */
    public static long start(Context context, String tag) {
        final int uid = getUid(context);
        if (uid > 0) {
            long appRxValue = TrafficStats.getUidRxBytes(uid);
            long appTxValue = TrafficStats.getUidTxBytes(uid);
            sReceivedBytes.put(tag, appRxValue);
            sSendBytes.put(tag, appTxValue);
            //LogUtils.v(TAG, "start() rxValue=" + appRxValue / 1000 + " txValue=" + appTxValue / 1000 + " uid=" + uid);
            return appRxValue;
        }
        return 0;
    }

    /**
     * 计算当前流量
     *
     * @param context Context
     * @param tag     traffic tag
     * @return received bytes
     */
    public static long current(Context context, String tag) {
        Long appRxValue = sReceivedBytes.get(tag);
        Long appTxValue = sSendBytes.get(tag);
        if (appRxValue == null || appRxValue == null) {
//            if (DEBUG) {
//                LogUtils.w(TAG, "current() appRxValue or appTxValue is null.");
//            }
            return 0;
        }
        final int uid = getUid(context);
        long appRxValue2 = TrafficStats.getUidRxBytes(uid);
        long appTxValue2 = TrafficStats.getUidTxBytes(uid);
        long rxValue = appRxValue2 - appRxValue;
        long txValue = appTxValue2 - appTxValue;
//        if (DEBUG) {
//            LogUtils.v(TAG, "current() rxValue=" + rxValue / 1000 + " txValue=" + txValue / 1000 + " uid=" + uid);
//        }
        return rxValue;

    }

    /**
     * 统计TAG流量
     *
     * @param context Context
     * @param tag     traffic tag
     * @return received bytes
     */
    public static long stop(Context context, String tag) {
        Long appRxValue = sReceivedBytes.remove(tag);
        Long appTxValue = sSendBytes.remove(tag);
        if (appRxValue == null || appRxValue == null) {
//            if (DEBUG) {
//                LogUtils.w(TAG, "stop() appRxValue or appTxValue is null.");
//            }
            return 0;
        }
        final int uid = getUid(context);
        long appRxValue2 = TrafficStats.getUidRxBytes(uid);
        long appTxValue2 = TrafficStats.getUidTxBytes(uid);
        long rxValue = appRxValue2 - appRxValue;
        long txValue = appTxValue2 - appTxValue;
//        if (DEBUG) {
//            LogUtils.v(TAG, "stop() rxValue=" + rxValue / 1000 + " txValue=" + txValue / 1000 + " uid=" + uid);
//        }
        return rxValue;

    }

    public static int getUid(Context context) {
        try {
            final PackageManager pm = context.getPackageManager();
            final String pn = context.getPackageName();
            ApplicationInfo ai = pm.getApplicationInfo(pn, 0);
            return ai.uid;
        } catch (PackageManager.NameNotFoundException e) {
//            if (DEBUG) {
//                LogUtils.e(TAG, "getUid() ex=" + e);
//            }
            return -1;
        }
    }

}
