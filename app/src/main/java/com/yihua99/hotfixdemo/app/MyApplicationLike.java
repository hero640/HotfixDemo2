package com.yihua99.hotfixdemo.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

/**
 * Created by linoli on 2017/1/13.
 */

public class MyApplicationLike extends DefaultApplicationLike {


    static {
        System.loadLibrary("app");
    }


    private static final String TAG = "MyApplicationLike";

    private static Application sIntance;

    public static Application getApplicationInstance() {
        return sIntance;
    }

    public static Context getContext() {
        return getApplicationInstance().getBaseContext();
    }


    public MyApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent, Resources[] resources, ClassLoader[] classLoader, AssetManager[] assetManager) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent, resources, classLoader, assetManager);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        Bugly.init(getApplication(), "d979f75836", true);
        Log.d(TAG,"onCreate");
        sIntance = getApplication();

        loadModifySoLib();
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);
        Log.d(TAG,"onBaseContextAttached");
    }

    /**
     * 加载有修改的so
     */
    private void loadModifySoLib() {
        Log.e("MyApplicationLike","loadModifySoLib");
        Beta.loadArmLibrary(getContext(),"app");

        // 设置是开发设备
        Bugly.setIsDevelopmentDevice(getContext(),true);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
        Log.d(TAG,"registerActivityLifecycleCallback");
    }


}
