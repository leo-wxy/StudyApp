package wxy.frame.finalframe.util;

/**
 * Created by xixi on 16/7/11.
 */

import android.util.Log;

import wxy.frame.finalframe.finals.AppConfig;

import static android.R.attr.type;

/**
 * 自定义的日志打印工具类 可以简易的进行日志的输出显示
 */
public class LogUtils {

    private final boolean showLog = AppConfig.SHOW_LOG;
    public static final int V = 1;
    public static final int D = 2;
    public static final int I = 3;
    public static final int W = 4;
    public static final int E = 5;

    public static void v(Object obj) {
        logs(1, null, obj);
    }

    private static void logs(int logType, String tagStr, Object obj) {
        String msg;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        int index = 4;
        String className = stackTrace[index].getFileName();
        String methodName = stackTrace[index].getMethodName();
        int lineNumber = stackTrace[index].getLineNumber();

        String tag = (tagStr == null ? className : tagStr);
        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ (").append(className).append(":").append(lineNumber).append(")#").append(methodName).append(" ] ");

        if (obj == null) {
            msg = "Log with null Object";
        } else {
            msg = obj.toString();
        }
        if (msg != null) {
            stringBuilder.append(msg);
        }

        String logStr = stringBuilder.toString();

        switch (type) {
            case V:
                Log.v(tag, logStr);
                break;
            case D:
                Log.d(tag, logStr);
                break;
            case I:
                Log.i(tag, logStr);
                break;
            case W:
                Log.w(tag, logStr);
                break;
            case E:
                Log.e(tag, logStr);
                break;
        }

    }


}
