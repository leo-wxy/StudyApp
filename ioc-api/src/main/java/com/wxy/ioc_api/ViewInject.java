package com.wxy.ioc_api;

import android.app.Activity;

import com.example.ProxyInfo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wxy on 2016/11/1.
 */

public class ViewInject {
    static final Map<Class<?>, InjectInterface<Object>> INJECTORS = new LinkedHashMap<>();
    public void bind(Activity activity) {
        InjectInterface<Object> injectInterface=findInjector(activity);
        injectInterface.inject(Finder.Activity, activity);
    }

    private InjectInterface<Object> findInjector(Object object) {
        Class class_=object.getClass();
        InjectInterface<Object> injectInterface=INJECTORS.get(class_);
        if (injectInterface==null) {
            try {
                Class injectClass=Class.forName(class_.getName()+"$$"+ ProxyInfo.PROXY);
                injectInterface= (InjectInterface<Object>) injectClass.newInstance();
                INJECTORS.put(class_, injectInterface);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return injectInterface;
    }
}
