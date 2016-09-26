package wxy.frame.finalframe;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.Iterator;
import java.util.Stack;

/**
 * Activity生命周期管理
 * Created by wxy on 2016/9/21.
 */

public class ActivityLifeCycleHelper implements Application.ActivityLifecycleCallbacks {

    public static Stack<Activity> activityStack;//activity堆栈
    public static ActivityLifeCycleHelper instance;

    public ActivityLifeCycleHelper() {
        activityStack = new Stack<>();
    }

    public static ActivityLifeCycleHelper getInstance() {
        if (instance == null) {
            synchronized (ActivityLifeCycleHelper.class) {
                if (instance == null) {
                    instance = new ActivityLifeCycleHelper();
                }
            }
        }
        return instance;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        removeActivity(activity);
    }

    /**
     * 往堆栈中添加activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null)
            activityStack = new Stack<>();
        activityStack.add(activity);
    }

    /**
     * 从堆栈中移除activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null)
            activityStack.remove(activity);
    }


    /**
     * 获取当前的activity
     *
     * @return
     */
    public Activity getCurrentActivity() {
        Activity activity = null;
        if (!activityStack.empty())
            activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 获取上层activity
     *
     * @return
     */
    public Activity getPreActivity() {
        int size = activityStack.size();
        if (size > 1)
            return activityStack.elementAt(size - 2);
        else
            return null;
    }

    /**
     * 结束当前的activity
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束选中的activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 关闭所有activity
     */
    public void finishAllActivity() {
        Iterator i = activityStack.iterator();
        while (i.hasNext()) {
            Activity activity = getCurrentActivity();
            if (activity == null) {
                return;
            }
            activity.finish();
        }
    }

    /**
     * 关闭所有 除了选中的activity
     *
     * @param activity
     */
    public void finishAllWithoutActivity(Activity activity) {
        if (activity != null)
            finishAllActivity();
        addActivity(activity);
    }

}
