package com.wenliu.chocolabsexam.api;

import com.wenliu.chocolabsexam.Constants;
import com.wenliu.chocolabsexam.object.Drama;
import com.wenliu.chocolabsexam.object.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRequestInterface {

    @GET(Constants.DRAMA_DATA_GET_URL)
    Call<Result<ArrayList<Drama>>> getCall();

}
