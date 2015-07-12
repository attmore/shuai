package hu.zhuren.shuai.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by zhuren.hu on 12/7/15.
 * 屏幕相关的工具类
 */
public class DisplayUtils {
    private static final String TAG = DisplayUtils.class.getSimpleName();

    /**
     * 获取显示信息DisplayMetrics
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm;
    }

    /**
     * 获得屏幕密度
     *
     * @param context
     * @return
     */
    public static float getDisplayDensity(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        return dm.density;
    }

    /**
     * @param context
     * @return
     */
    public static int getDisplayDensityDpi(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        return dm.densityDpi;
    }

    /**
     * @param context
     * @return
     */
    public static int getDisplayWidthPixel(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        return dm.widthPixels;
    }


    /**
     * @param context
     * @return
     */
    public static int getDisplayHeightPixel(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        return dm.heightPixels;
    }

    /**
     * 打印 显示信息DisplayMetrics
     */
    public static DisplayMetrics printDisplayInfo(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        StringBuilder sb = new StringBuilder();
        sb.append("_______  显示信息:  ");
        sb.append("\ndensity         :").append(dm.density);
        sb.append("\ndensityDpi      :").append(dm.densityDpi);
        sb.append("\nheightPixels    :").append(dm.heightPixels);
        sb.append("\nwidthPixels     :").append(dm.widthPixels);
        sb.append("\nscaledDensity   :").append(dm.scaledDensity);
        sb.append("\nxdpi            :").append(dm.xdpi);
        sb.append("\nydpi            :").append(dm.ydpi);
        Log.i(TAG, sb.toString());
        return dm;
    }
}
