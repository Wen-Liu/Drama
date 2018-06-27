package com.wenliu.chocolabsexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.wenliu.chocolabsexam.api.DramaApiManager;
import com.wenliu.chocolabsexam.api.callback.GetDramaListCallback;
import com.wenliu.chocolabsexam.object.Drama;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = Constants.TAG_MAIN_ACTIVITY;
    private ArrayList<Drama> mDramas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DramaApiManager.getInstance().getDramaList(new GetDramaListCallback() {
            @Override
            public void OnSuccess(ArrayList<Drama> dramas) {
                mDramas = dramas;

                for (int i = 0; i < mDramas.size(); i++) {
                    Log.d(TAG, "OnSuccess: " + mDramas.get(i).getName());
                }
            }

            @Override
            public void OnFailure(String errorMessage) {
                Log.d(TAG, "OnFailure: " + errorMessage);
            }
        });
    }
}
