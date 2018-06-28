package com.wenliu.chocolabsexam.api.retrofit;

import com.wenliu.chocolabsexam.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChocolabsClient {

    private static ChocolabsClient instance = new ChocolabsClient();
    public GetRequestInterface mGetRequestInterface;

    public static ChocolabsClient getInstance(){
        return instance;
    }

    private ChocolabsClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.DRAMA_DATA_BASE_URL + Constants.DRAMA_DATA_VERSION_URL) // 设置 Url
                .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 解析
                .build();

        mGetRequestInterface = retrofit.create(GetRequestInterface.class);
    }
}
