package com.yihua99.hotfixdemo.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by linoli on 2017/1/11.
 */

public class MyApplication extends TinkerApplication  {


    public MyApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL,
                "com.yihua99.hotfixdemo.app.MyApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader",
                false);
    }

}
