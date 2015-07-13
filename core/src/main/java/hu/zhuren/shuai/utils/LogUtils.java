package hu.zhuren.shuai.utils;

import android.util.Log;

/**
 * Created by zhuren.hu on 12/7/15.
 * 日志工具类
 */
public class LogUtils {
    private static final String TAG = LogUtils.class.getSimpleName();
    private static final boolean enableLog = true;

    private LogUtils() {
    }

    public static void debug(String tag, String msg) {
        if (enableLog)
            Log.d(tag, msg);
    }

    public static void debug(String msg) {
        debug(TAG, msg);
    }

    public static void error(String tag, String msg) {
        if (enableLog)
            Log.e(tag, msg);
    }

    public static void error(String msg) {
        error(TAG, msg);
    }

    public static void verbose(String tag, String msg) {
        if (enableLog)
            Log.v(tag, msg);
    }

    public static void verbose(String msg) {
        verbose(TAG, msg);
    }

    public static void warning(String tag, String msg) {
        if (enableLog)
            Log.i(tag, msg);
    }

    public static void warning(String msg) {
        warning(TAG, msg);
    }

    public static void info(String tag, String msg) {
        if (enableLog)
            Log.i(tag, msg);
    }

    public static void info(String msg) {
        info(TAG, msg);
    }

    public static void writeFileLog(String msg) {
        //TODO 等待StorageUtils完成后修改
    }
}
