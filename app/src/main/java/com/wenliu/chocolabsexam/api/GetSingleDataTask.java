package com.wenliu.chocolabsexam.api;

import android.os.AsyncTask;

import com.wenliu.chocolabsexam.DramaApplication;
import com.wenliu.chocolabsexam.api.callback.GetDramaByIdCallback;
import com.wenliu.chocolabsexam.database.DramaDatabase;
import com.wenliu.chocolabsexam.database.DramaEntry;
import com.wenliu.chocolabsexam.object.Drama;

public class GetSingleDataTask extends AsyncTask<Void, Void, Void> {

    private String mDramaId;
    private DramaEntry mDramaEntry;
    private GetDramaByIdCallback mCallback;

    public GetSingleDataTask(String dramaId, GetDramaByIdCallback callback) {
        mDramaId = dramaId;
        mCallback = callback;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mDramaEntry = DramaDatabase.getDatabase(DramaApplication.getAppContext()).getDramaDao().getDramaById(mDramaId);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if (mDramaEntry != null) {
            mCallback.OnSuccess(new Drama(mDramaEntry));
        } else {
            mCallback.OnFailure("Drama id = " + mDramaId + " is null");
        }
    }
}
