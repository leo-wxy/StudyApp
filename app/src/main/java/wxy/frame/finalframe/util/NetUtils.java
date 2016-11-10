package wxy.frame.finalframe.util;

/**
 * Created by wxy on 2016/11/7.
 */

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 获取网络相关的类
 */
public class NetUtils {
    /**
     * 打开网络设置界面
     */
    public static void openNetSetting(Context context) {
        if (android.os.Build.VERSION.SDK_INT > 10) {
            context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
        } else {
            context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }
    }

    /**
     * 获取活动网络信息
     *
     * @param context 上下文
     * @return NetworkInfo
     */
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetAvailable(Context context) {
        NetworkInfo networkInfo = getActiveNetworkInfo(context);
        return networkInfo.isAvailable() && networkInfo != null;
    }

    /**
     * 网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isNetConnected(Context context) {
        NetworkInfo networkInfo = getActiveNetworkInfo(context);
        return networkInfo.isConnected() && networkInfo != null;
    }


}
