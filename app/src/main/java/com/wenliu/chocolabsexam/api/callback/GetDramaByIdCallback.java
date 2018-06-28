package com.wenliu.chocolabsexam.api.callback;

import com.wenliu.chocolabsexam.object.Drama;

public interface GetDramaByIdCallback {

    void OnSuccess(Drama drama);

    void OnFailure(String errorMessage);
}
