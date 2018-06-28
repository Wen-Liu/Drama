package com.wenliu.chocolabsexam.api;

import android.os.AsyncTask;
import android.util.Log;

import com.wenliu.chocolabsexam.Constants;
import com.wenliu.chocolabsexam.DramaApplication;
import com.wenliu.chocolabsexam.api.callback.GetDatabaseCallback;
import com.wenliu.chocolabsexam.database.DramaDatabase;
import com.wenliu.chocolabsexam.database.DramaEntry;
import com.wenliu.chocolabsexam.object.Drama;

import java.util.ArrayList;
import java.util.List;

public class GetLocalDataTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = Constants.TAG_GET_LOCAL_DATA_TASK;
    private ArrayList<Drama> mDramas = new ArrayList<>();
    private List<DramaEntry> mDramaEntries;
    private GetDatabaseCallback mCallback;

    public GetLocalDataTask(GetDatabaseCallback callback) {
        mCallback = callback;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Log.d(TAG, "doInBackground: ");

        mDramaEntries = DramaDatabase.getDatabase(DramaApplication.getAppContext()).getDramaDao().getAll();

        for (DramaEntry dramaEntry : mDramaEntries) {
            mDramas.add(new Drama(dramaEntry));
            Log.d(TAG, "doInBackground: " + mDramas.get(mDramas.size()-1).getName());
        }

        mCallback.OnSuccess(mDramas);
        return null;
    }
}
