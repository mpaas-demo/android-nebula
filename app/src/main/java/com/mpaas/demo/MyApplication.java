package com.mpaas.demo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alipay.mobile.framework.quinoxless.IInitCallback;
import com.alipay.mobile.framework.quinoxless.QuinoxlessFramework;

import java.lang.reflect.Method;


public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        QuinoxlessFramework.setup(this, new IInitCallback() {
            @Override
            public void onPostInit() {
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        QuinoxlessFramework.init();

        // 内部测试使用，开发者无需关注
        initTest();
    }

    // 内部测试使用，开发者无需关注
    private void initTest(){
        try {
            Class diagnoseServiceClass = Class.forName("com.mpaas.diagnose.DiagnoseService");
            Method method = diagnoseServiceClass.getMethod("createInstance", Context.class);
            method.invoke(null, this);
        } catch (Exception e) {
        }
    }
}
