package com.wxy.ioc_api;

/**
 * Created by wxy on 2016/11/1.
 */

public enum Finder {
    Activity {
        @Override
        public void setContentView(Object activity, int id) {
            ((android.app.Activity) activity).setContentView(id);
        }
    };

    public abstract void setContentView(Object activity, int id);
}
