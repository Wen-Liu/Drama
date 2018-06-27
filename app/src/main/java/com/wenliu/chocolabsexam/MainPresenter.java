package com.wenliu.chocolabsexam;

import android.util.Log;

import com.wenliu.chocolabsexam.api.DramaApiManager;
import com.wenliu.chocolabsexam.api.callback.GetDramaListCallback;
import com.wenliu.chocolabsexam.object.Drama;

import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter {
    private static final String TAG = Constants.TAG_MAIN_PRESENTER;
    private MainContract.View mMainView;
    private ArrayList<Drama> mDramas = new ArrayList<>();


    public MainPresenter(MainContract.View mainView) {
        mMainView = mainView;
    }

    @Override
    public void start() {
        getDramas();
    }

    @Override
    public void getDramas() {
        Log.d(TAG, "getDramas: ");
        DramaApiManager.getInstance().getDramaList(new GetDramaListCallback() {
            @Override
            public void OnSuccess(ArrayList<Drama> dramas) {
                Log.d(TAG, "OnSuccess: dramas.size() " + dramas.size());
                mDramas = dramas;
                mMainView.showDramas(mDramas);
            }

            @Override
            public void OnFailure(String errorMessage) {
                Log.d(TAG, "OnFailure: " + errorMessage);
            }
        });
    }

    @Override
    public void transToDetail(Drama drama) {
        Log.d(TAG, "transToDetail: ");
        mMainView.transToDetail(drama);
    }

    @Override
    public ArrayList<Drama> searchData(String input) {
        Log.d(TAG, "searchData: " + input);

        ArrayList<Drama> newDramas = new ArrayList<>();
        for (Drama drama : mDramas) {

            if (drama.getName().contains(input)) {
                newDramas.add(drama);
            }
        }
        return newDramas;
    }
}
