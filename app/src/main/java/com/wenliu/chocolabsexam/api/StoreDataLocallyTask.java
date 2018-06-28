package com.wenliu.chocolabsexam.api;

import android.os.AsyncTask;
import android.util.Log;

import com.wenliu.chocolabsexam.Constants;
import com.wenliu.chocolabsexam.DramaApplication;
import com.wenliu.chocolabsexam.database.DramaDatabase;
import com.wenliu.chocolabsexam.database.DramaEntry;
import com.wenliu.chocolabsexam.object.Drama;

import java.util.ArrayList;

public class StoreDataLocallyTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = Constants.TAG_STORE_DATA_TASK;
    private ArrayList<Drama> mDramas;
    private DramaEntry mDramaEntry;

    public StoreDataLocallyTask(ArrayList<Drama> dramas) {
        mDramas = dramas;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(TAG, "doInBackground: ");

        for (Drama drama: mDramas){
            mDramaEntry = new DramaEntry(drama);
            DramaDatabase.getDatabase(DramaApplication.getAppContext()).getDramaDao().insertDrama(mDramaEntry);
        }

        return null;
    }
}
