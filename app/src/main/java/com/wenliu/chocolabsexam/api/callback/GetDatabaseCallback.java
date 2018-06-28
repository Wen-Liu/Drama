package com.wenliu.chocolabsexam.api.callback;

import com.wenliu.chocolabsexam.object.Drama;

import java.util.ArrayList;

public interface GetDatabaseCallback {

    void OnSuccess(ArrayList<Drama> dramas);

    void OnFailure(String errorMessage);
}
