package com.wenliu.chocolabsexam.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.wenliu.chocolabsexam.Constants;
import com.wenliu.chocolabsexam.DramaApplication;

public class SearchRecord {
    private static SearchRecord instance = new SearchRecord();
    private SharedPreferences mSearchData = DramaApplication.getAppContext().getSharedPreferences(Constants.SEARCH_SHARED_PREFERENCES, Context.MODE_PRIVATE);

    private SearchRecord() {
    }

    public static SearchRecord getInstance() {
        return instance;
    }

    public String getSearch() {
        Log.d("wen-qwe", "getSearch: " + mSearchData.getString(Constants.SEARCH_RECORD, null));
        return mSearchData.getString(Constants.SEARCH_RECORD, null);
    }

    public void setSearch(String searchData) {
        mSearchData.edit()
                .putString(Constants.SEARCH_RECORD, searchData)
                .commit();
    }

    public void clearSearch() {
        mSearchData.edit().remove(Constants.SEARCH_RECORD);
    }

    public boolean haveSearchRecord() {
        Log.d("wen-123", "haveSearchRecord: ");
        return (getSearch() == null) ? false : true;
    }
}
