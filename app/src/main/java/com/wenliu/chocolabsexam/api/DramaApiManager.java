package com.wenliu.chocolabsexam.api;

import android.util.Log;

import com.wenliu.chocolabsexam.Constants;
import com.wenliu.chocolabsexam.api.callback.GetDramaListCallback;
import com.wenliu.chocolabsexam.api.retrofit.ChocolabsClient;
import com.wenliu.chocolabsexam.object.Drama;
import com.wenliu.chocolabsexam.object.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DramaApiManager {
    private static final String TAG = Constants.TAG_DRAMA_API_MANAGER;
    private static DramaApiManager instance = new DramaApiManager();

    private DramaApiManager() {
    }

    public static DramaApiManager getInstance() {
        return instance;
    }

    
    public void getDramaList(final GetDramaListCallback callback) {
        Log.d(TAG, "getDramaList: ");

        Call<Result<ArrayList<Drama>>> call = ChocolabsClient.getInstance().mGetRequestInterface.getCall();

        call.enqueue(new Callback<Result<ArrayList<Drama>>>() {
            @Override
            public void onResponse(Call<Result<ArrayList<Drama>>> call, Response<Result<ArrayList<Drama>>> response) {
                callback.OnSuccess(new ArrayList<Drama>(response.body().getData()));
            }

            @Override
            public void onFailure(Call<Result<ArrayList<Drama>>> call, Throwable t) {
                callback.OnFailure(t.getLocalizedMessage());
            }
        });

    }
}
