package com.wenliu.chocolabsexam.mainpage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.wenliu.chocolabsexam.Constants;
import com.wenliu.chocolabsexam.DramaApplication;
import com.wenliu.chocolabsexam.api.DramaApiManager;
import com.wenliu.chocolabsexam.api.GetLocalDataTask;
import com.wenliu.chocolabsexam.api.StoreDataLocallyTask;
import com.wenliu.chocolabsexam.api.callback.GetDatabaseCallback;
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
        checkInternet();
    }

    @Override
    public void checkInternet() {

        ConnectivityManager connectivityManager =
                (ConnectivityManager) DramaApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //如果未連線的話，networkInfo 會等於null
        if (networkInfo != null && networkInfo.isConnected()) {
            //當前網路狀態可用
            getDramasInternet();
            Log.e(TAG, "checkInternet: " + "使用網路");
        } else {
            //當前網路不可用
            getDramasLocal();
            Log.e(TAG, "checkInternet: " + "無網路連接");
        }
    }

    @Override
    public void getDramasInternet() {
        Log.d(TAG, "getDramas: ");
        DramaApiManager.getInstance().getDramaList(new GetDramaListCallback() {
            @Override
            public void OnSuccess(ArrayList<Drama> dramas) {
                Log.d(TAG, "OnSuccess: dramas.size() " + dramas.size());
                mDramas = dramas;
                mMainView.showDramas(mDramas);
                storeDramas(mDramas);
            }

            @Override
            public void OnFailure(String errorMessage) {
                Log.d(TAG, "OnFailure: " + errorMessage);
            }
        });
    }

    @Override
    public void getDramasLocal() {
        Log.d(TAG, "getDramasLocal: ");
        new GetLocalDataTask(new GetDatabaseCallback() {
            @Override
            public void OnSuccess(ArrayList<Drama> dramas) {
                mDramas = dramas;
                mMainView.showDramas(dramas);
            }

            @Override
            public void OnFailure(String errorMessage) {

            }
        }).execute();
    }

    private void storeDramas(ArrayList<Drama> dramas) {
        Log.d(TAG, "storeDramas: ");
        new StoreDataLocallyTask(dramas).execute();

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
