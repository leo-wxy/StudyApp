package wxy.frame.finalframe;

import android.app.Activity;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.view.View;

/**
 * Created by xixi on 16/6/21.
 * 进行基础参数的设置以及数据的初始化
 */

public class MyApplication extends MultiDexApplication {

    public static final String TAG = "MyApplication";
    private static MyApplication instance;//volatile 多线程并发处理
    private ActivityLifeCycleHelper mActivityLifeCycleHelper = null;

    /**
     * 单例模式
     *
     * @return
     */
    public static MyApplication getInstance() {

        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        instance = this;
        this.mActivityLifeCycleHelper = new ActivityLifeCycleHelper();
        registerActivityLifecycleCallbacks(mActivityLifeCycleHelper);
    }

    public ActivityLifeCycleHelper getActivityLifecycleHelper() {
        return mActivityLifeCycleHelper;
    }

    /**
     * 低内存时调用
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Runtime.getRuntime().gc();
    }

    /**
     * 程序结束时调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(mActivityLifeCycleHelper);
    }

    public void onSlideBack(boolean isReset, float distance) {
        if (mActivityLifeCycleHelper != null) {
            Activity lastActivity = mActivityLifeCycleHelper.getPreActivity();
            if (lastActivity != null) {
                View contentView = lastActivity.findViewById(android.R.id.content);
                if (isReset) {
                    contentView.setX(contentView.getLeft());
                } else {
                    final int width = getResources().getDisplayMetrics().widthPixels;
                    contentView.setX(-width / 3 + distance / 3);
                }
            }
        }
    }


}
