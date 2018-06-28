package com.wenliu.chocolabsexam.base;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wenliu.chocolabsexam.Constants;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = Constants.TAG_BASE_ACTIVITY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setStatusBar();
    }

    private void setStatusBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT); //calculateStatusColor(Color.WHITE, (int) alphaValue)
        }
    }

    public int getStatusBarHeight() {

        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) { // resourceId > 0 means get resource id, = 0 means not get
            result = getResources().getDimensionPixelSize(resourceId);
            Log.d(Constants.TAG_BASE_ACTIVITY, "getStatusBarHeight: " + result);
        }
        return result;
    }

}
