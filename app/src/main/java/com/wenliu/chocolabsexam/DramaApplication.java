package com.wenliu.chocolabsexam;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

public class DramaApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        Stetho.initializeWithDefaults(this);
    }

    public static Context getAppContext() {
        return mContext;
    }

}
