package hu.zhuren.shuai.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

/**
 * Created by zhuren.hu on 12/7/15.
 * 内存工具
 */
public class MemeoryUtils {
    private static final String TAG = MemeoryUtils.class.getSimpleName();

    private static final String MEM_INFO_PATH = "/proc/meminfo";

    /**
     * 通过proc读取内存信息并打印
     * @return
     */
    public static String printMemInfo() {
        String info = FileUtils.getFileOutputString(MEM_INFO_PATH);
        Log.i(TAG, "_______  内存信息:   \n" + info);
        return info;
    }

    /**
     * 获得内存信息
     * @param context
     * @return
     */
    public static ActivityManager.MemoryInfo getMemoryInfo(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        return mi;
    }
}
