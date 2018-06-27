package com.wenliu.chocolabsexam;

import android.app.Application;
import android.content.Context;

public class DramaApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getAppContext() {
        return mContext;
    }

}
