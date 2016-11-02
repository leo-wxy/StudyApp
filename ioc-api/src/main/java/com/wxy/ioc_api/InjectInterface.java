package com.wxy.ioc_api;

/**
 * Created by wxy on 2016/11/1.
 */

public interface InjectInterface<T> {
    void inject(Finder finder, T target);
}
