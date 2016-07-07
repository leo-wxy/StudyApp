package wxy.frame.finalframe;

import android.app.Application;

/**
 * Created by xixi on 16/6/21.
 * 进行基础参数的设置以及数据的初始化
 */

public class MyApplication extends Application {

    private volatile MyApplication instance;//volatile 多线程并发处理

    /**
     * 单例模式
     *
     * @return
     */
    public MyApplication getInstance() {
        if (instance == null) {
            synchronized (MyApplication.class) {
                if (instance == null) {
                    instance = new MyApplication();
                }
            }
        }
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
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
    }
}
