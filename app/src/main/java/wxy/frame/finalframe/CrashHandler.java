package wxy.frame.finalframe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;

import wxy.frame.finalframe.util.LogUtils;

/**
 * Created by wxy on 2016/10/12.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    public static final String TAG = "CrashHandler";
    public static final String EXCEPTION = "exception";
    public static final String VERSION_NAME = "version_name";
    public static final String VERSION_CODE = "version_code";
    private static CrashHandler instance;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    private CrashHandler() {

    }

    /**
     * 单例
     *
     * @return
     */
    public static CrashHandler getInstance() {
        if (instance == null) {
            synchronized (CrashHandler.class) {
                if (instance == null) {
                    instance = new CrashHandler();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        this.mContext = context;
        this.mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (mUncaughtExceptionHandler == null) return;
        if (!handleException(ex)) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mUncaughtExceptionHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                LogUtils.e("error : ", e);
            }
//            if (mUiMainClazz == null) return;
//            Intent intent = new Intent(mContext.getApplicationContext(), mUiMainClazz);
//            PendingIntent restartIntent = PendingIntent.getActivity(
//                    mContext.getApplicationContext(),
//                    0,
//                    intent,
//                    Intent.FLAG_ACTIVITY_NEW_TASK);
//            // 退出程序
//            AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
//            // 1秒后重启应用
//            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis()+1000, restartIntent);
            // TODO 调用自定义Application中的关闭所有Activity的方法
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    private boolean handleException(final Throwable ex) {
        if (ex == null) return false;
        if (mContext == null) return false;
        ex.printStackTrace();
        // 拼接异常堆栈信息
        StringBuilder exceptionSB = new StringBuilder();
        exceptionSB.append(
                "API Level: " +
                        android.os.Build.VERSION.SDK_INT +
                        "\n");
        exceptionSB.append(
                "Android: " +
                        android.os.Build.VERSION.RELEASE +
                        " (" +
                        android.os.Build.MODEL +
                        ")\n\n\n");
        exceptionSB.append("异常信息: \n");
        exceptionSB.append("Exception: " + ex.getMessage() + "\n\n\n");
        exceptionSB.append("堆栈信息: \n");
        StackTraceElement[] elements = ex.getStackTrace();
        for (int i = 0; i < elements.length; i++) {
            exceptionSB.append(elements[i].toString() + "\n");
        }
        LogUtils.e("exception" + exceptionSB);
        // TODO 处理异常信息
//        if (mUiCrashClazz != null)
//            // 1. 跳转到统一的Activity，显示异常堆栈信息
//            showExceptionInActivity(exceptionSB.toString());
        // 2. 使用对话框显示异常堆栈信息
//        showExceptionByDialog(exceptionSB.toString());
        // 3. 保存为本地文件，按照一定策略上报异常
//        uploadCrashException(ex);
        return true;
    }

    /**
     * 使用Dialog显示异常信息
     *
     * @param exceptionStr
     */
    private void showExceptionByDialog(final String exceptionStr) {
        if (!(mContext instanceof Activity)) return;
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                new AlertDialog.Builder(mContext)
                        .setTitle("提示")
                        .setMessage(exceptionStr)
                        .setCancelable(true)
                        .setNeutralButton("我知道了", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                                // 杀死线程，退出应用。
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        }).create().show();
                Looper.loop();
            }
        }.start();
    }
}
