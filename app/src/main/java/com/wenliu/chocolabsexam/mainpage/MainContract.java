package com.wenliu.chocolabsexam.mainpage;

import com.wenliu.chocolabsexam.base.BasePresenter;
import com.wenliu.chocolabsexam.base.BaseView;
import com.wenliu.chocolabsexam.object.Drama;

import java.util.ArrayList;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void showDramas(ArrayList<Drama> dramas);

        void isShowLoading(boolean isShow);

        void transToDetail(Drama drama);
    }

    interface Presenter extends BasePresenter {

        void checkInternet();

        void getDramasInternet();

        void getDramasLocal();

        void transToDetail(Drama drama);

        ArrayList<Drama> searchData(String input);
    }
}
