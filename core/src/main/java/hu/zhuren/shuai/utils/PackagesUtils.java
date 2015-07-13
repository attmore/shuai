package hu.zhuren.shuai.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by zhuren.hu on 13/7/15.
 * 包管理工具
 */
public class PackagesUtils {
    private static final String TAG = PackagesUtils.class.getSimpleName();

    /**
     * App是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(final Context context, final String packageName) {
        try {
            final PackageManager pm = context.getPackageManager();
            final PackageInfo info = pm.getPackageInfo(packageName, 0);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     *
     * @param context
     * @param cls
     * @return
     */
    public static boolean isServiceRunning(Context context, Class<?> cls) {
        final ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(Integer.MAX_VALUE);
        final String className = cls.getName();
        for (ActivityManager.RunningServiceInfo service : services) {
            if (className.equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppRunning(final Context context, final String packageName) {
        final ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> apps = am.getRunningAppProcesses();
        if (apps == null || apps.isEmpty()) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo app : apps) {
            if (packageName.equals(app.processName)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param context
     * @return
     */
    public static PackageInfo getCurrentPackageInfo(final Context context) {
        final PackageManager pm = context.getPackageManager();
        try {
            return pm.getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    /**
     *
     * @param context
     * @param packageName
     * @return
     */
    public static PackageInfo getPackageInfo(final Context context, final String packageName) {
        final PackageManager pm = context.getPackageManager();
        try {
            return pm.getPackageInfo(packageName, PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

}
