package com.wenliu.chocolabsexam.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.wenliu.chocolabsexam.Constants;
import com.wenliu.chocolabsexam.DramaApplication;
import com.wenliu.chocolabsexam.DramaInfoActivity;
import com.wenliu.chocolabsexam.GridSpacingItemDecoration;
import com.wenliu.chocolabsexam.R;
import com.wenliu.chocolabsexam.base.BaseActivity;
import com.wenliu.chocolabsexam.database.SearchRecord;
import com.wenliu.chocolabsexam.object.Drama;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.View {
    //region "BindView"
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_drama_list)
    RecyclerView mRecyclerDramaList;
    @BindView(R.id.et_search_input)
    EditText mEtSearchInput;
    @BindView(R.id.btn_search)
    Button mBtnSearch;
    //endregion

    private static final String TAG = Constants.TAG_MAIN_ACTIVITY;
    private MainContract.Presenter mPresenter;
    private DramaAdapter mDramaAdapter;
    private GridLayoutManager mGridLayoutManager;
    private ArrayList<Drama> mDramas = new ArrayList<>();
    private ArrayList<Drama> mQueryDramas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Log.d(TAG, "onCreate: ");
        mToolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        setSupportActionBar(mToolbar);
        setRecyclerView();

        mPresenter = new MainPresenter(this);
        mPresenter.start();
    }

    private void setRecyclerView() {
        mDramaAdapter = new DramaAdapter(this, mPresenter, mDramas);
        mGridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerDramaList.addItemDecoration(new GridSpacingItemDecoration(2, DramaApplication.getAppContext().getResources().getDimensionPixelSize(R.dimen.space_drama_item), false));
        mRecyclerDramaList.setLayoutManager(mGridLayoutManager);
        mRecyclerDramaList.setAdapter(mDramaAdapter);
    }


    @Override
    public void showDramas(ArrayList<Drama> dramas) {
        mDramas = new ArrayList<>(dramas);
        Log.d(TAG, "showDramas: " + SearchRecord.getInstance().haveSearchRecord());

        if (SearchRecord.getInstance().haveSearchRecord()) {
            Log.d(TAG, "showDramas: " + SearchRecord.getInstance().getSearch());
            String searchSting = SearchRecord.getInstance().getSearch();
            mQueryDramas = new ArrayList<>(mPresenter.searchData(searchSting));
            mEtSearchInput.setText(searchSting);
            mDramaAdapter.updateData(mQueryDramas);
        } else {
            mDramaAdapter.updateData(dramas);
        }
    }

    @Override
    public void isShowLoading(boolean isShow) {

    }

    @Override
    public void transToDetail(Drama drama) {
        Intent intent = new Intent(MainActivity.this, DramaInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BUNDLE_DRAMA, drama);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
    }

    @OnClick(R.id.btn_search)
    public void onViewClicked() {
        String searchInput = mEtSearchInput.getText().toString().trim();

        if (searchInput.length() > 0) {
            mQueryDramas = new ArrayList<>(mPresenter.searchData(searchInput));
            mDramaAdapter.updateData(mQueryDramas);
            SearchRecord.getInstance().setSearch(searchInput);
        } else {
            mDramaAdapter.updateData(mDramas);
            SearchRecord.getInstance().clearSearch();
        }
    }
}
